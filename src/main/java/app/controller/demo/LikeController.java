package app.controller.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.expression.Expression;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.subexpression.LikeDoubleExpression;
import com.wiserun.common.expression.subexpression.LikeRightExpression;
import com.wiserun.common.expression.subexpression.ValueExpression;

import app.entity.SearchStudentEntity;
import app.entity.Student;
import app.service.Student1Service;


// like查询
@Controller
public class LikeController extends CrudController<Student, Student1Service> {

	
	// 查询手机号开头为133的学生
	@RequestMapping("/likeRightExpression_backuser.do")
	public  @ResponseBody GridResult likeRightExpression() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new LikeRightExpression("mobile", "133"));
		return this.query(query);
	}
	
	// 查询手机号尾号567的学生
	@RequestMapping("/likeLeftExpression_backuser.do")
	public  @ResponseBody GridResult likeLeftExpression(@RequestBody SearchStudentEntity searchStudentEntity) {
		List<Expression> conditions = new ArrayList<Expression>();
		boolean flag = true;
		// 根据条件判断添加条件
		// 如果true进行模糊查询,否则进行全值查询
		if(flag) { 
			conditions.add(new LikeDoubleExpression("name", "zhangsan"));
		}else{
			conditions.add(new ValueExpression("name", "zhangsan"));
		}
		return this.query(searchStudentEntity,conditions);
	}
	
	// 查询地区为开封的学生
	@RequestMapping("/likeDoubleExpression_backuser.do")
	public  @ResponseBody GridResult likeDoubleExpression() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new LikeDoubleExpression("address", "开封"));
		return this.query(query);
	}
	
}
