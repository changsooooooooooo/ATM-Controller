package atm.controllerAdvice;

import atm.Exception.BalanceIsLittleThanMoney;
import atm.Exception.NoMatchingAccount;
import atm.Exception.NotCorrectPW;
import atm.data.dto.ApiFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ATMControllerAdvice {

    @ExceptionHandler({
            NotCorrectPW.class,
            NoMatchingAccount.class,
            BalanceIsLittleThanMoney.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ApiFormat> pwFailed(Exception e) {
        ApiFormat.Response res = ApiFormat.Response.builder()
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(
                new ApiFormat(false, res), HttpStatus.BAD_REQUEST
        );
    }
}
