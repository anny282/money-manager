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

    @BeforeEach
    void runBefore(){
        earningA = new Earning("Job", 1000);
        earningB = new Earning("Other", 100);
        earningC = new Earning("Job",200);
        earningD = new Earning("Financial", 500);
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
        assertEquals(0, Earning.getOtherTotal());
        assertEquals(0, Earning.getLottoTotal());
        assertEquals(500, Earning.getFinancialTotal());
    }

    @Test
    void testResetCategoryTotal(){
        assertEquals(0, Earning.getJobTotal());
        assertEquals(0, Earning.getOtherTotal());
        assertEquals(0, Earning.getLottoTotal());
        assertEquals(0, Earning.getFinancialTotal());
    }
}
