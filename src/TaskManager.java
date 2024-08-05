
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int currentId;
    private static final String FILE_PATH = "data/tasks.txt";

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.currentId = 1;
        loadTasks();
    }

    public void addTask(String description, LocalDate dueDate, TaskPriority priority) {
        Task newTask = new Task(currentId++, description, dueDate, priority);
        tasks.add(newTask);
        System.out.println("Task added: " + newTask);
        saveTasks();
    }

    public void listTasks() {
        System.out.println("Task List:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                System.out.println("Task marked as completed: " + task);
                saveTasks();
                return;
            }
        }
        System.out.println("Task not found with ID: " + id);
    }

    public void updateTask(int id, String newDescription, LocalDate newDueDate, TaskPriority newPriority) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                task.setDueDate(newDueDate);
                task.setPriority(newPriority);
                System.out.println("Task updated: " + task);
                saveTasks();
                return;
            }
        }
        System.out.println("Task not found with ID: " + id);
    }

    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("Task removed with ID: " + id);
        saveTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            tasks = (List<Task>) ois.readObject();
            if (!tasks.isEmpty()) {
                currentId = tasks.get(tasks.size() - 1).getId() + 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous tasks found, starting fresh.");
        }
    }
}
