package com.mino.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.MemberVO;
import com.mino.domain.OrderCancelDTO;
import com.mino.domain.OrderSuccessDTO;
import com.mino.domain.PageDTO;
import com.mino.domain.Paging;
import com.mino.service.AdminService;
import com.mino.service.MemberService;
import com.mino.service.OrderService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/main")
	public void adminMain() throws Exception{
		log.info("관리자페이지 진입");
	}
	
	// 상품 등록 페이지 접속
    @GetMapping("/shopManage")
    public void shopManageGET(Model m) throws Exception{
    	log.info("상품 등록 페이지 접속");
    	ObjectMapper objm = new ObjectMapper();  // 객체 초기화
    	List list = adminService.cateList();
    	
    	String cateList = objm.writeValueAsString(list);  // String 타입의 JSON형식 데이터로 변환 
    	m.addAttribute("cateList",cateList);
    	log.info("변경전 =" +list);
    	log.info("변경후 =" +cateList);
    }
    
    @PostMapping("/shopManage")
    public String shopManagePost(ItemVO item, RedirectAttributes ra)throws Exception{
    	log.info("상품등록포스트" + item);
    	adminService.itemInsert(item);

    	ra.addFlashAttribute("manage_result",item.getItemName()); //상품이름이 등록되었음을 알리는 경고창 띄우기위함
    	return "redirect:/admin/shopManage";
    }
    
    // 상품 목록 페이지 접속
    @GetMapping("/shopList")
    public void shopListGet(Paging page, Model model) throws Exception{
    	//상품 리스트 데이터

        log.info("상품 목록 페이지 접속");
    	List list = adminService.shopList(page);
    	
    	if(!list.isEmpty()) {
    		model.addAttribute("list",list);
    	}else {
    		model.addAttribute("listCheck", "empty");
    	}
    	
    	model.addAttribute("pageMaker", new PageDTO(page,adminService.shopTotal(page)));
    	
    }
    
  @GetMapping({"/view" , "/modify"})  //맵핑 배열로 할수 있음.
  public void shopViewGet(int itemId, Paging page, Model model) throws JsonProcessingException {
	  log.info("뷰 진입");
	  ObjectMapper mapper = new ObjectMapper();
	  
	  //카테고리 데이터
	  model.addAttribute("cateList",mapper.writeValueAsString(adminService.cateList()));
	  //페이지 정보
	  model.addAttribute("page", page);
	  //조회 페이지 정보
	  model.addAttribute("viewInfo",adminService.shopView(itemId));
  }
  
  @PostMapping("/modify")
  public String shopModifyPost(ItemVO item,  RedirectAttributes ra) throws Exception{
	  int result = adminService.shopModify(item);
	  
	  ra.addFlashAttribute("modify_result",result);
	  
	  return "redirect:/admin/shopList";
  }
  
  @PostMapping("/delete")
  public String shopDelete(int itemId, RedirectAttributes ra) throws Exception{
	  List<ImageVO> imgList = adminService.getImageInfo(itemId); 
	  
	  //해당 itemId가 image정보를 가지고 잇으면 
	  if(imgList != null) {
		  
		  List<Path> pathList = new ArrayList();
		  imgList.forEach(item ->{
			  
				// 원본 이미지
				Path path = Paths.get("C:\\Users\\PC\\Desktop\\upload", item.getUploadPath(), item.getUuid() + "_" + item.getFileName());
				pathList.add(path);
				
				// 섬네일 이미지
				path = Paths.get("C:\\Users\\PC\\Desktop\\upload", item.getUploadPath(), "s_" + item.getUuid()+"_" + item.getFileName());
				pathList.add(path);
				
			});
			
				pathList.forEach(path ->{
					path.toFile().delete();
			});
	  }
	  
	  int result = adminService.shopDelete(itemId);
	  
	  ra.addFlashAttribute("delete_result",result);
	  
	  return "redirect:/admin/shopList";
	  
  }
  
  @PostMapping( value="/imgUpload", produces = MediaType.APPLICATION_JSON_VALUE ) // 뷰로 전송될때 utf-8인코딩 되어있는 상태로 전달하기 위해 produces 속성을 추가
  public ResponseEntity<List<ImageVO>> imgUploadPost(MultipartFile[] uploadFile) {
	  
	  log.info("이미지 업로드 컨트롤러=" + uploadFile);
	  //이미지 파일 체크 
	  for(MultipartFile file : uploadFile) {
		  File checkFile = new File(file.getOriginalFilename());
		  String type = null;
		  
		  try {
		  type = Files.probeContentType(checkFile.toPath()); 
		  //파라미터로 전달받은 파일을 문자열로 반환, 파라미터는 Path객체를 전달받아야함 , checkFile 을 path객체로 만들기위해 toPath사용
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		 if(!type.startsWith("image")) { // 첫 단어가 image 인지 확인하기위해 String 클래스의 startsWith() 메서드를 사용
			 List<ImageVO> list = null;
			 return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);  //상태코드 400인 객체를 반환해줌
		 } 
	  }
	  
	  String uploadFolder = "C:\\Users\\PC\\Desktop\\upload";
	  
	  //날짜에 맞게 폴더 생성후 저장하기 위해 초기화
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //SimpleDateFormat 날짜->날짜문자, 날짜문자열->날짜로 변환 해줌
	  Date date = new Date();  
	  String str = sdf.format(date);  //2023년 1월 1일 -> "2023-01-01"  문자열로 변환됨
	  String datePath = str.replace("-", File.separator); // - 를 \\ 로 변경하기 위해 String 클래스의 replace사용
	  
	  File uploadPath = new File(uploadFolder,datePath);
	  
	  if(uploadPath.exists()== false) { //exists() = 파일,디렉토리가 존재하는지 확인
		  
	  uploadPath.mkdirs(); //File클래스의 mkdirs()는 여러개의 폴더 생성 
	  
	  }

	   //이미지 정보를 담는 객체 초기화
	  List<ImageVO> list = new ArrayList();
	  
	  for(MultipartFile file : uploadFile) {
		 
		//이미지 정보 객체
		ImageVO img = new ImageVO();
		  
		//파일이름
		String uploadFileName = file.getOriginalFilename();
		img.setFileName(uploadFileName);
		img.setUploadPath(datePath);
		
		
		//uuid 파일이름
		String uuid = UUID.randomUUID().toString();
		img.setUuid(uuid);
		 
		uploadFileName=uuid+"_"+uploadFileName;  //uuid _ 파일이름으로 변경
		
		//파일 위치 + 파일이름 변수 저장
		File saveFile = new File(uploadPath, uploadFileName);
		
		// 파일 저장
		try {
			file.transferTo(saveFile);
			
			//섬네일 저장
			File thFile = new File(uploadPath, "s_"+uploadFileName); 
			
			
			Thumbnails.of(saveFile)
			.size(170, 170)
			.toFile(thFile); //섬네일 끝
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	  
	   list.add(img); // for문이 끝나기전 img정보를 list에 추가
	  } //for end
	  ResponseEntity<List<ImageVO>> result = new ResponseEntity<List<ImageVO>>(list, HttpStatus.OK);
	  //Http의 바디에 추가될 데이터는 List<ImageVO>이고 상태 코드가 OK(200)인 ReseponseEntity 객체 생성
	  
	  return result;
  }
  
  @PostMapping("/deleteFile")
  public ResponseEntity<String> deleteFile(String fileName) {
	  
	  File file = null;
	  
	  try {
		  //썸네일 이미지 삭제
		  file = new File("C:\\Users\\PC\\Desktop\\upload" + URLDecoder.decode(fileName, "UTF-8")); //디코딩 
		  file.delete();
		  
		  //원본 이미지 삭제
		  String originFileName = file.getAbsolutePath().replace("s_", ""); 
		  // getAbsolutePath() 메서드를 사용할 것인데 해당 메서드를 호출하면 대상 File 객체의 경로를 문자열String 타입의 데이터로 반환
		  //replace를 이용해 s_ 를 ""로 치환
		  log.info("원본 파일 이름="+originFileName);
		  file.delete();
		  
	  }catch (Exception e) {
		  
		e.printStackTrace();
		return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
	}
	  return new ResponseEntity<String>("success", HttpStatus.OK);
  }
  
  /* 주문 현황 페이지 */
	@GetMapping("/orderList")
	public String orderListGET(Paging page, Model model) {
		List<OrderSuccessDTO> list = adminService.getOrderList(page);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			model.addAttribute("pageMaker", new PageDTO(page, adminService.getOrderTotal(page)));
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		return "/admin/orderList";
	}
  
	/* 주문삭제 */
	@PostMapping("/orderCancle")
	public String orderCanclePOST(OrderCancelDTO dto, HttpServletRequest request) {
		
		orderService.orderCancle(dto);
		HttpSession session = request.getSession();
		
		
		MemberVO member = new MemberVO();
		member.setMemberId(dto.getMemberId());
		try {
			MemberVO memberLogin = memberService.memberLogin(member);
			memberLogin.setMemberPw("");
			session.setAttribute("member", memberLogin);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return "redirect:/admin/orderList?keyword=" + dto.getKeyword() + "&amount=" + dto.getAmount() + "&pageNum=" + dto.getPageNum();
	}
    
}
