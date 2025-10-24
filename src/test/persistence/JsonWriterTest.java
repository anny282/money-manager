package persistence;

import model.User;
import model.Earning;
import model.Payment;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOExceptioin was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User("Anny", 100);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser1.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser1.json");
            user = reader.read();
            assertEquals("Anny", user.getName());
            assertEquals(100, user.getBankAmt());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("Anny", 200);
            user.addEarning("lotto", 20, LocalDate.now());
            user.addEarning("job", 200, LocalDate.now());
            user.addPayment("rent", 1000, LocalDate.now());

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser1.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser1.json");
            user = reader.read();
            assertEquals("Anny", user.getName());
            assertEquals(200, user.getBankAmt());
            List<Earning> earnings = user.getEarnings();
            List<Payment> payments = user.getPayments();
            assertEquals(2, earnings.size());
            checkEarning("lotto", 20, LocalDate.now().toString(), earnings.get(0));
            checkEarning("job", 200, LocalDate.now().toString(), earnings.get(1));
            assertEquals(1, payments.size());
            checkPayment("rent", 1000, LocalDate.now().toString(), payments.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
