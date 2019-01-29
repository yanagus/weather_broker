<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Enter city</title>
    <meta charset="UTF-8">
    <style>
        .error
        {
            color: #ff0000;
            font-weight: bold;
        }
        </style>
</head>
<body>
<h2>Enter city</h2>
      <c:if test="${not empty exception.message}">
          <p style="color: red; font-weight: bold;">
              <c:out value="${exception.message}"/>
          </p>
      </c:if>

      <form:form method = "POST" action = "/stady_war_exploded/" modelAttribute="city">
         <table>
            <tr>
               <td><form:label path = "name">City</form:label></td>
               <td><form:input path = "name" placeholder = "Moscow or New York"/></td>
               <td><form:errors path = "name" cssClass = "error" /></td>
            </tr>
            <tr>
               <td><form:label path = "region">Region</form:label></td>
               <td><form:input path = "region" placeholder="Moscow Oblast or NY"/></td>
               <td><form:errors path = "region" cssClass = "error" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
         </table>
      </form:form>
</body>
</html>