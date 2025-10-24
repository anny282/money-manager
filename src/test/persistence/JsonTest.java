package persistence;

import model.User;
import model.Earning;
import model.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class JsonTest {
    protected void checkEarning(String category, double amount, String dateString, Earning earning) {
        assertEquals(category, earning.getCategory());
        assertEquals(amount, earning.getAmount());
        assertEquals(dateString, earning.getDate().toString());
    }

    protected void checkPayment(String category, double amount, String dateString, Payment payment) {
        assertEquals(category, payment.getCategory());
        assertEquals(amount, payment.getAmount());
        assertEquals(dateString, payment.getDate().toString());
    }
}
