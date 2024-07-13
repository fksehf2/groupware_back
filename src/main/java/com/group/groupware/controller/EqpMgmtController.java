package com.group.groupware.controller;

import com.group.groupware.dto.EqpMgmt;
import com.group.groupware.service.EqpMgmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * <pre>
 * 1. 클래스명 : EqpMgmtController.java
 * 2. 작성일 : 2024.02.29
 * 3. 작성자 : seran
 * 4. 설명 : 장비 관리 컨트롤러
 * </pre>
 */

@RestController
public class EqpMgmtController {

    protected Logger log = LoggerFactory.getLogger(EqpMgmtController.class);

    private final EqpMgmtService eqpMgmtService;

    public EqpMgmtController(EqpMgmtService eqpMgmtService) {
        this.eqpMgmtService = eqpMgmtService;
    }

    /**
     *
     * <pre>
     * 1. 메소드명 : queryEqpMgmtMList
     * 2. 작성일 : 2024. 02. 29.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 목록 조회 컨트롤러
     * </pre>
     * @param params
     * @param offset
     * @param perPageNum
     * @return
     * @throws Exception
     */
    @GetMapping("/erpList")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List eqpList (@RequestParam Map<String, Object> params, @RequestParam Integer offset,
                         @RequestParam Integer perPageNum) throws Exception{

//    	System.out.println("Received offset: " + offset + ", limit: " + limit);
        System.out.println("Received params: " + params);

        List eqpMgmtMList = eqpMgmtService.getEqpList(params, offset, perPageNum);
//		int totCnt = eqpMgmtMList.size();
        int totCnt = eqpMgmtService.getErpTotCnt(params, offset, perPageNum);
        eqpMgmtMList.add(totCnt);
        return eqpMgmtMList;

    }

    @GetMapping("/erpCode/{cdId}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List erpCode(@PathVariable String cdId) throws Exception {
        System.out.println("cdId     "+ cdId);
        List erpCode = eqpMgmtService.getCode(cdId);
        return erpCode;
    }

    @GetMapping("/erpDtl")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List erpDtl(@RequestParam String eqpSno) throws Exception{
        System.out.println("eqpSno     "+ eqpSno);
        List erpDtl = eqpMgmtService.erpDtl(eqpSno);
        return erpDtl;
    }
    @GetMapping("/popupList")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List popupList (@RequestParam Map<String, Object> params, @RequestParam Integer offset,
                         @RequestParam Integer perPageNum) throws Exception{

//    	System.out.println("Received offset: " + offset + ", limit: " + limit);
        System.out.println("Received params: " + params);

        List eqpPopList = eqpMgmtService.popList(params, offset, perPageNum);
//		int totCnt = eqpMgmtMList.size();
        int totCnt = eqpMgmtService.getPopListCnt(params, offset, perPageNum);
        eqpPopList.add(totCnt);
        return eqpPopList;

    }


    @PostMapping("/regeqp")
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    public void regEqp (@RequestBody HashMap<String, Object> params ) throws Exception{
        System.out.println("params = " + params);
        eqpMgmtService.regEqpMgmtRDtl(params);

    }

    @GetMapping("/deleqp/{eqpSno}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public void delEqp(@PathVariable String eqpSno) throws Exception {
        System.out.println("eqpSno = " + eqpSno);
        eqpMgmtService.delEqpMgmtUDtl(eqpSno);
    }

    @PostMapping("/modifyeqp")
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    public void modifyEqp(@RequestBody HashMap<String, Object> params) throws Exception{
        System.out.println("params = " + params);
        eqpMgmtService.updEqpMgmtUDtl(params);
    }
}
