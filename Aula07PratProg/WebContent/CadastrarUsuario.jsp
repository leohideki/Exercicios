
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cadastrar usuario</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<c:import url="Menu.jsp"/>
<div id="main" class="container">
       <h3 class="page-header">cadastrar</h3>
	<form action="controller.do" method="post">
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="username">usuario</label>
                    <input type="text" class="form-control" name="username" id="username" required  placeholder="username">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" id="password" required   placeholder="password">
                </div>

            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="command" value="CadastrarUsuario">Salvar</button>
                    <a href="index.jsp" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
        </div>
</body>
</html>