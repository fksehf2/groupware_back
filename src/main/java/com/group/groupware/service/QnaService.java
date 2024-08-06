package com.group.groupware.service;

import com.group.groupware.dto.QnaBoard;
import com.group.groupware.dto.QnaComent;

import java.util.List;
import java.util.Map;

public interface QnaService {

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaList
     * 2. 작성일 : 2024. 07. 08.
     * 3. 작성자 : seran
     * 4. 설명 : qna 리스트 조회 서비스
     * </pre>
     * @param
     * @throws Exception
     */

    List<QnaBoard> getQnaList (Map<String, Object> params, Integer offset,
                                      Integer perPageNum) throws Exception;

    int getQnaTotCnt(Map<String, Object> params, Integer offset,
                            Integer perPageNum) throws Exception;

    List<QnaBoard> getQnaDetail(String num) throws Exception;

    List<QnaComent> getComment(String num) throws Exception;

    void delComt(String comt) throws Exception;

    void regComnt(QnaComent qnaComent) throws Exception;
}
