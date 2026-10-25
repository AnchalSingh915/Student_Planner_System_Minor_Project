
<%@ page import="java.util.List" %>
<%@ page import="com.studyplanner.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Study Planner</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f5f9;
            margin: 0;
            padding: 30px;
            color: #1e293b;
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
        }
        h1 { color: #2563eb; }
        input, textarea, button {
            padding: 10px;
            margin: 5px 0;
            box-sizing: border-box;
        }
        input, textarea { width: 100%; }
        button {
            background: #2563eb;
            color: white;
            border: 0;
            border-radius: 5px;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th { background: #dbeafe; }
        a { margin-right: 8px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Student Study Planner</h1>
    <p>Plan your study tasks and track your progress.</p>

    <h2>Add New Task</h2>
    <form action="<%= request.getContextPath() %>/tasks"
          method="post">
        <label>Task Title *</label>
        <input type="text" name="title"
               placeholder="e.g. Complete DAA assignment"
               required>

        <label>Subject</label>
        <input type="text" name="subject"
               placeholder="e.g. DAA">

        <label>Due Date</label>
        <input type="date" name="dueDate">

        <label>Description</label>
        <textarea name="description"
                  placeholder="Task details"></textarea>

        <button type="submit">Add Task</button>
    </form>

    <h2>My Study Tasks</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Subject</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            List<Task> taskList =
                (List<Task>) request.getAttribute("taskList");

            if (taskList != null && !taskList.isEmpty()) {
                for (Task task : taskList) {
        %>
        <tr>
            <td><%= task.getTitle() %></td>
            <td><%= task.getSubject() == null
                    ? "" : task.getSubject() %></td>
            <td><%= task.getDueDate() %></td>
            <td><%= task.getStatus() %></td>
            <td>
                <% if (!"Completed".equals(task.getStatus())) { %>
                    <a href="<%= request.getContextPath() %>/tasks?action=complete&id=<%= task.getTaskId() %>">
                        Complete
                    </a>
                <% } %>
                <a href="<%= request.getContextPath() %>/tasks?action=delete&id=<%= task.getTaskId() %>"
                   onclick="return confirm('Delete this task?')">
                    Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No tasks added yet.</td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
