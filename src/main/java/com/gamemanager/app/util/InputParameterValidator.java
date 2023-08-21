package com.gamemanager.app.util;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Component
public class InputParameterValidator {

    private static final String LOG_MESSAGE = "There are validation errors in the received dto object: ";
    
    private static final String TYPED_ERROR_MESSAGE = "The parameters received are not correctly typed (text, numeric, etc.). Please check the type of parameters.";
    
	private static final Logger log = LoggerFactory.getLogger( InputParameterValidator.class );


	public static List<String> prepareValidationErrorMessagesList( BindingResult bindingResult  ){
										
			log.error( LOG_MESSAGE, bindingResult.getAllErrors() );
			
			List<String> errors = new ArrayList<>();
			
			List<ObjectError> errorList = bindingResult.getAllErrors();
						
			
			for( ObjectError auxiliary : errorList  ){
				
				String extractedErrorMessage = auxiliary.getDefaultMessage();	

				if( auxiliary.getCode().equals("typeMismatch") ){
					
					extractedErrorMessage = TYPED_ERROR_MESSAGE;
				}

				errors.add( extractedErrorMessage );
			}

			return errors;
	}

}