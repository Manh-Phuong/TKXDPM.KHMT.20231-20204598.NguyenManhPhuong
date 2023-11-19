import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import controller.*;

class ValidatePhoneNumberTest {

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
		"0123456789, true",
		"01234, false",
		"012345678910, false",
		"999, false",
		"9999999999, false",
		"99999999999, false",
		"999999999abc, false",
	})

	public void test(String phone, boolean expected) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		assertEquals(expected, isValid);
	}

}
