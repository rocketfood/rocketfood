package com.spring.rocketfood.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.rocketfood.admin.category.service.CategoryService;
import com.spring.rocketfood.admin.category.vo.CategoryVO;
import com.spring.rocketfood.common.base.BaseController;
import com.spring.rocketfood.goods.board.service.GoodsBoardService;
import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.GoodsReviewVO;
import com.spring.rocketfood.goods.service.GoodsService;
import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.member.service.MemberService;
import com.spring.rocketfood.member.vo.MemberVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value="/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	@Autowired 
	private GoodsBoardService goodsBoardSerivce;
	@Autowired
	private MemberService memberService;
	@Autowired
	private GoodsVO goodsVO;
	@Autowired
	private MemberVO memberVO;
	
	//상품 목록 페이지 불러오기
	@Override
	@RequestMapping(value="/goodsList.do" ,method = RequestMethod.GET)
	public ModelAndView goodsList(@RequestParam("category_id") int category_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//카테고리에 해당 하는 상품 리스트 불러오기 - goods_sort(상품정렬 기준)가 없을 경우, new(최신등록)를 디폴트로 사용함		
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("category_id", category_id);
		String goods_sort = request.getParameter("goods_sort");
		
		if(goods_sort == null) {
			goods_sort = "new";
		}
		goodsMap.put("goods_sort", goods_sort);
		List<GoodsVO> goodsList = goodsService.listGoodsByCategory(goodsMap);
		
		//해당 카테고리 정보 불러오기
		CategoryVO categoryVO = categoryService.selectCategoryItem(category_id);
		
		Map<String, List<CategoryVO>> other_categoryMap = null;
		String other_categoryMapKey = "";
		
		//해당 카테고리와 부모가 같은 다른 카테고리들 불러오기
		if(categoryVO.getParent_id() != 0) {
			other_categoryMap = categoryService.selectCategoryList(categoryVO.getParent_id());
			other_categoryMapKey = String.valueOf(categoryVO.getParent_id());
			
		// 해당 카테고리의 하위 카테고리 불러오기
		} else {
			other_categoryMap = categoryService.selectCategoryList(categoryVO.getCategory_id());
			other_categoryMapKey = String.valueOf(categoryVO.getCategory_id());
		}
					
		mav.addObject("goodsList", goodsList);
		mav.addObject("categoryInfo", categoryVO);
		mav.addObject("other_categoryMap", other_categoryMap );
		mav.addObject("other_categoryMapKey", other_categoryMapKey);
		return mav;	
	}
		
	//상품 상세 페이지 불러오기
	@Override
	@RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") int goods_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();	
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		ModelAndView mav = new ModelAndView(viewName);
		
		//해당 상품 정보 불러오기
		Map goodsMap = goodsService.goodsDetail(goods_id);
		
		//goods_id로 해당 상품에 관련된 문의글만 불러오기
		List<GoodsQnaVO> bodsList = goodsBoardSerivce.listGoodsQna(goods_id);
		List<Object> reviewList = goodsBoardSerivce.listGoodsReview(goods_id);
		if(memberVO != null && memberVO.getMember_id() != null) {
			List<GoodsReviewVO> helpReviewList = goodsBoardSerivce.listHelpReivew(goods_id, memberVO.getMember_id());
			mav.addObject("helpReviewList", helpReviewList);
		}
		
		
		//퀵메뉴에 최근에 본 상품 정보 저장하기
		goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id, goodsVO, session);
		
		mav.addObject("goodsMap", goodsMap);
		mav.addObject("bodsList", bodsList);
		mav.addObject("reviewList", reviewList);
		
		mav.addObject("isLogOn", session.getAttribute("isLogOn"));	
		System.out.println(bodsList.size());
		return mav;
	}
	
	//검색어로 상품 목록 불러오기
	@Override
	@RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
	public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,
			                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println(keyword);
		if(keyword == null || keyword.equals(""))
		   return null ;
	
		keyword = keyword.toUpperCase();
	    List<String> keywordList = goodsService.keywordSearch(keyword);
	    
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		 		
	    String jsonInfo = jsonObject.toString();
	    return jsonInfo ;
	}
	
	//검색어로 상품 목록 불러오기
	@Override
	@RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
			                       HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsList", goodsList);
		return mav;
		
	}
	
	//퀵메뉴에 최근 본 상품 추가하기
	private void addGoodsInQuick(int goods_id, GoodsVO goodsVO, HttpSession session){
		boolean already_existed = false;
		List<GoodsVO> quickGoodsList = (List<GoodsVO>) session.getAttribute("quickGoodsList");
		
		if(quickGoodsList != null){
			if(quickGoodsList.size() < 4){ 
				for(int i=0; i<quickGoodsList.size();i++){
					GoodsVO _goodsBean = (GoodsVO)quickGoodsList.get(i);
					if(goods_id ==_goodsBean.getGoods_id()){
						already_existed=true;
						break;
					}
				}
				if(already_existed == false){
					quickGoodsList.add(goodsVO);
				}
			}
			
		} else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
			
		}
		session.setAttribute("quickGoodsList", quickGoodsList);
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());
	}
}
