<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <jsp:useBean id="i" class="DAO.DAO1" scope="request"></jsp:useBean>


            <title>Attendance Report</title>
            <link rel="stylesheet" type="text/css" href="style.css">   
            <link href= "css/attendance.css" rel="stylesheet" type="text/css" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.2/css/bootstrap.min.css">
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.2/css/bootstrap.min.css" rel="stylesheet">

        </head>
        <body>

            <div class="container">
                <nav>
                    <ul>
                        <li>
                            <a href="#" class="logo">
                                <img src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png">

                            </a>
                        </li>
                        <li>
                            <a href="TeacherHomePage.jsp"">
                                <i class="fas fa-menorah"></i>
                                <span class="nav-item">Home</span>
                            </a>
                        </li>
                        <li>
                            <a href="feedback.jsp">
                                <i class="fas fa-comment"></i>
                                <span class="nav-item">Feedback</span>
                            </a>
                        </li>
                        <li>
                            <a href="tchTimeTable">
                                <i class="fas fa-database"></i>
                                <span class="nav-item">Schedule</span>
                            </a>
                        </li>
                        
                        <li>
                            <a href="logout" class="logout">
                                <i class="fas fa-sign-out-alt"></i>
                                <span class="nav-item">Log out</span>
                            </a>
                        </li>
                    </ul>
                </nav>

                <section class="main">
                    <div class="main-top">
                        <h1>Attendance</h1>
                        <i class="fas fa-user-cog"></i>
                    </div>

                    <section class="attendance">
                        <div class="attendance-list">
                            <h1>Check Attendance</h1>
                            <form action="check" method="post">

                                <table class="table">
                                    <thead>

                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>MSSV</th>
                                            <th>Present</th>
                                            <th>Absent</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${studentList}" var="e" varStatus="i">
                                        <tr>
                                            <td>${e.studentID}</td>
                                            <td>${e.firstName}</td>
                                            <td>${e.MSV}</td>
                                            <td>
                                                <input type="radio" class="btn-check" id="btn-check-2" checked autocomplete="off" name="${e.studentID}" value="Attend">
                                                <label class="btn btn-primary" for="btn-check-2">Present</label>
                                            </td>
                                            <td>
                                                <input type="radio" class="btn-check" id="btn-check-2" autocomplete="off" name="${e.studentID}" value="Absent">
                                                <label class="btn btn-primary" for="btn-check-2">Absent</label>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>

                            </table>    
                            <div class="button">
                                <div style="text-align: center;">
                                    <button type="submit" class="btn btn-primary" style="background-color: steelblue; color: white; border-radius: 3px; font-size: 20px; cursor: pointer; width: 150px;">Submit</button>
                                </div>
                            </div>
                                <input type="hidden" value="${scId}" name="scId">
                                <input type="hidden" value="${classId}" name="classId">
                        </form>

                    </div>
                </section>
            </section>
        </div>
    </body>
</html>

