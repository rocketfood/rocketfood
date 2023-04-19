package com.spring.rocketfood.mypage.coupon.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

public interface CouponService {
	public List<CouponVO> listUsableCoupon(String member_id) throws Exception;
	public int downloadCoupon(MyCouponVO myCouponVO) throws Exception;
	public int updateMyCoupon(MyCouponVO myCouponVO) throws Exception;
}
