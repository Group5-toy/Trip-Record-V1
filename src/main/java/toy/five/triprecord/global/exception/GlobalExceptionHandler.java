package toy.five.triprecord.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static toy.five.triprecord.global.exception.ValidationCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<String>> handleBaseException(BaseException e) {
        return new ResponseEntity<>(
                ApiResponse.fail(e.getStatusCode(), e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : null;

        BaseException baseException;

        logger.info("Error Message: {}", errorMessage); // 여기서 로깅을 합니다.

        if (errorMessage != null) {
            if (TRIP_PARAMETER_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.TRIP_IVALID_UPDATE);
            } else if (TRIP_START_TIME_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.TRIP_INVALID_PARAMETER);
            } else if (TRIP_TIME_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.TRIP_INVALID_TIME);
            } else if (TRIP_PARAMETER_ONE_ERROR.getMessage().equals(errorMessage)) {
                baseException = new BaseException(ErrorCode.TRIP_INVALID_PARAMETER);
            } else {
                baseException = new BaseException(ErrorCode.TRIP_SERVER_ERROR);
            }
        } else {
            baseException = new BaseException(ErrorCode.TRIP_IVALID_UPDATE);
        }

        return new ResponseEntity<>(
                ApiResponse.fail(baseException.getStatusCode(), baseException.getMessage()),
                HttpStatus.valueOf(baseException.getStatusCode())
        );
    }


}
