package training.onepay.errors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import training.onepay.domain.ErrorResponse;

@ControllerAdvice
public class ErrorHandlerController {

	private static final Logger LOGGER = LogManager.getLogger(ErrorHandlerController.class);

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
		LOGGER.error(e);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(400, e.getMessage()), HttpStatusCode.valueOf(400));
	}

	@ExceptionHandler(WSException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handle(WSException e) {
		LOGGER.warn(e.getWsMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getCode(), e.getWsMessage()),
				HttpStatusCode.valueOf(e.getCode()));
	}
}
