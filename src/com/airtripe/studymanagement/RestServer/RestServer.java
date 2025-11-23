package com.airtripe.studymanagement.RestServer;


import com.airtripe.studymanagement.entity.*;
import com.airtripe.studymanagement.service.*;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RestServer {

    public static void main(String[] args) throws Exception {

        StudentService studentService = new StudentService();
        CourseService courseService   = new CourseService();
        EnrollmentService enrollService = new EnrollmentService(studentService, courseService);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // ---- STUDENT CRUD ----
        server.createContext("/students", exchange -> {
            String method = exchange.getRequestMethod();

            // CREATE STUDENT (POST)
            if (method.equals("POST")) {
                String body = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                        .lines().collect(Collectors.joining("\n"));

                // very simple parsing: id:name
                String[] parts = body.split(":");
                String id = parts[0];
                String name = parts[1];

                studentService.createStudent(new Student(name, id));

                send(exchange, 200, "Student created: " + id);
            }

            // GET ALL STUDENTS (GET)
            else if (method.equals("GET")) {
                String response = studentService
                        .listAllStudents()
                        .stream()
                        .map(s -> s.getId() + " -> " + s.getName())
                        .collect(Collectors.joining("\n"));

                send(exchange, 200, response);
            }
        });

        // ---- GET STUDENT BY ID ----
        server.createContext("/student", exchange -> {
            if (!exchange.getRequestMethod().equals("GET")) {
                send(exchange, 405, "Method Not Allowed");
                return;
            }

            String query = exchange.getRequestURI().getQuery(); // id=S1
            String id = query.split("=")[1];

            try {
                Student s = studentService.getStudentById(id);
                send(exchange, 200, s.getId() + " -> " + s.getName());
            } catch (Exception e) {
                send(exchange, 404, e.getMessage());
            }
        });


        // ---- COURSE CRUD ----
        server.createContext("/courses", exchange -> {
            String method = exchange.getRequestMethod();

            // CREATE COURSE - POST  Body:  C1:Maths:Raju
            if (method.equals("POST")) {

                String body = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                        .lines().collect(Collectors.joining("\n"));

                String[] p = body.split(":");
                String id = p[0];
                String name = p[1];
                String instructor = p[2];

                courseService.addCourse(new Course(id, name, instructor));

                send(exchange, 200, "Course added: " + id);
            }

            // LIST ALL COURSES
            else if (method.equals("GET")) {
                String response = courseService
                        .listAllCourses()
                        .stream()
                        .map(c -> c.getId() + " -> " + c.getName())
                        .collect(Collectors.joining("\n"));

                send(exchange, 200, response);
            }
        });


        // ---- ENROLLMENT CRUD ----
        server.createContext("/enroll", exchange -> {
            String method = exchange.getRequestMethod();

            // POST BODY: E1:S1:C1
            if (method.equals("POST")) {

                String body = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
                        .lines().collect(Collectors.joining("\n"));

                String[] p = body.split(":");
                String eid = p[0];
                String sid = p[1];
                String cid = p[2];

                try {
                    enrollService.createEnrollment(eid, sid, cid);
                    send(exchange, 200, "Enrolled student " + sid + " to " + cid);
                } catch (Exception e) {
                    send(exchange, 400, e.getMessage());
                }
            }

            else send(exchange, 405, "Only POST supported");
        });

        server.start();
        System.out.println("REST server running at http://localhost:8080/");
    }


    // Utility method to send HTTP responses
    private static void send(HttpExchange exchange, int status, String response) throws IOException {
        exchange.sendResponseHeaders(status, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

