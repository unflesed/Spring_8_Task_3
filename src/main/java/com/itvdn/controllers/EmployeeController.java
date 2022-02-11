package com.itvdn.controllers;

import com.itvdn.persistence.dao.services.interfaces.EmployeeSimpleService;
import com.itvdn.persistence.model.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Log LOG = LogFactory.getLog(EmployeeController.class);
    @Autowired
    private EmployeeSimpleService employeeSimpleService;

    @Autowired
    private CacheManager cacheManager;


    @PostMapping(value = "/add")
    public String addNewEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getParameter("name"));
        employee.setPosition(request.getParameter("position"));
        employee.setPhone(request.getParameter("phone"));
        LOG.info("New employee with id " + employeeSimpleService.addEmployee(employee).getId()
        + " was added.");
        return "redirect:/employee/all";
    }

    @GetMapping(value = "/remove/{id}")
    public ModelAndView deleteEmployee(@PathVariable long id, ModelAndView modelAndView) throws InterruptedException {
        employeeSimpleService.removeById(id);
        modelAndView.addObject("employees", employeeSimpleService.findAll());
        modelAndView.setViewName("redirect:/employee/all");
        return modelAndView;
    }
    @PostMapping(value = "/findByName")
    public ModelAndView findEmployeeByName(@RequestParam("name") String name, ModelAndView modelAndView) {

        modelAndView.addObject("employees", employeeSimpleService.findEmployeeByName(name));

        modelAndView.setViewName("/employee/search-results");
        return modelAndView;
    }
    @PostMapping(value = "/findByNameAndPosition")
    public ModelAndView findEmployeeByNameAndPosition(@RequestParam("name") String name,
                                                      @RequestParam("position") String position,
                                                      ModelAndView modelAndView) {
        modelAndView.addObject("employees", employeeSimpleService.findEmployeeByNameAndPosition(
                name, position
        ));
        modelAndView.setViewName("/employee/search-results");
        return modelAndView;
    }

    // для обновления данных используйте аннотацию scheduling
    @GetMapping(value = "/all")
    public ModelAndView listAllEmployee(ModelAndView modelAndView) throws InterruptedException {
        modelAndView.addObject("employees", employeeSimpleService.findAll());
        modelAndView.setViewName("/employee/employees");
        return modelAndView;
    }

    @GetMapping("/clearCache")
    public String clearCache() {
        for (String name : cacheManager.getCacheNames()) {
            System.out.println(name);
            System.out.println(((ConcurrentMapCache)cacheManager.getCache(name)).getNativeCache().entrySet());
        }
        employeeSimpleService.clearCache();
        return "/employee/employees";
    }

    @GetMapping("/info/{id}")
    public ModelAndView getEmployeeInfo(@PathVariable long id, ModelAndView modelAndView) throws InterruptedException {
        Employee empl = employeeSimpleService.findById(id).get();

//        Employee emplFromCash = Objects.requireNonNull(cacheManager.getCache("empl")).get(id, Employee.class);

//        Employee empl = emplFromCash != null ? emplFromCash : employeeSimpleService.findById(id).get();

        List<Employee> employees = new ArrayList<>();
        employees.add(empl);
        modelAndView.addObject("employees", employees);
        modelAndView.setViewName("/employee/search-results");
        return modelAndView;
    }

}
