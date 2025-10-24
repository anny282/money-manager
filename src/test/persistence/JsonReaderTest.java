package persistence;

import model.User;
import model.Earning;
import model.Payment;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader user = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User us1 = user.read();
            assertEquals("Anny", us1.getName());
            assertEquals(0, us1.getEarnings().size());
            assertEquals(0, us1.getPayments().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("Annyw", user.getName());
            assertEquals(2000.0, user.getBankAmt());
            assertEquals(500.0, user.getBudget());
            assertFalse(user.getBroke());
            List<Earning> earnings = user.getEarnings();
            assertEquals(2, earnings.size());
            checkEarning("Job", 1200, "2025-10-01", earnings.get(0));
            checkEarning("Gift", 200, "2025-10-05", earnings.get(1));

            List<Payment> payments = user.getPayments();
            assertEquals(1, payments.size());
            checkPayment("Rent", 800, "2025-10-10", payments.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
