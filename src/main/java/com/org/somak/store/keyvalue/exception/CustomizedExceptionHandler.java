package com.org.somak.store.keyvalue.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Optional;


@ControllerAdvice
@RestController
public class CustomizedExceptionHandler {

	@ExceptionHandler(CustomEventException.class)
	public ResponseEntity<ExceptionBean> handleAllCustomException(CustomEventException ex, WebRequest request) throws Exception {

		return new ResponseEntity<ExceptionBean>(ex.getException(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ExceptionBean> invalidFormatException(final IOException e) {
		return error(e, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity <ExceptionBean> error(final Exception exception, final HttpStatus httpStatus) {
		final String message = Optional.ofNullable(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity(
				new ExceptionBean(
						Integer.valueOf(HttpStatus.BAD_REQUEST.value()).toString(),
						message), httpStatus);
	}
}
