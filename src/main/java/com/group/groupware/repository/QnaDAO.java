package com.group.groupware.repository;

import com.group.groupware.dto.QnaBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public class QnaDAO {

    @Autowired
    SqlSession session;

    public List<QnaBoard> getQnaList(Map<String, Object> params, Integer offset, Integer perPageNum) {
        params.put("offset", offset);
        params.put("perPageNum", perPageNum);
        return session.selectList("qnaDAO.qnaBoardList", params);
    }

    public int getQnaTotCnt(Map<String, Object> params, Integer offset, Integer perPageNum) {
        params.put("offset", offset);
        params.put("perPageNum", perPageNum);
        return session.selectOne("qnaDAO.getQnaTotCnt", params);
    }
}
