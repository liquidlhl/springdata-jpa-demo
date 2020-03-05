package com.st.springdatajpa.oneToMany;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Employee
 *
 * @author:
 * @create: 2020-03-03
 **/
@Table(name = "t_employee")
@Entity
@Setter
@Getter
@ToString(exclude = {"department"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String name;
    //　　在JPA规范中，一对多的双向关系由多端(Employee)来维护。就是说多端(Employee)为关系维护端，负责关系的增删改查。一端(department)则为关系被维护端，不能维护关系。
    //关系护端即负责外键记录的更新，一方为被维护端即没有权力更新外键记录。

    //延迟加载
    @ManyToOne(fetch = FetchType.LAZY)//可选属性optional=false,表示department不能为空
    @JoinColumn(name = "department_id")
    //fastjson的注解 设置不序列化的字段
    @JSONField(serialize = false)
    private Department department;
//
//    //一下注解只获取外键id，不获取内容
//    @Column(name = "department_id", updatable = false, insertable = false)
//    private Long departmentId;//用户id
//

}
