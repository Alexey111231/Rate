import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vk.sladkiipirojok.controllers.MoneyRateController;

@RunWith(SpringRunner.class)
public class MoneyRateTests {

    @MockBean
    MoneyRateController rateController;

    @Test
    void dataTest(){

    }
}
