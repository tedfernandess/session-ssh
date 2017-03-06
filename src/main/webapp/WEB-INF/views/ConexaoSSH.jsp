<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connect here to a server</title>
</head>
<body>
	<h1>SSH Connect</h1>
	<p id="message"/>
	<form name="connectSSH" action="conectarServidor" method="post">
		Hostname:<input type="text" name="host"/>
		Username:<input type="text" name="username">
		Password:<input type="password" name="password">
		Port:<input type="text" name="port" onkeypress='return event.charCode >=48 && event.charCode <=57' value="22">
<%-- 		Port:<input type="text" onkeypress='return event.charCode >=48 && event.charCode <=57' name="port" value="0"> --%>
		<input type="submit" value="Conectar ao servidor"/>
	</form>
	
	<script type="text/javascript">
		function validar(){
			
			var formulario = document.forms["connectSSH"].childNodes;
			alert(formulario.length + "");
			
// 			var port = document.forms["connectSSH"]["port"].value;
			
// 			var result=false;
// 			result = doubleCheck(host) && doubleCheck() && doubleCheck() doubleCheck();
// 			if(port==""){
// 				document.getElementById("message").innerHTML = "Preenche correctamente o campo porta."
// 				return false
// 			}
// 			return result;
		}

		function doubleCheck(campo){
			if(campo==""){
				document.getElementById("message").innerHTML = "Preenche correctamente os campos."
				return false
			}
			return true;
		}
		</script>

</body>
</html>