package com.st.springdatajpa.oneToMany;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * dept
 *
 * @author:
 * @create: 2020-03-03
 **/
@Table(name = "t_department")
@Entity
@Setter
@Getter
@ToString(exclude = {"employees"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String name;

    //@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true,mappedBy="user",)//此时移除department里的某一个employees会级联删除该address 太危险 不建议用
    //PERSIST
    //级联持久化（保存）操作（持久保存拥有方实体时，也会持久保存该实体的所有相关数据。）
    //MERGE
    //级联更新（合并）操作。当Department中的数据改变，会相应地更新department中的数据。
/*

    CascadeType.PERSIST

    级联新增，保存父对象时会新建其中包含的子对象

    CascadeType.MERGE

    级联修改，保存父对象时会更新其中所包含的子对象数据
*/

//　　在JPA规范中，一对多的双向关系由多端(Employee)来维护。就是说多端(Employee)为关系维护端，负责关系的增删改查。一端(department)则为关系被维护端，不能维护关系。
    //fastjson的注解 设置不序列化的字段
    @JSONField(serialize = false)
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="department"中的department是Employee中的department属性
    @OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Employee> employees;

}
