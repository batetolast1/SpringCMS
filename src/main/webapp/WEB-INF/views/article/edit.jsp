<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit article</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <form:form modelAttribute="articleDto">
        <fieldset>
            <legend>Article data:</legend>
            <form:hidden path="id"/>

            <form:label path="title">Title: </form:label>
            <form:input path="title"/>
            <br/>
            <form:label path="content">Content: </form:label>
            <form:input path="content"/>
            <br/>
            <form:label path="authorDto">Author: </form:label>
            <form:select path="authorDto" items="${authorDtos}" itemLabel="fullName" itemValue="id"/>
            <br/>
            <form:label path="categoryDtos">Authors: </form:label>
            <form:select path="categoryDtos" items="${categoryDtos}" itemLabel="name" itemValue="id" multiple="true"/>
            <br/>
            <input type="submit" value="Submit"/>
        </fieldset>
    </form:form>

</body>
</html>
