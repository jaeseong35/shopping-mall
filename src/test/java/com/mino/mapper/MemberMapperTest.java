package com.mino.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mino.domain.MemberVO;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
//	//회원가입 테스트
//	@Test
//	public void memberSign() throws Exception{
//		
//		MemberVO member = new MemberVO();
//		
//		member.setMemberId("test");			//회원 id
//		member.setMemberPw("test");			//회원 비밀번호
//		member.setMemberName("test");		//회원 이름
//		member.setMemberMail("test");		//회원 메일
//		member.setMemberAddr1("test");		//회원 우편번호
//		member.setMemberAddr2("test");		//회원 주소
//		member.setMemberAddr3("test");		//회원 상세주소
//		
//		memberMapper.memberSign(member);			//쿼리 메서드 실행
//		
//	}
	
	//중복 아이디 체크테스트 //중복있으면 1 없으면 0
//	@Test
//	public void memgerIdChkTest() {
//		String memberId = "11";
//		
//		memberMapper.memberIdChk(memberId);
//		
//		log.info("cnt = "+memberMapper.memberIdChk(memberId));
//		
//	}
	
	//로그인
	@Test
	public void memberLogin() {
		MemberVO member = new MemberVO();
		member.setMemberId("ika8354");
		member.setMemberPw("111");

		memberMapper.memberLogin(member);
	}

}
