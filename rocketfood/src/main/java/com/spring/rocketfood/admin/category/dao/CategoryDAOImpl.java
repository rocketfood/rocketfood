package com.spring.rocketfood.admin.category.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.admin.category.vo.CategoryVO;

@Repository("adminCategoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private CategoryVO categoryVO;
	
	@Override
	public List<CategoryVO> selectAllCategory() throws DataAccessException {
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		categoryList = sqlSession.selectList("mapper.admin.category.selectAllCategory");
		return categoryList;
	}
	
	@Override
	public List<CategoryVO> selectCategory2depth(int parent_id) throws DataAccessException {
		List<CategoryVO> categoryList = sqlSession.selectList("mapper.admin.category.selectCategoryByParent", parent_id);
		return categoryList;		
	}
	
	@Override
	public void insertCategory(CategoryVO categoryVO) throws DataAccessException {
		sqlSession.insert("mapper.admin.category.insertNewCategory", categoryVO);
	}
	@Override
	public void deleteCategory(int category_id) throws DataAccessException {
		sqlSession.delete("mapper.admin.category.insertNewCategory", category_id);
	}
	@Override
	public void updateCategory(CategoryVO categoryVO) throws DataAccessException {
		sqlSession.update("mapper.admin.category.deleteCategory", categoryVO);
	}
	
	public int countCategory() throws DataAccessException {
		int countCate = sqlSession.selectOne("mapper.admin.category.countCategory");
		return countCate;
	}
	
	public CategoryVO selectCategoryItem(int category_id) throws DataAccessException {
		categoryVO = sqlSession.selectOne("mapper.admin.category.selectCategoryById", category_id);
		return categoryVO;
	}
}
