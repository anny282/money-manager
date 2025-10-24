package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EarningTest {
    private Earning earningA;
    private Earning earningB;
    private Earning earningC;
    private Earning earningD;
    private Earning earningE;
    private Earning earningF;

    @BeforeEach
    void runBefore(){
        Earning.resetCategoryTotal();
        earningA = new Earning("Job", 1000, LocalDate.now());
        earningB = new Earning("Other", 100, LocalDate.now());
        earningC = new Earning("Job",200, LocalDate.now());
        earningD = new Earning("Financial", 500, LocalDate.now());
        earningE = new Earning("Lotto", 30, LocalDate.now());
        earningF = new Earning("Award",300, LocalDate.now());
    }

    @Test
    void testConstructor(){
        assertEquals("Job", earningA.getCategory());
        assertEquals(1000, earningA.getAmount());
        assertEquals(LocalDate.now(), earningA.getDate());
    }

    @Test
    void testAddToCategory(){
        assertEquals(1200, Earning.getJobTotal());
        assertEquals(400, Earning.getOtherTotal());
        assertEquals(30, Earning.getLottoTotal());
        assertEquals(500, Earning.getFinancialTotal());
    }

    @Test
    void testRemoveCate(){
        earningA.removeHandle();
        earningD.removeHandle();
        earningE.removeHandle();
        assertEquals(200, Earning.getJobTotal());
        assertEquals(400, Earning.getOtherTotal());
        assertEquals(0, Earning.getLottoTotal());
        assertEquals(0, Earning.getFinancialTotal());
    }

    @Test
    void testResetCategoryTotal(){
        Earning.resetCategoryTotal();
        assertEquals(0, Earning.getJobTotal());
        assertEquals(0, Earning.getOtherTotal());
        assertEquals(0, Earning.getLottoTotal());
        assertEquals(0, Earning.getFinancialTotal());
    }
}
