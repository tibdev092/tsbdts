package com.banigeo.webpoc.exception;

import com.banigeo.webpoc.exception.department.DepartmentNotFoundException;
import com.banigeo.webpoc.exception.employee.DuplicateEmailAddressException;
import com.banigeo.webpoc.exception.employee.DuplicatePhoneNumberException;
import com.banigeo.webpoc.exception.employee.EmployeeNotFoundException;
import com.banigeo.webpoc.exception.employee.SalaryOutOfRangeException;
import com.banigeo.webpoc.exception.job.JobAlreadyPresentException;
import com.banigeo.webpoc.exception.job.JobNotFoundException;
import com.banigeo.webpoc.exception.job.SalaryRangeMissmatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler({SalaryOutOfRangeException.class,
            DuplicateEmailAddressException.class,
            DuplicatePhoneNumberException.class,
            JobAlreadyPresentException.class,
            SalaryRangeMissmatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleValidationExceptions(RuntimeException e) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("exception", e);
        mv.setViewName("errorDefault");
        return mv;
        /*
        Map<String, String> responseParams = new LinkedHashMap<>();
        responseParams.put("Reason", e.getMessage());
        responseParams.put("When", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseParams);
         */
    }
    @ExceptionHandler({EmployeeNotFoundException.class,
            DepartmentNotFoundException.class,
            JobNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundExceptions(RuntimeException e) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("exception", e);
        mv.setViewName("notFound");
        /*
        Map<String, String> responseParams = new LinkedHashMap<>();
        responseParams.put("Reason", e.getMessage());
        responseParams.put("When", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseParams);
         */
        return mv;
    }

/*
    @ExceptionHandler({BindException.class})
    public ResponseEntity handle(BindException exception) {
        Map<String, String> responseParams = new LinkedHashMap<>();
        String message = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" , "));
        responseParams.put("Reason", message);
        responseParams.put("When", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(responseParams);
    }
*/

}
