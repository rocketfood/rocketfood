package com.spring.rocketfood.admin.category.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.admin.category.vo.CategoryVO;
import com.spring.rocketfood.member.vo.MemberVO;


public interface CategoryDAO {
	public List<CategoryVO> selectAllCategory() throws DataAccessException;
	public List<CategoryVO> selectCategory2depth(int parent_id) throws DataAccessException;
	public void insertCategory(CategoryVO categoryVO) throws DataAccessException;
	public void deleteCategory(int category_id) throws DataAccessException;
	public void updateCategory(CategoryVO categoryVO) throws DataAccessException;
	public int countCategory() throws DataAccessException;
	public CategoryVO selectCategoryItem(int category_id) throws DataAccessException;
}
