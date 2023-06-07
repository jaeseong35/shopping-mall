package com.mino.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mino.domain.CartDTO;
import com.mino.domain.MemberVO;
import com.mino.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart/add")
	@ResponseBody
	public String addCartPOST(CartDTO cart, HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		MemberVO membervo = (MemberVO) session.getAttribute("member");
		
		if(membervo == null ) {
			return  "5";
		}
		
		int result = cartService.addCart(cart);
		return result + "";
	}
	
	//장바구니 이동
	@GetMapping("/cart/{memberId}")
	public String cartPage(@PathVariable("memberId") String memberId, Model model) {

		model.addAttribute("cartInfo", cartService.cartList(memberId)); //장바구니 데이터를 모델에
		return "/cart";
	}
	
	//장바구니 수정
	@PostMapping("/cart/update")
	public String updateCart(CartDTO cart) {
		cartService.modifyCount(cart);
		
		System.out.println("업데이트 진입");
		return "redirect:/cart/"+ cart.getMemberId();
	}
	
	//장바구니 삭제
	@PostMapping("/cart/delete")
	public String deleteCartPOST(CartDTO cart) {
		
		cartService.deleteCart(cart.getCartId());
		
		return "redirect:/cart/" + cart.getMemberId();
		
	}		

}
