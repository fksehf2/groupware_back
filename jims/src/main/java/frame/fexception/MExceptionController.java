/**
 *
 */

package frame.fexception;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import egovframework.com.cmm.EgovMessageSource;
import frame.futil.JimsConst;
import frame.futil.JimsUtil;

/**
 * <pre>
 * 1. 클래스명 : ExceptionController.java
 * 2. 작성일 : 2021. 4. 19.
 * 3. 작성자 : Eric
 * 4. 설명 : 
 * </pre>
 */
@ControllerAdvice
public class MExceptionController {

	//@Resource MappingJackson2JsonView ajaxMainView;

	

	/*

	@ExceptionHandler(MException.class)	
	public ModelAndView noDelivery(MException nde) {
		
		
		System.out.println("Exception Controller ______");
		//ModelAndView mView=new ModelAndView();
		ModelAndView mView = new ModelAndView("jsonView", new HashMap());
		mView.addObject("exception",nde);
		mView.setViewName("validate");

		//return new ModelAndView();
		return mView;
	}
	*/
	
	@ExceptionHandler(MException.class)
    protected ResponseEntity<HashMap> noDelivery(MException e) {
        
    	System.out.println("noDelivery___________");
       
        HashMap map = new HashMap();
        
       
        
        map.put(JimsConst.Messages_UserErrMessage, e.getMessage());
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    
	}
	
	
    
	
	/*
	public ResponseEntity<String> noDelivery(MException nde,ModelMap model) {
		
		
		System.out.println("Exception Controller ______");
		ModelAndView mView=new ModelAndView();
		//mView.addObject("exception",nde);
		//mView.setViewName("code501");

		model.addAttribute("message", (nde.getMessage()));
		return new ResponseEntity<String>(nde.getMessage(), HttpStatus.BAD_REQUEST);
	}
*/
}
