/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO1.ApplicationDAO;
import DAO1.TeacherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model1.Application;

/**
 *
 * @author lecha
 */
@WebServlet(name = "ViewApplicationController", urlPatterns = {"/viewApplication"})
public class ViewApplicationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDAO dbAppli = new ApplicationDAO();
        TeacherDAO dbTeacher = new TeacherDAO();
        String x = "abc-";
        ArrayList<Application> applications = dbAppli.list();
        
        req.setAttribute("applications", applications);
        req.setAttribute("teachers", dbTeacher.list());
        req.getRequestDispatcher("View_Application.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
