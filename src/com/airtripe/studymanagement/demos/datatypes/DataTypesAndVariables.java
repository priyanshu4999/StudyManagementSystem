package com.airtripe.studymanagement.demos.datatypes;

public class DataTypesAndVariables {
    public static void main(String[] args){
        DataPrimitives primitiveAgent = new DataPrimitives();
        primitiveAgent.Display("\n-Default Values-\n");
        primitiveAgent.setAll();
        primitiveAgent.Display("\n-Example values-\n");
        System.out.println("-----------------------------------------------------");
        TypeCastingDemo typeCastingAgent = new TypeCastingDemo();
        TypeCastingDemo.showCasting();
        System.out.println("=====================================================");
        System.out.println("STATIC VS INSTANCE VARIABLE WITH COUNTERS");
        System.out.println("=====================================================");
        StaticVariableCounterDemo counterOne = new StaticVariableCounterDemo("CounterONE");
        StaticVariableCounterDemo counterTwo = new StaticVariableCounterDemo("CounterTWO");
        counterOne.increase();  // static = 1
        counterTwo.increase();  // static = 3

        counterOne.display();
        counterTwo.display();
        System.out.println("=====================================================");
        System.out.println("SCOPE DEMO");
        System.out.println("=====================================================");
        ScopeDemo scopedemoAgent = new ScopeDemo();
        System.out.println("-----------------------------------------------------");
        scopedemoAgent.testShadowing();

    }
}

class DataPrimitives{
    // Initalise with default values
    byte b ;
    short s ;
    int i ;
    long l ;

    float f ;
    double d ;

    char c ;
    boolean flag ;
    public DataPrimitives(){
        System.out.println("=====================================================");
        System.out.println("DATA PREMITIVES DEMO");
        System.out.println("=====================================================");
    }
    public void Display(String a) {
        System.out.println(a);
        System.out.println("byte: " + this.b);
        System.out.println("short: " + this.s);
        System.out.println("int: " + this.i);
        System.out.println("long: " + this.l);
        System.out.println("float: " + this.f);
        System.out.println("double: " + this.d);
        System.out.println("char: " + this.c);
        System.out.println("boolean: " + this.flag);
    }

    public void setAll(){
        // Hardcode random values for example
        this.b = 10;
        this.s = 200;
        this.i = 5000;
        this.l = 9999999999L;

        this.f = 5.75f;
        this.d = 19.9999;

        this.c = 'A';
        this.flag = true;
    }
}

class TypeCastingDemo {
    public static void showCasting() {
        System.out.println("=====================================================");
        System.out.println("TYPE CASTING DEMO");
        System.out.println("=====================================================");
        //Implicit Casting (To higher bytes)
        int i = 100;
        double d = i;   // int -> double
        System.out.println("Implicit: int 100 → double = " + d);

        // Explicit Casting (To lower bytes)
        double big = 99.99;
        int reduceDoubleToInt = (int) big;   // double -> int
        System.out.println("Explicit: double 99.99 → int = " + reduceDoubleToInt);

        byte b = (byte) 257; // value wraps due to overflow
        System.out.println("Explicit overflow: 257 → byte = " + b);
    }
}

class StaticVariableCounterDemo{
    static int staticCounter;
    int nonstaticCounter;
    String currentObject;
    public StaticVariableCounterDemo(String name){
        currentObject = name;
    }
    public void increase(){
        System.out.println(this.currentObject + " INCREMENT BOTH STATIC AND NON-STATIC Variables");
        staticCounter++;
        nonstaticCounter++;
    }

    void display(){
        System.out.println(this.currentObject + " --> class staticCounter: " + staticCounter +
            ", instance nonstaticCounter: " + nonstaticCounter);
    }

}

class ScopeDemo{
    static int staticScope = 42;
    int instanceScope = 402;

    public ScopeDemo(){
        System.out.println("SHOW OF SCOPES , FUNCTIONAL , BLOCK AND SHADOW SCOPES");
    }

    public void testShadowing() {

        int staticScope = 500;   // shadows class staticScope
        int instanceScope = 800; // shadows real instanceScope

        System.out.println("Shadowed staticScope (local): " + staticScope);
        System.out.println("Shadowed instanceScope (local): " + instanceScope);

        System.out.println("Shadowed staticScope (Static value that was overridden): " + ScopeDemo.staticScope);
        System.out.println("Original instanceScope (instance variable value that was overriden): " + this.instanceScope);
    }
}