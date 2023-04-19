package com.spring.rocketfood.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.member.dao.MemberDAO;
import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.GradeVO;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.member.vo.PointVO;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Map<String, Object> login(Map<String, Object> loginMap) throws Exception{
		Map<String, Object> loginInfoMap = memberDAO.login(loginMap);
		return loginInfoMap; 
	}
	
	@Override
	public void addMember(MemberVO memberVO) throws Exception{
		memberDAO.insertNewMember(memberVO);
	}
	@Override
	public void addAddress(AddressVO addressVO) throws Exception {
		memberDAO.insertNewAddress(addressVO);
	}
	
	@Override
	public AddressVO findBasicAddress(String member_id) throws Exception {
		AddressVO addressVO = memberDAO.selectBasicAddress(member_id);
		return addressVO;
	}
	
	@Override
	public AddressVO findAddressInfo(int address_id) throws Exception {
		return memberDAO.selectAddress(address_id);
		
	}
	
	@Override
	public List<AddressVO> findAllAddress(String member_id) throws Exception {
		List<AddressVO> addressList = memberDAO.selectAllAddress(member_id);
		return addressList;
	}
	
	@Override
	public int updateAddress(AddressVO addressVO) throws Exception {
		return memberDAO.updateAddress(addressVO);
	}
	
	@Override
	public int updateBasicAddress(int address_id) throws Exception {
		return memberDAO.updateBasicAddress(address_id);
	}
	
	@Override
	public int deleteAddress(int address_id) throws Exception {
		return memberDAO.deleteAddress(address_id);
	}
	
	@Override
	public String overlapped(String member_id) throws Exception{
		return memberDAO.selectOverlappedID(member_id);
	}
	
	@Override
	public String findMemberInfo(String member_id, String member_email) throws Exception {
		String result = memberDAO.selectMemberInfo(member_id, member_email);
		System.out.println("memberDAO.selectMemberInfo result : " + result);
		return result;
	}
	
	@Override
	public String findMemberInfoForPw(String member_id, String member_email) throws Exception {
		return memberDAO.selectMemberInfoForPw(member_id, member_email);
	}
	
	@Override
	public int updateChangePw(MemberVO memberVO) throws Exception {
		return memberDAO.updateChangePw(memberVO);
	}
	
	@Override
	public MemberVO findMemberById(String member_id) throws Exception {
		return memberDAO.selectMemberById(member_id);
	}
	
	@Override
	public GradeVO findMemberGrade(String member_id) throws Exception {
		return memberDAO.selectGrade(member_id);		
	}
	
	@Override
	public int findMemberPoint(String member_id) throws Exception {
		return memberDAO.selectPoint(member_id);		
	}
	
	@Override
	public void updateMemberPoint(PointVO pointVO) throws Exception {
		memberDAO.updatePoint(pointVO);		
	}
	
	
}
