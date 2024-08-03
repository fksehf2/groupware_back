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
     * 2. 작성일 : 2024. 07. 08.
     * 3. 작성자 : seran
     * 4. 설명 : qna 목록 조회 컨트롤러
     * </pre>
     * @param params
     * @return
     * @throws Exception
     */

    @GetMapping("/getQnaList")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaBoard> getQnaList (@RequestParam Map<String, Object> params, @RequestParam Integer offset,
                                      @RequestParam Integer perPageNum) throws Exception{
        log.info("start getQna: {}", params);
        List<QnaBoard> qnaList = qnaService.getQnaList(params, offset, perPageNum);
        int totCnt = qnaService.getQnaTotCnt(params, offset, perPageNum);
//        qnaList.add()
        return qnaList;
    }

    @GetMapping("/getQnaDetail/{num}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaBoard> getQnaDetail (@PathVariable String num) throws Exception{
        System.out.println("num     "+ num);
        List<QnaBoard> qnaDetail = qnaService.getQnaDetail(num);
        return qnaDetail;
    }

    @GetMapping("/getComent/{num}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<QnaComent> getComent(@PathVariable String num) throws Exception{
        log.info("getParameter", num);
        List<QnaComent> qnaComents = qnaService.getComment(num);
        return qnaComents;
    }
}
