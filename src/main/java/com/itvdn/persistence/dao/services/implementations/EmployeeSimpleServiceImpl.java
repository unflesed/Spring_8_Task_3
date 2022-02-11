package com.itvdn.persistence.dao.services.implementations;

import com.google.common.collect.Lists;
import com.itvdn.persistence.dao.repositories.EmployeeRepository;
import com.itvdn.persistence.dao.services.interfaces.EmployeeSimpleService;
import com.itvdn.persistence.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSimpleServiceImpl implements EmployeeSimpleService {
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeSimpleService employeeSimpleService;

//    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @Cacheable("employees")
    @Override
    public List<Employee> findAll() throws InterruptedException {
        System.out.println("Start sleeping for 3 seconds");
        Thread.sleep(3000L);
        System.out.println("End sleeping for 3 seconds");
        return Lists.newArrayList(employeeRepository.findAll());
    }

    @CacheEvict("employees")
    @Override
    public void clearCache() {
        System.out.println("employees cache cleared");
    }

    @Override
    @CachePut(value = "empl", condition = "#result != null", key = "#result.generatedKey()")
    public Optional<Employee> findById(long id) throws InterruptedException {
//        employeeSimpleService.findAll();
        System.out.println("Getting employee from repo");
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void removeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public List<Employee> findEmployeeByNameAndPosition(String name, String position) {
        return employeeRepository.findEmployeeByNameAndPosition(name, position);
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void throwException() {
        throw new UnsupportedOperationException("Not support yet.");
    }

}
