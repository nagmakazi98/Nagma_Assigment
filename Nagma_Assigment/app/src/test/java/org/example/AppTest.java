package org.example;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest{

    AppTest testCases;

    @BeforeClass
    public void setUp() {
        testCases = new AppTest();
    }

    @Test
    public void testCase01() throws InterruptedException {
        testCases.testCase01();
    }

    @AfterClass
    public void tearDown() {
        testCases.endTest();
    }

    private void endTest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTest'");
    }
}
