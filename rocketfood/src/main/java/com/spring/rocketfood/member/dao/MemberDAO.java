package com.spring.rocketfood.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.member.security.UserDetailsDTO;
import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.GradeVO;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.member.vo.PointVO;

public interface MemberDAO {
	public Map<String, Object> login(Map<String, Object> loginMap) throws DataAccessException;
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	public void insertNewAddress(AddressVO addressVO) throws DataAccessException;
	public AddressVO selectBasicAddress(String member_id) throws DataAccessException;
	public List<AddressVO> selectAllAddress(String member_id) throws DataAccessException;
	public AddressVO selectAddress(int address_id) throws DataAccessException;
	public int updateAddress(AddressVO addressVO) throws DataAccessException;
	public int deleteAddress(int address_id) throws DataAccessException;
	public int updateBasicAddress(int address_id) throws DataAccessException;
	public String selectOverlappedID(String member_id) throws DataAccessException;
	public String selectMemberInfo(String member_name, String member_email) throws DataAccessException;
	public String selectMemberInfoForPw(String member_id, String member_email) throws DataAccessException;
	public int updateChangePw(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMemberById(String member_id) throws DataAccessException;
	public int selectPoint(String member_id) throws DataAccessException;
	public void updatePoint(PointVO pointVO) throws DataAccessException;
	public GradeVO selectGrade(String member_id) throws DataAccessException;
	public ArrayList<String> getAuthList(String member_id) throws DataAccessException;
	public UserDetailsDTO selectMember(String member_id) throws DataAccessException;
	
}
