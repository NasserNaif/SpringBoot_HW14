package com.example.employeemangehw.Models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.NumberFormat;

import java.text.DateFormat;

@Data
@AllArgsConstructor
public class EmployeeModel {
    @NotEmpty(message = "sorry! ID must be not empty")
    @Size(min = 2, message = "sorry! ID must be more than 2 characters")
    private String id;


    @NotEmpty(message = "sorry! Name must be not empty")
    @Size(min = 4, message = "sorry! Name must be more than 4 characters")
    private String name;

    @NotNull(message = "age must not be null value")
    @Positive(message = "age must not be negative")
    @Min(value = 25, message = "sorry! Age must be greater than 25.")
    private int age;


    @NotEmpty(message = "sorry! position must be not empty")
    @Pattern(regexp = "(coordinator|supervisor)", message = "sorry! position must be supervisor OR coordinator.")
    private String position;

    @NotNull(message = "onLeave must be not null")
    @AssertFalse(message = "onLeave must be false")
    private boolean onLeave;


    @NotNull(message = "sorry! employment year must be not null")
    @Positive(message = "year must not be negative")
    @Min(value = 1900, message = "sorry! year must be more than 1900s")
    @Max(value = 2023, message = "sorry! year must be less than 2023")
    private int employmentYear;


    @NotNull(message = "sorry! annulLeave year must be not null")
    @Positive(message = "year must not be negative")

    private int annualLeave;
}
