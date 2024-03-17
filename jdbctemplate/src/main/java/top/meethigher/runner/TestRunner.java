package top.meethigher.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Slf4j
public class TestRunner implements CommandLineRunner {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private Timer timer = new Timer();

    @Override
    public void run(String... args) throws Exception {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String s = jdbcTemplate.queryForObject("select student_name from student where student_id = ?", String.class, "1");
                log.info(s);
            }
        }, 50, 5000);
    }
}
