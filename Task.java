
package com.studyplanner;

public class Task {
    private int taskId;
    private String title;
    private String subject;
    private String dueDate;
    private String status;

    public Task(int taskId, String title, String subject,
                String dueDate, String status) {
        this.taskId = taskId;
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }
}
