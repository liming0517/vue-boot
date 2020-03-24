package app.controller.demo;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.expression.ExpressionQuery;
import com.wiserun.common.expression.SqlContent;
import com.wiserun.common.expression.subexpression.LikeRightExpression;
import com.wiserun.common.expression.subexpression.ListExpression;
import com.wiserun.common.expression.subexpression.ValueExpression;

import app.entity.Student;
import app.service.Student1Service;

// 使用ExpressionQuery组装查询条件
@Controller
public class ExpressionQueryController extends CrudController<Student, Student1Service> {

	// 查询性别=1的学生
	@RequestMapping("/listValueExpression_backuser.do")
	public  @ResponseBody GridResult list() {
		
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("gender",1));
		return this.query(query);
	}
	
	// 查询性别为1,并且手机号有133的学生
	@RequestMapping("/listValueExpression2_backuser.do")
	public  @ResponseBody GridResult list2() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("gender", "=",1));
		query.add(new LikeRightExpression("mobile", "133"));
		return this.query(query);
	}
	
	// 查询性别为1,或者性别为0的学生,即查询全部
	@RequestMapping("/listValueExpression3_backuser.do")
	public  @ResponseBody GridResult list3() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ValueExpression("gender", "=",1));
		query.add(new ValueExpression(SqlContent.OR, "gender", "=", 0));
		return this.query(query);
	}
	
	// 查询主键ID为31,37的学生,即:id in(31,37)
	@RequestMapping("/listListExpression_backuser.do")
	public  @ResponseBody GridResult list4() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ListExpression("id", Arrays.asList(31,37)));
		return this.query(query);
	}
	
	// 如果有其它值需要传入到mybatis中去,则调用
	// query.addParam("mobile", "13398761567");
	// 在mybatis中#{param.mobile} 即可
	@RequestMapping("/listOtherExpression_backuser.do")
	public  @ResponseBody GridResult listOther() {
		ExpressionQuery query = new ExpressionQuery();
		query.add(new ListExpression("id", Arrays.asList(31,37)));
		query.addParam("mobile", "13398761567");
		return this.query(query);
	}
	
	// 通过主键查询某条记录
	// ${ctx}/getById_backuser.do
	@RequestMapping("/getById_backuser.do")
	public  @ResponseBody Student getById(){
		Student schStu = new Student();
		schStu.setId(31);
		
		//Student stu = this.getService().get(schStu); // 这样也可以
		Student stu = this.getService().get(31);
		
		return stu;
	}
}
