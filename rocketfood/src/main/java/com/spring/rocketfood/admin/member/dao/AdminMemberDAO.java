package com.spring.rocketfood.admin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.member.vo.MemberVO;

public interface AdminMemberDAO {
	public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException;
	public MemberVO memberDetail(String member_id) throws DataAccessException;
	public int modifyMemberInfo(HashMap memberMap) throws DataAccessException;
}
