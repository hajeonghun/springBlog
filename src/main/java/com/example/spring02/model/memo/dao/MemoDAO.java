package com.example.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.memo.dto.MemoDTO;

//mybatis interface mapper
@Repository
public interface MemoDAO {
	@Select("select * from memo order by idx desc")
	public List<MemoDTO> list(); 
	
	@Insert("insert into memo (idx,writer,memo) values " 
	+ "( (select nvl(max(idx)+1,1) from memo)" 
	+ ", #{writer}, #{memo} )")
	public void insert(@Param("writer") String writer
			,@Param("memo") String memo);	//@Param 이나 MemoDTO로 받으나 같다. 편한걸로
	
	@Select("select * from memo where idx=#{idx}")
	public MemoDTO view(@Param("idx") int idx);
	
	@Update("update memo set writer=#{writer}, memo=#{memo}" + " where idx=#{idx}")
	public void update(MemoDTO dto);
	
	@Delete("delete from memo where idx=#{idx}")
	public void delete(@Param("idx") int idx);
}






 




