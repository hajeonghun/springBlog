package com.example.spring02.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.ReplyDTO;
import com.example.spring02.service.board.ReplyService;

@Controller
@RequestMapping("reply/*")
public class ReplyController {

	@Autowired
	ReplyService replyService;
	
	@ResponseBody
	@RequestMapping("insert.do")
	public void insert(@RequestBody ReplyDTO dto, HttpSession session) {
		System.out.println("실행은 되냐고?");
		
		//댓글 작성자 아이디
		String userid=(String)session.getAttribute("userid");
		
		dto.setReplyer(userid);
		//댓글이 테이블에 저장됨
		replyService.create(dto);
		//jsp페이지로 가거나 데이터를 리턴하지 않음
		
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(int bno, ModelAndView mav) {
		List<ReplyDTO> list = replyService.list(bno);	//댓글 목록
		mav.setViewName("board/reply_list");	//뷰의 이름
		mav.addObject("list",list);	//뷰에 전달할 데이터 저장
		return mav;
	}
}
