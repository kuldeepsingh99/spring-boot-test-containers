package com.portal.testcontainer.service;

import com.portal.testcontainer.dto.DepartmentVO;
import com.portal.testcontainer.model.Department;
import com.portal.testcontainer.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void saveDepartment(DepartmentVO departmentVO) {
        Department department = new Department();
        department.setName(departmentVO.name());
        department.setHead(departmentVO.head());
        departmentRepository.save(department);
    }

    public void updateDepartment(DepartmentVO departmentVO) {

        Department existingDepartment = departmentRepository.findById(departmentVO.Id())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Department department = new Department();
        department.setId(existingDepartment.getId());
        updateIfNotNullOrEmpty(departmentVO.name(), existingDepartment::setName);
        updateIfNotNullOrEmpty(departmentVO.head(), existingDepartment::setHead);
        departmentRepository.save(existingDepartment);
    }

    private void updateIfNotNullOrEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
