package egovframework.com.utl.fcc.service;

/**
 * 
 * 번호유효성체크 에 대한 Util 클래스 
 * @author 공통컴포넌트 개발팀 윤성록
 * @since 2009.06.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.10  윤성록          최초 생성
 *
 * </pre>
 */
public class EgovNumberCheckUtil {
    
    /**
     * <p>XXXXXX - XXXXXXX 형식의 법인번호 앞, 뒤 문자열 2개 입력 받아 유효한 법인번호인지 검사.</p>
     * 
     * 
     * @param   6자리 법인앞번호 문자열 , 7자리 법인뒷번호 문자열
     * @return  유효한 법인번호인지 여부 (True/False)
     */
    public static boolean checkBubinNumber(String bubin1, String bubin2) {
    	
    	String bubinNumber = bubin1 + bubin2;
    	
    	int hap = 0;
    	int temp = 1;	//유효검증식에 사용하기 위한 변수
    	
    	if(bubinNumber.length() != 13) return false;	//법인번호의 자리수가 맞는 지를 확인    		
    	
    	for(int i=0; i < 13; i++){
    		if (bubinNumber.charAt(i) < '0' || bubinNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인
    			return false;    		
    	}
    	
    	

    	for ( int i=0; i<13; i++){
    		if(temp ==3) temp = 1;    		
    		hap = hap + (Character.getNumericValue(bubinNumber.charAt(i)) * temp);
    		temp++;
    	}	//검증을 위한 식의 계산
    				
    	if ((10 - (hap%10))%10 == Character.getNumericValue(bubinNumber.charAt(12))) //마지막 유효숫자와 검증식을 통한 값의 비교
    		return true;
    	else
    		return false;    				
    	}
    
    /**
     * <p>XXXXXXXXXXXXX 형식의 13자리 법인번호 1개를 입력 받아 유효한 법인번호인지 검사.</p>
     *     
     * 
     * @param   13자리 법인번호 문자열
     * @return  유효한 법인번호인지 여부 (True/False)
     */
    public static boolean checkBubinNumber(String bubin) {
    	
    	if(bubin.length() != 13) return false;
    	
    	return checkBubinNumber(bubin.substring(0,6), bubin.substring(6,13));
    	}
       

    /**
     * <p>XXX - XX - XXXXX 형식의 사업자번호 앞,중간, 뒤 문자열 3개 입력 받아 유효한 사업자번호인지 검사.</p>
     *     
     * 
     * @param   3자리 사업자앞번호 문자열 , 2자리 사업자중간번호 문자열, 5자리 사업자뒷번호 문자열
     * @return  유효한 사업자번호인지 여부 (True/False)
     */
	public static boolean checkCompNumber(String comp1, String comp2, String comp3) {
	
		String compNumber = comp1 + comp2 + comp3;
		
		int hap = 0;
		int temp = 0;
		int check[] = {1,3,7,1,3,7,1,3,5};  //사업자번호 유효성 체크 필요한 수
	
		if(compNumber.length() != 10)    //사업자번호의 길이가 맞는지를 확인한다.
			return false;
	
		for(int i=0; i < 9; i++){
			if(compNumber.charAt(i) < '0' || compNumber.charAt(i) > '9')  //숫자가 아닌 값이 들어왔는지를 확인한다.
				return false; 
			
			hap = hap + (Character.getNumericValue(compNumber.charAt(i)) * check[temp]); //검증식 적용
			temp++;
		}
			
		hap += (Character.getNumericValue(compNumber.charAt(8))*5)/10;

		if ((10 - (hap%10))%10 == Character.getNumericValue(compNumber.charAt(9))) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
 	}	
	
	 /**
     * <p>XXXXXXXXXX 형식의 10자리 사업자번호 3개를 입력 받아 유효한 사업자번호인지 검사.</p>
     *      
     * 
     * @param   10자리 사업자번호 문자열
     * @return  유효한 사업자번호인지 여부 (True/False)
     */
	public static boolean checkCompNumber(String comp) {
		
		if(comp.length() != 10) return false;
		return checkCompNumber(comp.substring(0,3), comp.substring(3,5), comp.substring(5,10));
		
 	}	
	
	
	
}   


