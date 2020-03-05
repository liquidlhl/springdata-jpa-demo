package com.st.springdatajpa.manyToMany;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Authority
 *
 * @author:
 * @create: 2020-03-05
 **/
@Table(name="t_authority")
@Entity
@Setter
@Getter
@ToString(exclude = {"users"})
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;//权限名

    //双向：关系被维护端:不能操作中间表
    @ManyToMany(mappedBy="authorities")
    @JSONField(serialize = false)
    private Set<User> users;


}
