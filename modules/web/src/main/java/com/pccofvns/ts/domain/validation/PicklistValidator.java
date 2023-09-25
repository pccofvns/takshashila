/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////////////////////
 * //
 * // hCentive, Inc. CONFIDENTIAL
 * // __________________
 * //
 * //  Copyright 2010 - 2017 hCentive, Inc.
 * //
 * //  All Rights Reserved.
 * //
 * // NOTICE:  All information contained herein is, and remains
 * // the property of hCentive, Inc., and its suppliers, if any.
 * // The intellectual and technical concepts contained
 * // herein are proprietary to hCentive, Inc. and its suppliers
 * // and may be covered by U.S. and Foreign Patents,
 * // patents in process, and are protected by trade secret or copyright law.
 * // Dissemination of this information or reproduction of this material
 * // is strictly forbidden unless prior written permission is obtained
 * // from hCentive, Inc.
 * //////////////////////////////////////////////////////////////////////////////////////////////
 * //////////////////////
 * ///CHANGE LOG
 * ///
 * ///@hCentive.CHANGELOG
 * ///
 * ////////////////////
 ******************************************************************************/
package com.pccofvns.ts.domain.validation;

import com.pccofvns.ts.service.PicklistService;
import com.pccofvns.ts.utils.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PicklistValidator implements ConstraintValidator<PicklistConstraint, Object> {

	private boolean isMandatory;

	private String picklistName;

	private String separator;

	@Autowired
	private PicklistService picklistService;

	@Override
	public void initialize(PicklistConstraint constraintAnnotation) {
		this.picklistName = constraintAnnotation.name();
		this.separator = constraintAnnotation.separator();
		this.isMandatory= constraintAnnotation.mandatory();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null){
			return !isMandatory;
		}
		Map<String, String> pickList = picklistService.getPickList(picklistName);
		Collection<String> keySet = pickList.keySet();
		List<String> valueList = null;

		if (value instanceof String pValue) {
			if (!StringUtils.isEmpty(separator)) {
				valueList = Arrays.asList(pValue.split(separator));
			} else {
				valueList = List.of((pValue));
			}
		}
		else if(value instanceof List<?> pListValue){
			valueList = (List<String>) pListValue;
		}
        if(valueList != null){
        	return keySet.containsAll(valueList);
        }else{
        	return true;
        }

	}
}
