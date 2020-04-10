package app.service;

import app.dao.OrderInfoDao;
import app.entity.OrderInfo;
import org.springframework.stereotype.Service;

import com.wiserun.common.service.CrudService;

@Service
public class OrderInfoService extends CrudService<OrderInfo, OrderInfoDao> {

}