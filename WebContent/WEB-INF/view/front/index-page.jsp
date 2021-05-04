<%@page import="com.vlado.FriendlyUrl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Blog</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Custom icon font-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontastic.css">
    <!-- Google fonts - Open Sans-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <!-- Fancybox-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/@fancyapps/fancybox/jquery.fancybox.min.css">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.default.css" id="theme-stylesheet">
   
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
            
    <!-- owl carousel 2 stylesheet-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.carousel.min.css" id="theme-stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/owl-carousel2/assets/owl.theme.default.min.css" id="theme-stylesheet">    
	
	
	 <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
	
  </head>
  <body onload="displayDate()">
    <header class="header">
      <!-- Main Navbar-->
      <nav class="navbar navbar-expand-lg">
        <div class="search-area">
          <div class="search-area-inner d-flex align-items-center justify-content-center">
            <div class="close-btn"><i class="icon-close"></i></div>
            <div class="row d-flex justify-content-center">
              <div class="col-md-8">
                <form action="blog-search">
                  <div class="form-group">
                    <input type="search" name="keywords" id="keywords" placeholder="What are you looking for?">
                    <button type="submit" class="submit"><i class="icon-search-1"></i></button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="container">
          <!-- Navbar Brand -->
          <div class="navbar-header d-flex align-items-center justify-content-between">
            <!-- Navbar Brand --><a href="index.html" class="navbar-brand">Blog</a>
            <!-- Toggle Button-->
            <button type="button" data-toggle="collapse" data-target="#navbarcollapse" aria-controls="navbarcollapse" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler"><span></span><span></span><span></span></button>
          </div>
          <!-- Navbar Menu -->
          <div id="navbarcollapse" class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item"><a href="" class="nav-link active">Home</a>
              </li>
              <li class="nav-item"><a href="blog" class="nav-link">Blog</a>
              </li>
              <li class="nav-item"><a href="contact" class="nav-link">Contact</a>
              </li>
              <li class="nav-item"><a href="administration/post-list" class="nav-link">Login</a>
              </li>
              
            </ul>
            <div class="navbar-text"><a href="#" class="search-btn"><i class="icon-search-1"></i></a></div>
          </div>
        </div>
      </nav>
    </header>

    <!-- Hero Section-->
    <div id="index-slider" class="owl-carousel">
    
      <c:forEach items="${slider}" var="slide">
      <section style="background: url(${slide.image}); background-size: cover; background-position: center center; min-height: 550px;" class="hero">
        <div class="container">
          <div class="row">
            <div class="col-lg-7">
              <h1>${slide.title}</h1>
              <a href="${slide.button_url}" class="hero-link">${slide.button_label}</a>
            </div>
          </div>
        </div>
      </section>
      </c:forEach>
      
    </div>

    <!-- Intro Section-->
    <section class="intro">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <h2 class="h3">Some great intro here</h2>
            <p class="text-big">Place a nice <strong>introduction</strong> here <strong>to catch reader's attention</strong>.</p>
          </div>
        </div>
      </div>
    </section>
    
    <section class="featured-posts no-padding-top">
      <div class="container">
      
      <c:forEach items="${importantPosts}" var="post" begin="0" end="2" varStatus="item">
        <!-- Post-->
        
        <c:choose>
    		<c:when test="${item.index % 2 == 0}">
    		
    		<div class="row d-flex align-items-stretch">
    		
		          <div class="text col-lg-7">
		            <div class="text-inner d-flex align-items-center">
		              <div class="content">
		                <header class="post-header">
		                  <div class="category"><a href="blog-category?id=${post.category.id}">${post.category.name}</a></div><a href="blog-post/${post.id}/${post.user_friendly_url}">
		                    <h2 class="h4">${post.title}</h2></a>
		                </header>
		                <p>${post.description}</p>
		                <footer class="post-footer d-flex align-items-center">
		                <a href="blog-author?username=${post.user.username}" class="author d-flex align-items-center flex-wrap">
		                    <div class="avatar"><img src="${post.user.image}" alt="..." class="img-fluid"></div>
		                    <div class="title"><span>${post.user.name} ${post.user.surname}</span></div></a>
		                  <div class="date">
		                  	<i class="icon-clock"></i>
		                  	<span class="dateAgo">${post.date}</span>                         	 	
		                  </div>
		                  <div class="comments"><i class="icon-comment"></i>${fn:length(post.comments)}</div>
		                </footer>
		              </div>
		            </div>
		          </div>
          		<div class="image col-lg-5"><img src="${post.image}" alt="..."></div>
        	</div>		
        		
    		</c:when>
    		
    		<c:otherwise>
    		
    		<div class="row d-flex align-items-stretch">
    		
    		<div class="image col-lg-5"><img src="${post.image}" alt="..."></div>
    		
    			 <div class="text col-lg-7">
		            <div class="text-inner d-flex align-items-center">
		              <div class="content">
		                <header class="post-header">
		                  <div class="category"><a href="blog-category?id=${post.category.id}">${post.category.name}</a></div><a href="blog-post/${post.id}/${post.user_friendly_url}">
		                    <h2 class="h4">${post.title}</h2></a>
		                </header>
		                <p>${post.description}</p>
		                <footer class="post-footer d-flex align-items-center"><a href="blog-author?username=${post.user.username}" class="author d-flex align-items-center flex-wrap">
		                    <div class="avatar"><img src="${post.user.image}" alt="..." class="img-fluid"></div>
		                    <div class="title"><span>${post.user.name} ${post.user.surname}</span></div></a>
		                  <div class="date"><i class="icon-clock"></i>
		                  	<span class="dateAgo">${post.date}</span>
		                  </div>
		                  <div class="comments"><i class="icon-comment"></i>${fn:length(post.comments)}</div>
		                </footer>
		              </div>
		            </div>
		          </div>
          		
        	</div>
        	
    		</c:otherwise>
		</c:choose>
          
        </c:forEach>
                
      </div>
    </section>
    <!-- Divider Section-->
    <section style="background: url(img/divider-bg.jpg); background-size: cover; background-position: center bottom" class="divider">
      <div class="container">
        <div class="row">
          <div class="col-md-7">
            <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</h2>
            <a href="contact" class="hero-link">Contact Us</a>
          </div>
        </div>
      </div>
    </section>
    
    
    
    <!-- Latest Posts -->
    <section class="latest-posts"> 
      <div class="container">
        <header> 
          <h2>Latest from the blog</h2>
          <p class="text-big">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
        </header>
        
        <div class="owl-carousel" id="latest-posts-slider"> 
        
          <c:forEach items="${latestPosts}" var="post">      
          <div class="row">
          
           
          	 <div class="post col-12">
                   
	              <div class="post-thumbnail">
	              <a href="blog-post/${post.id}/${post.user_friendly_url}">
	              <img src="${post.image}" alt="..." class="img-fluid" style="height:184px; min-width: 349px; max-width: 351px;">
	              </a>
	              </div>
	              <div class="post-details" style="min-width: 349px; max-width: 351px;">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">
	                  	<span class="dateAgo">${post.date}</span>      
	                  </div>
	                  <div class="category"><a href="blog-category?id=${post.category.id}">${post.category.name}</a></div>
	                </div>
	                <a href="blog-post/${post.id}/${post.user_friendly_url}"><h3 class="h4">${post.title}</h3></a>
	                <p class="text-muted">${post.description}</p>
	              </div>
              
              </div>
                
            </div> 
            </c:forEach> 
                  
          </div>
        </div>
               
    </section>  <!-- Latest Posts END -->
    
    <!-- Gallery Section-->
    <section class="gallery no-padding">    
      <div class="row">
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="img/gallery-1.jpg" data-fancybox="gallery" class="image">
              <img src="img/gallery-1.jpg" alt="gallery image alt 1" class="img-fluid" title="gallery image title 1">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="img/gallery-2.jpg" data-fancybox="gallery" class="image">
              <img src="img/gallery-2.jpg" alt="gallery image alt 2" class="img-fluid" title="gallery image title 2">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="img/gallery-3.jpg" data-fancybox="gallery" class="image">
              <img src="img/gallery-3.jpg" alt="gallery image alt 3" class="img-fluid" title="gallery image title 3">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        <div class="mix col-lg-3 col-md-3 col-sm-6">
          <div class="item">
            <a href="img/gallery-4.jpg" data-fancybox="gallery" class="image">
              <img src="img/gallery-4.jpg" alt="gallery image alt 4" class="img-fluid" title="gallery image title 4">
              <div class="overlay d-flex align-items-center justify-content-center"><i class="icon-search"></i></div>
            </a>
          </div>
        </div>
        
      </div>
    </section>
    <!-- Page Footer-->
    <footer class="main-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <div class="logo">
              <h6 class="text-white">Bootstrap Blog</h6>
            </div>
            <div class="contact-details">
              <p>53 Broadway, Broklyn, NY 11249</p>
              <p>Phone: (020) 123 456 789</p>
              <p>Email: <a href="mailto:info@company.com">Info@Company.com</a></p>
              <ul class="social-menu">
                <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-behance"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-pinterest"></i></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="menus d-flex">
              <ul class="list-unstyled">
                <li> <a href="/BlogSpringMvcApp">Home</a></li>
                <li> <a href="blog">Blog</a></li>
                <li> <a href="contact">Contact</a></li>
                <li> <a href="#">Login</a></li>
              </ul>
             
              <ul class="list-unstyled">              
                <c:forEach items="${latestCategories}" var="category" begin="0" end="3">               
                	<li><a href="blog-category?id=${category.id}">${category.name} <b>(${category.numberOfPosts})</b></a></li>
                </c:forEach>
              </ul>
              
            </div>
          </div>
          <div class="col-md-4">
            <div class="latest-posts">
            
            <c:forEach items="${latestPosts}" var="post" begin="0" end="2">
            	<a href="blog-post/${post.id}/${post.user_friendly_url}">
	                <div class="post d-flex align-items-center">
	                  <div class="image"><img src="${post.image}" alt="..." class="img-fluid"></div>
	                  <div class="title"><strong>${post.title}</strong>
		                  <span class="date last-meta">
		                  <span class="footerDate">${post.date}</span>
		                  </span>
	                  </div>
	                </div>
                </a>
            </c:forEach>    
                
          </div>
        </div>
      </div>
      <div class="copyrights">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>&copy; 2017. All rights reserved. Your great site.</p>
            </div>
            <div class="col-md-6 text-right">
              <p>Template By <a href="https://bootstrapious.com/p/bootstrap-carousel" class="text-white">Bootstrapious</a>
                <!-- Please do not remove the backlink to Bootstrap Temple unless you purchase an attribution-free license @ Bootstrap Temple or support us at http://bootstrapious.com/donate. It is part of the license conditions. Thanks for understanding :)                         -->
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- JavaScript files-->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>

    <script src="plugins/owl-carousel2/owl.carousel.min.js"></script>
    <script>
      $("#index-slider").owlCarousel({
        "items": 1,
        "loop": true,
        "autoplay": true,
        "autoplayHoverPause": true
      });

      $("#latest-posts-slider").owlCarousel({
        /* "items": 3, */
        "loop": true,
        "autoplay": false,
        "autoplayHoverPause": true,
       responsive: {
    	   0 : {
    		   "items": 1 
    	   },
    	   992 : {
    		   "items": 2 
    	   },
    	  1200 : {
    		   "items": 3 
    	   }
       }
      });
    </script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/front.js"></script>
	<script src="${pageContext.request.contextPath}/js/format-dates.js"></script>
    
  </body>
</html>