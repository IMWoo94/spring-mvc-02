package hello.itemservice.message;

import static org.assertj.core.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

	@Autowired
	MessageSource ms;

	@Test
	void helloMessage() {
		String result = ms.getMessage("hello", null, null);
		String enResult = ms.getMessage("hello", null, Locale.ENGLISH);
		String test = ms.getMessage("test", null, null);

		assertThat(result).isEqualTo("안녕");
		assertThat(enResult).isEqualTo("hello");
		assertThat(test).isEqualTo("test 안녕");
	}

	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
	}

	@Test
	void notFoundMessageCodeDefaultMessage() {
		String result = ms.getMessage("no_code", null, "기본 메시지", null);
		assertThat(result).isEqualTo("기본 메시지");
	}

	@Test
	void argumentMessage() {
		String result = ms.getMessage("hello.name", new Object[] {"Spring"}, null);
		String enResult = ms.getMessage("hello.name", new Object[] {"Spring"}, Locale.ENGLISH);
		String test = ms.getMessage("test.name", new Object[] {"tester"}, null);

		assertThat(result).isEqualTo("안녕 Spring");
		assertThat(enResult).isEqualTo("hello Spring");
		assertThat(test).isEqualTo("test 안녕 tester");

	}

	@Test
	void defaultLang() {
		assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
		assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
	}

	@Test
	void enLang() {
		assertThat(ms.getMessage("hello", null,
			Locale.ENGLISH)).isEqualTo("hello");
	}
}
