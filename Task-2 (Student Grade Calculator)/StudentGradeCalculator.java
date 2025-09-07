import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take number of subjects input
        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] subjects = new String[n];
        int[] marks = new int[n];
        int total = 0;

        // Input subject names and marks
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of subject " + (i + 1) + ": ");
            subjects[i] = sc.nextLine();

            System.out.print("Enter marks obtained in " + subjects[i] + " (out of 100): ");
            marks[i] = sc.nextInt();
            sc.nextLine(); // consume newline

            total += marks[i];
        }

        // Calculate average percentage
        double average = (double) total / n;

        // Calculate grade
        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else if (average >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n--- Student Report ---");
        for (int i = 0; i < n; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }

        System.out.println("Total Marks: " + total);
        System.out.println("Average Percentage: " + average + "%");
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
