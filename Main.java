import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SMART TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = sc.nextLine();
                    manager.addTask(title);
                }
                case 2 -> {
                    System.out.print("Enter task ID to delete: ");
                    int id = sc.nextInt();
                    manager.deleteTask(id);
                }
                case 3 -> {
                    System.out.print("Enter task ID to mark as done: ");
                    int id = sc.nextInt();
                    manager.markTaskDone(id);
                }
                case 4 -> manager.viewTasks();
                case 5 -> {
                    System.out.println("Goodbye 👋");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
