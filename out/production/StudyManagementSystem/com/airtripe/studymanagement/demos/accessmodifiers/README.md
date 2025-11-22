
---

# 📘 Access Modifier Demonstration (Public vs Package-Private)

This module demonstrates how **Java access modifiers** work inside the same package and across different packages.
All examples are taken directly from the following package:

```
com.airtripe.studymanagement.demos.accessmodifiers
```

The goal is to show:

* How **public** members behave
* How **package-private** (default) members behave
* Why package-private members cannot be accessed from another package
* How Java enforces access rules at **compile time**

---

## 📂 Files Involved

```
accessmodifiers/
 ├── TestAccessModifers.java            (public class)
 └── TestPackagePrivateAndPublic.java   (package-private class inside same file)
```

### ✔ `TestAccessModifers` (public class)

This class:

* Is accessible from anywhere (because it is `public`)
* Creates an object of the package-private helper class
* Successfully calls **public** and **package-private** methods (same package)
* Provides delegated methods (`publicCall()` and `packagePrivateCall()`) for demonstration

### ✔ `TestPackagePrivateAndPublic` (package-private class)

This class has **no access modifier**, meaning:

* It is **invisible** outside its package
* Only classes inside the same package can instantiate it
* Contains:

    * a **public** method → accessible everywhere
    * a **package-private** method → accessible only inside this package



---



# 🔁 What Happens Inside the Same Package

Inside the constructor of `TestAccessModifers`, I wrote:

```java
testagent.publicCall();          // works
testagent.packagePrivateCall();  // works (same package)
```

Both the public and package-private methods run successfully because visibility is only restricted when crossing package boundaries.

---

# 🌐 Testing Access From a Different Package

To fully test the visibility rules, I used `DemosMain.java` which sits in a **different package**:

```
com.airtripe.studymanagement.demos
```

Here, I create the object using its **Fully Qualified Class Name (FQCN)**:

```java
TestAccessModifers accessAgent =
        new com.airtripe.studymanagement.demos.accessmodifiers.TestAccessModifers();
```

Calling the **public** method works fine:

```java
accessAgent.publicCall();   // ✔ works
```

But calling the **package-private** method fails at **compile time**:

```java
// accessAgent.packagePrivateCall();   // ❌ compile-time error
```

The compiler error clearly states that the method is **not public** and therefore not accessible from outside its package.

This is exactly the behavior I wanted to demonstrate.

---

# 📝 Summary

* `public` → accessible from any package
* `package-private` (default) → only accessible inside the same package
* Trying to call a package-private method from another package leads to a **compile-time error**
* FQCN works without explicit imports
* `DemosMain` ties the entire demonstration together and prints a readable output

This completes my access modifier demonstration, and the code structure makes it easy to follow how Java enforces visibility rules at a package level.

---