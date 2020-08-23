package com.observeai.tinyurl;

import com.observeai.tinyurl.resource.TinyUrlResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TinyurlApplicationTests {

	@Autowired
	private TinyUrlResource tinyUrlResource;

	@Test
	void testCreateUrl() {
		ResponseEntity<Object> responseEntity = this.tinyUrlResource.create("www.google.com");
		assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
		assertEquals(responseEntity.getBody(), "py3ubD0C");

		responseEntity = this.tinyUrlResource.create("www.youtube.com");
		assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
		assertEquals(responseEntity.getBody(), "qy3ubD0C");
	}

	@Test
	void testGetUrl() throws IOException {
		//TinyUrlModel.clearMap();
		String url = "www.google.com";
		ResponseEntity<Object> responseEntity = this.tinyUrlResource.create("www.google.com");
		MockHttpServletResponse response = new MockHttpServletResponse();
		this.tinyUrlResource.getUrl(response, responseEntity.getBody().toString());
		assertEquals(response.getHeader("Location"), url);
	}

	@Test
	void testGetHitCount() throws IOException {
		//TinyUrlModel.clearMap();
		ResponseEntity<Object> responseEntity = this.tinyUrlResource.create("www.google.com");
		HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
		this.tinyUrlResource.getUrl(httpServletResponse, responseEntity.getBody().toString());
		this.tinyUrlResource.getUrl(httpServletResponse, responseEntity.getBody().toString());
		String hitCount = this.tinyUrlResource.getHitCount(responseEntity.getBody().toString());
		assertEquals(hitCount, "Tiny url oy3ubD0C accessed 2 times");
	}

}
