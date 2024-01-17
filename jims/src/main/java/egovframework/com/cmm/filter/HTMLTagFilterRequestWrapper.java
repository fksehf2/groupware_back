/*
 * Copyright 2008-2009 MOPAS(MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


/**
*
* HTMLTagFilterRequestWrapper 
* @author 공통컴포넌트 팀 신용호
* @since 2018.03.21
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일              수정자              수정내용
*  -------      --------    ---------------------------
*   2018.03.21  신용호              getParameterMap()구현 추가
*   2019.01.31  신용호              whiteList 태그 추가
*
*/

public class HTMLTagFilterRequestWrapper extends HttpServletRequestWrapper {

	// Tag 화이트 리스트 ( 허용할 태그 등록 )
	static private String[] whiteListTag = { "<p>","</p>","<br />" };
	private final Charset encoding;
	private byte[] rawData;
	private HashMap<String, Object> params;

	
	public HTMLTagFilterRequestWrapper(HttpServletRequest request) {
		super(request);
		
		//this.params.putAll(request.getParameterMap()); // 원래의 파라미터를 저장
		this.params = new HashMap<String, Object>(request.getParameterMap());

		String charEncoding = request.getCharacterEncoding(); // 인코딩 설정
		this.encoding = StringUtils.isBlank(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);

		try {
			InputStream is = request.getInputStream();
			this.rawData = IOUtils.toByteArray(is); // InputStream 을 별도로 저장한 다음 getReader() 에서 새 스트림으로 생성

			// body 파싱
			/*String collect = this.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			if (StringUtils.isEmpty(collect)) { // body 가 없을경우 로깅 제외
				return;
			}
			if (request.getContentType() != null && request.getContentType().contains(
				ContentType.MULTIPART_FORM_DATA.getMimeType())) { // 파일 업로드시 로깅제외
				return;
			}
			
			System.out.println("collect__"+collect);*/
		} catch (Exception e) {
		}
		
	}
	/*
    public String getParameter(String name) {
        String returnValue = null;
        String[] paramArray = getParameterValues(name);
        if (paramArray != null && paramArray.length > 0) {
            returnValue = paramArray[0];
        }
        return returnValue;
    }

    @SuppressWarnings("unchecked")
    public Map getParameterMap() {
        return Collections.unmodifiableMap(params);
    }

    @SuppressWarnings("unchecked")
    public Enumeration getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    public String[] getParameterValues(String name) {
        String[] result = null;
        String[] temp = (String[]) params.get(name);
        if (temp != null) {
            result = new String[temp.length];
            System.arraycopy(temp, 0, result, 0, temp.length);
        }
        return result;
    }

    public void setParameter(String name, String value) {
        String[] oneParam = { value };
        setParameter(name, oneParam);
    }

    public void setParameter(String name, String[] value) {
        params.put(name, value);
    }
	
    @Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);

		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

		
			public int read() {
				return byteArrayInputStream.read();
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	 */
	
    public String[] getParameterValues(String name) {
        String[] result = null;
        String[] temp = (String[]) params.get(name);
        if (temp != null) {
            result = new String[temp.length];
            System.arraycopy(temp, 0, result, 0, temp.length);
        }
        
        if(result==null){
			return null;			
		}
		
		for (int i = 0; i < result.length; i++) {
			if (result[i] != null) {
				result[i] = getSafeParamData(result[i]);
				//System.out.println( "[HTMLTagFilter getParameterValues] "+ parameter + "===>>>"+values[i] );
			} else {
				result[i] = null;
			}
		}
        
        return result;
    }
    
    public String getParameter(String name) {
        String returnValue = null;
        String[] paramArray = getParameterValues(name);
        if (paramArray != null && paramArray.length > 0) {
            returnValue = paramArray[0];
        }
        
        if( returnValue == null) {
        	return null;
        }
        return getSafeParamData(returnValue);
    }

    /*
	public String getParameter(String parameter) {
		
		
		String value = super.getParameter(parameter);
		
		
		if(value==null){
			return null;
		}
		
		value = getSafeParamData(value);
		
		
		
		//System.out.println( "[HTMLTagFilter getParameter] "+ parameter + "===>>>"+value );
		return value;
	}
     */
	
    /*
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);
		
		if(values==null){
			return null;			
		}
		
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {
				values[i] = getSafeParamData(values[i]);
				//System.out.println( "[HTMLTagFilter getParameterValues] "+ parameter + "===>>>"+values[i] );
			} else {
				values[i] = null;
			}
		}

		return values;
	}
     */
	
    
    @SuppressWarnings("unchecked")
    public Map getParameterMap() {
        
    	 Map<String, Object> valueMap = Collections.unmodifiableMap(params);
    	

    	String[] values;
    	for( String key : valueMap.keySet() ){
    		values = (String[]) valueMap.get(key);

    		for (int i = 0; i < values.length; i++) {			
    			if (values[i] != null) {				
    				values[i] = getSafeParamData(values[i]);
    				//System.out.println( "[HTMLTagFilter getParameterMap] "+ key + "===>>>"+values[i] );
    			} else {
    				values[i] = null;
    			}
    		}
    		
            //System.out.println( String.format("키 : %s, 값 : %s", key, valueMap.get(key)) );
        }
    	
    	return valueMap;//Collections.unmodifiableMap(params);
    }


	/**
	 * Map으로 바인딩된 경우를 처리한다.
	 *
	 * @return  Map - String Type Key / String배열타입 값
	 */
    /*public Map<String, String[]> getParameterMap() {
    	Map<String, String[]> valueMap = super.getParameterMap();

    	String[] values;
    	for( String key : valueMap.keySet() ){
    		values = valueMap.get(key);

    		for (int i = 0; i < values.length; i++) {			
    			if (values[i] != null) {				
    				values[i] = getSafeParamData(values[i]);
    				//System.out.println( "[HTMLTagFilter getParameterMap] "+ key + "===>>>"+values[i] );
    			} else {
    				values[i] = null;
    			}
    		}
    		
            //System.out.println( String.format("키 : %s, 값 : %s", key, valueMap.get(key)) );
        }

    	return valueMap;
    }*/
    
	  @SuppressWarnings("unchecked")
	    public Enumeration getParameterNames() {
	        return Collections.enumeration(params.keySet());
	    }
    
	private String getSafeParamData(String value) {
		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				if ( checkNextWhiteListTag(i, value) == false )
					strBuff.append("&lt;");
				else 
					strBuff.append(c);
				//System.out.println("checkNextWhiteListTag = "+checkNextWhiteListTag(i, value));
				break;
			case '>':
				if ( checkPrevWhiteListTag(i, value) == false )
					strBuff.append("&gt;");
				else 
					strBuff.append(c);
				//System.out.println("checkPrevWhiteListTag = "+checkPrevWhiteListTag(i, value));
				break;
			//case '&':
			//	strBuff.append("&amp;");
			//	break;
			case '"':
				strBuff.append("&quot;");
				break;
			case '\'':
				strBuff.append("&apos;");
				break;	
			default:
				strBuff.append(c);
				break;
			}
		}
		
		value = strBuff.toString();
		return value;
	}

	private boolean checkNextWhiteListTag(int index, String data) {
		String extractData = "";
		//int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    //System.out.println("===>>> whiteListData="+whiteListData);
			endIndex = index+whiteListData.length();
		    if ( data.length() > endIndex )
		    	extractData = data.substring(index, endIndex);
		    else
		    	extractData = "";
		    //System.out.println("extractData="+extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
	
	private boolean checkPrevWhiteListTag(int index, String data) {
		String extractData = "";
		int beginIndex = 0;
		int endIndex = 0;
		for(String whiteListData: whiteListTag) {
		    //System.out.println("===>>> whiteListData="+whiteListData);
			beginIndex = index-whiteListData.length()+1;
			endIndex = index+1;
		    //System.out.println("  range ["+beginIndex+" ~ "+endIndex+"]");
		    if ( beginIndex >= 0 )
		    	extractData = data.substring(beginIndex, endIndex);
		    else
		    	extractData = "";
		    //System.out.println("extractData="+extractData);
		    if ( whiteListData.equals(extractData) ) return true; // whiteList 대상으로 판정
		}
		
		return false;
	}
	
	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);

		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

		
			public int read() {
				return byteArrayInputStream.read();
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public void setParameter(String name, String value) {
		
		String[] oneParam = {value};
		setParameter(name, oneParam);
	}

	/**
	 * <pre>
	 * 1. 메소드명 : setParameter
	 * 2. 작성일 : 2021. 5. 9. 오전 11:01:54
	 * 3. 작성자 : Eric
	 * 4. 설명 : 
	 * </pre>
	 * @param name
	 * @param oneParam
	 */
	
	private void setParameter(String name, String[] value) {

		params.put(name,value);
	}
	

 
//	@Override
//	public BufferedReader getReader() {
//		return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
//	}

}