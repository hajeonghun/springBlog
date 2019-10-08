package com.example.spring02.controller.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;
 


@Controller
@RequestMapping("board/*")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("list.do")
	public ModelAndView list(
			@RequestParam(defaultValue="1") int curPage)
					throws Exception{
		//레코드 개수 계산
		int count = boardService.countArticle();
		//페이지 관련 설정
		Pager pager = new Pager(count,curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		
		List<BoardDTO> list = boardService.listAll(start,end);
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("count",count);
		map.put("pager",pager);	//페이지 네비게이션을 위한 변수
				
		mav.setViewName("board/list");
		mav.addObject("map", map);
		return mav; 
	}
	
	
	@RequestMapping("write.do")
	public String write() {
		return "board/write";
	}
	
	@RequestMapping("insert.do")
	public String insert(BoardDTO dto, HttpSession session)throws Exception{
		String writer=(String)session.getAttribute("userid");
		dto.setWriter(writer);
		boardService.create(dto);
		
		return "redirect:/board/list.do";
	}

	@RequestMapping("view.do")
	public ModelAndView view(int bno, HttpSession session) throws Exception {
		//조회수 증가 처리
		boardService.increateViewcnt(bno, session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		//자료 저장(상세 조회)
		mav.addObject("dto", boardService.read(bno));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(BoardDTO dto) throws Exception {
		if(dto != null) {
			boardService.update(dto);
		}
		return "redirect:/board/list.do";
	}
}
