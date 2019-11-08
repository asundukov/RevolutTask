package ru.my2i.wallet.web.exception.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrorDto {
    @JsonProperty("field_name")
    public String fieldName;

    @JsonProperty("constraint_type")
    public String constraintType;
}