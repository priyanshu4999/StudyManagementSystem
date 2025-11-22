package com.airtripe.studymanagement.demos;

import com.airtripe.studymanagement.demos.accessmodifiers.TestAccessModifers;

public class DemosMain {
    public static void main(String[] args){
        System.out.println("========================================================");
        System.out.println("( I )  Demonstrating PUBLIC vs PACKAGE-PRIVATE Access in Java ");
        System.out.println("========================================================\n");

        // ----------------------------------------------------
        // FIRST: Calling methods inside the SAME package
        // ----------------------------------------------------
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("FIRST , Calling inside same package namespace :->");
        System.out.println("-------------------------------------------------------");
        System.out.println("ON INSTANTIATING with FQCN usage :->  Object : accessAgent >>");
        TestAccessModifers accessAgent = new com.airtripe.studymanagement.demos.accessmodifiers.TestAccessModifers();
        System.out.println("    ABOVE CALL WORKS AS BOTH PACKAGE-PRIVATE AND PUBLIC FUNCTIONS ARE CALLED WITHIN PACKAGE");
        // ----------------------------------------------------
        // SECOND: Calling PUBLIC method from a DIFFERENT package
        // ----------------------------------------------------
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SECOND , Calling Public Fucntion in a different package :->");
        System.out.println("-------------------------------------------------------");
        System.out.println("    On calling public fucntion from Object:accessAgent > accessAgent.publicCall(); >>");
        accessAgent.publicCall();
        System.out.println("    ABOVE CALL WORKS EVEN THOUGH CALLED OUTSIDE PACKAGE AS THE FUNCTION IS DECLARED PUBLIC ");
        // ----------------------------------------------------
        // THIRD: Trying to call PACKAGE-PRIVATE from DIFFERENT package
        // ----------------------------------------------------
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("THIRD , Calling PackagePrivate Fucntion in a different package :->");
        System.out.println("-------------------------------------------------------");
        System.out.println("    On calling public fucntion from Object:accessAgent > accessAgent.packagePrivateCall(); >>");
        System.out.println("    ***Throws COMPILATION ERROR >> packagePrivateCall()' is not public in 'com.airtripe.studymanagement.demos.accessmodifiers.TestAccessModifers'. Cannot be accessed from outside package ***");

    }


}

