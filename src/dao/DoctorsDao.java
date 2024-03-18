package dao;

import model.Doctor;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;

public class DoctorsDao {
    public void createTable(Connection connection){

        try {
            String query = "CREATE TABLE IF NOT EXISTS doctors(doctorId INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), phone VARCHAR(50), address VARCHAR(50), description TEXT);";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTable(Connection connection, Doctor doctor){
        try {
            String query = "INSERT INTO doctors (name, email, phone, address, description) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getEmail());
            preparedStatement.setString(3, doctor.getPhone());
            preparedStatement.setString(4, doctor.getAddress());
            preparedStatement.setString(5, doctor.getDescription());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTable(Connection connection, Doctor doctor) {
        try {
            String query = "UPDATE doctors SET name = ?, email = ?, phone = ?, address = ?, description = ? WHERE doctorId = "+doctor.getDoctorId();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getEmail());
            preparedStatement.setString(3, doctor.getPhone());
            preparedStatement.setString(4, doctor.getAddress());
            preparedStatement.setString(5, doctor.getDescription());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(Connection connection, int id) {
        String query = "DELETE FROM doctors WHERE doctorId = "+id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            fetchTable(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Doctor fetchDoctor(Connection connection, int doctorId) {
        String query = "SELECT doctorId, name, email, phone, address, description FROM doctors WHERE doctorId =" + doctorId;

        Doctor doctor = new Doctor();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {

                int id = resultSet.getInt("doctorId");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");

                System.out.println(id + ",  " + name + ", " + email + " ");
                System.out.println("Hello");

                doctor = new Doctor(id, name, email, phone, address, description);

            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public ArrayList<Doctor> fetchTable(Connection connection) {
        String query = "SELECT * FROM doctors";
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("doctorId");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");

                System.out.println(id + ",  " + name + ", " + email + " ");
                System.out.println("Hello");

                Doctor doctor = new Doctor(id, name, email, phone, address, description);
                doctorArrayList.add(doctor);

            }
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctorArrayList;
    }


}
