
package com.studyplanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    // View tasks and handle Complete/Delete actions
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try (Connection con = DBConnection.getConnection()) {

            // Mark task as completed
            if ("complete".equals(action)) {
                int id = Integer.parseInt(
                        request.getParameter("id"));

                String sql =
                        "UPDATE tasks SET status='Completed' WHERE task_id=?";

                try (PreparedStatement ps =
                             con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                response.sendRedirect(
                        request.getContextPath() + "/tasks");
                return;
            }

            // Delete task
            if ("delete".equals(action)) {
                int id = Integer.parseInt(
                        request.getParameter("id"));

                String sql =
                        "DELETE FROM tasks WHERE task_id=?";

                try (PreparedStatement ps =
                             con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                response.sendRedirect(
                        request.getContextPath() + "/tasks");
                return;
            }

            // Fetch all tasks from database
            List<Task> taskList = new ArrayList<>();

            String sql =
                    "SELECT task_id, title, subject, due_date, status " +
                    "FROM tasks ORDER BY task_id DESC";

            try (PreparedStatement ps =
                         con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Date date = rs.getDate("due_date");

                    Task task = new Task(
                            rs.getInt("task_id"),
                            rs.getString("title"),
                            rs.getString("subject"),
                            date == null ? "" : date.toString(),
                            rs.getString("status")
                    );

                    taskList.add(task);
                }
            }

            request.setAttribute("taskList", taskList);

            request.getRequestDispatcher("/tasks.jsp")
                   .forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(
                    "Unable to process study tasks.", e);
        }
    }

    // Add a new task from the website form
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String subject = request.getParameter("subject");
        String dueDate = request.getParameter("dueDate");
        String description = request.getParameter("description");

        // Validate task title
        if (title == null || title.trim().isEmpty()) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Task title is required.");
            return;
        }

        String sql =
                "INSERT INTO tasks " +
                "(title, description, subject, due_date, status) " +
                "VALUES (?, ?, ?, ?, 'Pending')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title.trim());
            ps.setString(2, description);
            ps.setString(3, subject);

            if (dueDate == null || dueDate.isBlank()) {
                ps.setNull(4, Types.DATE);
            } else {
                ps.setDate(4, Date.valueOf(dueDate));
            }

            ps.executeUpdate();

            // Return to the updated task list
            response.sendRedirect(
                    request.getContextPath() + "/tasks");

        } catch (SQLException | IllegalArgumentException e) {
            throw new ServletException(
                    "Unable to save study task.", e);
        }
    }
}
