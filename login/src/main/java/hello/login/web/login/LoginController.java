package hello.login.web.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute LoginForm form) {
		return "login/loginForm";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm form
		, BindingResult bindingResult
		, HttpServletResponse response
	) {
		if (bindingResult.hasErrors()) {
			return "login/loginForm";
		}

		Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

		log.info("login? {}", loginMember);

		if (loginMember == null) {
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}

		// 로그인 성공 처리 todo

		// 쿠키에 시간 정보를 주지 않으면 세션 쿠키 ( 브라우저 종료 시 모두 종료 )
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);

		return "redirect:/";
	}
}