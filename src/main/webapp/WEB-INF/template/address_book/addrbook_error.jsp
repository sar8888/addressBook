<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE HTML>
<html>
<body>
    <div align="center">
        <h2>주소록 에러</h2>
        <hr>
        <table cellpadding="5" width="400">
            <tr width="100%" bgcolor="pink">
                <td>
                    주소록 처리중 에러가 발생 했습니다.<br>
                    관리자에게 문의해 주세요..<br>
                    빠른시일내 복구하겠습니다.
                    <hr>
                    에러내용 : <%=exception%>
                    <hr>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>