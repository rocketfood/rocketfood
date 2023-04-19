package com.spring.rocketfood.mypage.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rocketfood.mypage.coupon.dao.CouponDAO;
import com.spring.rocketfood.mypage.coupon.vo.CouponVO;
import com.spring.rocketfood.mypage.coupon.vo.MyCouponVO;

@Service("couponService")
public class CouponServiceImpl implements CouponService {
	@Autowired
	CouponDAO couponDAO;
	
	public List<CouponVO> listUsableCoupon(String member_id) throws Exception {
		List<CouponVO> usableCouponList = couponDAO.listUsableCoupon(member_id);
		return usableCouponList;
	}
	public int downloadCoupon(MyCouponVO myCouponVO) throws Exception {
		return couponDAO.downloadCoupon(myCouponVO);
	}
	public int updateMyCoupon(MyCouponVO myCouponVO) throws Exception {
		return couponDAO.updateMyCoupon(myCouponVO);
	}
}
