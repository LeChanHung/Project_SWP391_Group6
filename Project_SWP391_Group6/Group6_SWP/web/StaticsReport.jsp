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
                            <a href="StudentHomepage.jsp">
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
                            <a href="#">
                                <i class="fas fa-database"></i>
                                <span class="nav-item">Schedule</span>
                            </a>
                        </li>
                        <li>
                            <a href="attendanceReport.jsp">
                                <i class="fas fa-chart-bar"></i>
                                <span class="nav-item">Attendance</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="fas fa-cog"></i>
                                <span class="nav-item">Setting</span>
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
                        <h1>Report</h1>
                        <table class="table">
                            <thead>
                                
                                <tr>
                                    <th>Slot</th>
                                    <th>Day</th>
                                    <th>StartTime</th>
                                    <th>EndTime</th>
                                    <th>Status</th>
                                    
                                  
                                </tr>
                            </thead>
                            <tbody>
                            
                                <tr class="active">
                               
                                    <td>1</td>
                                    <td>2023-12-21</td>
                                    <td>7h30</td>
                                    <td>9h30</td>
                                    <td>Asbent</td>
                                    
                                    
                                  
                                </tr>
                            
                                
                            </tbody>
                        </table>    
                    </div>
                </section>
            </section>
        </div>

    </body>
</html>

