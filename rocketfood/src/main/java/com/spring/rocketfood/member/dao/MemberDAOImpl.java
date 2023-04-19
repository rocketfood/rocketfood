package com.spring.rocketfood.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.member.security.UserDetailsDTO;
import com.spring.rocketfood.member.vo.AddressVO;
import com.spring.rocketfood.member.vo.GradeVO;
import com.spring.rocketfood.member.vo.MemberVO;
import com.spring.rocketfood.member.vo.PointVO;

@Repository("memberDAO")
public class MemberDAOImpl  implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;	
	@Autowired
	private AddressVO addressVO;
	@Autowired
	private MemberVO memberVO;
	
	//로그인 정보 조회 후 회원 정보 리턴
	@Override
	public Map<String, Object> login(Map<String, Object> loginMap) throws DataAccessException{
		//입력 받은 아이디 비밀번호로 회원 정보 조회하여 memberVO 생성
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.member.login",loginMap);
		String member_id = String.valueOf(loginMap.get("member_id"));
		System.out.println("Login member_id: " + member_id);	
		
		//Map 형태로 저장하여 회원 정보 리턴
		Map<String, Object> loginInfoMap = new HashMap<String, Object>();
		loginInfoMap.put("memberVO", memberVO);
		return loginInfoMap;
	}
	
	//새 회원 추가
	@Override
	public void insertNewMember(MemberVO memberVO) throws DataAccessException{
		//전달 받은 회원 정보를 회원 테이블에 저장
		sqlSession.insert("mapper.member.insertNewMember", memberVO);
		
		//참조로 연결된 테이블의 데이터 생성은 DB의 트리거로 처리함 2023-03-28
		//point, cart, member_auth
	}
	
	//새 주소 추가
	@Override
	public void insertNewAddress(AddressVO addressVO) throws DataAccessException{
		String isBasic = addressVO.getIs_basic_address();
		if(isBasic != null && !isBasic.equals("") && isBasic.equals("y")) {
			sqlSession.update("mapper.member.basicAddressN");
		}
		sqlSession.insert("mapper.member.insertNewAddress", addressVO);
	}
	
	//회원 기본 주소 불러오기
	@Override
	public AddressVO selectBasicAddress(String member_id) throws DataAccessException{
		addressVO = sqlSession.selectOne("mapper.member.selectBasicAddress", member_id);
		return addressVO;
	}
	
	//회원 주소 목록 불러오기
	@Override
	public List<AddressVO> selectAllAddress(String member_id) throws DataAccessException {
		List<AddressVO> addressList = sqlSession.selectList("mapper.member.selectAddressList", member_id);
		return addressList;
	}
	
	//주소 아이디로 주소 정보 불러오기
	@Override
	public AddressVO selectAddress(int address_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectAddressByAddressId", address_id);
	}
	
	//주소 수정하기
	@Override
	public int updateAddress(AddressVO addressVO) throws DataAccessException {
		String isBasic = addressVO.getIs_basic_address();
		if(isBasic != null && !isBasic.equals("") && isBasic.equals("y")) {
			sqlSession.update("mapper.member.basicAddressN");
		}
		return sqlSession.update("mapper.member.updateAddress", addressVO);
		
	}
	
	public int updateBasicAddress(int address_id) throws DataAccessException {
		sqlSession.update("mapper.member.basicAddressN");
		return sqlSession.update("mapper.member.updateBasicAddress", address_id);
	}
	
	//주소 삭제하기
	@Override
	public int deleteAddress(int address_id) throws DataAccessException {
		return sqlSession.delete("mapper.member.deleteAddress", address_id);
	}
	
	//아이디 중복 조회
	@Override
	public String selectOverlappedID(String member_id) throws DataAccessException {
		//result값은 'true' or 'false'
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID", member_id);
		return result;
	}
	
	//아이디 찾기용 회원 정보 조회
	@Override
	public String selectMemberInfo(String member_name, String member_email) throws DataAccessException {
		Map<String, String> memberInfoMap = new HashMap<String, String>();
		memberInfoMap.put("member_email", member_email);
		memberInfoMap.put("member_name", member_name);
		
		//입력한 이메일로 조회하여 해당하는 회원 아이디가 있는지 조회 후 아이디 반환
		memberVO = sqlSession.selectOne("mapper.member.selectMemberByEmailAndName", memberInfoMap);
		
		//String result = "";
		//멤버 아이디가 존재하고 서로 같다면 
		if(memberVO != null) {
			//result = memberVO.getMember_id();
		}
		
		return memberVO.getMember_id();
	}
	
	//비번 찾기용 회원 정보 확인
	@Override
	public String selectMemberInfoForPw(String member_id, String member_email) throws DataAccessException {
		Map<String, String> memberInfoMap = new HashMap<String, String>();
		memberInfoMap.put("member_email", member_email);
		memberInfoMap.put("member_id", member_id);
		
		//입력한 이메일로 조회하여 해당하는 회원 아이디가 있는지 조회 후 아이디 반환
		memberVO = sqlSession.selectOne("mapper.member.selectMemberByIdAndEmail", memberInfoMap);
		
		String result = "";
		//멤버 아이디가 존재하고 서로 같다면 
		if(memberVO != null) {
			result = memberVO.getMember_id();
		}
		
		return result;
	}
	
	//새 비밀번호 업데이트
	@Override
	public int updateChangePw(MemberVO memberVO) throws DataAccessException {
		//true면 1, false면 0
		int result = sqlSession.update("mapper.member.updateChangePwd", memberVO);
		return result;
	}
		
	//회원 정보 불러오기(데이터 전송용)
	@Override
	public MemberVO selectMemberById(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectMemberById", member_id );
	}
	
	//회원 포인트 조회
	@Override
	public int selectPoint(String member_id) throws DataAccessException {
		int point = sqlSession.selectOne("mapper.member.selectPoint", member_id);
		return point;
	}
	
	//회원 포인트 업데이트
	@Override
	public void updatePoint(PointVO pointVO) throws DataAccessException {
		sqlSession.insert("mapper.mypage.updatePoint", pointVO);
	}
	
	//회원 등급 조회
	@Override
	public GradeVO selectGrade(String member_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.member.selectGradeById", member_id);
	}
		
	//시큐리티용 회원정보 조회
	@Override
	public UserDetailsDTO selectMember(String member_id) throws DataAccessException {
		// 입력 받은 아이디로 조회하여 해당하는 회원 정보 조회하여 해당 회원 정보를 memberVO에 저장
		memberVO = sqlSession.selectOne("mapper.security.selectUser", member_id );
		
		//시큐리티용 회원 정보 객체인 UserDetailsVO를 생성하여 memberVO가 존재한다면 userDetails에 정보를 세팅하여 새로 생성 후 반환
		UserDetailsDTO userDetails = null;
		if(memberVO != null) {
			Boolean enabled = memberVO.getEnabled();
			String member_pw = memberVO.getMember_pw();
			// 조회한 회원 정보로 UserDetailsDTO 객체 생성
			userDetails = new UserDetailsDTO(member_id, member_pw, enabled);
		} 
		return userDetails;
	}
	
	//시큐리티용 회원 권한 조회
	@Override
	public ArrayList<String> getAuthList(String member_id) throws DataAccessException {
		// 입력 받은 아이디로 조회하여 해당하는 회원 권한 조회 후 권한을 리스트로 반환(회원이 가지는 권한은 1개 이상일 수 있음)
		List<String> list = sqlSession.selectList("mapper.security.getAuthList", member_id);
		ArrayList<String> authList = new ArrayList<String>();
		// 권한 리스트를 확인용 
		String item = "";
		for(int i = 0; i<list.size(); i++) {
			item = list.get(i);
			authList.add(item);
			System.out.println("getAuthList " + item);
		}
		return authList;
	}
	
}
