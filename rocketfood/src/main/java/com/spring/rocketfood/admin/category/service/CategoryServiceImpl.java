package com.spring.rocketfood.admin.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rocketfood.admin.category.dao.CategoryDAO;
import com.spring.rocketfood.admin.category.vo.CategoryVO;

@Service("adminCategoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private CategoryVO categoryVO;
	
	
	//모든 카테고리를 리스트로 받아오기
	@Override
	public List<CategoryVO> selectAllCategoryList() throws Exception {
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		categoryList = categoryDAO.selectAllCategory();
		return categoryList;
	}
	
	//1차 카테고리 아이디로 모든 하위 카테고리를 받아오기
	@Override
	public Map<String, List<CategoryVO>> selectCategoryList(int parent_id) throws Exception {
		Map<String, List<CategoryVO>> categoryMap = new HashMap<String, List<CategoryVO>>();
		
		// parent_id가 임의의 숫자 999 일 때 모든 하위 카테고리를 반환한다 (해당하는 parent_id가 없다면 전체를 불러오도록 함)
		if(parent_id == 999) {
			//1차 카테고리의 갯수를 불러와서 해당 숫자만큼만 반복
			int countCate = categoryDAO.countCategory();
			for(int i=0; i<=countCate; i++) {
				parent_id = i;
				List<CategoryVO> categoryList = categoryDAO.selectCategory2depth(parent_id);
				categoryVO = categoryDAO.selectCategoryItem(parent_id);
				String pCateName = categoryVO.getCategory_name();
				categoryMap.put(pCateName, categoryList);
			}			
			//특정 카테고리의 부모 카테고리 아이디를 받은 경우 해당 카테고리의 하위 카테고리만 반환한다
		} else {
			List<CategoryVO> categoryList = categoryDAO.selectCategory2depth(parent_id);
			categoryMap.put(String.valueOf(parent_id), categoryList);
		}
		return categoryMap;
	}
	
	@Override
	public void addCategory(CategoryVO categoryVO) throws Exception {
		categoryDAO.insertCategory(categoryVO);
	}
	
	@Override
	public void updateCategory(CategoryVO categoryVO) throws Exception {
		categoryDAO.updateCategory(categoryVO);
	}
	
	@Override
	public void deleteCategory(int category_id) throws Exception {
		categoryDAO.deleteCategory(category_id);
	}
	
	@Override
	public CategoryVO selectCategoryItem(int category_id) throws Exception {
		categoryVO = categoryDAO.selectCategoryItem(category_id);
		return categoryVO;
	}
}
