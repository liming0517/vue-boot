package app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wiserun.common.GridResult;
import com.wiserun.common.MessageResult;
import com.wiserun.common.controller.CrudController;

import app.entity.BuiOrderInfoSch;
import app.entity.OrderInfo;
import app.entity.OrderInfoSch;
import app.service.OrderInfoService;

@Controller
public class OrderInfoBuiController extends CrudController<OrderInfo, OrderInfoService> {

	@RequestMapping("/addBuiOrderInfo.do")
	public @ResponseBody MessageResult addOrderInfo(@RequestBody OrderInfo entity) {
		return this.save(entity);
	}

	@RequestMapping("/listBuiOrderInfo.do")
	public @ResponseBody GridResult listOrderInfo(@RequestBody BuiOrderInfoSch searchEntity) {
		return this.query(searchEntity);
	}
	
	@RequestMapping("/listEasyuiOrderInfo_backuser.do")
	public @ResponseBody GridResult listEasyuiOrderInfo_backuser(@RequestBody OrderInfoSch searchEntity,HttpServletResponse response) {
		// 支持跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		return this.query(searchEntity);
	}

	@RequestMapping("/updateBuiOrderInfo.do")
	public @ResponseBody MessageResult updateOrderInfo(@RequestBody OrderInfo entity) {
		return this.update(entity);
	}

	@RequestMapping("/delBuiOrderInfo.do")
	public @ResponseBody MessageResult delOrderInfo(@RequestBody OrderInfo entity) {
		return this.delete(entity);
	}

}
