/**
 *
 */

package frame.futil;

import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.EgovMessageSource;
import frame.fexception.MException;

/**
 * <pre>
 * 1. 클래스명 : ValidateUtil.java
 * 2. 작성일 : 2021. 4. 19.
 * 3. 작성자 : jmkim
 * 4. 설명 : 
 * </pre>
 */

@Component
public class ValidateUtil {

	protected Logger log = LoggerFactory.getLogger(ValidateUtil.class);

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;


	/**
	 * <pre>
	 * 1. 메소드명 : check
	 * 2. 작성일 : 2021. 4. 19. 오후 5:50:47
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 
	 * </pre>
	 * @param map
	 * @param manParams
	 * @throws MException 
	 */
	
	public void check(HashMap<String, String> paramMap, HashMap<String, String> manParams) throws MException {
		// TODO Auto-generated method stub
		
		if( log.isDebugEnabled()) {
			log.debug("paramMap : {}", paramMap);
			log.debug("manParams : {} ", manParams);
		}
		

		Iterator<String> keys = manParams.keySet().iterator();
        while (keys.hasNext()){
            String key = keys.next();
            if( log.isDebugEnabled()) {
    			log.debug("KEY : " + key);
    		}
            String message[] = null;
            if( "".equals(paramMap.get(key) )){
            	
            	message = manParams.get(key).split("[||]");
            	 Object[] args = {message[0]};
            	if( log.isDebugEnabled()) {
        			log.debug("message : {}", egovMessageSource.getMessageArgs("errors.required", args));
        		}
        		
            	throw new MException(egovMessageSource.getMessageArgs("errors.required", args));
            }
            
            
        }
		
		
	}

}
