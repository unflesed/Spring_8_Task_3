package com.itvdn.persistence.dao.services.interfaces;

import com.itvdn.persistence.model.Employee;
import java.util.List;
import java.util.Optional;


public interface EmployeeSimpleService {
    List<Employee> findAll() throws InterruptedException;

    Employee addEmployee(Employee employee);

    void removeById(long id);

    List<Employee> findEmployeeByName(String name);

    List<Employee> findEmployeeByNameAndPosition(String name, String position);

    void throwException();

    public void clearCache();

    Optional<Employee> findById(long id) throws InterruptedException;

}
