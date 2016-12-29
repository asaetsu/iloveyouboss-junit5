/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package scratch;

import org.junit.jupiter.api.*;


public class AssertMoreTest {
    @BeforeAll
    public static void initializeSomethingReallyExpensive() {
        System.out.println("@BeforeAll - Run before all methods once");
    }

    @AfterAll
    public static void cleanUpSomethingReallyExpensive() {
        System.out.println("@AfterAll - Run after all test methods once");
    }

    @BeforeEach
    public void createAccount() {
        System.out.println("  @BeforeEach - Run before each test methods ");
    }

    @AfterEach
    public void closeConnections() {
        System.out.println("  @AfterEach - Run after each test methods ");
    }

    @Test
    public void depositIncreasesBalance() {
        System.out.println("    Test method 1");
    }

    @Test
    public void hasPositiveBalance() {
        System.out.println("    Test method 2");
    }
}

