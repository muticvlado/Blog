<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
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
                <form action="blog-search.html">
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
              <li class="nav-item"><a href="/BlogSpringMvcApp" class="nav-link">Home</a>
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
    <div class="container">
      <div class="row">
        <!-- Latest Posts -->
        <main class="posts-listing col-lg-8"> 
          <div class="container">
            <h2 class="mb-3 author d-flex align-items-center flex-wrap">
<%--               <div class="avatar"><img src="${user.image}" alt="..." class="img-fluid rounded-circle" style="max-width:25%;"></div> --%>
	              <div class="title">
	                <span>Search results for "<%=request.getParameter("keywords")%>"</span>
	                <hr>
	              </div>                            
            </h2>
            <div class="row">
            
            <c:forEach items="${posts}" var="post">
              <!-- post -->
              <div class="post col-xl-6">
                <div class="post-thumbnail"><a href="blog-post/${post.id}/${post.user_friendly_url}"><img src="${post.image}" alt="..." class="img-fluid"></a></div>
                <div class="post-details">
                  <div class="post-meta d-flex justify-content-between">
                    <div class="date meta-last">
                    	<span class="dateUp">${post.date}</span>
                    </div>
                    <div class="category"><a href="blog-category?id=${post.category.id}">${post.category.name}</a></div>
                  </div><a href="blog-post/${post.id}/${post.user_friendly_url}">
                    <h3 class="h4">${post.title}</h3></a>
                  <p class="text-muted">${post.description}</p>
                  <div class="post-footer d-flex align-items-center"><a href="blog-author?username=${post.user.username}" class="author d-flex align-items-center flex-wrap">
                      <div class="avatar"><img src="${post.user.image}" alt="..." class="img-fluid"></div>
                      <div class="title"><span>${post.user.name} ${post.user.surname}</span></div></a>
                    <div class="date"><i class="icon-clock"></i>
                    	<span class="dateAgo">${post.date}</span>
                    </div>
                    <div class="comments meta-last"><i class="icon-comment"></i>${fn:length(post.comments)}</div>
                  </div>
                </div>
              </div>
              <!-- post             -->
              </c:forEach>
              
           
          </div>
          </div>
        </main>
        <aside class="col-lg-4">
          <!-- Widget [Search Bar Widget]-->
          <div class="widget search">
            <header>
              <h3 class="h6">Search the blog</h3>
            </header>
            <form action="blog-search" class="search-form" method="get">
              <div class="form-group">
                <input type="text" name="keywords" placeholder="What are you looking for?">
                <button type="submit" class="submit"><i class="icon-search"></i></button>
              </div>
            </form>
          </div>
          <!-- Widget [Latest Posts Widget]        -->
          <div class="widget latest-posts">
            <header>
              <h3 class="h6">Latest Posts</h3>
            </header>
            
            <c:forEach items="${latestPosts}" var="lastPost">
            
            <div class="blog-posts">
            <a href="blog-post/${lastPost.id}/${lastPost.user_friendly_url}">
              <div class="item d-flex align-items-center">
                <div class="image"><img src="${lastPost.image}" alt="..." class="img-fluid"></div>
                <div class="title"><strong>${lastPost.title}</strong>
                  <div class="d-flex align-items-center">
                    <div class="views"><i class="icon-eye"></i>${lastPost.views_number}</div>
                    <div class="comments"><i class="icon-comment"></i>${fn:length(lastPost.comments)}</div>
                  </div>
                </div>                
              </div>
              </a>
            </div>            
            </c:forEach>  
         </div>
          <!-- Widget [Categories Widget]-->
          <div class="widget categories">
            <header>
              <h3 class="h6">Categories</h3>
            </header>
            
            <c:forEach items="${latestCategories}" var="category" begin="0" end="3">   
            	<div class="item d-flex justify-content-between"><a href="blog-category?id=${category.id}">${category.name}</a><span>(${category.numberOfPosts})</span></div>            
            </c:forEach>
            
          </div>
          <!-- Widget [Tags Cloud Widget]-->
          <div class="widget tags">       
            <header>
              <h3 class="h6">Tags</h3>
            </header>
            <ul class="list-inline">
            <c:forEach items="${tags}" var="tag">
              <li class="list-inline-item"><a href="blog-tag?id=${tag.id}" class="tag">#${tag.name} (${tag.use_number})</a></li>
            </c:forEach> 
            </ul>
          </div>
        </aside>
      </div>
    </div>
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
                <li> <a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                <li> <a href="${pageContext.request.contextPath}/contact">Contact</a></li>
                <li> <a href="${pageContext.request.contextPath}/administration/post-list">Login</a></li>
              </ul>
              <ul class="list-unstyled">
               <c:forEach items="${latestCategories}" var="category" begin="0" end="3">               
                	<li><a href="blog-category?id=${category.id}">${category.name} <b>(${category.numberOfPosts})</b></a></li>
                </c:forEach>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="latest-posts"><a href="blog-post.html">                
                <c:forEach items="${latestPosts}" var="post" begin="0" end="2">
            	<a href="blog-post?id=${post.id}">
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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ==" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js" integrity="sha512-LGXaggshOkD/at6PFNcp2V2unf9LzFq6LE+sChH7ceMTDP0g2kn6Vxwgg7wkPP7AAtX+lmPqPdxB47A0Nz0cMQ==" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/js/front.js"></script>
	<script src="${pageContext.request.contextPath}/js/format-dates.js"></script>
  </body>
</html>