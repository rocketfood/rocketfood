package com.spring.rocketfood.mypage.coupon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

@Repository("couponDAO")
public class CouponDAOImpl implements CouponDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//사용 가능한 쿠폰 목록 불러오기
	@Override
	public List<CouponVO> listUsableCoupon(String member_id) throws DataAccessException {
		List<CouponVO> usableCouponList = sqlSession.selectList("mapper.mypage.selectMyUsableCoupon", member_id);
		return usableCouponList;
	}
	
	//쿠폰 다운받기
	@Override
	public int downloadCoupon(MyCouponVO myCouponVO) throws DataAccessException {
		return sqlSession.insert("mapper.mypage.downloadCoupon", myCouponVO);
	}
	
	
	//쿠폰 상태 업데이트
	@Override
	public int updateMyCoupon(MyCouponVO myCouponVO) throws DataAccessException {
		return sqlSession.update("mapper.mypage.updateCoupon", myCouponVO);
		
	}
}
