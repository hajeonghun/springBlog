package com.example.spring02.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;

	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		boardDao.create(dto);

	}

	@Override
	public BoardDTO read(int bno) throws Exception {

		return boardDao.read(bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto);

	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardDTO> listAll(int start, int end) throws Exception {

		return boardDao.listAll(start, end);
	}

	@Override
	public void increateViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		if (session.getAttribute("update_time_" + bno) != null) {
			//최근에 조회수를 올린 시간
			update_time = (long) session.getAttribute("update_time_" + bno);
		} 
		long current_time = System.currentTimeMillis();
		//일정 시간이 경과한 후 조회수 증가 처리
		if (current_time - update_time > 5*1000) {	//1시간 : 60*60*1000  / 24시간 : 24*60*60*1000
			//조회수 증가 처리
			boardDao.increateViewcnt(bno);
			//조회수를 올린 시간 저장
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	@Override
	public int countArticle() throws Exception {
		return boardDao.countArticle();
	}

}
