package com.spring.rocketfood.goods.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.goods.board.dao.GoodsBoardDAO;
import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.GoodsReviewVO;
import com.spring.rocketfood.goods.board.vo.ReviewHelpVO;

@Service("goodsBoardService")
@Transactional(propagation = Propagation.REQUIRED)
public class GoodsBoardServiceImpl implements GoodsBoardService {
	@Autowired
	GoodsBoardDAO goodsBoardDAO;
	
	public List<GoodsQnaVO> listGoodsQna(int goods_id) throws Exception{
		List<GoodsQnaVO> bodsList = goodsBoardDAO.selectAllGoodsQnaList(goods_id);
		return bodsList;
	}
	public int addNewGoodsQna(Map<String, Object> bodMap) throws Exception{
		return goodsBoardDAO.insertNewGoodsQna(bodMap);
	}
	
	public int addReply(Map<String, Object> bodMap) throws Exception {
		return goodsBoardDAO.updateReply(bodMap);
	}
	
	@Override
	public List<Object> listGoodsReview(int goods_id) throws Exception {
		List<Object> reviewList = goodsBoardDAO.selectAllGoodsReviewList(goods_id);
		return reviewList;
	}
	
	@Override
	public List<GoodsReviewVO> listHelpReivew(int goods_id, String member_id) throws Exception {
		List<GoodsReviewVO> reviewList = goodsBoardDAO.selectMyReviewHelp(goods_id, member_id);
		return reviewList;
	}
	
	@Override
	public int addNewGoodsReview(Map<String, Object> reviewMap) throws Exception {
		return goodsBoardDAO.insertNewGoodsReview(reviewMap);
	}
	
	@Override
	public Boolean updateMyHelp(ReviewHelpVO reviewHelpVO) throws Exception {
		 int result = goodsBoardDAO.updateReviewHelp(reviewHelpVO);
		 if(result < 1) {
			 return false;
		 } else {
			 return true;
		 }
	}
}
