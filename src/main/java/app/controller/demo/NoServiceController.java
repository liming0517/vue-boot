package app.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wiserun.common.controller.SearchController;
import com.wiserun.common.expression.ExpressionQuery;

import app.dao.StudentDao;
import app.entity.Student;

@Controller
public class NoServiceController extends SearchController<Student, StudentDao> // 此处注入的是DAO
{

	@RequestMapping("listNoService_backuser.do")
	public  Object listNoService() {
		ExpressionQuery query = new ExpressionQuery();
		return this.getService().find(query);
	}
	
}
