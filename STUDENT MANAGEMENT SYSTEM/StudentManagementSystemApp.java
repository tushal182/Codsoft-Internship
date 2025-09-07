import java.io.*;
import java.util.*;

// ================== Student Class ==================
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Roll: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

// ================== Student Management System ==================
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("Student added!");
    }

    public void removeStudent(int rollNumber) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getRollNumber() == rollNumber) {
                it.remove();
                saveToFile();
                System.out.println("Student removed!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void editStudent(int rollNumber, String newName, String newGrade) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                s.setName(newName);
                s.setGrade(newGrade);
                saveToFile();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
        } else {
            System.out.println("All Students:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save students to file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}

// ================== Main Application ==================
public class StudentManagementSystemApp {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    // Login system with 3 attempts
    private static boolean login(Scanner sc) {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter username: ");
            String user = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();

            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login successful! Welcome, " + user + ".");
                return true;
            } else {
                attempts--;
                System.out.println("Invalid login! Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // First check login
        if (!login(sc)) {
            sc.close();
            return;
        }

        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to Remove: ");
                    sms.removeStudent(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Search: ");
                    sms.searchStudent(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter Roll Number to Edit: ");
                    int r = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();
                    sms.editStudent(r, newName, newGrade);
                    break;

                case 5:
                    sms.displayAll();
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
