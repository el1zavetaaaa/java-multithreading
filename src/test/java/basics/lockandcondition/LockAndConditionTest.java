package basics.lockandcondition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LockAndConditionTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testRestaurantWillWorkBothWithLockAndWaitMechanisms() throws InterruptedException {
        new Restaurant();

        TimeUnit.SECONDS.sleep(1);

        String restaurantWorkingHours = outputStreamCaptor.toString();

        assertThat(restaurantWorkingHours).contains("Order up! Waitperson got Meal 1\n" +
                "Order up! Waitperson got Meal 2\n" +
                "Order up! Waitperson got Meal 3\n" +
                "Order up! Waitperson got Meal 4\n" +
                "Order up! Waitperson got Meal 5\n" +
                "Order up! Waitperson got Meal 6\n" +
                "Order up! Waitperson got Meal 7\n" +
                "Order up! Waitperson got Meal 8\n" +
                "Order up! Waitperson got Meal 9\n" +
                "Out of food, closing");
    }
}
