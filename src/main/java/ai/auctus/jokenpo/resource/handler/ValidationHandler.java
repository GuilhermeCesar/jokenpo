package ai.auctus.jokenpo.resource.handler;

import ai.auctus.jokenpo.dto.ErrorMessage;
import ai.auctus.jokenpo.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.stream.Collectors;

import static ai.auctus.jokenpo.helper.MessageHelper.ErrorCode.ERROR_VALID_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ValidationHandler {

    private final MessageHelper messageHelper;

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorMessage exceptionHandler(ValidationException e) {
        log.debug(e.getMessage(), e.getCause());
        return ErrorMessage
                .builder()
                .message(e.getMessage())
                .error(e.getMessage())
                .build();
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorMessage constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.debug(e.getMessage(), e.getCause());
        var errors = e.getConstraintViolations()
                .parallelStream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ErrorMessage
                .builder()
                .message(e.getMessage())
                .error(e.getClass().getName())
                .errorsList(errors)
                .build();
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        var listErrors = ex.getBindingResult().getAllErrors().parallelStream()
                .map(objectError -> this.messageHelper.get(ERROR_VALID_DATA, ((FieldError) objectError).getField(), (objectError).getDefaultMessage()))
                .collect(Collectors.toList());
        listErrors.add(ex.getMessage());

        return ErrorMessage
                .builder()
                .message(listErrors.get(0))
                .error(ex.getClass().getName())
                .errorsList(listErrors)
                .build();
    }
}
