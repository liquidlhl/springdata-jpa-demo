package com.st.springdatajpa.oneToMany;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserJpa
 *
 * @author:
 * @create: 2019-11-20
 **/

public interface EmployeeJpa extends JpaRepository<Employee,Integer>{

}
