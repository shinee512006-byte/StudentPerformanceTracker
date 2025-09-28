package studentTracker;

import java.util.Scanner;

public class StudentPerformanceTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String studentName = "";
        String studentID = "";
        int numCourses = 0;
        int[] marks = null;
        
        int choice;
        
        do {
            System.out.println("Menu:");
            System.out.println("1. Enter Student Details");
            System.out.println("2. Enter Marks");
            System.out.println("3. Display Marks Info.");
            System.out.println("4. Display Student Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter Student Name:");
                    studentName = toTitleCase(input.nextLine().trim());
                    
                    boolean validID = false;
                    while (!validID) {
                        System.out.println("Enter ID Number:");
                        studentID = input.nextLine().trim();
                        if (isValidID(studentID)) {
                            validID = true;
                        } else {
                            System.out.println("Invalid ID. Must start with 4 and be 9 digits.");
                        }
                    }
                    
                    boolean validCourses = false;
                    while (!validCourses) {
                        System.out.println("How many courses last semester?");
                        numCourses = input.nextInt();
                        input.nextLine();
                        if (numCourses > 0 && numCourses <= 5) {
                            validCourses = true;
                        } else {
                            System.out.println("Number of courses must be between 1 and 5.");
                        }
                    }
                    
                    marks = new int[numCourses];
                    break;
                    
                case 2:
                    if (numCourses == 0) {
                        System.out.println("Please enter student details first (Option 1).");
                        break;
                    }
                    
                    System.out.println("Enter Marks for " + numCourses + " subjects:");
                    for (int i = 0; i < numCourses; i++) {
                        boolean validMark = false;
                        while (!validMark) {
                            System.out.print("Mark for course " + (i + 1) + ": ");
                            int mark = input.nextInt();
                            if (isValidMark(mark)) {
                                marks[i] = mark;
                                validMark = true;
                            } else {
                                System.out.println("Invalid mark. Must be between 0 and 100.");
                            }
                        }
                    }
                    break;
                    
                case 3:
                    if (marks == null || marks.length == 0) {
                        System.out.println("No marks entered yet.");
                        break;
                    }
                    displayMarksInfo(marks);
                    break;
                    
                case 4:
                    if (studentName.isEmpty()) {
                        System.out.println("No student details entered yet.");
                        break;
                    }
                    displayStudentDetails(studentName, studentID, marks);
                    break;
                    
                case 5:
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1–5.");
            }
            
        } while (choice != 5);
        
        input.close();
    }
    
    // دالة للتحقق من صحة ID
    public static boolean isValidID(String id) {
        return id.matches("4\\d{8}");
    }
    
    // دالة للتحقق من صحة العلامة
    public static boolean isValidMark(int mark) {
        return mark >= 0 && mark <= 100;
    }
    
    // تحويل الاسم إلى Title Case
    public static String toTitleCase(String name) {
        String[] words = name.split("\\s+");
        StringBuilder titleCase = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                         .append(word.substring(1).toLowerCase())
                         .append(" ");
            }
        }
        return titleCase.toString().trim();
    }
    
    // حساب المتوسط باستخدام Math.round لتقريب النتيجة
    public static double calculateAverage(int[] marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        double avg = (double) sum / marks.length;
        return Math.round(avg * 10.0) / 10.0; // تقريب لمنزلة عشرية واحدة
    }
    
    // إيجاد أعلى درجة باستخدام Math.max
    public static int getMax(int[] marks) {
        int max = marks[0];
        for (int mark : marks) {
            max = Math.max(max, mark);
        }
        return max;
    }
    
    // إيجاد أقل درجة باستخدام Math.min
    public static int getMin(int[] marks) {
        int min = marks[0];
        for (int mark : marks) {
            min = Math.min(min, mark);
        }
        return min;
    }
    
    // عرض معلومات العلامات
    public static void displayMarksInfo(int[] marks) {
        System.out.println("Marks Info:");
        int max = getMax(marks);
        int min = getMin(marks);
        double avg = calculateAverage(marks);
        
        for (int i = 0; i < marks.length; i++) {
            String passFail = (marks[i] >= 60) ? "Pass" : "Fail";
            String maxMin = "";
            if (marks[i] == max) maxMin = "Max";
            if (marks[i] == min) maxMin = "Min";
            System.out.printf("Course %d: %d - %s %s%n", (i + 1), marks[i], passFail, maxMin);
        }
        
        System.out.printf("Average: %.1f%n", avg);
        System.out.println("Max Mark: " + max);
        System.out.println("Min Mark: " + min);
    }
    
    // عرض تفاصيل الطالب
    public static void displayStudentDetails(String name, String id, int[] marks) {
        System.out.println("Name: " + name);
        System.out.println("KKU ID: " + id);
        
        double avg = calculateAverage(marks);
        System.out.printf("Course Mark P/F Avg: %.1f%n", avg);
        System.out.println("===========================");
        
        int max = getMax(marks);
        int min = getMin(marks);
        
        for (int i = 0; i < marks.length; i++) {
            String passFail = (marks[i] >= 60) ? "Pass" : "Fail";
            String maxMin = "";
            if (marks[i] == max) maxMin = "Max";
            if (marks[i] == min) maxMin = "Min";
            System.out.printf("%d    %d    %s    %s%n", (i + 1), marks[i], passFail, maxMin);
        }
    }
}