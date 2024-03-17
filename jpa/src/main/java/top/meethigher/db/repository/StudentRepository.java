package top.meethigher.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.meethigher.db.Student;

public interface StudentRepository extends JpaRepository<Student,String> {
}
