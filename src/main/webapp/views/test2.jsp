<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<body>
<h1>test page</h1>

<h1>
    Name: ${name}, id= ${id}
</h1>
<hr>


<c:forEach var="item" items="${marks }">
    <h1>${item }</h1>
</c:forEach>


</body>
</html>