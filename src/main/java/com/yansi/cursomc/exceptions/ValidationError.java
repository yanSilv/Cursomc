/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.exceptions;

import com.yansi.cursomc.error.FieldMessage;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final Long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fileName, String message) {
        this.errors.add(new FieldMessage(fileName, message));
    }

}
