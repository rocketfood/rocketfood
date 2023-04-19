package com.spring.rocketfood.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.spring.rocketfood.order.dao.OrderDAO;
import com.spring.rocketfood.order.vo.OrderVO;


@Service("orderService")
@Transactional(propagation=Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	//주문 상품 리스트 가져오기
	@Override
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception{
		List<OrderVO> orderGoodsList;
		orderGoodsList=orderDAO.listMyOrderGoods(orderVO);
		return orderGoodsList;
	}
	
	// 주문 추가하기
	@Override
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception{
		orderDAO.insertNewOrder(myOrderList);
	}	
	
	// 주문내역 상세 보기
	@Override
	public OrderVO findMyOrder(String order_id) throws Exception{
		return orderDAO.findMyOrder(order_id);
	}

	// 주문 한 상품 장바구니에서 삭제 
	@Override
	public void deleteGoodsFromCart(List<String> orderCartGoodsList)throws Exception {
		orderDAO.removeGoodsFromCart(orderCartGoodsList);
	}
}
