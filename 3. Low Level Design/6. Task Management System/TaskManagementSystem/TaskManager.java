package TaskManagementSystem;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager {
    private static TaskManager instance;
    private final Map<String, Task> tasks;
    private final Map<String, List<Task>> userTasks;

    private TaskManager() {
        this.tasks = new ConcurrentHashMap<>();
        this.userTasks = new ConcurrentHashMap<>();
    }

    public static TaskManager getInstance() {
        if(instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        assignedTaskToUser(task.getAssignedUser(), task);
    }

    public void deleteTask(String taskId) {
        Task task = tasks.remove(taskId);
        if(task != null) {
            unAssignedTaskFromUser(task.getAssignedUser(), task);
        }
    }

    public void updateTask(Task updatedTask) {
        Task existingTask = tasks.get(updatedTask.getId());
        if(existingTask != null) {
            synchronized (existingTask) {
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setDueDate(updatedTask.getDueDate());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setStatus(updatedTask.getStatus());

                User previousUser = existingTask.getAssignedUser();
                User newUser = updatedTask.getAssignedUser();

                if(!previousUser.equals(newUser)) {
                    assignedTaskToUser(newUser, existingTask);
                    unAssignedTaskFromUser(previousUser, existingTask);
                }
            }
        }
    }

    public List<Task> searchTask(String keyword) {
        List<Task> matchingTask = new ArrayList<>();
        for (Task task : tasks.values()) {
            if(task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTask.add(task);
            }
        }

        return matchingTask;
    }

    public List<Task> filterTasks(TaskStatus status, Date startDate, Date endDate, Priority priority) {
        List<Task> filteredTask = new ArrayList<>();
        for (Task task : tasks.values()) {
            if(task.getStatus() == status && task.getDueDate().compareTo(startDate) >= 0 && task.getDueDate().compareTo(endDate) <= 0 && task.getPriority() == priority) {
                filteredTask.add(task);
            }
        }

        return filteredTask;
    }

    public void markTaskAsCompleted(String taskId) {
        Task task = tasks.get(taskId);
        if(task != null) {
            synchronized (task) {
                task.setStatus(TaskStatus.COMPLETED);
            }
        }
    }

    public List<Task> getTaskHistory(User user) {
        return new ArrayList<>(userTasks.getOrDefault(user.getId(), new ArrayList<>()));
    }

    private void assignedTaskToUser(User user, Task task) {
        userTasks.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(task);
    }

    private void unAssignedTaskFromUser(User user, Task task) {
        List<Task> tasks = userTasks.get(user.getId());
        if(tasks != null) {
            tasks.remove(task);
        }
    }

    @Override
    public String toString() {
        String allTasks = "";
        for (String userId : userTasks.keySet()) {
            allTasks += "\n######################## " + userId + " ########################";

            for(Task task : userTasks.get(userId)) {
                allTasks += task + "\n-------------------------------------------";
            }
        }

        return allTasks;
    }
}
