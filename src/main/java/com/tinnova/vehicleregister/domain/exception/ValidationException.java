package com.tinnova.vehicleregister.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BindingResult bindingResult;

}
