//package rmignac.commons.errors;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import javax.persistence.EntityNotFoundException;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = BadCredentialsException.class)
//    public ResponseEntity<ErrorDTO> catchBadCredentialException(BadCredentialsException badCredentialsException) {
//        ErrorDTO errorDTO = new ErrorDTO("BAD_CREDENTIALS", badCredentialsException.getMessage());
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDTO);
//    }
//
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ResponseEntity<ErrorDTO> catchAccessDeniedException(AccessDeniedException accessDeniedException) {
//        ErrorDTO errorDTO = new ErrorDTO("ACCESS_DENIED", accessDeniedException.getMessage());
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDTO);
//    }
//
//}