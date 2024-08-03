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

    @Override
    public List<QnaBoard> getQnaList(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return qnaDAO.getQnaList(params, offset, perPageNum);
    }

    @Override
    public int getQnaTotCnt(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return qnaDAO.getQnaTotCnt(params, offset, perPageNum);
    }

    @Override
    public List<QnaBoard> getQnaDetail(String num) throws Exception {
        return qnaDAO.getQnaDetail(num);
    }

    @Override
    public List<QnaComent> getComment(String num) throws Exception {
        return qnaDAO.getComment(num);
    }
}
