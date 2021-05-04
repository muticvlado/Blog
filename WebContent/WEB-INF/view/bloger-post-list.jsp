<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                <a href="#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>My posts</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="bloger-post-form" class="nav-link">
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
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>My Posts</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Posts</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
              
<!--                 <h3 class="card-title">All Posts</h3> -->
                <div class="card-tools">
                  <a href="bloger-post-form" class="btn btn-success">
                    <i class="fas fa-plus-square"></i>
                    Add new Post
                  </a>
                </div>
                
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>                  
                    <tr>
                      <th>#</th>
                      <th>Image</th>
<!--                       <th>Enable</th> -->
                      <th>Important</th>                     
                      <th>Category</th>
                      <th>Title</th>
                      <th>Comments</th> 
                      <th>Views</th>
                      <th>Author</th>
                      <th>Date</th>                                          
                      <th class="text-center">Actions</th>                   
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="post" items="${posts}">
                  		<c:if test="${post.enable == false}">
	                    	<tr style="background-color: #d6d6c2; color: white; font-size: small">
	                    </c:if>
	                    <c:if test="${post.enable == true}">
	                    	<tr style="font-size: small">
	                    </c:if>
	                      <td>${post.id}</td>
	                      <td style="text-align: center"><img src="${post.image}" style="max-height: 50px"/></td>
	                      <td>${post.important}</td>	                     
	                      <td>${post.category.name}</td>
	                      <td><strong>${post.title}</strong></td>
	                      <td>${fn:length(post.comments)}</td>
	                      <td>${post.views_number}</td>
	                      <td>${post.user.name} ${post.user.surname}</td>
	                      <td><fmt:formatDate value="${post.date}" pattern="dd.MMM.yyyy"/></td>
	                      <td class="text-center">
	                        <div class="btn-group">	                          
	                          <a href="post-update-form?id=${post.id}" class="btn btn-info">
	                            <i class="fas fa-edit"></i>
	                          </a>
	                          <button type="button" class="btn btn-info btn-danger" data-toggle="modal" data-target="#delete-modal-${post.id}">
	                            <i class="fas fa-trash"></i>
	                          </button>
	                        </div>
	                      </td>
	                    </tr>
	                    
	                    <div class="modal fade" id="delete-modal-${post.id}">
					      <div class="modal-dialog">
					        <div class="modal-content">
					          <div class="modal-header">
					            <h4 class="modal-title">Delete Post</h4>
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					              <span aria-hidden="true">&times;</span>
					            </button>
					          </div>
					          <div class="modal-body">
					            <p>Are you sure you want to delete post?</p>
					            <strong></strong>
					          </div>
					          <div class="modal-footer justify-content-between">
					            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					            <a href="post-delete?id=${post.id}" class="btn btn-danger">Delete</a>					            
					          </div>
					        </div>
					        <!-- /.modal-content -->
					      </div>
					      <!-- /.modal-dialog -->
					    </div>
					    
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
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
</body>
</html>
    