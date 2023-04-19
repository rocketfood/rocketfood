package com.spring.rocketfood.goods.board.service;

import java.util.List;
import java.util.Map;

import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.GoodsReviewVO;
import com.spring.rocketfood.goods.board.vo.ReviewHelpVO;

public interface GoodsBoardService {
	public List<GoodsQnaVO> listGoodsQna(int goods_id) throws Exception;
	public int addNewGoodsQna(Map<String, Object> bodMap) throws Exception;
	public int addReply(Map<String, Object> bodMap) throws Exception;
	public List<Object> listGoodsReview(int goods_id) throws Exception;
	public List<GoodsReviewVO> listHelpReivew(int goods_id, String member_id) throws Exception;
	public int addNewGoodsReview(Map<String, Object> reviewMap) throws Exception;
	public Boolean updateMyHelp(ReviewHelpVO reviewHelpVO) throws Exception;
}
