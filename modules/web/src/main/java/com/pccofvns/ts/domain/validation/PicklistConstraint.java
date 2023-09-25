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

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PicklistValidator.class)
public @interface PicklistConstraint {

	String message() default "Invalid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String name();

    boolean mandatory() default false;

    String separator() default ",";

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy=PicklistValidator.class)
            @interface List
    {
    	PicklistConstraint[] value();
    }
}
