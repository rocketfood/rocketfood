package com.spring.rocketfood.admin.category.service;

import java.util.List;
import java.util.Map;

import com.spring.rocketfood.admin.category.vo.CategoryVO;

public interface CategoryService {
	public List<CategoryVO> selectAllCategoryList() throws Exception;
	public Map<String, List<CategoryVO>> selectCategoryList(int parent_id) throws Exception;
	public void addCategory(CategoryVO categoryVO) throws Exception;
	public void updateCategory(CategoryVO categoryVO) throws Exception;
	public void deleteCategory(int category_id) throws Exception;
	public CategoryVO selectCategoryItem(int category_id) throws Exception;
}
