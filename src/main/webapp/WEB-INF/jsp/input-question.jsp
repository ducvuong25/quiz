<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="mvc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Online quiz</title>
        <!-- Favicon-->
        <link rel="icon" href="favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="/resources/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="/resources/plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="/resources/plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="/resources/css/style.css" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="/resources/css/themes/theme-green.css" rel="stylesheet" />

        <link href="/resources/css/custom.css" rel="stylesheet">    
    </head>

    <body class="theme-green">
        <!-- Page Loader -->
        <div class="page-loader-wrapper">
            <div class="loader">
                <div class="preloader">
                    <div class="spinner-layer pl-green">
                        <div class="circle-clipper left">
                            <div class="circle"></div>
                        </div>
                        <div class="circle-clipper right">
                            <div class="circle"></div>
                        </div>
                    </div>
                </div>
                <p>Please wait...</p>
            </div>
        </div>
        <!-- #END# Page Loader -->
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Search Bar -->
        <form action="/search" method="POST">
            <div class="search-bar">
                <div class="search-icon">
                    <i class="material-icons">search</i>
                </div>
                <input type="text" name="keyword" placeholder="SEARCH QUESTION...">
                <input type="submit" id="submit-search"> 

                <div class="close-search">
                    <i class="material-icons">close</i>
                </div>
            </div>
        </form>
        <!-- #END# Search Bar -->
        <!-- Top Bar -->
        <nav class="navbar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                    <a href="javascript:void(0);" class="bars"></a>
                    <a class="navbar-brand" href="/"></a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <!-- Call Search -->
                        <li><a href="javascript:void(0);" class="js-search" data-close="true"><i class="material-icons">search</i></a></li>
                        <!-- #END# Call Search -->
                    </ul>
                </div>
            </div>
        </nav>
        <!-- #Top Bar -->
        <section>
            <!-- Left Sidebar -->
            <aside id="leftsidebar" class="sidebar">
                <!-- Menu -->
                <div class="menu">
                    <ul class="list">
                        <li class="header">MENU</li>
                        <li>
                            <a href="/">
                                <span>Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Question management</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="/listQuestion">Question list</a>
                                </li>
                                <li>
                                    <a href="/createQuestion">Create question</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Test management</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="/listTest">Test list</a>
                                </li>
                                <li>
                                    <a href="/createTest">Create test</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- #Menu -->
                <!-- Footer -->
                <div class="legal">
                    <div class="copyright">
                        &copy; 2018 <a href="javascript:void(0);">abc123</a>.
                    </div>
                    <div class="version">
                        <b>Version: </b> 1.0.0
                    </div>
                </div>
                <!-- #Footer -->
            </aside>
            <!-- #END# Left Sidebar -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header align-center">
                    <h2>Test: IQ 200</h2>
                </div>
                <!-- Basic Table -->
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <button type="button" class="btn btn-success waves-effect">Import from file</button>
                            </div>
                            <div class="body table-responsive">
                                <form action="/addQuestionToTest" method="POST">
                                    <input type="hidden" name="testId" value="${testId}">
                                    <table class="table table-bordered table-striped">
                                        <thead class="btn-success">
                                            <tr class="text-center">
                                                <th style="width: 5%"></th>
                                                <th style="width: 10%">ID</th>
                                                <th style="width: 25%">Question content</th>
                                                <th style="width: 15%">Correct Answer</th>
                                                <th style="width: 15%">Answer 1</th>
                                                <th style="width: 15%">Answer 2</th>
                                                <th style="width: 15%">Answer 3</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <mvc:forEach var="q" items="${questions}"> 
                                                    <td>

                                                        <mvc:if test="${q.selected==true}">
                                                            <input type="checkbox" id="${q.questionId}" name="questionId" value="${q.questionId}" class="filled-in chk-col-green" checked />
                                                            <label for="${q.questionId}"></label>
                                                        </mvc:if>
                                                        <mvc:if test="${q.selected!=true}">
                                                            <input type="checkbox" id="${q.questionId}" name="questionId" value="${q.questionId}" class="filled-in chk-col-green"/>
                                                            <label for="${q.questionId}"></label>
                                                        </mvc:if>
                                                    </td>

                                                    <td>${q.questionId}</td>
                                                    <td>${q.content}</td>
                                                    <td>${q.crtAns}</td>
                                                    <td>${q.ans1}</td>
                                                    <td>${q.ans2}</td>
                                                    <td>${q.ans3}</td>
                                                </tr>
                                            </mvc:forEach> 
                                        </tbody>
                                    </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #END# Basic Table -->
                <div class="row clearfix">
                    <center>
                        <button type="submit" class="btn btn-success m-t-15 w-90 waves-effect">Save</button>    
                        <a href="/listTest"> <button type="button" class="btn btn-warning m-t-15 w-90 waves-effect">Cancel</button></a>
                    </center>
                </div>
                </form>

            </div>
        </section>

        <!-- Jquery Core Js -->
        <script src="/resources/plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="/resources/plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Select Plugin Js -->
        <script src="/resources/plugins/bootstrap-select/js/bootstrap-select.js"></script>

        <!-- Slimscroll Plugin Js -->
        <script src="/resources/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="/resources/plugins/node-waves/waves.js"></script>

        <!-- Custom Js -->
        <script src="/resources/js/admin.js"></script>
    </body>

</html>
