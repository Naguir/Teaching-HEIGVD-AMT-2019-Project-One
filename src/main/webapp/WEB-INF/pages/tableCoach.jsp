<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Football Management</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet"/>


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="assets/img/background-image2.jpg">

        <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


        <div class="sidebar-wrapper">
            <div class="logo">
                Football Management
            </div>

            <ul class="nav">
                <li>
                    <a href="./loginpage">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li>
                    <a href="./coachProfile">
                        <i class="pe-7s-user"></i>
                        <p>Coach Profile</p>
                    </a>
                </li>
                <li>
                    <a href="./tableTeamPage/myTeams?currentPage=1">
                        <i class="pe-7s-note2"></i>
                        <p>My team List</p>
                    </a>
                </li>
                <li>
                    <a href="./tablePlayerPage/myPlayers?currentPage=1">
                        <i class="pe-7s-note2"></i>
                        <p>My players List</p>
                    </a>
                </li>
                    <li>
                        <a href="./tableTeamPage/allTeams?currentPage=1">
                            <i class="pe-7s-note2"></i>
                            <p>All teams List</p>
                        </a>
                    </li>
                <c:if test="${coach.isAdmin == true}">
                    <li>
                        <a href="./tablePlayerPage/allPlayers?currentPage=1">
                            <i class="pe-7s-note2"></i>
                            <p>All players List</p>
                        </a>
                    </li>
                    <li class="active">
                        <a href="./tableCoachPage?currentPage=1">
                            <i class="pe-7s-note2"></i>
                            <p>All coaches List</p>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Coach List</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="">
                                <p> ${coach.username}</p>
                            </a>
                        </li>
                        <li>
                            <a href="./logoutPage">
                                <p>Log out</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg hidden-md"></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Manage Coach(es)</h4>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped">
                                    <thead>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Is Admin</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${coaches}" var="coaches">
                                        <tr>
                                            <td>${coaches.firstName}</td>
                                            <td>${coaches.lastName}</td>
                                            <td>${coaches.username}</td>
                                            <td>${coaches.password}</td>
                                            <td>${coaches.isAdmin}</td>
                                            <form action ="./deleteCoach" methode="post">
                                                <td>
                                                    <input type="submit" value="Delete" style="background:url('../images/trash.png');">
                                                    <input type="hidden" name="deletename" value="${coaches.username}"/>
                                                </td>
                                            </form>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                        <ul class="pagination">
                            <c:if test="${currentPage != 1}">
                                <li class="page-item">
                                        <a class="page-link" href="./tableCoachPage?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="page-item active"><a class="page-link">
                                                ${i} <span class="sr-only">(current)</span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                                <a class="page-link" href="./tableCoachPage?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>

                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${currentPage lt noOfPages}">
                                <li class="page-item">
                                        <a class="page-link" href="./tableCoachPage?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                </div>
            </div>
        </div>

        <footer class="footer">
            <div class="container-fluid">
                <p class="copyright pull-right">
                    &copy;
                    <script>document.write(new Date().getFullYear())</script>
                    Teklehaimanot - Alic, made with love for a better web
                </p>
            </div>
        </footer>


    </div>
</div>


</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>


</html>
