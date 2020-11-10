<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit article</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <form:form modelAttribute="articleDto">
        <fieldset>
            <legend>Article data:</legend>
            <form:hidden path="id"/>

            <form:label path="title">Title: </form:label>
            <form:input path="title"/>
            <form:errors path="title" cssClass="text-danger"/>
            <br/>
            <form:label path="content">Content: </form:label>
            <form:input path="content"/>
            <form:errors path="content" cssClass="text-danger"/>
            <br/>
            <form:label path="authorDto">Author: </form:label>
            <form:select path="authorDto" items="${authorDtos}" itemLabel="fullName" itemValue="id"/>
            <form:errors path="authorDto" cssClass="text-danger"/>
            <br/>
            <form:label path="categoryDtos">Authors: </form:label>
            <form:select path="categoryDtos" items="${categoryDtos}" itemLabel="name" itemValue="id" multiple="true"/>
            <form:errors path="categoryDtos" cssClass="text-danger"/>
            <br/>
            <input type="submit" value="Submit"/>
        </fieldset>
    </form:form>

</body>
</html>
