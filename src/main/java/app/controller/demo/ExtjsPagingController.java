package app.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.SearchPojo;
import com.wiserun.common.controller.SearchController;

import app.entity.ExtStudentSearch;
import app.entity.Student;
import app.service.Student1Service;

/**
 * 支持Extjs查询
 * @author hc.tang
 *
 */
@Controller
public class ExtjsPagingController extends SearchController<Student, Student1Service> {
	
	// ${ctx}/listExtjsPagging.do?start=0&limit=10
	@RequestMapping("listExtjsPagging_backuser.do")
	public  @ResponseBody GridResult listExtjsPagging(@RequestBody SearchPojo searchEntity) {
		return this.query(searchEntity);
	}
	
	// ${ctx}/listExtjsPagging2.do?start=3&limit=5&schName=jim
	@RequestMapping("listExtjsPagging2_backuser.do")
	public  @ResponseBody GridResult listExtjsPagging2(@RequestBody ExtStudentSearch searchEntity) {
		return this.query(searchEntity);
	}
}
