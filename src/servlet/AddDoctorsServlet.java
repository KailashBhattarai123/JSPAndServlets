package servlet;

import dao.DoctorsDao;
import dao.JdbcConnector;
import model.Doctor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddDoctorsServlet extends HttpServlet {

    Connection connection;

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DoctorsDao doctorsDao = new DoctorsDao();

        // uri = contextpath + urlPattern
        String uri = request.getRequestURI();
        String cp = request.getContextPath();
        System.out.println(cp + "/adddoctors");

        String action = request.getParameter("action");
        System.out.println(action);

        if (action != null) {
            if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                doctorsDao.deleteDoctor(connection, id);
                System.out.println(id + ": Hello man.");
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));

                RequestDispatcher dispatcher = request.getRequestDispatcher("/adddoctors.jsp");
                Doctor doctor = doctorsDao.fetchDoctor(connection, id);
                request.setAttribute("id", doctor.getDoctorId());
                request.setAttribute("name", doctor.getName());
                request.setAttribute("email", doctor.getEmail());
                request.setAttribute("phone", doctor.getPhone());
                request.setAttribute("address", doctor.getAddress());
                request.setAttribute("description", doctor.getDescription());

                System.out.println(doctor.getName() + " " + doctor.getName() + " " + doctor.getEmail() + " " + doctor.getAddress());

                dispatcher.forward(request, response);
            }
        }

        if (uri.equalsIgnoreCase(cp + "/adddoctors")) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/adddoctors.jsp");
            dispatcher.forward(request, response);
        } else if (uri.equalsIgnoreCase(cp + "/displayDoctors")) {




                ArrayList<Doctor> doctors = null;
                doctors = doctorsDao.fetchTable(connection);
                System.out.println(doctors);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/displayDoctors.jsp");
                request.setAttribute("doctors", doctors);
                dispatcher.forward(request, response);

        }
    }




    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String cp = req.getContextPath();
        if (uri.equalsIgnoreCase(cp + "/adddoctors")) {

                DoctorsDao doctorsDao = new DoctorsDao();
                doctorsDao.createTable(connection);

                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String address = req.getParameter("address");
                String description = req.getParameter("description");

                if (req.getParameter("id").isEmpty()){
                    Doctor doctor = new Doctor(name, email, phone, address, description);
                    doctorsDao.insertTable(connection, doctor);
                }
                else {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Doctor doctor = new Doctor(id, name, email, phone, address, description);
                    doctorsDao.updateTable(connection, doctor);
                }


                resp.sendRedirect(cp + "/displayDoctors");

        }



    }




}
