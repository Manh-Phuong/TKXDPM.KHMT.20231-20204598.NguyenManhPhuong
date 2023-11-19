import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.*;

public class ValidateDeliveryInstructionsTest {
    
    private PlaceOrderController placeOrderController;

    
    /** 
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
        "Hà Nội, true",
        "Hà Nội @23, false",
    })

    public void test(String instrucions, boolean expected) {
        boolean isValid = placeOrderController.validateDeliveryInstructions(instrucions);
        assertEquals(expected, isValid);
    }
}
