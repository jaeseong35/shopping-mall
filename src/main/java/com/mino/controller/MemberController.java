package com.mino.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mino.domain.MemberVO;
import com.mino.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder pwBCryptPasswordEncoder;

	//로그인 페이지 이동
	@GetMapping("/login")
	public void loginGET() {
		
//		log.info("로그인 페이지 진입");
		
	}
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, MemberVO member, RedirectAttributes ra) throws Exception{
		
//		System.out.println("login 메서드 진입");
//		System.out.println("데이터=" + member);
		HttpSession session = request.getSession();
		String pw = "";
		String encodePw="";
		MemberVO loginVo = memberservice.memberLogin(member);
		
		if(loginVo != null) {  //일치하는 아이디가 있을시
			pw=member.getMemberPw();  //memeber에 담긴 비밀번호 pw 저장
			encodePw=loginVo.getMemberPw(); //db에 저장되있는 인코딩된 비밀번호 저장
			
			if(true == pwBCryptPasswordEncoder.matches(pw, encodePw)){ //인코딩된 비밀번호 확인하려면 matches를 이용하여 확인해야함.
				loginVo.setMemberPw(""); // session에 사용자 정보를 저장하기전 인코딩된 비밀번호 정보 지움
				session.setAttribute("member", loginVo); //로그인정보를 member에 담는다. 
				return "redirect:/"; 
			}else {
				ra.addFlashAttribute("result", 0);            
                return "redirect:/member/login";    // 로그인 페이지로 이동
			}
			
			
		}else {    //일치하는 아이디가 없을시
		
			ra.addFlashAttribute("result",0);
			return "redirect:/member/login";  //로그인실패 ,로그인페이지로 이동
		}
		
	}
	
	//회원가입 페이지 이동
	@GetMapping("/sign")
	public void signGET() {
		
		log.info("회원가입 페이지 진입");
		
	}
	
	//회원가입
	@PostMapping("/sign")
	public String signPOST(MemberVO member) throws Exception{

		String pw= "";
		String encodePw="";
		
		pw = member.getMemberPw(); //member객체에 담긴 비밀번호를 pw에 저장
		encodePw = pwBCryptPasswordEncoder.encode(pw);  //pw에 저장된 비밀번호를 인코딩
		member.setMemberPw(encodePw); //member객체에 인코딩된 비밀번호 저장.
		
		memberservice.memberSign(member);
		
		return "redirect:/";
		
	}
	
	//아이디 중복 체크
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChk(String memberId) throws Exception{
		log.info("아이디체크");
		
		int result = memberservice.memberIdChk(memberId);
		
		if(result != 0) {
			return "fail"; //중복 아이디가 존재
		}else {
			return "success"; // 중복아이디가 없음
		}
	
	}
	//이메일 인증
    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheck(String email) throws Exception{
        
        /* 뷰(View)로부터 넘어온 데이터 확인 */
        log.info("이메일 데이터 전송 확인");
        log.info("인증번호 : " + email);
        
        Random random = new Random();
        int checkNum = random.nextInt(888888)+111111;
        
        log.info("인증번호"+checkNum);
        
        //이메일 보내기
        String setFrom = "regular2644@naver.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
        		"홈페이지를 방문해주셔서 감사합니다." +
        		"<br><br>" + 
        		"인증 번호는"+ checkNum + "입니다." + 
        		"<br>" + 
        		"해당 인증번호를 인증번호 입력란에 기입해주세요.";
        	
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            helper.setFrom(setFrom);
            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하면됨.
            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);   
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        String num = Integer.toString(checkNum); //ajax를 통한 요청은 String타입만 가능.  형변환 진행
        
        return num; //String으로 형변환된 num 객체 리턴
        
    }
	
    //로그아웃
    @GetMapping("/logout")
    public String logOutGet(HttpServletRequest request) throws Exception{
    	log.info("logout메서드 진입");
    	HttpSession session = request.getSession();
//    	session.removeAttribute(member) //특정 이름 세션객체를 삭제
    	session.invalidate(); //세션 전체를 무효화 함
    	return "redirect:/";
    }
	
    //로그아웃
    @PostMapping("/logout")
    @ResponseBody
    public String logOutPost(HttpServletRequest request) throws Exception{
    	log.info("logout메서드 진입");
    	HttpSession session = request.getSession();
//    	session.removeAttribute(member) //특정 이름 세션객체를 삭제
    	session.invalidate(); //세션 전체를 무효화 함
    	return "redirect:/";
    }
	
	
}
	

