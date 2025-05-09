package top.meethigher.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import top.meethigher.db.Student;
import top.meethigher.db.repository.StudentRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Slf4j
public class TestRunner implements CommandLineRunner {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private EntityManager entityManager;

    private Timer timer = new Timer(false);

    @Override
    public void run(String... args) throws Exception {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Optional<Student> optional = studentRepository.findById("1");
                    optional.ifPresent(student -> log.info("repository get: {}", student.getStudentName()));
                    TypedQuery<Student> query = entityManager.createQuery("SELECT u FROM Student u WHERE u.studentId = :username", Student.class);
                    query.setParameter("username", "1");
                    List<Student> resultList = query.getResultList();
                    if (!ObjectUtils.isEmpty(resultList)) {
                        log.info("entityManager get: {}", resultList.get(0).getStudentName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10L, 5000L);

    }
}
