<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table border="1" >
<c:forEach var="result" items="${rssList}" varStatus="status">
<tr>
	<td align="left" ><c:out value="${result.id}"/> </td>
	<td align="center" ><a href="/rssData"><c:out value="${result.name}"/></a> </td>
	<td align="center"><c:out value="${result.url}"/> </td>
</tr>
</c:forEach>
</table>