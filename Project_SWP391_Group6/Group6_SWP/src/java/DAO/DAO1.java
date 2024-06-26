/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO1.DBContext;
import DAO1.*;
import Entity.classes;
import Entity.feedbacks;
import Entity.news;
import Entity.schedules;
import Entity.students;
import Entity.subjects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Duy Anh
 */
public class DAO1 extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<feedbacks> getAllFeedback() {
        List<feedbacks> list = new ArrayList<>();

        String query = "select Feedbacks.StudentID,Feedbacks.OfficeID,Feedbacks.FeedbackText,Feedbacks.FeedbackDate, Students.FirstName, Students.LastName,Students.Email from Feedbacks\n"
                + "inner join Students on Students.StudentID = Feedbacks.StudentID";
        try {
            //  conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                feedbacks f = new feedbacks(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
                list.add(f);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

//    public List<feedbacks> getAllFeedback(int currentPage, int recordsPerPage) {
//        List<feedbacks> list = new ArrayList<>();
//        int startIndex = (currentPage - 1) * recordsPerPage;
//        String query = "select Feedbacks.StudentID,Feedbacks.OfficeID,Feedbacks.FeedbackText,Feedbacks.FeedbackDate, Students.FirstName, Students.LastName,Students.Email from Feedbacks\n"
//                + "inner join Students on Students.StudentID = Feedbacks.StudentID\n"
//                + "order by FeedbackID\n"
//                + "limit ? offset ?";
//        try {
//            //  conn = new DBContext().getConnection();
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, recordsPerPage);
//            ps.setInt(2, startIndex);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                feedbacks f = new feedbacks(rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7));
//                list.add(f);
//            }
//            return list;
//        } catch (Exception e) {
//        }
//        return null;
//    }
    public List<subjects> getAllSubject() {
        List<subjects> list = new ArrayList<>();
        String query = "select * from Subjects";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                subjects sub = new subjects(rs.getInt(1),
                        rs.getString(2));
                list.add(sub);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<students> getAllStudent() {
        List<students> list = new ArrayList<>();
        String query = "select * from Students";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                students s = new students(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                list.add(s);
            }

            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<classes> getAllClass() {
        List<classes> list = new ArrayList<>();
        String query = "select*from Classes where ClassID='1'";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                classes c = new classes(rs.getInt(1), rs.getString(2));
                list.add(c);

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void insertFeedback(int StudentID, String FeedbackText) {
        String query = "insert into Feedbacks(StudentID,OfficeID,FeedbackText,FeedbackDate)\n"
                + "values(?,'1',?,'2023-02-01');";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, StudentID);
            ps.setString(2, FeedbackText);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertFeedbackOffice(int FeedbackID, String ResponseText) {
        String query = "insert into OfficeResponses(FeedbackID,ResponseText,ResponseDate)\n"
                + "values('?','?','2023-02-01');";
        try {
            //  conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, FeedbackID);
            ps.setString(2, ResponseText);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public int authenticationStudent(String email, String password) {
        String query = "SELECT StudentID FROM Students WHERE Email = '?' AND PasswordHash = '?'";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt("StudentID");
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public List<schedules> getAllSchedules() {
        List<schedules> list = new ArrayList<>();
        String query = "select WeeklySchedules.SubjectID,Subjects.SubjectName,Teachers.FirstName + ' ' + Teachers.LastName AS TeacherFullName,Classes.ClassName,WeeklySchedules.DayOfWeek from WeeklySchedules\n"
                + "inner join Classes on Classes.ClassID=WeeklySchedules.ClassID\n"
                + "inner join Teachers on Teachers.TeacherID=WeeklySchedules.TeacherID\n"
                + "inner join Subjects on Subjects.SubjectID=WeeklySchedules.SubjectID\n"
                + "inner join TimeSlots on TimeSlots.SlotID = WeeklySchedules.SlotID\n"
                + "\n"
                + "where WeeklySchedules.ClassID='1';";
        try {
            //    conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                schedules sc = new schedules(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(sc);

            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public int totalFeedback() {
        String query = "select count(*) from Feedbacks \n";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int pagingFeedback(String search) {
        String query = "select count(*) from Feedbacks \n"
                + "inner join Students\n"
                + "on Feedbacks.StudentID = Students.StudentID\n"
                + "where Students.FirstName like ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<feedbacks> getAllFeedbackBySearch(String search) {
        List<feedbacks> list = new ArrayList<>();
        String query = "SELECT COUNT(Feedbacks.StudentID) AS TotalCount,\n"
                + "       Students.FirstName,\n"
                + "       Students.LastName,\n"
                + "       Students.Email\n"
                + "FROM Feedbacks\n"
                + "INNER JOIN Students ON Feedbacks.StudentID = Students.StudentID\n"
                + "WHERE Students.FirstName LIKE ?\n"
                + "GROUP BY Students.FirstName, Students.LastName, Students.Email;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                while (rs.next()) {
                    rs.getInt(1);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<feedbacks> getFeedbackBySearchAndPaging(String search, int index) {
        List<feedbacks> list = new ArrayList<>();
        String query = " select * from Feedbacks f join Students s on f.StudentID = s.StudentID\n"
                + " where s.[FirstName] like '%" + search + "%'\n "
                + " order by f.FeedbackDate desc\n"
                + " OFFSET ? ROWS FETCH NEXT 4  ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new feedbacks(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public int countFeedbackBySearch(String search) {
        List<feedbacks> list = new ArrayList<>();
        String query = " select count(*) from Feedbacks f join Students s on f.StudentID = s.StudentID\n"
                + " where f.FeedbackText like '%" + search + "%'\n";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<students> getStudentAttendance(int classId) {
        List<students> list = new ArrayList<>();
        String query = "select Students.StudentID,FirstName,MSV from StudentEnrollments\n"
                + "inner join Students on Students.StudentID=StudentEnrollments.StudentID\n"
                + "inner join Classes on Classes.ClassID=StudentEnrollments.ClassID where Classes.ClassID = ?";
        try {
            //  conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                students s = new students(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
        }
        return null;

    }

    public void takeAttendanceStudent(int enrolmentID, int scheduleId, String status) {
        String query = "insert into Attendance([EnrollmentID],[ScheduleID],[AttendanceDate],[Status])\n"
                + "values(?,?,?,?);";
        try {
            //  conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, enrolmentID);
            ps.setInt(2, scheduleId);
            ScheduleDAO dbSchedule = new ScheduleDAO();
            Date today = new Date();
            ps.setDate(3, new java.sql.Date(today.getTime()));
            ps.setString(4, status);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getEnrollmentId(int studentId, int classId) {
        String query = "SELECT [EnrollmentID] FROM [StudentEnrollments] WHERE StudentID = ? and ClassID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setInt(2, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("EnrollmentID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<news> getAllNews() {
        List<news> list = new ArrayList<>();

        String query = "select news_id,img,tilte,content,date from news order by news_id desc";
        try {
            //  conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                news n = new news(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(n);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void insertNews(String title, String content, String date, String img) {
        String query = "insert into news(tilte,content,date,OfficeID,img)\n"
                + "values(?,?,?,'1',?);";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, date);
            ps.setString(4, img);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void editNews(String id, String title, String content, String date, String img) {
        String query = "UPDATE news\n"
                + "SET tilte=?, content = ?, img=?,date=?\n"
                + "WHERE news_id = ?;";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, date);
            ps.setString(4, img);
            ps.setString(5, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void deleteNew(String id) {
        String query = "delete from news where news_id=?";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
    }

    public news getNewsById(String id) {
        String query = "select * from news where news_id =?";
        try {
            // conn = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new news(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<news> getListbByPage(List<news> list, int start, int end) {
        ArrayList<news> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public int getTotalNews() {
        String query = "select count(*) from news";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public boolean checkStudenTAttend(int enrollmentId, int scId) {
        String query = "SELECT [AttendanceID]\n"
                + "      ,[EnrollmentID]\n"
                + "      ,[ScheduleID]\n"
                + "      ,[AttendanceDate]\n"
                + "      ,[Status]\n"
                + "  FROM Attendance where [EnrollmentID] =  ? and ScheduleID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, enrollmentId);
            ps.setInt(2, scId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
        }
        return false;
    }
    
    public void updateStudenTAttend(String status,int enrollmentId, int scId) {
        String query = """
                       Update Attendance set Status = ? where [EnrollmentID] =  ? and ScheduleID = ?""";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, enrollmentId);
            ps.setInt(3, scId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

    }
}
