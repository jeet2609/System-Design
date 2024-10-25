package TaskManagementSystem;

import java.util.Date;

public class Task {
    private final String id;
    private String title;
    private String description;
    private Date dueDate;
    private Priority priority;
    private TaskStatus status;
    private final User assignedUser;

    public Task(String id, String title, String description, Date dueDate, Priority priority, User assignedUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.assignedUser = assignedUser;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Date getDueDate() { return dueDate; }
    public Priority getPriority() { return priority; }
    public TaskStatus getStatus() { return status; }
    public User getAssignedUser() { return assignedUser; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setStatus(TaskStatus status) { this.status = TaskStatus.PENDING; }

    @Override
    public String toString() {
        return "\nId: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nDue Date: " + dueDate + "\nPriority: " + priority + "\nStatus: " + status + "\nAssigned User: " + assignedUser;
    }
}
