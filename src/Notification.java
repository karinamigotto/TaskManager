
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Notification {
    private TaskManager taskManager;

    public Notification(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void checkForDueDates() {
        List<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            if (!task.isCompleted() && ChronoUnit.DAYS.between(LocalDate.now(), task.getDueDate()) <= 1) {
                System.out.println("Reminder: Task \"" + task.getDescription() + "\" is due soon.");
            }
        }
    }
}
