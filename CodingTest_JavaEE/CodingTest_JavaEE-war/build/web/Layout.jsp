<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.title}</title>
    </head>
    <body>
        <jsp:include page="${requestScope.content}" >
            <jsp:param name="params" value="${requestScope.params}" />
        </jsp:include>
    </body>
</html>
