<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*" %>
<%@page import="com.dikuanteberh.web.jdbc.*" %>
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
  
  <div class="col-sm-8">
	<div  class="container-fluid">    
	
		<%Hashtable<Integer, Integer> ht = (Hashtable<Integer,Integer>)request.getAttribute("ITEM_COUNT"); %>  
	  	<c:forEach var="tempProduct" items="${PRODUCT_LIST}">
			<%
				
				Product p = (Product)pageContext.getAttribute("tempProduct");
				int count = ht.get(p.getProductKey());
				pageContext.setAttribute("COUNT", count);
			%>
			 <!-- <c:out value="Count: ${COUNT}"/> -->
			
			<c:url var="detailLink" value="ControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="productKey" value="${tempProduct.productKey}" />		
			</c:url>
			<div class="row">
				<div class="col-sm-4">
			      <div class="panel panel-primary" style="position: relative">
			      	<a href="${detailLink}"> 
	      				<span class="hyperspan"></span>
	  			 	 </a>
			        <div class="panel-body"><img src="${tempProduct.imagePath}?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> 
			      </div>
			    </div>
			    
			    <div class="col-sm-8">
			      <div class="panel panel-primary" style="position: relative">
			      	<div class="panel-body">
		        		Details: ${tempProduct.description} <br>
		        		Category: ${tempProduct.type}<br>
		        		In Stock: <%= "${tempProduct.quantity}".equals("0") ? "Not Available" : "Available" %>
		       		 </div>
		       		 <div style="position: relative; left:15px"> Price: $ ${tempProduct.price}</div> <br>
		       		 <div class="row" style="position: relative; left:15px; bottom:15px">
				       		<div class="col-sm-8">
				  				<form class="form-inline" action="ControllerServlet" method="GET">
									<input type="hidden" name="command" value="UPDATE" >
									<input type="hidden" name="productKey" value="${tempProduct.productKey}" >
									Quantity: <input type="number" name="quantity" value="${COUNT}" min="1" max="5">
									<button type="submit" value="Submit" title="Update Quantity With Value In Box." class="btn btn-success">UPDATE</button>
								</form>
								
		  					</div>
				       		 <div class="col-sm-4">
				  				<form action="ControllerServlet" method="GET">
									<input type="hidden" name="command" value="REMOVE" >
									<input type="hidden" name="productKey" value="${tempProduct.productKey}" >
									<button type="submit" value="Submit" title="Remove Item From Cart." class="btn btn-warning">REMOVE</button>
								</form>
		  					</div>
	  				 </div>
			      </div>
			    </div>
			</div>
	    </c:forEach>
	   
	</div><br><br>
  </div>
  <div class="col-sm-4">
	  	<div class="panel panel-primary" style="position: relative">
	  		<div class="panel-heading"><h3>Totals</h3></div>
	  		<div class="panel-body">
	        	Subtotal: ${SUBTOTAL} <br>
	        	Tax: ${VAT}<br>
	        	Total Due: ${TOTAL}
			      
			</div> 
	  	</div>
	  	<form action="ControllerServlet" method="GET">
			<input type="hidden" name="command" value="CHECKOUT" >
			<input type="hidden" name="total" value="${TOTAL}" >
			<button type="submit" value="Submit" title="Proceed To Checkout." class="btn btn-success">CHECK-OUT</button>
		</form> 
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
