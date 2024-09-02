package com.group.groupware.controller;

import com.group.groupware.dto.QnaBoard;
import com.group.groupware.dto.QnaComent;
import com.group.groupware.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class QnaController {

    protected Logger log = LoggerFactory.getLogger(QnaController.class);

    private final QnaService qnaService;


    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaList
     * 2. 작성일 : 2024. 08. 03
     * 3. 작성자 : seran
     * 4. 설명 : qna 목록 조회 컨트롤러
     * </pre>
     * @param params
     * @return QnaBoard
     * @throws Exception
     */

    @GetMapping("/qnaList")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaBoard> getQnaList (@RequestParam Map<String, Object> params, @RequestParam Integer offset,
                                      @RequestParam Integer perPageNum) throws Exception{
        log.info("start getQna: {}", params);
        List<QnaBoard> qnaList = qnaService.getQnaList(params, offset, perPageNum);
        int totCnt = qnaService.getQnaTotCnt(params, offset, perPageNum);
//        qnaList.add()
        return qnaList;
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : getQnaDetail
     * 2. 작성일 : 2024. 08. 03
     * 3. 작성자 : seran
     * 4. 설명 : qna 상세 조회 컨트롤러
     * </pre>
     * @param num
     * @return QnaBoard
     * @throws Exception
     */
    @GetMapping("/qnaDetail/{num}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaBoard> getQnaDetail (@PathVariable String num) throws Exception{
        System.out.println("num     "+ num);
        List<QnaBoard> qnaDetail = qnaService.getQnaDetail(num);
        return qnaDetail;
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : getComent
     * 2. 작성일 : 2024. 08. 04
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 조회 컨트롤러
     * </pre>
     * @param num
     * @return QnaComent
     * @throws Exception
     */
    @GetMapping("/coment/{num}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaComent> getComent(@PathVariable String num) throws Exception{
        log.info("getParameter", num);
        List<QnaComent> qnaComents = qnaService.getComment(num);
        return qnaComents;
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : delComt
     * 2. 작성일 : 2024. 08. 05.
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 삭제 컨트롤러
     * </pre>
     * @param comt
     * @return
     * @throws Exception
     */
    @GetMapping("/delComt/{comt}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public void delComt(@PathVariable String comt) throws Exception{
        System.out.println("QnaController.delComt   " + comt);
        qnaService.delComt(comt);
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : regComnt
     * 2. 작성일 : 2024. 08. 06.
     * 3. 작성자 : seran
     * 4. 설명 : qna 댓글 등록 컨트롤러
     * </pre>
     * @param qnaComent
     * @return QnaComent
     * @throws Exception
     */
    @PostMapping("/comnt")
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    public void regComnt(@RequestBody QnaComent qnaComent) throws Exception{
        log.info("getParameter", qnaComent);
        qnaService.regComnt(qnaComent);

    }
}
