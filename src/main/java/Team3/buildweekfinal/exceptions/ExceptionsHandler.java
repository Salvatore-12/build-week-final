package Team3.buildweekfinal.exceptions;

import Team3.buildweekfinal.payloads.ErrorsDTO;
import Team3.buildweekfinal.payloads.ErrorsPayloadWhitList;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(Team3.buildweekfinal.exceptions.BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadWhitList handleBadRequest(Team3.buildweekfinal.exceptions.BadRequestException ex) {
        List<String> errorsMessages = new ArrayList<>();
        if(ex.getErrorList() != null)
            errorsMessages = ex.getErrorList().stream().map(errore -> errore.getDefaultMessage()).toList();
        return new ErrorsPayloadWhitList(ex.getMessage(), newDateAndHour(), errorsMessages);
    }

    @ExceptionHandler(Team3.buildweekfinal.exceptions.UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsDTO handleUnauthorized(Team3.buildweekfinal.exceptions.UnauthorizedException e) {
        return new ErrorsDTO(e.getMessage(),newDateAndHour());
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsDTO accessDenied(AccessDeniedException ex){
        return new ErrorsDTO("Access Denied", newDateAndHour());
    }

    @ExceptionHandler(Team3.buildweekfinal.exceptions.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(Team3.buildweekfinal.exceptions.NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(),newDateAndHour());
    }



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("Work in progress!", newDateAndHour());
    }

    public static String newDateAndHour(){
        String pattern = "E, dd MMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
}
