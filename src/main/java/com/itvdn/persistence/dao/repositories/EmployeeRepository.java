package com.itvdn.persistence.dao.repositories;

import com.itvdn.persistence.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findEmployeeByName(String name);
    List<Employee> findEmployeeByNameAndPosition(String name, String position);
}
