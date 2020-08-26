package demo;

import com.zm.demo.AutoGenerate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoGenerate.class)
class BaseTest {

	@Test
	void contextLoads() {
	}

}
