package app.controller.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.MessageResult;
import com.wiserun.common.controller.SearchController;

import app.entity.Student;
import app.service.Student1Service;

@Controller
public class ExceptionController extends
		SearchController<Student, Student1Service> {

	// 返回异常信息
	@SuppressWarnings("unused")
	@RequestMapping("/exceptionStudent_backuser.do")
	public @ResponseBody MessageResult addStudent(@RequestBody Student student) {
		int i = 1 / 0; // 模拟出错
		return this.success();
	}
	
	@Override
	protected MessageResult exceptionHandler(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		// 默认实现方式
		//return super.exceptionHandler(request, response, e);
		// 自定义错误返回
		return this.error("出错:" + e.getMessage());
	}
}
