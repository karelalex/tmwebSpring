package ru.karelin.tmwebspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmwebspring.entity.Project;
import ru.karelin.tmwebspring.entity.Task;
import ru.karelin.tmwebspring.entity.User;
import ru.karelin.tmwebspring.enumeration.Status;
import ru.karelin.tmwebspring.repository.*;
import ru.karelin.tmwebspring.util.MD5Generator;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @PostConstruct
    void init() {
        //register user
        User user = new User();
        user.setLogin("sk");
        user.setPassHash(MD5Generator.generate("skPass"));
        user.setUsername("Саня");
        userRepository.save(user);
        for (int i = 0; i < 10; i++) {
            Project p = new Project();
            p.setName("Проект " + i);
            p.setDescription(p.getName() + " очень интересное описание");
            p.setStartingDate(new Date());
            p.setFinishDate(new Date());
            p.setStatus(Status.PLANNED);
            p.setUserId(user.getId());
            projectRepository.save(p);
            for (int j = 0; j < 9; j++) {
                Task t = new Task();
                t.setName("Задача" + j);
                t.setDescription(t.getName() + " классное описание");
                t.setStartingDate(new Date());
                t.setFinishDate(new Date());
                t.setStatus(Status.PLANNED);
                t.setUserId(user.getId());
                t.setProjectId(p.getId());
                taskRepository.save(t);
            }
        }
        User user2 = new User();
        user2.setLogin("bb");
        user2.setPassHash(MD5Generator.generate("bbPass"));
        user2.setUsername("Боря");
        userRepository.save(user2);
        for (int i = 0; i < 10; i++) {
            Project p = new Project();
            p.setName("Проектус " + i);
            p.setDescription(p.getName() + " так себе описание");
            p.setStartingDate(new Date());
            p.setFinishDate(new Date());
            p.setStatus(Status.PLANNED);
            p.setUserId(user2.getId());
            projectRepository.save(p);
            for (int j = 0; j < 3; j++) {
                Task t = new Task();
                t.setName("Задачаща" + j);
                t.setDescription(t.getName() + " классное и хреновое описание");
                t.setStartingDate(new Date());
                t.setFinishDate(new Date());
                t.setStatus(Status.PLANNED);
                t.setUserId(user2.getId());
                t.setProjectId(p.getId());
                taskRepository.save(t);
            }
        }
    }
}

