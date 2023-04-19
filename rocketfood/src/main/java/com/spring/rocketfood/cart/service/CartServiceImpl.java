package com.spring.rocketfood.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.cart.dao.CartDAO;
import com.spring.rocketfood.cart.vo.CartGoodsVO;
import com.spring.rocketfood.cart.vo.CartVO;
import com.spring.rocketfood.goods.vo.GoodsVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl  implements CartService {
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private CartVO cartVO;
	
	public Map<String ,Object> myCartList(CartVO cartVO) throws Exception{
		Map<String, Object> cartMap = new HashMap<String , Object>();		
		//cart_goods 테이블에 저장된 데이터를 리스트로 받아옴
		List<CartGoodsVO> myCartGoodsList = cartDAO.selectCartList(cartVO);
		
		//cart_goods 테이블에 해당 회원이 저장한 데이터가 있을 시에만 상품 리스트를 조회한다.
		if(myCartGoodsList.size() != 0 && myCartGoodsList != null) {						
			List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartGoodsList);
			cartMap.put("myCartGoodsList", myCartGoodsList);
			cartMap.put("myGoodsList", myGoodsList);
		}

		int cart_id = 0;
		if(cartVO.getMember_id() != null && cartVO.getMember_id() != "") {
			cart_id = cartDAO.findCartId((String)cartVO.getMember_id());
		}
		//delete 및 update를 위해 cart_id를 함께 넣어줌
		cartMap.put("myCart_id", cart_id);
		return cartMap;
	}
	
	//장바구니에 해당 상품이 존재하는지 확인
	public boolean findCartGoods(Map<String, Object> cartGoodsMap) throws Exception{
		 return cartDAO.selectCountInCart(cartGoodsMap);
		
	}
	
	// 장바구니 정보 불러오기
	public CartVO findCartInfo(int cart_id) throws Exception {
		cartVO = cartDAO.selectCartInfo(cart_id);
		return cartVO;
	}
	
	//장바구니에 상품 추가하기
	public void addGoodsInCart(CartGoodsVO cartGoodsVO) throws Exception{
		cartDAO.insertGoodsInCart(cartGoodsVO);
	}
	
	//장바구니 수량 변경
	public boolean modifyCartQty(CartGoodsVO cartGoodsVO) throws Exception{
		//리턴할 결과 값
		boolean result = true;
		//수량 업데이트 실행 및 결과 값 저장
		int update_success = cartDAO.updateCartGoodsQty(cartGoodsVO);
		if(update_success != -1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	// 장바구니 상품 삭제
	public void removeCartGoods(Map<String, Object> cartGoodsMap) throws Exception {
		cartDAO.deleteCartGoods(cartGoodsMap);
	}
	
	//장바구니 아이디 찾기
	public int findCartId(String member_id) throws Exception {
		return cartDAO.findCartId(member_id);
	}
	
}
