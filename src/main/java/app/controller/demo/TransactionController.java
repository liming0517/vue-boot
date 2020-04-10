package app.controller.demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.DurcException;
import com.wiserun.common.MessageResult;
import com.wiserun.common.controller.CrudController;

import app.entity.Student;
import app.service.Student1Service;

// 事务处理
@Controller
public class TransactionController extends CrudController<Student, Student1Service>{

	@Autowired
	private TransactionTemplate transactionTemplate;
	
	private Logger logger = Logger.getLogger(getClass());
	
	// ${ctx}/updateTran.do
	@RequestMapping("/updateTran_backuser.do")
	public  @ResponseBody MessageResult updateTran() {
		
		transactionTemplate.execute(new TransactionCallback<Student>() {

			@SuppressWarnings("unused")
			@Override
			public Student doInTransaction(TransactionStatus arg0) {
				
				try{
					Student student = getService().get(1);
					student.setName("李四3");
					getService().update(student);
					int i = 1/0; // 模拟出错
				}catch(Exception e) {
					logger.error(e.getMessage());
					arg0.setRollbackOnly();
					throw new DurcException("修改出错,事务回滚");
				}
				
				return null;
			}
		});
		
		return this.success();
	}
	
	
}
