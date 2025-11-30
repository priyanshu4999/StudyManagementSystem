package com.airtripe.studymanagement.main;

import com.airtripe.studymanagement.DatabasePersistence.MongoDBManager;
import com.airtripe.studymanagement.entity.*;
import com.airtripe.studymanagement.metrics.RequestMetrics;
import com.airtripe.studymanagement.service.*;

import com.mongodb.client.MongoDatabase;
import com.sun.net.httpserver.*;

import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RestServer {

    public static void main(String[] args) throws Exception {

        // --------------------------------------------------------------------
        // MONGO DB INIT
        // --------------------------------------------------------------------
        MongoDBManager.getInstance().init(
                "mongodb+srv://priyanshujain4999_db_user:Studentmanagementprojectpriyanshujain4999@studentmanagementsystem.z7lzwlc.mongodb.net/student_management?retryWrites=true&w=majority&authSource=admin&appName=StudentManagementSystem",
                "student_management"
        );


        MongoDatabase db = MongoDBManager.getInstance().getDB();

        // --------------------------------------------------------------------
        // SERVICE LAYER
        // --------------------------------------------------------------------
        StudentService studentService = new StudentService();
        CourseService courseService   = new CourseService();
        EnrollmentService enrollService = new EnrollmentService(studentService, courseService);

        // --------------------------------------------------------------------
        // HTTP SERVER
        // --------------------------------------------------------------------
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);


        // ====================================================================
        // STUDENTS
        // ====================================================================
        server.createContext("/students", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String method = exchange.getRequestMethod();

            // ------------------------------
            // CREATE STUDENT
            // ------------------------------
            if (method.equals("POST")) {

                JSONObject json = readJSON(exchange);
                String id = json.getString("id");
                String name = json.getString("name");

                studentService.createStudent(new Student(name, id));

                RequestMetrics.totalRequests++;
                RequestMetrics.studentsCreated++;

                send(exchange, 200, "Student created: " + id);
            }

            // ------------------------------
            // LIST ALL STUDENTS
            // ------------------------------
            else if (method.equals("GET")) {

                StringBuilder out = new StringBuilder();

                for (Student s : studentService.listAllStudents()) {
                    out.append(s.getId()).append(" -> ").append(s.getName()).append("\n");
                }

                RequestMetrics.totalRequests++;
                send(exchange, 200, out.toString());
            }

            else send(exchange, 405, "Method Not Allowed");
        });


        // ====================================================================
        // STUDENT BY ID  (GET, PUT, DELETE)
        // ====================================================================
        server.createContext("/student", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String method = exchange.getRequestMethod();
            String query = exchange.getRequestURI().getQuery(); // id=S1
            if (query == null || !query.contains("=")) {
                send(exchange, 400, "Missing id parameter");
                return;
            }
            String id = query.split("=")[1];

            // ------------------------------
            // GET ONE STUDENT
            // ------------------------------
            if (method.equals("GET")) {
                try {
                    Student s = studentService.getStudentById(id);
                    send(exchange, 200, s.getId() + " -> " + s.getName());
                } catch (Exception e) {
                    send(exchange, 404, e.getMessage());
                }
                RequestMetrics.totalRequests++;
            }

            // ------------------------------
            // UPDATE STUDENT
            // ------------------------------
            else if (method.equals("PUT")) {

                JSONObject json = readJSON(exchange);
                String name = json.getString("name");

                try {
                    studentService.updateStudent(id, new Student(name, id));
                    send(exchange, 200, "Student updated: " + id);
                } catch (Exception e) {
                    send(exchange, 404, e.getMessage());
                }
            }

            // ------------------------------
            // DELETE STUDENT
            // ------------------------------
            else if (method.equals("DELETE")) {
                try {
                    studentService.deleteStudent(id);
                    send(exchange, 200, "Student deleted: " + id);
                } catch (Exception e) {
                    send(exchange, 404, e.getMessage());
                }
            }

            else send(exchange, 405, "Method Not Allowed");
        });



        // ====================================================================
        // COURSES (POST, GET ALL)
        // ====================================================================
        server.createContext("/courses", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String method = exchange.getRequestMethod();

            // ------------------------------
            // CREATE COURSE
            // ------------------------------
            if (method.equals("POST")) {

                JSONObject json = readJSON(exchange);
                String id = json.getString("id");
                String name = json.getString("name");
                String instructor = json.getString("instructor");

                courseService.addCourse(new Course(id, name, instructor));
                RequestMetrics.totalRequests++;
                RequestMetrics.coursesCreated++;

                send(exchange, 200, "Course added: " + id);
            }

            // ------------------------------
            // LIST COURSES
            // ------------------------------
            else if (method.equals("GET")) {

                StringBuilder out = new StringBuilder();
                for (Course c : courseService.listAllCourses()) {
                    out.append(c.getId()).append(" -> ")
                            .append(c.getName()).append("\n");
                }

                RequestMetrics.totalRequests++;
                send(exchange, 200, out.toString());
            }

            else send(exchange, 405, "Method Not Allowed");
        });



        // ====================================================================
        // ENROLLMENT (POST)
        // ====================================================================
        server.createContext("/enroll", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!exchange.getRequestMethod().equals("POST")) {
                send(exchange, 405, "Only POST supported");
                return;
            }

            JSONObject json = readJSON(exchange);
            String eid = json.getString("id");
            String sid = json.getString("studentId");
            String cid = json.getString("courseId");

            try {
                enrollService.createEnrollment(eid, sid, cid);
                RequestMetrics.totalRequests++;
                RequestMetrics.enrollmentsCreated++;

                send(exchange, 200, "Enrolled student " + sid + " to " + cid);
            }
            catch (Exception e) {
                send(exchange, 400, e.getMessage());
            }
        });



        // ====================================================================
        // LIST ALL ENROLLMENTS
        // ====================================================================
        server.createContext("/enrollments", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!exchange.getRequestMethod().equals("GET")) {
                send(exchange, 405, "Method Not Allowed");
                return;
            }

            StringBuilder out = new StringBuilder();

            for (Enrollment e : enrollService.listAllEnrollments()) {
                out.append(e.getId())
                        .append(" -> student ")
                        .append(e.getStudentId())
                        .append(", course ")
                        .append(e.getCourseId())
                        .append(", grade ")
                        .append(e.getGrade())
                        .append("\n");
            }

            RequestMetrics.totalRequests++;
            send(exchange, 200, out.toString());
        });



        // ====================================================================
        // METRICS
        // ====================================================================
        server.createContext("/metrics", exchange -> {
            addCORS(exchange);

            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            RequestMetrics.totalRequests++;

            String response =
                    "app_requests_total " + RequestMetrics.totalRequests + "\n" +
                            "app_students_created_total " + RequestMetrics.studentsCreated + "\n" +
                            "app_courses_created_total " + RequestMetrics.coursesCreated + "\n" +
                            "app_enrollments_created_total " + RequestMetrics.enrollmentsCreated + "\n";

            send(exchange, 200, response);
        });


        // ====================================================================
        // START SERVER
        // ====================================================================
        server.start();
        System.out.println("REST server running at http://localhost:8080/");
    }



    // ========================================================================
    // HELPER METHODS
    // ========================================================================

    private static JSONObject readJSON(HttpExchange exchange) throws IOException {
        String body = new BufferedReader(
                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining());

        return new JSONObject(body);
    }

    private static void addCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
    }

    private static void send(HttpExchange exchange, int status, String response) throws IOException {
        exchange.sendResponseHeaders(status, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
