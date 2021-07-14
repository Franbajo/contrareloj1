package com.fran.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DniSpainValidator implements ConstraintValidator<DniSpainConstraint, String>{
	  
	public boolean menor99999999(String idField) {    
		  int number = Integer.parseInt(idField.substring(0,8));
		return number<=99999999;
	}

	@Override
	 public boolean isValid(String idField, ConstraintValidatorContext cxt) {
		    return idField != null
		            && idField.matches("[0-9]{8}[a-zA-Z]{1}")
		            && menor99999999(idField);
		  }

}
