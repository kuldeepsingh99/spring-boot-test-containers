package com.portal.testcontainer.controller;

import com.portal.testcontainer.dto.DepartmentVO;
import com.portal.testcontainer.model.Department;
import com.portal.testcontainer.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @GetMapping("/department")
    public Department getDepartmentByName(@RequestBody DepartmentVO departmentVO) {
        return departmentService.getDepartmentByName(departmentVO.name());
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentService.findAll();
    }

    @PostMapping("/department")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDepartment(@RequestBody DepartmentVO departmentVO) {
        departmentService.saveDepartment(departmentVO);
    }

    @PutMapping("/department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartment(@RequestBody DepartmentVO departmentVO) {
        departmentService.updateDepartment(departmentVO);
    }

}
