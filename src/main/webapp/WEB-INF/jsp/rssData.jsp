<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script type="text/javascript">
    function go(link){
        location.href = '/rssContent?link=' + encodeURI(link);
    }

</script>
    
<table border="1" >
<c:forEach var="result" items="${rssDataList}" varStatus="status">
<tr>
	<td align="left" ><c:out value="${result.id}"/> </td>
	<td align="center" ><c:out value="${result.rssId}"/> </td>
	<td align="center" ><c:out value="${result.title}"/> </td>
	<td align="center"><a href="javascript:go('<c:out value="${result.link}"/>')"><c:out value="${result.link}"/></a> </td>
	<td align="center"><c:out value="${result.pubDate}"/> </td>
</tr>
</c:forEach>
</table>