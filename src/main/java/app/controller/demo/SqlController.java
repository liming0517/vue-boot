package app.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.SqlExpression;

import app.entity.Student;
import app.service.Student1Service;

// 使用自定义SQL语句拼接查询条件
@Controller
public class SqlController  extends CrudController<Student, Student1Service> {

	// ${ctx}/likeSqlExpression.do
	// 查询性别为女或id为19,20的学生
	// SQL:SELECT * FROM student t WHERE (gender=0 or id in (19,20)) ORDER BY ID desc LIMIT 0,10
	@RequestMapping("/likeSqlExpression_backuser.do")
	public  @ResponseBody GridResult likeSqlExpression() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new SqlExpression("gender=0 or id in (19,20)"));
		return this.query(query);
	}
}
