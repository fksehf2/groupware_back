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
     * 2. 작성일 : 2024. 08. 06.
     * 3. 작성자 : seran
     * 4. 설명 : qna 목록 조회 serivce
     * </pre>
     * @param
     * @throws Exception
     */

    List<QnaBoard> getQnaList (Map<String, Object> params, Integer offset,
                                      Integer perPageNum) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaTotCnt
     * 2. 작성일 : 2024. 08. 06
     * 3. 작성자 : seran
     * 4. 설명 : qna 목록 카운트 serivce
     * </pre>
     * @param
     * @throws Exception
     */

    int getQnaTotCnt(Map<String, Object> params, Integer offset,
                            Integer perPageNum) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaDetail
     * 2. 작성일 : 2024. 08. 03
     * 3. 작성자 : seran
     * 4. 설명 : qna 상세 조회 serivce
     * </pre>
     * @param num
     * @throws Exception
     */
    List<QnaBoard> getQnaDetail(String num) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaDetail
     * 2. 작성일 : 2024. 08. 04
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 조회 serivce
     * </pre>
     * @param num
     * @throws Exception
     */
    List<QnaComent> getComment(String num) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : delComt
     * 2. 작성일 : 2024. 08. 05.
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 삭제serivce
     * </pre>
     * @param comt
     * @throws Exception
     */
    void delComt(String comt) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : regComnt
     * 2. 작성일 : 2024. 08. 06
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 등록 serivce
     * </pre>
     * @param qnaComent
     * @throws Exception
     */
    void regComnt(QnaComent qnaComent) throws Exception;
}
