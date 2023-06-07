package com.mino.service;

import com.mino.domain.MemberVO;

	//회원가입
		public interface MemberService {
		
		public void memberSign(MemberVO member) throws Exception; 

	//중복 아이디 체크
		public int memberIdChk(String memberId) throws Exception;
		
	//로그인
		public MemberVO memberLogin(MemberVO memberVO) throws Exception;
		
	// 주문자 정보 
		public MemberVO memberInfo(String memberId);

}
