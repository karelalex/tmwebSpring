package ru.karelin.tmwebspring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestClass {
    @Autowired
    UserRepository userRepository;

    @Test
    public void userRepoTest(){
        User user = new User();
        user.setLogin("papa");
        user.setUserName("popa");
        userRepository.save(user);
        User testUser = userRepository.findOneById(user.getId());
        Assert.assertEquals(user.getUserName(), testUser.getUserName());
    }
}
