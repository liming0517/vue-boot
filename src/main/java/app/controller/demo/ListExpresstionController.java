package app.controller.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.controller.SearchController;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.LikeRightExpression;
import com.wiserun.common.expression.subexpression.ValueExpression;

import app.entity.SearchStudentEntity;
import app.entity.Student;
import app.service.Student1Service;

@Controller
public class ListExpresstionController extends
		SearchController<Student, Student1Service> {

	// 查询名为JIM并且手机号133开头的学生
	@RequestMapping("/listExpresstions_backuser.do")
	public  @ResponseBody GridResult listExpresstions(@RequestBody SearchStudentEntity entity) {
		// 追加条件
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("name", "JIM"));
		expressions.add(new LikeRightExpression("mobile", "133"));
		
		return this.query(entity,expressions);
	}
	
	// 查询名为JIM并且手机号133开头的学生,方式2
	@RequestMapping("/listExpresstions2_backuser.do")
	public  @ResponseBody GridResult listExpresstions2() {
		ExpressionQuery query = new ExpressionQuery();
		// 追加条件
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.add(new ValueExpression("name", "JIM"));
		expressions.add(new LikeRightExpression("mobile", "133"));
		
		query.addAll(expressions);
		
		return this.query(query);
	}
}
