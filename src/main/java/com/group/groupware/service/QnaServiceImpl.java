package com.group.groupware.service;

import com.group.groupware.dto.QnaBoard;
import com.group.groupware.dto.QnaComent;
import com.group.groupware.repository.EqpMgmtDAO;
import com.group.groupware.repository.QnaDAO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QnaServiceImpl implements QnaService{

    @Resource(name="qnaDAO")
    private QnaDAO qnaDAO;


    /**
     * Qna 조회 impl
     */
    @Override
    public List<QnaBoard> getQnaList(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return qnaDAO.getQnaList(params, offset, perPageNum);
    }

    /**
     * Qna 목록 카운트 impl
     */
    @Override
    public int getQnaTotCnt(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return qnaDAO.getQnaTotCnt(params, offset, perPageNum);
    }

    /**
     * Qna 상세 조회 impl
     */
    @Override
    public List<QnaBoard> getQnaDetail(String num) throws Exception {
        return qnaDAO.getQnaDetail(num);
    }

    /**
     * Qna 댓글 조회 impl
     */
    @Override
    public List<QnaComent> getComment(String num) throws Exception {
        return qnaDAO.getComment(num);
    }

    /**
     * Qna 댓글 삭제 impl
     */
    @Override
    public void delComt(String comt) throws Exception {
        qnaDAO.delComt(comt);
    }

    /**
     * Qna 댓글 등록 impl
     */
    @Override
    public void regComnt(QnaComent qnaComent) throws Exception {
        qnaDAO.regComnt(qnaComent);
    }
}
