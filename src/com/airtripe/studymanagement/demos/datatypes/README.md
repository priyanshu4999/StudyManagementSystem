
# 📘 **Java Data Types, Typecasting, Static vs Instance Variables & Scope – Demo Project**

This project demonstrates **four core Java concepts**:

1. **Primitive Data Types**
2. **Typecasting (Implicit & Explicit)**
3. **Static vs Instance Variables**
4. **Variable Scope (Functional, Block, Shadowing)**

All examples are implemented in:

`DataTypesAndVariables.java`

---

# ✅ **1. Primitive Data Types**

Java provides 8 primitive types.
The class **DataPrimitives** shows:

* Default values
* Assigned example values
* Printing each value

### **Output**

```
=====================================================
DATA PREMITIVES DEMO
=====================================================

-Default Values-

byte: 0
short: 0
int: 0
long: 0
float: 0.0
double: 0.0
char:  
boolean: false

-Example values-

byte: 10
short: 200
int: 5000
long: 9999999999
float: 5.75
double: 19.9999
char: A
boolean: true
-----------------------------------------------------
```

---

# ✅ **2. Type Casting (Implicit & Explicit)**

**Implicit Casting**: small type → large type
**Explicit Casting**: large type → small type

Demonstrated in `TypeCastingDemo.showCasting()`.

### **Output**

```
=====================================================
TYPE CASTING DEMO
=====================================================
Implicit: int 100 → double = 100.0
Explicit: double 99.99 → int = 99
Explicit overflow: 257 → byte = 1
```

---

# ✅ **3. Static vs Instance Variables**

`StaticVariableCounterDemo` shows:

| Variable Type             | Meaning                   |
| ------------------------- | ------------------------- |
| **static**                | Shared across all objects |
| **non-static (instance)** | Separate for each object  |

### **Execution**

```java
StaticVariableCounterDemo counterOne = new StaticVariableCounterDemo("CounterONE");
StaticVariableCounterDemo counterTwo = new StaticVariableCounterDemo("CounterTWO");

counterOne.increase();
counterTwo.increase();
```

### **Output**

```
=====================================================
STATIC VS INSTANCE VARIABLE WITH COUNTERS
=====================================================
CounterONE INCREMENT BOTH STATIC AND NON-STATIC Variables
CounterTWO INCREMENT BOTH STATIC AND NON-STATIC Variables
CounterONE --> class staticCounter: 2, instance nonstaticCounter: 1
CounterTWO --> class staticCounter: 2, instance nonstaticCounter: 1
```

👉 **Notice:**

* `staticCounter` increased to **2** (shared).
* Each instance has **nonstaticCounter = 1**.

---

# ✅ **4. Scope Demonstration (Shadowing Example)**

The `ScopeDemo` class shows:

### Types of Scope

| Scope Type | Meaning                                       |
| ---------- | --------------------------------------------- |
| static     | class-level variable                          |
| instance   | per-object variable                           |
| local      | inside method                                 |
| shadowed   | local variable hides instance/static variable |

### Method Called

```java
scopedemoAgent.testShadowing();
```

### Output

```
=====================================================
SCOPE DEMO
=====================================================
SHOW OF SCOPES , FUNCTIONAL , BLOCK AND SHADOW SCOPES
-----------------------------------------------------
Shadowed staticScope (local): 500
Shadowed instanceScope (local): 800
Shadowed staticScope (Static value that was overridden): 42
Original instanceScope (instance variable value that was overriden): 402
```

👉 **Key idea:**
Local variables **shadow** the class-level variables, but you can access originals using:

* `this.instanceScope`
* `ScopeDemo.staticScope`

---
