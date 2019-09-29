<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Online Store</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
  /* Make whole card clickable */ 
	  .hyperspan {
	    position:absolute;
	    width:100%;
	    height:100%;
	    left:0;
	    top:0;
	}
 	 #add-to-cart{
  		position: absolute;
  		right: 30px;
	 }
    /* Remove the navbar's default rounded borders and increase the bottom margin */ 
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
  </style>
</head>
<body>

	<div class="jumbotron">
	  <div class="container text-center">
	    <h1>Online Store</h1>      
	    <p>Mission, Vission & Values</p>
	  </div>
	</div>
	
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Stores</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>  


			
			<form action="ControllerServlet" method="GET">
				<input type="hidden" name="command" value="UPDATE" >
				<input type="hidden" name="productKey" value="${THE_PRODUCT.productKey}" >
				<table>
					<tr>
						<td><label>Product Name:</label></td>
						<td><input type="text" name="firstName" value="${THE_PRODUCT.name}"/></td>
					</tr>
					
					<tr>
						<td><label>Type:</label></td>
						<td><input type="text" name="lastName" value="${THE_PRODUCT.type}"/></td>
					</tr>
					
					<tr>
						<td><label>Description:</label></td>
						<td><input type="text" name="email" value="${THE_PRODUCT.description}"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="ADD TO CART" class="save"/></td>
					</tr>
				</table>
			
			</form>
			
	<footer class="container-fluid text-center">
	  <p>Online Store Copyright</p>  
	  <form class="form-inline">Get deals:
	    <input type="email" class="form-control" size="50" placeholder="Email Address">
	    <button type="button" class="btn btn-danger">Sign Up</button>
	  </form>
	</footer>

</body>
</html>
			