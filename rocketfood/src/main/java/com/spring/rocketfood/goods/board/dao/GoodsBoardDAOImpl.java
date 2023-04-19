package com.spring.rocketfood.goods.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.goods.board.vo.GoodsQnaVO;
import com.spring.rocketfood.goods.board.vo.GoodsReviewVO;
import com.spring.rocketfood.goods.board.vo.ReviewHelpVO;


@Repository("goodsBoardDAO")
public class GoodsBoardDAOImpl implements GoodsBoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
	//상품 문의 글 불러오기
	@Override
	public List<GoodsQnaVO> selectAllGoodsQnaList(int goods_id) throws DataAccessException {
		List<GoodsQnaVO> bodsList = sqlSession.selectList("mapper.goodsBoard.selectAllQnaList", goods_id);
		return bodsList;
	}
	
	//상품 도움돼요
	@Override
	public List<GoodsReviewVO> selectMyReviewHelp(int goods_id, String member_id) throws DataAccessException {
		Map helpInfoMap = new HashMap();
		helpInfoMap.put("goods_id", goods_id);
		helpInfoMap.put("member_id", member_id);
		List<GoodsReviewVO> bodsList = sqlSession.selectList("mapper.goodsBoard.selectMyReviewHelp", helpInfoMap);
		return bodsList;
	}
	
	//상품 문의 추가하기
	@Override
	public int insertNewGoodsQna(Map<String, Object> bodMap) throws DataAccessException {
		//String board_name = (String) bodMap.get("board_name");
		//int article_id = selectNewArticleId(board_name);
		//bodMap.put("article_id", article_id);
		return sqlSession.insert("mapper.goodsBoard.insertNewQna", bodMap);
		 
	}
	
	//문의글 수정하기
	@Override
	public int updateQnaArticle(Map<String, Object> bodMap) throws DataAccessException {
		return sqlSession.update("mapper.goodsBoard.updateQnaArticle", bodMap);
	}
	
	//답변 달기
	@Override
	public int updateReply(Map<String, Object> bodMap) throws DataAccessException {
		return sqlSession.update("mapper.board.updateQnaReply", bodMap);
	}
	
	//상품 후기 글 불러오기
	@Override
	public List<Object> selectAllGoodsReviewList(int goods_id) throws DataAccessException {
		List<Object> reviewList = sqlSession.selectList("mapper.goodsBoard.selectAllReviewList", goods_id);
		return reviewList;
	}
		
	//상품 후기 글 추가하기
	@Override
	public int insertNewGoodsReview(Map<String, Object> reviewMap) throws DataAccessException {
		//String board_name = (String) reviewMap.get("board_name");
		//int article_id = selectNewArticleId(board_name);
		//reviewMap.put("article_id", article_id);
		return sqlSession.insert("mapper.goodsBoard.insertNewReview", reviewMap);
		
	}
	
	// 도움돼요 수정
	public int updateReviewHelp(ReviewHelpVO reviewHelpVO) throws DataAccessException {
		String help_y = reviewHelpVO.getHelp_y();
		
		if(help_y.equals("y")) {
			return sqlSession.insert("mapper.goodsBoard.insertMyHelp", reviewHelpVO);
		} else {
			return sqlSession.delete("mapper.goodsBoard.deleteMyHelp", reviewHelpVO);
		}
	}
	
	//article_id 새로 만들기
	private int selectNewArticleId(String board_name) throws DataAccessException {
		return sqlSession.selectOne("mapper.goodsBoard.selectNewArticleId", board_name);		
	}
	

}