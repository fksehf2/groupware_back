/**
 *
 */

package frame.fexception;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;

import egovframework.com.cmm.EgovMessageSource;

/**
 * <pre>
 * 1. 클래스명 : MException.java
 * 2. 작성일 : 2021. 4. 19.
 * 3. 작성자 : Eric
 * 4. 설명 : 
 * </pre>
 */

public class MException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int ERR_CODE;// 생성자를 통해 초기화 한다.


	public MException(String msg, int errcode){ //생성자

		super(msg);

		ERR_CODE=errcode;

	}
	
	
	public MException(String msg){// 생성자

		this(msg, 100);// ERR_CODE를 100(기본값)으로 초기화한다.

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : getErrCode
	 * 2. 작성일 : 2021. 4. 19. 오후 5:09:21
	 * 3. 작성자 : Eric
	 * 4. 설명 : 
	 * </pre>
	 * @return
	 */

	public int getErrCode(){// 에러 코드를 얻을 수 있는 메서드도 추가한다.

		return ERR_CODE;// 이 메서드는 주로 getMessage()와 함께 사용될 것이다.

	}
	
	
	
	/*
	private ErrorCode errorCode;
		 
	public MException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }*/


}
