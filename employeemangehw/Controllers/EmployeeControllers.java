package com.example.employeemangehw.Controllers;

import com.example.employeemangehw.APIresponse.ApiResponse;
import com.example.employeemangehw.Models.EmployeeModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeControllers {

    ArrayList<EmployeeModel> employees = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<EmployeeModel> getEmployees() {
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeModel newEmp, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        employees.add(newEmp);
        return ResponseEntity.status(201).body(new ApiResponse("employee added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@RequestBody @Valid EmployeeModel newEmp, @PathVariable String id, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }


        for (EmployeeModel emp : employees
        ) {
            if (emp.getId().equals(id)) {
                emp.setAge(newEmp.getAge());
                emp.setName(newEmp.getName());
                emp.setPosition(newEmp.getPosition());
                emp.setId(newEmp.getId());
                emp.setEmploymentYear(newEmp.getEmploymentYear());
                break;
            }
        }
        return ResponseEntity.status(201).body(new ApiResponse("employee updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id) {
        for (EmployeeModel emp : employees
        ) {
            if (emp.getId().equals(id)) {
                employees.remove(emp);
                return ResponseEntity.status(201).body(new ApiResponse("employee deleted"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("sorry! we haven't employee with this ID : " + id));
    }


    @PutMapping("/vacation/{id}/{days}")
    public ResponseEntity vacation(@PathVariable String id, @PathVariable int days) {
        for (EmployeeModel emp : employees) {
            if (emp.getId().equals(id)) {
                if (emp.getAnnualLeave() < days) {
                    return ResponseEntity.status(400).body(new ApiResponse("sorry! you haven't enough day to take vacation, your annul year is : " + emp.getAnnualLeave()));

                } else {
                    emp.setAnnualLeave(emp.getAnnualLeave() - days);
                    emp.setOnLeave(true);
                    return ResponseEntity.status(201).body(new ApiResponse("congratulations your vacation start, have fun "));

                }
            }
        }

        return ResponseEntity.status(400).body(new ApiResponse("sorry! we haven't employee with this ID : " + id));
    }

}
