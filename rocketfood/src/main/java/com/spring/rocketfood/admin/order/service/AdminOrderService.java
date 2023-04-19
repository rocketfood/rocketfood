package com.spring.rocketfood.admin.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.order.vo.OrderVO;

public interface AdminOrderService {
	public List<OrderVO>listNewOrder(Map condMap) throws Exception;
	public void  modifyDeliveryState(Map deliveryMap) throws Exception;
	public Map orderDetail(int order_id) throws Exception;
}
