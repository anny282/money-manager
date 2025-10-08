package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class UserTest {
    private User userA;

    @BeforeEach
    void runBefore(){
        userA = new User("Anny", 10000);
    }


    @Test
    void testConstructor(){
        userA.getCurrentDate();
        assertEquals("Anny", userA.getName());
        assertEquals(10000,userA.getBankAmt());
        assertEquals(0.0,userA.getBudget());
        assertEquals(null, userA.getPayments());
        assertEquals(null, userA.getEarnings());
    }

    @Test
    void testSetNewName(){
        assertEquals("Anny", userA.getName());
        userA.setNewName("Jade");
        assertEquals("Jade", userA.getName());
    }

    @Test
    void testSetBudgetLeft(){
        userA.setBudget(2000);
        assertEquals(2000, userA.getBudget());
    }

    @Test
    void testSetBankAmt(){
        assertEquals(10000,userA.getBankAmt());
        userA.setBankAmt(20000);
        assertEquals(20000,userA.getBankAmt());
    }

    @Test
    void testAddPayment(){
        assertTrue(userA.getPayments().isEmpty());
        userA.addPayment("Housing",800);
        assertEquals(1, userA.getPayments().size());
    }

    @Test
    void testAddEarning(){
        userA.addEarning("Job",1000);
        assertEquals(1, userA.getEarnings().size());
    }

    @Test
    void testRemovePayment(){
        assertEquals(0, userA.getPayments().size());
        assertTrue(userA.getPayments().isEmpty());
        userA.addPayment("Housing",800);
        assertEquals(1, userA.getPayments().size());
        userA.addPayment("Food",20);
        assertEquals(2, userA.getPayments().size());
        userA.removePayment("Housing", 800, LocalDate.now());
        assertEquals(1, userA.getPayments().size());
    }

    @Test
    void testRemoveEarning(){
        assertEquals(0, userA.getEarnings().size());
        userA.addEarning("Job",1000);
        assertEquals(1, userA.getEarnings().size());
        userA.addEarning("Other",400);
        assertEquals(2, userA.getEarnings().size());
        userA.removeEarning("Job", 1000, LocalDate.now());
        assertEquals(1, userA.getEarnings().size());
    }

    @Test
    void testGetTotalPayments(){
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        assertEquals(820, userA.getTotalPayments());
        userA.removePayment("Housing", 800, LocalDate.now());
        assertEquals(20, userA.getTotalPayments());
    }

    @Test
    void testGetTotalEarnings(){
        userA.addEarning("Job",1000);
        userA.addEarning("Other",400);
        assertEquals(1400, userA.getTotalEarnings());
        userA.removeEarning("Job", 1000, LocalDate.now());
        assertEquals(1400, userA.getTotalEarnings());
    }

    @Test
    void testEndCurrentMonth(){
        userA.setBudget(2000);
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        userA.addEarning("Job",1000);
        userA.addEarning("Other",400);
        userA.endCurrentMonth();
        assertEquals(10580, userA.getBankAmt());
        assertTrue(userA.getPayments().isEmpty());
        assertTrue(userA.getEarnings().isEmpty());
        assertEquals(2000, userA.getBudget());
    }

    @Test
    void testgetBudgetLeft(){
        userA.setBudget(2000);
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        assertEquals(1180, userA.getBudgetLeft());
    }
}
