package hello.upload.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {

	@GetMapping("/upload")
	public String newFile() {
		return "upload-form";
	}

	@PostMapping("/upload")
	public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
		log.info("request ={}", request);

		String itemName = request.getParameter("itemName");
		log.info("itemName={}", itemName);

		// multipart/form-data 전송 방식에서 각각 나누어진 부분을 받아서 확인 할 수 있다.
		Collection<Part> parts = request.getParts();
		log.info("parts={}", parts);

		return "upload-form";
	}
}
