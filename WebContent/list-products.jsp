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
  		right: 5px;
  		bottom: 4px;
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
	<div class="row">
		<div class="col-sm-4" >
			<img src="images/divbg2.jpeg?text=IMAGE" class="img-responsive" style="width:100%" alt="Image">	
		</div>
		<div class="col-sm-4">
			 <div class="container text-center">
    			<h1>Online Store</h1>      
    			<h3>Fresh, Organic @ Fair Price!</h3>
 			 </div>
		</div>
		<div class="col-sm-4" >
			<img src="images/divbg3.jpeg?text=IMAGE" class="img-responsive" style="width:100%" alt="Image">	
		</div>
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
        <li class="active"><a href="ControllerServlet?command=LIST">Home</a></li>
        <li><a href="#">Products</a></li>
        <li><a href="#">Deals</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Your Account</a></li>
        <li><a href="ControllerServlet?command=CART"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>  

<div class="row">  
  <div class="col-sm-2">Left Panel</div>
  <div class="col-sm-10">
	<div  class="container-fluid">    
	  <div class="row">
	  	<c:forEach var="tempProduct" items="${PRODUCT_LIST}">
	  		<!-- set up a link for each student -->
			<c:url var="toCartLink" value="ControllerServlet">
				<c:param name="command" value="ADD" />
				<c:param name="productKey" value="${tempProduct.productKey}" />
			</c:url>
			
			<c:url var="detailLink" value="ControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="productKey" value="${tempProduct.productKey}" />		
			</c:url>
			
		    <div class="col-sm-3">
	    	  
		      <div class="panel panel-primary" style="position: relative">
		      	<a href="${detailLink}"> 
      				<span class="hyperspan"></span>
  			 	 </a>
		        <div class="panel-heading">${tempProduct.name}</div>
		        <div class="panel-body"><img src="${tempProduct.imagePath}?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
		        <div class="panel-footer"> $ ${tempProduct.price} <a id="add-to-cart" href="${toCartLink}"><button type="button" class="btn btn-success">Add To Cart</button></a></div>
		      </div>
		    </div>
	    </c:forEach>
	   </div>
	</div><br><br>
  </div>
 </div>	
	
<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>
