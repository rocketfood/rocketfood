package com.spring.rocketfood.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.GradeVO;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.member.vo.PointVO;

public interface MemberService {
	public Map<String, Object> login(Map<String, Object> loginMap) throws Exception;
	public void addMember(MemberVO memberVO) throws Exception;
	public void addAddress(AddressVO addressVO) throws Exception;
	public AddressVO findBasicAddress(String member_id) throws Exception;
	public List<AddressVO> findAllAddress(String member_id) throws Exception;
	public AddressVO findAddressInfo(int address_id) throws Exception;
	public int updateAddress(AddressVO addressVO) throws Exception;
	public int updateBasicAddress(int address_id) throws Exception;
	public int deleteAddress(int address_id) throws Exception;
	public String overlapped(String member_id) throws Exception;
	public String findMemberInfo(String member_id, String member_email) throws Exception;
	public String findMemberInfoForPw(String member_id, String member_email) throws Exception;
	public int updateChangePw(MemberVO memberVO) throws Exception;
	public MemberVO findMemberById(String member_id) throws Exception;
	public GradeVO findMemberGrade(String member_id) throws Exception;
	public int findMemberPoint(String member_id) throws Exception;
	public void updateMemberPoint(PointVO pointVO) throws Exception;
}
   