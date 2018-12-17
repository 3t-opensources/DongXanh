<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Multiple File Upload Example using Single Browse button -
	Struts2</title>
</head>
<body>
	<!-- show error message only when error occurrs -->
	<s:if test="hasActionErrors()">
		<s:actionerror />
	</s:if>
	<!-- show success message only when file successfully uploads -->
	<s:if test="hasActionMessages()">
		<s:actionmessage />
	</s:if>
	<%-- <s:form action="uploadMultipleFile" method="post"
		enctype="multipart/form-data">
		<!-- input type file for file upload, make sure to set multiple="multiple" for multiple uploads -->
		<s:file label="File" id="files" name="files" multiple="multiple"></s:file>
		<s:submit value="Upload Files"></s:submit>
	</s:form> --%>
	
	<s:form action="uploadMultipleFile" method="post"
		enctype="multipart/form-data">		
		<label>Upload (*.jpg, *.png, *.gif) </label>
		<input id="files" name="files" type="file" multiple/>
		<s:submit value="Upload All"></s:submit>
	</s:form>				
	
</body>
</html>