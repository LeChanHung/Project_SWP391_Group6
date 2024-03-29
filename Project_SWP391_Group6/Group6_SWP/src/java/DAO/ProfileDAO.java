/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO1.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Entity.Student;

/**
 *
 * @author lecha
 */
public class ProfileDAO {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Student checkInfo(String Email) {
        try {
            String query = "select * from Students where Email=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Email);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Student(
//                        rs.getInt("id"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        Email,
                        rs.getString("PasswordHash"),
                        rs.getString("gender"),
                        rs.getDate("dob"),
                        rs.getString("MSV"));
//                        rs.getInt("role"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

       public static void main(String[] args) {
        ProfileDAO d = new ProfileDAO();
        String Email = "hunglche160179@fpt.edu.vn";
           Student u = d.checkInfo(Email);
        System.out.println(u.getFirstName());
        
    }
}
