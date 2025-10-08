package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {
    private User userA;

    @BeforeEach
    void runBefore(){
        userA = new User("Anny", 10000);
    }


    @Test
    void testConstructor(){
        assertEquals("Anny", userA.getName());
        assertEquals(10000,userA.getBankAmt());
        assertEquals(0.0,userA.getBudget());
        assertTrue(userA.getPayments().isEmpty());
        assertTrue(userA.getEarnings().isEmpty());
    }

    @Test
    void testSetNewName(){
        assertEquals("Anny", userA.getName());
        userA.setNewName("Jade");
        assertEquals("Jade", userA.getName());
    }

    @Test
    void testSetBudget(){
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
        userA.removePayment(0);
        assertEquals(1, userA.getPayments().size());
    }

    @Test
    void testRemoveEarning(){
        assertEquals(0, userA.getEarnings().size());
        userA.addEarning("Job",1000);
        assertEquals(1, userA.getEarnings().size());
        userA.addEarning("Other",400);
        assertEquals(2, userA.getEarnings().size());
        userA.removeEarning(0);
        assertEquals(1, userA.getEarnings().size());
    }

    @Test
    void testGetTotalPayments(){
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        assertEquals(820, userA.getTotalPayments());
        userA.removePayment(0);
        assertEquals(20, userA.getTotalPayments());
    }

    @Test
    void testGetTotalEarnings(){
        userA.addEarning("Job",1000);
        userA.addEarning("Other",400);
        assertEquals(1400, userA.getTotalEarnings());
        userA.removeEarning(1);
        assertEquals(1000, userA.getTotalEarnings());
    }

    @Test
    void testEndCurrentRecord(){
        userA.setBudget(2000);
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        userA.addEarning("Job",100);
        userA.addEarning("Other",400);
        assertTrue(userA.getOutOfBudget());
        userA.endCurrentRecord();
        assertEquals(9680, userA.getBankAmt());
        assertTrue(userA.getPayments().isEmpty());
        assertTrue(userA.getEarnings().isEmpty());
        assertFalse(userA.getBroke());
        assertFalse(userA.getOutOfBudget());
        assertEquals(2000, userA.getBudget());
    }

    @Test
    void testgetBudgetLeft(){
        userA.setBudget(2000);
        userA.addPayment("Housing",800);
        userA.addPayment("Food",20);
        assertEquals(1180, userA.getBudgetLeft());
        assertFalse(userA.getOutOfBudget());
    }
}
