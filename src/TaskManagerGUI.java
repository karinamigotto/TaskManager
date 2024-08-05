import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TaskManagerGUI {
    private JFrame frame;
    private TaskManager taskManager;
    private JTextArea taskDisplayArea;

    public TaskManagerGUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Task Manager");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        taskDisplayArea = new JTextArea();
        taskDisplayArea.setEditable(false);
        frame.getContentPane().add(new JScrollPane(taskDisplayArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnAddTask = new JButton("Add Task");
        btnAddTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        panel.add(btnAddTask);

        JButton btnListTasks = new JButton("List Tasks");
        btnListTasks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listTasks();
            }
        });
        panel.add(btnListTasks);

        JButton btnCompleteTask = new JButton("Complete Task");
        btnCompleteTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completeTask();
            }
        });
        panel.add(btnCompleteTask);

        JButton btnRemoveTask = new JButton("Remove Task");
        btnRemoveTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });
        panel.add(btnRemoveTask);

        listTasks();
        frame.setVisible(true);
    }

    private void addTask() {
        String description = JOptionPane.showInputDialog(frame, "Enter task description:");
        LocalDate dueDate = LocalDate.parse(JOptionPane.showInputDialog(frame, "Enter due date (YYYY-MM-DD):"));
        TaskPriority priority = TaskPriority.valueOf(JOptionPane.showInputDialog(frame, "Enter priority (LOW, MEDIUM, HIGH):").toUpperCase());
        taskManager.addTask(description, dueDate, priority);
        listTasks();
    }

    private void listTasks() {
        StringBuilder tasksText = new StringBuilder();
        for (Task task : taskManager.getTasks()) {
            tasksText.append(task).append("\n");
        }
        taskDisplayArea.setText(tasksText.toString());
    }

    private void completeTask() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter task ID to complete:"));
        taskManager.markTaskAsCompleted(id);
        listTasks();
    }

    private void removeTask() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter task ID to remove:"));
        taskManager.removeTask(id);
        listTasks();
    }
}
