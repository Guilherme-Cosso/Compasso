package Sprint4.config;

import Sprint4.dto.ErroValidacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class MsnErroTratado {

    @Autowired
    private MessageSource menssageSorce;


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacaoDto> erro(MethodArgumentNotValidException exception){
        List<ErroValidacaoDto> dtos = new ArrayList<>();
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        errors.forEach(
                e->{
                        String msg =  menssageSorce.getMessage(e, LocaleContextHolder.getLocale());
                        ErroValidacaoDto erroValidacaoDto = new ErroValidacaoDto(e.getField(),msg);
                         dtos.add(erroValidacaoDto);
                }

        );
        return  dtos;
    }
}
