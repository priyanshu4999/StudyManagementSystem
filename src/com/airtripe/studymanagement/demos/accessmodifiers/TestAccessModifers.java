package com.airtripe.studymanagement.demos.accessmodifiers;

public class TestAccessModifers {

    public TestPackagePrivateAndPublic packagePrivateAgent;
    public TestAccessModifers(){
        this.packagePrivateAgent = new TestPackagePrivateAndPublic();
        System.out.println("TESTING ACCESS MODIFIERS PUBLIC AND PACKAGE-PRIVATE**");
        TestPackagePrivateAndPublic testagent = new TestPackagePrivateAndPublic();

        testagent.publicCall();
        testagent.packagePrivateCall();
    }
    public void publicCall(){
        this.packagePrivateAgent.publicCall();
    }
    void packagePrivateCall(){
        this.packagePrivateAgent.packagePrivateCall();
    }
}

class TestPackagePrivateAndPublic{
    public void publicCall(){
        System.out.println("!!! This is public call , It works  !!!");
    }

    void packagePrivateCall(){
        System.out.println("!!! This is packagePrivateCall , called inside same package, it Works!!!");
    }
}
