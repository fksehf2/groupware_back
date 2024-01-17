package frame.futil;

import java.util.HashMap;


/**
 * 
 * <pre>
 * 1. 클래스명 : PageUtil.java
 * 2. 작성일 : 2021. 5. 8.
 * 3. 작성자 : jmkim
 * 4. 설명 : 페이지 계산용 유틸
 * </pre>
 */

public class PageUtil {
	
   

    
    public static void calcPage(HashMap payload) {

    	int page = payload.get("page") == null ? Integer.parseInt((String) payload.get("pageNumber")) : Integer.parseInt((String) payload.get("page"));
    	int perPageNum = payload.get("pageSize") == null ? Integer.parseInt((String) payload.get("perPageNum")) : Integer.parseInt((String) payload.get("pageSize"));


    	int totalCount = (Integer) payload.get("totalCount");

    	int startPage;
    	int endPage;
    	boolean prev;
    	boolean next;
    	int displayPageNum =10;

    	endPage = (int) (Math.ceil(page / (double)displayPageNum ) * displayPageNum);

    	startPage = (endPage - displayPageNum) + 1;

    	int tempEndPage = (int)(Math.ceil(totalCount / (double)perPageNum));

    	if(endPage > tempEndPage){
    		endPage = tempEndPage;
    	}

    	prev = startPage ==1 ? false : true;

    	next = endPage * perPageNum >= totalCount ? false : true;

    	int pageStart = (page -1)* perPageNum;

    	int totalPages = (int)(Math.ceil(totalCount / (double)perPageNum));

    	payload.put("startPage", startPage);
    	payload.put("endPage", endPage);
    	payload.put("prev", prev);
    	payload.put("next", next);
    	payload.put("pageStart", pageStart);
    	payload.put("perPageNum", perPageNum);
    	payload.put("totalPages", totalPages);

    }

}
