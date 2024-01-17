<%--
/**
 * <pre>
 * 1. 작성일 : 2021. 4. 30. 오전 10:47:19
 * 2. 작성자 : sjw7240
 * 3. 화면명 : 로그 상세 팝업
 * 4. 설명 : 로그 상세 팝업
 * </pre>
 */
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>로그 상세 팝업</title>
<script type="text/javaScript" language="javascript" defer="defer">

    $(document).ready(function() {
    	
    	gfn_init({startFnNm:'fn_queryDtl', param:1, codeSet:"N"});

    });
    
    function fn_queryDtl() {
	    var callUrl = "<c:url value="/fsys/log/queryFsysLogDtlQList.do"/>";
	
	    requestUtil.search({callUrl:callUrl, srhFormNm:'insForm', callbackNm:'fn_queryDtlQListCallback'});
	}

function fn_queryDtlQListCallback(data){
}

</script>

</head>
<body>
<div id="con_wrap_pop">
	<div class="contents">
               <div id="contents_info">
                      <!--- contnets  적용 ------>
                      <div class="window_popup">
                          <div class="sub_ttl">상세 로그 확인</div>
                         
                          <div class="sub">
                             <!--------------검색------------------>
                             <form name="insForm" id="insForm" method="post">
			                     <input type="hidden" id="srcRequstId" name="srcRequstId" value="<c:out value="${paramMap.srcRequstId}" />" />
			                      <div class="t_list">
					                 <table class="iptTblX">
						               <caption>상세</caption>
						               <colgroup>
							             <col width="20%" />
							             <col width="*" />
						               </colgroup>
						               <tbody>
						               		<tr>
						                        <th scope="row">ID</th>
						                        <td >
						                            <span id="rqesterId"/> 
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">메뉴명</th>
						                        <td>
						                            <span id="menuNm"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">URL</th>
						                        <td>
						                            <span id="url"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">등록일</th>
						                        <td>
						                            <span id="occrrncDeDt"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">IP</th>
						                        <td>
						                            <span id="rqesterIp"/>
						                        </td>
						                    </tr>
						                    <tr>
						                        <th scope="row">PARAM</th>
						                        <td>
						                            <span id="rqesterParam"/>
						                        </td>
						                    </tr>
						            </tbody>
					                 </table>
				                  </div>
								</form>
                           
                          </div>
                         
                    </div>
               </div>
                 <!---  //contnets  적용 ------>
       </div>
  </div>
</body>
</html>