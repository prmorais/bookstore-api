package com.paulo.bookstore.resources.exception;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String field, String message) {
        this.errors.add(new FieldMessage(field, message));
    }
}
