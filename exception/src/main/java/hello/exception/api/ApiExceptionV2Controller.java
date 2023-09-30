package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiExceptionV2Controller {

	// 단, @ResponseStatus 을 쓰게 되면 상황에 따라서 동적으로 에러 코드를 정할 수 없다.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResult illegalExHandler(IllegalArgumentException e) {
		log.error("[exceptionHandle] ex", e);
		return new ErrorResult("BAD", e.getMessage());
	}

	// 파라미터의 변수 값의 Exception이 자동 적용되서 처리된다.
	@ExceptionHandler
	public ResponseEntity<ErrorResult> userExHandle(UserException e) {
		log.error("[exceptionHandle] ex", e);
		ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}

	// 해당 컨트롤러에서 발생하는 에러는 모두 아래의 처리 방식으로 해결한다.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandle(Exception e) {
		log.error("[exceptionHandle] ex", e);
		return new ErrorResult("EX", "내부 오류");
	}

	@GetMapping("/api2/members/{id}")
	public MemberDto getMember(@PathVariable("id") String id) {
		if (id.equals("ex")) {
			throw new RuntimeException("잘못된 사용자");
		}

		if (id.equals("bad")) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}

		if (id.equals("user-ex")) {
			throw new UserException("사용자 오류");
		}

		return new MemberDto(id, "hello" + id);
	}

	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String meberId;
		private String name;
	}
}
