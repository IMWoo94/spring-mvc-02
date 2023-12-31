package hello;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocaleConfig extends WebMvcConfigurationSupport {

	/**
	 * LocaleResolver 설정
	 * - Cookie 방식 사용
	 */
	@Bean
	public LocaleResolver localeResolver() {
		// 쿠키
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		cookieLocaleResolver.setCookieHttpOnly(true);
		return cookieLocaleResolver;
	}

	/**
	 * LocaleResolver 설정
	 * - Session 방식 사용
	 */
	// @Bean
	// public LocaleResolver localeResolver() {
	// 	SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	// 	sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
	// 	return sessionLocaleResolver;
	// }

	/**
	 * Locale 정보 변경 ( 인터셉터 )
	 * - 모든 URL에 대해 ?lang= 쿼리를 날리면 이 인터셉터를 지난다.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	/**
	 * 인터셉터 등록
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
