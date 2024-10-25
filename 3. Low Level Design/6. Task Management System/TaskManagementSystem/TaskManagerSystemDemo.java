package TaskManagementSystem;

import java.util.Date;
import java.util.List;

public class TaskManagerSystemDemo {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        // Create Users
        User user1 = new User("1", "Jeet", "jeet@example.com");
        User user2 = new User("2", "Namrata", "namrata@example.com");

        // Create Tasks
        Task task1 = new Task("1", "Task 1", "Description 1", new Date(), Priority.LOW, user1);
        Task task2 = new Task("2", "Task 2", "Description 2", new Date(), Priority.LOW, user2);
        Task task3 = new Task("3", "Task 3", "Description 3", new Date(), Priority.MEDIUM, user1);

        // Add task to taskManager
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        // Display all tasks
        System.out.println(taskManager + "\n");

        // Update a task
        task2.setDescription("Updated Description 2");
        taskManager.updateTask(task2);

        // Display all tasks
        System.out.println(taskManager + "\n");

        // Search task
        List<Task> searchResults = taskManager.searchTask("Task");
        System.out.println("\nSearch Results with keyword: " + "Task");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        // Filter tasks
        List<Task> filteredTasks = taskManager.filterTasks(TaskStatus.PENDING, new Date(0), new Date(), Priority.LOW);
        System.out.println("\nFiltered Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task);
        }

        // Mark a task as completed
        taskManager.markTaskAsCompleted("1");

        // Get task history for a user
        List<Task> taskHistory = taskManager.getTaskHistory(user1);
        System.out.println("\nTask History for " + user1.getName() + ":");
        for (Task task : taskHistory) {
            System.out.println(task.getTitle());
        }

        // Delete a task
        taskManager.deleteTask("3");

        // Display all tasks
        System.out.println(taskManager + "\n");
    }
}
