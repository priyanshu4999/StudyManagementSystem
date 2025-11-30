
---

# 📄 **Setup_Instructions.md**

````md
# 📘 Setup Instructions for Student Management System (Java)

This document explains how to set up the Java development environment, configure the system, verify installation, and run the project for the first time.  
It fulfills the **Environment Setup (5 points)** requirement of the assignment.

---

# 1️⃣ Install JDK (Java Development Kit)

### ✔ Recommended Version
Use **JDK 17 or later**  
(Stable LTS release, widely supported, works perfectly for console + OOP + HTTP server demos)

---

# 2️⃣ Download & Install JDK

## **Windows**
1. Download from:  
   https://adoptium.net  
2. Choose:  
   - **Temurin 17**  
   - OS: Windows  
   - Architecture: x64  
3. Run the installer  
4. Make sure “Add to PATH” is selected  

---


````


After installation:

```bash
sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk \
/Library/Java/JavaVirtualMachines/openjdk-17.jdk
```

---

# 3️⃣ Verify Installation

Run:

```bash
java -version
javac -version
```

Expected output:

```
java version "17.x.x"
javac 17.x.x
```

If this appears, the JDK is installed successfully.

---

# 4️⃣ Configure Environment Variables

## ✔ **Windows (Important)**

Go to:

```
Control Panel → System → Advanced System Settings → Environment Variables
```

Add:

```
JAVA_HOME = C:\Program Files\Java\jdk-17
PATH += %JAVA_HOME%\bin
```

Restart terminal and re-run:

```bash
java -version
```

---

# 5️⃣ Folder Structure Setup

Create the following structure (already present in your project):

```
src/
└── com/
    └── airtripe/
        └── studymanagement/
            ├── demos/
            ├── entity/
            ├── exception/
            ├── main/
            ├── service/
            └── util/
```
![Java Installation Verification](images/DirectoryStructure.png)
This ensures proper use of:

* Packages
* Imports
* Access modifiers
* FQCN usage

---

# 6️⃣ Compile & Run a Java Program

Inside your project root:

```bash
cd src
javac com/airtripe/studymanagement/demos/helloworld/HelloWorld.java
```

Run:

```bash
java com.airtripe.studymanagement.demos.helloworld.HelloWorld
```

Expected output:

![Java Installation Verification](images/java_verification.png)

If you see this, your environment works correctly.

---

# 7️⃣ Running Your Full Project

## **Compile everything**

```bash
cd src
javac com/airtripe/studymanagement/main/Main.java
```

## **Run main console application**

```bash
java com.airtripe.studymanagement.main.Main
```

## **Run HTTP Server version**

```bash
java com.airtripe.studymanagement.main.RestServer
```

---

# 8️⃣ IDE Setup

## ✔ IntelliJ IDEA (Recommended)

1. Download IntelliJ Community Edition
2. Open your project folder
3. IntelliJ automatically detects the `src/` structure
4. Go to:
   **File → Project Structure → SDK → Add SDK → JDK 17**
5. Enable auto-import for Java

**Why IntelliJ?**

* Excellent package visualization
* Easy build/run configuration
* Built-in debugger
* Code auto-completion

---

# 9️⃣ Example Build & Run Output

### Compile

```
> javac com/airtripe/studymanagement/main/Main.java
```

### Run

```
> java com.airtripe.studymanagement.main.Main

STUDENT MANAGEMENT SYSTEM BOOTING...
```

---

