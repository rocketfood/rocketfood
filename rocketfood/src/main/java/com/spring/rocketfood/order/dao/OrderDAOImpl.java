package com.spring.rocketfood.order.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 주문서 불러오기
	@Override
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException{
		List<OrderVO> orderGoodsList = new ArrayList<OrderVO>();
		orderGoodsList = (ArrayList) sqlSession.selectList("mapper.order.selectMyOrderList", orderVO);
		return orderGoodsList;
	}
	
	// 새 주문 추가
	@Override
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException{
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			sqlSession.insert("mapper.order.insertNewOrder",orderVO);
		}
	}	
	
	//주문 내역 보기
	@Override
	public OrderVO findMyOrder(String order_id) throws DataAccessException{
		OrderVO orderVO = (OrderVO)sqlSession.selectOne("mapper.order.selectMyOrder",order_id);		
		return orderVO;
	}
		
	//주문 후 장바구니에서 삭제하기
	public void removeGoodsFromCart(List<String> orderCartGoodsList)throws DataAccessException {
		Map<String, Integer> cartGoodsMap = new HashMap<String, Integer>();
		
		for(int i=0; i<orderCartGoodsList.size();i++){
			String[] item = orderCartGoodsList.get(i).split(":");
			int cart_id = Integer.parseInt(item[0]);
			int goods_id = Integer.parseInt(item[1]);
			
			cartGoodsMap.put("cart_id", cart_id);
			cartGoodsMap.put("goods_id", goods_id);			
			sqlSession.delete("mapper.order.deleteGoodsFromCart", cartGoodsMap);
		}
	}	

}

