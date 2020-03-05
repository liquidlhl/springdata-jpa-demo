package com.st.springdatajpa.manyToMany;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Person
 *
 * @author:
 * @create: 2019-11-17
 **/
@Entity
@Table(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString(exclude = {"authorities"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String userName;

    //双向：关系维护端:可以操作中间表
    @ManyToMany
    @JoinTable(name="t_user_authority",joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="authority_id"))
    //关系维护端，负责多对多关系的绑定和解除
    //@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Player)
    //inverseJoinColumns指定外键的名字，要关联的关系被维护端(Game)
    //其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为player_game
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即player_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即game_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    @JSONField(serialize = false)
    private Set<Authority> authorities;


//    @JoinColumn
//    private Integer orderId;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(updatable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    private Date modifyTime;

}

