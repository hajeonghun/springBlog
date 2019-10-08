package com.example.spring02.model.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cartMoney() {
		
		return null;
	}

	@Override
	public void insert(CartDTO dto) {
		
		
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		System.out.println("::::userid : " + userid);
		return sqlSession.selectList("cart.listCart",userid);
	}

	@Override
	public void delete(int cart_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int sumMoney(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countCart(String userid, int product_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCart(CartDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyCart(CartDTO dto) {
		// TODO Auto-generated method stub
		
	}
	
}
