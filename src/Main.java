
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        new TaskManagerGUI(taskManager);

        // Uncomment the following line to enable notifications for due dates
        // new NotificationManager(taskManager).checkForDueDates();
    }
}
