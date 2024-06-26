/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.DAO1;
import DAO1.ScheduleDAO;
import Entity.students;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Duy Anh
 */
@WebServlet(name = "checkAttendance", urlPatterns = {"/check"})
public class checkAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkAttendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkAttendance at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String raw_classID = request.getParameter("classId");
        String raw_scId = request.getParameter("scId");
        int classId = 1;
        int scId = 1;
        try {
            classId = Integer.parseInt(raw_classID);
            scId = Integer.parseInt(raw_scId);
        } catch (Exception e) {
            response.sendRedirect("tchTimeTable");
        }

        DAO1 dao = new DAO1();
        List<students> list = dao.getStudentAttendance(classId);
        ScheduleDAO dbSchedule = new ScheduleDAO();
        for (students s : list) {
            int enrollmentId = dao.getEnrollmentId(s.getStudentID(), classId);

            if (enrollmentId == 0) {
                continue;
            }
            boolean checkStudentAttend = dao.checkStudenTAttend(enrollmentId, scId);
            String studentId_string = s.getStudentID() + "";
            String status = request.getParameter(studentId_string);
            if (checkStudentAttend) {
                dao.updateStudenTAttend(status, enrollmentId, scId);
            } else {
                dao.takeAttendanceStudent(enrollmentId, scId, status);
            }

        }
         dbSchedule.updateSchedule(scId, 1);
        

        response.sendRedirect("submit.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
