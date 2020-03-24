package app.service;

import org.springframework.stereotype.Service;

import com.wiserun.common.service.CrudService;

import app.dao.StudentDao;
import app.entity.Student;


@Service
public class Student1Service extends CrudService<Student, StudentDao> {
}
