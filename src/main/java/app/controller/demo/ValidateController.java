package app.controller.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.MessageResult;
import com.wiserun.common.ValidateHolder;
import com.wiserun.common.controller.BaseController;
import com.wiserun.common.util.ValidateUtil;

import app.entity.Student;

// 验证
@Controller
public class ValidateController extends BaseController {

	@RequestMapping("/validateStu_backuser.do")
	public @ResponseBody ValidateHolder validateStu(@RequestBody Student student) {
		ValidateHolder result = ValidateUtil.validate(student);
		return result;
	}
	
	@RequestMapping("/validateStu2_backuser.do")
	public @ResponseBody MessageResult validateStu2(@RequestBody Student student) {
		ValidateHolder result = ValidateUtil.validate(student);
		List<String> errors = result.getErrors();
		return this.error("error", errors);
	}

}
