package app.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.ValueExpression;

import app.entity.Student;
import app.service.Student1Service;

// 分页相关
@Controller
public class PagingController extends CrudController<Student, Student1Service> {

	
	// 传统方式分页
	@RequestMapping("/listPage2_backuser.do")
	public  @ResponseBody GridResult listPage2() {
		ExpressionQuery query = new ExpressionQuery();
		query.setPageIndex(1) // 第一页
			.setPageSize(20); // 每页显示20条
		
		// 添加排序
		query.addSort("regist_date","desc");
		
		return this.query(query);
	}
	
	// 类似mysql形式分页
	@RequestMapping("/listPage_backuser.do")
	public  @ResponseBody GridResult listPage() {
		ExpressionQuery query = new ExpressionQuery();
		// 第一页
		query.setStart(0);
		// 每页显示10条
		query.setLimit(10);
		
		// 筛选性别
		query.add(new ValueExpression("gender",1));
		// 添加排序
		query.addSort("regist_date","desc");
		
		return this.query(query);
	}
	
}
