package com.spring.rocketfood.goods.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.GoodsReviewVO;
import com.spring.rocketfood.goods.board.vo.ReviewHelpVO;

public interface GoodsBoardDAO {
	public List<GoodsQnaVO> selectAllGoodsQnaList(int goods_id) throws DataAccessException;
	public int insertNewGoodsQna(Map<String, Object> bodMap) throws DataAccessException;
	public int updateQnaArticle(Map<String, Object> bodMap) throws DataAccessException;
	public int updateReply(Map<String, Object> bodMap) throws DataAccessException;
	public List<Object> selectAllGoodsReviewList(int goods_id) throws DataAccessException;
	public List<GoodsReviewVO> selectMyReviewHelp(int goods_id, String member_id) throws DataAccessException;
	public int insertNewGoodsReview(Map<String, Object> reviewMap) throws DataAccessException;
	public int updateReviewHelp(ReviewHelpVO reviewHelpVO) throws DataAccessException;

}
