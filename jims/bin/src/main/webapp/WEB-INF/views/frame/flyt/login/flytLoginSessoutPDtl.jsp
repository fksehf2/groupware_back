<%
 /**
  * @Class Name : SessionOut.jsp
  * @Description : 세션 아웃 시 메인 프레임을 login페이지로 이동
  * @Modification Information
  * 
  * @author 김규형
  * @since 2018.10.18
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Jenix 통합 관리 시스템</title>

<!-- css 및 js include  -->

<script type="text/javaScript" language="javascript" defer="defer">
//alert("사용시간이 경과하여 로그아웃 되었습니다.\n로그인 후 사용해주시기 바랍니다.");
window.top.location.href="<c:url value='/login/login.do' />";
<!--

// -->
</script>
</head>
<body>

</body>
</html>