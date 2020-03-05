package com.st.springdatajpa;

import com.st.springdatajpa.oneToMany.Department;
import com.st.springdatajpa.oneToMany.DepartmentJpa;
import com.st.springdatajpa.oneToMany.Employee;
import com.st.springdatajpa.oneToMany.EmployeeJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 测试双向一对多新增 删除,级联新增 更新
 *
 * @author:
 * @create: 2019-11-17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringdataJpaApplication.class)
public class TestOneToMany {

    @Autowired
    EmployeeJpa employeeJpa;

    @Autowired
    DepartmentJpa departmentJpa;

    /*
     * 测试保存数据 关系的维护端是employee 被维护端是department
     *
     * @return void
     */
    @Test
    public void testSave() {
        Department department = new Department();
        department.setName("dept1");

        Department dept1 = departmentJpa.save(department);
        System.out.println("save = " + dept1);

        Employee employee1 = new Employee();
        employee1.setName("employ1");
        employee1.setDepartment(dept1);

        Employee employee11 = employeeJpa.save(employee1);
        System.out.println("employee11 = " + employee11);

        Employee employee2 = new Employee();
        employee2.setName("employ2");
        employee2.setDepartment(dept1);
        Employee employee22 = employeeJpa.save(employee2);
        System.out.println("employee22 = " + employee22);


    }

    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testQuery() {
        Department department = departmentJpa.findById(1).orElse(null);
        System.out.println("department = " + department);

        employeeJpa.findAll().stream().forEach(System.out::println);

    }


    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testDelDept() {
        //外键约束 不能直接删除部门
     /*
        employeeJpa.findAll().stream().forEach(System.out::println);
        departmentJpa.deleteById(1);
        employeeJpa.findAll().stream().forEach(System.out::println);
*/
//正确姿势 先解除绑定关系 在删除部门
        System.out.println("绑定关系前的employee");
        employeeJpa.findAll().stream().forEach(System.out::println);
        List<Employee> all = employeeJpa.findAll();
        all.stream().forEach(e -> e.setDepartment(null));
        employeeJpa.saveAll(all);
        System.out.println("解除关系后的employee");
        employeeJpa.findAll().stream().forEach(System.out::println);

        System.out.println("查询删除前的所有department");
        departmentJpa.findAll().stream().forEach(System.out::println);
        System.out.println("删除department");
        departmentJpa.deleteById(1);
        System.out.println("查询删除后的所有department");
        departmentJpa.findAll().stream().forEach(System.out::println);


    }


    /*
     * 测试级联新增 更新操作
     *
     * @return void
     */
    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testCascade() {
        Department department = departmentJpa.findById(1).get();
        System.out.println("department = " + department);
//修改department中的employee 在保存department时 也将保存employee

//        List<Employee> employees = department.getEmployees();

//        employees.get(0).setName(employees.get(0).getName() + "_update11");
//        System.out.println("employees = " + employees);

//        department.setEmployees(employees);
//        department.setId(2);

        Department save = departmentJpa.save(department);
        System.out.println("save = " + save);

    }

}
