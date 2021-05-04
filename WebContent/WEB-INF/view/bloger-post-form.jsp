<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

  <title>Admin | Blog</title>
  

  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin_plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin_dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
  
   <style>
	  .ui-datepicker {
	   background: #333;
	   border: 1px solid #555;
	   color: #EEE;
	   padding: 10px;
	}	  
  </style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    
      <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
    </ul>
    
    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <!-- Messages Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="#" class="dropdown-item">
            <!-- Message Start -->
            <div class="media align-items-center">
              <img src="${user.image}" alt="User Avatar" class="img-brand-50 mr-3 img-circle" style="max-height: 75px">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  ${user.username}
                </h3>
              </div>
            </div>
            <!-- Message End -->
          </a>
          
          <div class="dropdown-divider"></div>
          <a href="${pageContext.request.contextPath}/administration/user-form-edit" class="dropdown-item">
            <i class="fas fa-user"></i> Your Profile
          </a>
          <div class="dropdown-divider"></div>
          <a href="${pageContext.request.contextPath}/administration/user-change-password" class="dropdown-item">
            <i class="fas fa-user"></i> Change Password
          </a>
          <div class="dropdown-divider"></div>
          <a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
              <i class="fas fa-sign-out-alt"></i> Log Out
          </a>
        </div>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="/BlogSpringMvcApp" class="brand-link">
      <img src="${pageContext.request.contextPath}/admin_dist/img/AdminLTELogo.png" alt="Blog Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">BLOG</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Bloger
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="post-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>My posts</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add post</p>
                </a>
              </li>
            </ul>
          </li>
          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="min-height: 521px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Post Form</h1>
          </div>
          
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>              
              <li class="breadcrumb-item active">Post Form</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6">
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Post Form</h3>
              </div>
              <!-- /.card-header -->
              
              <!-- form start -->
              <form:form action="bloger-post-save" modelAttribute="post">
                <div class="card-body">
                <form:hidden path="id"/>               
                  <div class="form-group">
                    <label>Title</label>                   
                    <form:input path="title" class="form-control" placeholder="Enter title"/>                    
                  </div>
                  <div class="form-group">
                    <label>Description</label>
                    <form:textarea cols="100" rows="3" path="description" class="form-control" placeholder="Enter description"/>                    
                  </div>
                  <div class="form-group">
                    <label>Content</label>
                    <form:textarea cols="100" rows="10" path="content" class="form-control" placeholder="Enter content"/>                   
                  </div>
                  <div class="form-group">
                  	<label>Date</label>                  	
                  	<form:input path="date" class="form-control" id="date" name="date"/>               
                  </div>
                  <div class="form-group">
                  	<div class="form-check">
                    	<form:checkbox path="important" class="form-check-input"/>
                        <label class="form-check-label">Important</label>
                   	</div>                  
                  </div>                  
                  <div class="form-group">
                    <label>Image</label>
                    <form:input path="image" class="form-control" placeholder="Enter path to image"/>
                  </div>
                  <div class="form-group">
                    <label>Image II</label>
                    <form:input path="image_add" class="form-control" placeholder="Enter path to second image"/>
                  </div>
                  <div class="form-group">
                    <label>Category</label>
                    <form:select items="${categoryList}" path="category.id" itemLabel="name" itemValue="id"  class="form-control" placeholder="Choose category"/> 
                  </div>
                  <div class="form-group">
                    <label>Tags</label>
                    <form:select items="${tagList}" path="tags" itemLabel="name" itemValue="id" class="form-control" placeholder="Choose tags" />                    
                  </div>
                  <div class="form-group">
                  	<div class="form-check">
                    	<form:checkbox path="enable" class="form-check-input"/>
                        <label class="form-check-label">Enable</label>
                   	</div>                  
                  </div>
                </div>
                
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Save</button>  
                  <c:if test="${post.id > 0}">
                  	<a href="make-slide?id=${post.id}" class="btn btn-success">Make Slide</a>
                  </c:if>                
                </div>
              </form:form>
              
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Vladimir
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->



<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin_plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin_plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/admin_dist/js/adminlte.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/jquery.datepicker-plus-css@1.0.0/jquery-datepicker.min.js"></script>
  
  <script type="text/javascript">
		$(function() {
			$("#date").datepicker({ dateFormat: 'yy/mm/dd' });
		});
  </script>

</body>
</html>
    