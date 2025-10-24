package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PaymentTest {

    private Payment paymentA;
    private Payment paymentB;
    private Payment paymentC;
    private Payment paymentD;
    private Payment paymentE;
    private Payment paymentF;
    private Payment paymentG;
    private Payment paymentH;
    private Payment paymentI;
    private Payment paymentW;

    @BeforeEach
    void runBefore() {
        paymentA = new Payment("Housing", 1000, LocalDate.now());
        paymentB = new Payment("Beauty", 100, LocalDate.now());
        paymentC = new Payment("Food",200, LocalDate.now());
        paymentD = new Payment("Daily use", 500, LocalDate.now());
        paymentE = new Payment("Drink", 7, LocalDate.now());
        paymentF = new Payment("EnTerTainment", 40, LocalDate.now());
        paymentG = new Payment("TRANSPORTATION", 20, LocalDate.now());
        paymentH = new Payment("studies",30, LocalDate.now());
        paymentI = new Payment("Cloth",100, LocalDate.now());
        paymentW = new Payment("Lotto",5, LocalDate.now());
    }

    @Test
    void testConstructor() {
        assertEquals("Housing", paymentA.getCategory());
        assertEquals(1000, paymentA.getAmount());
        assertEquals(LocalDate.now(), paymentA.getDate());
    }

    @Test
    void testAddToCategory() {
        assertEquals(1000, Payment.getHousingTotal());
        assertEquals(5, Payment.getOtherTotal());
        assertEquals(200, Payment.getBeautyClothTotal());
        assertEquals(500, Payment.getDailyTotal());
        assertEquals(40,Payment.getEntertainmentTotal());
        assertEquals(20,Payment.getTransportationTotal());
        assertEquals(30,Payment.getStudiesTotal());
        assertEquals(207,Payment.getFoodDrinkTotal());
    }

    @Test
    void testRemoveCate() {
        paymentI.removeHandle();
        paymentW.removeHandle();
        paymentG.removeHandle();
        paymentD.removeHandle();
        paymentF.removeHandle();
        paymentH.removeHandle();
        assertEquals(1000, Payment.getHousingTotal());
        assertEquals(0, Payment.getOtherTotal());
        assertEquals(100, Payment.getBeautyClothTotal());
        assertEquals(0, Payment.getDailyTotal());
        assertEquals(0,Payment.getEntertainmentTotal());
        assertEquals(0,Payment.getTransportationTotal());
        assertEquals(0,Payment.getStudiesTotal());
        assertEquals(207,Payment.getFoodDrinkTotal());
    }

    @Test
    void testResetCategoryTotal() {
        Payment.resetCategoryTotal();
        assertEquals(0, Payment.getHousingTotal());
        assertEquals(0, Payment.getOtherTotal());
        assertEquals(0, Payment.getBeautyClothTotal());
        assertEquals(0, Payment.getDailyTotal());
        assertEquals(0,Payment.getEntertainmentTotal());
        assertEquals(0,Payment.getTransportationTotal());
        assertEquals(0,Payment.getStudiesTotal());
        assertEquals(0,Payment.getFoodDrinkTotal());
    }
}


