package com.mino.service;

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
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
//	//회원가입 테스트
//	@Test
//	public void memberSignTest() throws Exception {
//		MemberVO member1 = new MemberVO();
//		
//		member1.setMemberId("서비스테스트");
//		member1.setMemberPw("서비스테스트");
//		member1.setMemberName("서비스테스트");
//		member1.setMemberMail("서비스테스트");
//		member1.setMemberAddr1("서비스테스트");
//		member1.setMemberAddr2("서비스테스트");
//		member1.setMemberAddr3("서비스테스트");
//		
//		memberService.memberSign(member1);
//	}
	
	//아이디 중복 체크 //중복있으면 1, 없으면 0
//	@Test
//	public void memgerIdChkTest() throws Exception{
//		String id = "11"; 
//		memberService.memberIdChk(id);
//		
//		log.info("cnt==" + memberService.memberIdChk(id));
//	}
	
	@Test
	public void memberLoginTest() throws Exception{
		MemberVO member =new MemberVO();
		
		member.setMemberId("ika8354");
		member.setMemberPw("1111");
		
		memberService.memberLogin(member);
		
		
	}

}
