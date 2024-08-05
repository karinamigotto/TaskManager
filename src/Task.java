import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private int id;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private TaskPriority priority;

    public Task(int id, String description, LocalDate dueDate, TaskPriority priority) {
        this.id = id;
        this.description = description;
        this.completed = false;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Due Date: " + dueDate +
                ", Completed: " + completed + ", Priority: " + priority;
    }
}
