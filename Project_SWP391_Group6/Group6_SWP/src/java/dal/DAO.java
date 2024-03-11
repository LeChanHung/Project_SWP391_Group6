/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DAO1.DBContext;
import Entity.timeSlots;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import model1.Attendance;
import model1.Report;
import model1.Schedule;

/**
 *
 * @author minhdang
 */
public class DAO extends DBContext {

    public void Register(String firstName, String lastName, String email, String passwordHash, String gender, String dob, String MSV) {
        String sql = "insert into Students(FirstName,LastName,Email,PasswordHash,gender,dob,MSV) values(?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, passwordHash);
            ps.setString(5, gender);
            ps.setString(6, dob);
            ps.setString(7, MSV);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Report> stuReport(int StudentID, int status) {
        List<Report> list = new ArrayList<>();
        String sql = "select ts.SlotNumber, a.AttendanceDate,ts.SlotStartTime,ts.SlotEndTime, a.Status from WeeklySchedules ws\n" +
"                inner join Attendance a on ws.ScheduleID = a.ScheduleID\n" +
"                inner join TimeSlots ts on ws.SlotID = ts.SlotID\n" +
"				where ws.StudentID = ? ";
        try {
            if(status==1) {
                sql+="AND a.Status='attend'";
            } else if(status==2) {
                sql+="AND a.Status='absent'";
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, StudentID);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Report r = new Report();
                timeSlots ts = new timeSlots();
                Attendance a = new Attendance();
                ts.setSlotNumber(rs.getInt("SlotNumber"));
                a.setAttendanceDate(rs.getDate("AttendanceDate"));
                ts.setSlotStartTime(rs.getTimestamp("SlotStartTime"));
                ts.setSlotEndTime(rs.getTimestamp("SlotEndTime"));
                a.setStatus(rs.getString("Status"));
                r.setAttendance(a);
                r.setSlot(ts);
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {        
        
      DAO dao = new DAO();

        
        List<Report> resultList = dao.stuReport(15,0);
        
        for (Report r : resultList) {
            System.out.println("Slot Number: " + r.getSlot().getSlotNumber());
            System.out.println("Attendance Date: " + r.getAttendance().getAttendanceDate());
            System.out.println("Slot Start Time: " + r.getSlot().getSlotStartTime());
            System.out.println("Slot End Time: " + r.getSlot().getSlotEndTime());
            System.out.println("Status: " + r.getAttendance().getStatus());
            System.out.println();
        }
    }
}
