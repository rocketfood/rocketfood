package com.spring.rocketfood.mypage.coupon.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

public interface CouponDAO {
	public List<CouponVO> listUsableCoupon(String member_id) throws DataAccessException;
	public int downloadCoupon(MyCouponVO myCouponVO) throws DataAccessException;
	public int updateMyCoupon(MyCouponVO myCouponVO) throws DataAccessException;
}
