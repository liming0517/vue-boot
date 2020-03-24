package app.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.MessageResult;
import com.wiserun.common.controller.CrudController;
import com.wiserun.common.util.DateUtil;

import app.entity.SearchStudentEntity;
import app.entity.Student;
import app.service.Student1Service;

/**
 * 接收时间参数
 * @author hc.tang
 * 2013年12月10日
 *
 */
@Controller
public class DateFormatController extends CrudController<Student, Student1Service> {
	
	/**
	 * 重写父类方法,使得时间参数精确到时分秒
	 * 默认不重写的话默认只精确到天,即yyyy-MM-dd
	 */
	@Override
	protected String getDateFormatPattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
	// ${ctx}/listDateFormat_backuser.do?&schBirthday=2011-12-02%2012:11:00
	@RequestMapping(value="/listDateFormat_backuser.do",method=RequestMethod.GET)
	public @ResponseBody MessageResult listDateFormat(@RequestBody SearchStudentEntity searchStudentEntity) {
		return this.success(DateUtil.getYmdhmsFormat(searchStudentEntity.getSchBirthday())); 
	}
	
	
	
}
