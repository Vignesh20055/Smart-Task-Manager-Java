import java.io.*;
import java.util.*;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private final String FILE_PATH = "tasks.txt";

    public TaskManager() {
        loadTasks();
    }

    public void addTask(String title) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, title));
        saveTasks();
        System.out.println("Task added successfully!");
    }

    public void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
        saveTasks();
        System.out.println("Task deleted successfully!");
    }

    public void markTaskDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.markCompleted();
                saveTasks();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found!");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet!");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    private void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task t : tasks) {
                bw.write(t.getId() + "," + t.getTitle() + "," + t.isCompleted());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Task t = new Task(Integer.parseInt(parts[0]), parts[1]);
                if (Boolean.parseBoolean(parts[2])) t.markCompleted();
                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
