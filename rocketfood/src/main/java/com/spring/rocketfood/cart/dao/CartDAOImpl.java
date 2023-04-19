 package com.spring.rocketfood.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.cart.vo.CartGoodsVO;
import com.spring.rocketfood.cart.vo.CartVO;
import com.spring.rocketfood.goods.vo.GoodsVO;

@Repository("cartDAO")
public class CartDAOImpl  implements  CartDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 장바구니 정보 불러오기 
	@Override
	public List<CartGoodsVO> selectCartList(CartVO cartVO) throws DataAccessException {
		
		List<CartGoodsVO> cartList = (List) sqlSession.selectList("mapper.cart.selectCartList", cartVO);
		return cartList;
	}
	
	// 장바구니 정보로 해당 상품 정보를 불러오기
	@Override
	public List<GoodsVO> selectGoodsList(List<CartGoodsVO> cartList) throws DataAccessException {		
		List<GoodsVO> myGoodsList = null;
		myGoodsList = sqlSession.selectList("mapper.cart.selectGoodsList", cartList);

		return myGoodsList;
		
	}
	
	//회원 아이디와 카트아이디가 세션에 저장된 회원 정보와 일치하는지 확인용
	@Override
	public CartVO selectCartInfo(int cart_id) throws DataAccessException {
		CartVO cartVO = sqlSession.selectOne("mapper.cart.selectCart", cart_id);
		return cartVO;
	}
	
	//회원의 장바구니에 존재하는 해당 상품의 갯수를 불러오기
	@Override
	public boolean selectCountInCart(Map<String, Object> cartGoodsMap) throws DataAccessException {
		String  result = sqlSession.selectOne("mapper.cart.selectCountInCart", cartGoodsMap);
		System.out.println("selectCountInCart" + result);
		return Boolean.parseBoolean(result);
	}

	//장바구니에 상품 추가
	@Override
	public void insertGoodsInCart(CartGoodsVO cartGoodsVO) throws DataAccessException{
		sqlSession.insert("mapper.cart.insertGoodsInCart", cartGoodsVO);
	}
	
	//장바구니에 상품 수량 변경
	@Override
	public int updateCartGoodsQty(CartGoodsVO cartGoodsVO) throws DataAccessException{
		return sqlSession.update("mapper.cart.updateCartGoodsQty", cartGoodsVO);
		
	}
	
	//장바구니에 상품 삭제
	@Override
	public void deleteCartGoods(Map<String, Object> cartGoodsMap) throws DataAccessException{
		sqlSession.delete("mapper.cart.deleteCartGoods", cartGoodsMap);
	}
	
	// 회원에게 배정된 장바구니가 있는지 확인 및 회원 아이디로 cart_id 찾기
	@Override
	public int findCartId(String member_id) throws DataAccessException {		
		int result_id = sqlSession.selectOne("mapper.cart.selectCartByMemberId", member_id);
		return result_id;
	}

}
