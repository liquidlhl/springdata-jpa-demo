package com.st.springdatajpa;

import com.st.springdatajpa.manyToMany.Authority;
import com.st.springdatajpa.manyToMany.AuthorityJpa;
import com.st.springdatajpa.manyToMany.User;
import com.st.springdatajpa.manyToMany.UserJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Set;

/**
 * TestPerson
 *
 * @author:
 * @create: 2019-11-17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringdataJpaApplication.class)
public class TestManyToMany {

    @Autowired
    UserJpa userJpa;
    @Autowired
    AuthorityJpa authorityJpa;

    @Test
    public void testSave() {
        User user1 = new User();
        user1.setId(1);
        user1.setUserName("user1");
        userJpa.save(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setUserName("user2");
        userJpa.save(user2);

        Authority authority1 = new Authority();
        authority1.setId(1);
        authority1.setName("insert");
        authorityJpa.save(authority1);

        Authority authority2 = new Authority();
        authority2.setId(2);
        authority2.setName("update");
        authorityJpa.save(authority2);

        System.out.println(userJpa.findAll());
        System.out.println(authorityJpa.findAll());
    }


    @Test
    @Transactional
    @Rollback(false)
    public void testSetAuthority() {
        User user1 = userJpa.findById(1).get();
        User user2 = userJpa.findById(2).get();

        Authority authority1 = authorityJpa.findById(1).get();
        Authority authority2 = authorityJpa.findById(2).get();

        user1.getAuthorities().add(authority1);
        userJpa.save(user1);

        user2.getAuthorities().add(authority1);
        user2.getAuthorities().add(authority2);
        userJpa.save(user2);


    }


    //　　3、多对多关系的绑定由关系维护端来完成，即由Player.setGames(games)来绑定多对多的关系。关系被维护端不能绑定关系，即Game不能绑定关系。
    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testQuery() {
        Authority authority1 = authorityJpa.findById(1).get();
        Set<User> users1 = authority1.getUsers();
        System.out.println("authority1 users = " + users1);

        Authority authority2 = authorityJpa.findById(2).get();
        Set<User> users2 = authority2.getUsers();
        System.out.println("authority2 users = " + users2);

        User user1 = userJpa.findById(1).get();
        Set<Authority> authorities1 = user1.getAuthorities();
        System.out.println("user1 authorities = " + authorities1);

        User user2 = userJpa.findById(2).get();
        Set<Authority> authorities2 = user2.getAuthorities();
        System.out.println("user2 authorities = " + authorities2);

    }


    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testDelete1() {
        List<Authority> all = authorityJpa.findAll();
        all.forEach(System.out::println);
        //不能直接删除关系被维护端 需要先由关系维护段接触关系后在删除维护端
        authorityJpa.delete(all.get(0));
        List<Authority> all1 = authorityJpa.findAll();
        all1.forEach(System.out::println);

    }

    //　　4、多对多关系的解除由关系维护端来完成，即由Player.getGames().remove(game)来解除多对多的关系。关系被维护端不能解除关系，即Game不能解除关系。
    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testDeleteAuthorityRight() {
        User user1 = userJpa.findById(1).get();
        Set<Authority> authorities = user1.getAuthorities();
        Authority authority1 = authorityJpa.findById(1).get();
        authorities.remove(authority1);
        userJpa.save(user1);
        user1 = userJpa.findById(1).get();
        System.out.println("user1 = " + user1.getAuthorities());

    }

    //　　5、如果Player和Game已经绑定了多对多的关系，那么不能直接删除Game，需要由Player解除关系后，才能删除Game。
    // 但是可以直接删除Player，因为Player是关系维护端，删除Player时，会先解除Player和Game的关系，再删除Player。
    @Test
    @Transactional
    //取消自动回滚
    @Rollback(false)
    public void testDeleteUser() {
        userJpa.deleteById(1);
        //可以在数据库看到 中间表的数据也删除了 即删除user user与authority也自动解除了
    }





}
