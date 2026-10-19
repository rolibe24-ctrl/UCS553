package Qno1;
import java.util.*;

class Course {
    String courseCode;
    String courseName;
    int capacity;

    HashSet<String> enrolledStudents;
    ArrayList<String> prerequisites;
    LinkedList<String> waitlist;

    Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;

        enrolledStudents = new HashSet<>();
        prerequisites = new ArrayList<>();
        waitlist = new LinkedList<>();
    }

    void addPrerequisite(String code) {
        prerequisites.add(code);
    }

    void displayCourse() {
        System.out.println("\nCourse: " + courseName);
        System.out.println("Code: " + courseCode);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
        System.out.println("Prerequisites: " + prerequisites);
        System.out.println("Waitlist: " + waitlist);
    }
}

public class CourseRegistrationPortal {

    // Fast course lookup by course code
    static HashMap<String, Course> courses = new HashMap<>();

    // Stores courses completed by each student
    static HashMap<String, HashSet<String>> completedCourses =
            new HashMap<>();

    // Add a new course
    static void addCourse(Course course) {
        courses.put(course.courseCode, course);
        System.out.println("Course added: " + course.courseCode);
    }

    // Record a completed course for a student
    static void completeCourse(String studentId, String courseCode) {
        completedCourses
            .computeIfAbsent(studentId, k -> new HashSet<>())
            .add(courseCode);
    }

    // Check whether all prerequisites are completed
    static boolean prerequisitesMet(String studentId, Course course) {
        HashSet<String> completed = completedCourses.get(studentId);

        if (completed == null) {
            return course.prerequisites.isEmpty();
        }

        return completed.containsAll(course.prerequisites);
    }

    // Register a student
    static void registerStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.enrolledStudents.contains(studentId)) {
            System.out.println("Student already enrolled.");
            return;
        }

        if (course.waitlist.contains(studentId)) {
            System.out.println("Student already on the waitlist.");
            return;
        }

        if (!prerequisitesMet(studentId, course)) {
            System.out.println("Prerequisites not completed.");
            return;
        }

        if (course.enrolledStudents.size() < course.capacity) {
            course.enrolledStudents.add(studentId);
            System.out.println(studentId + " enrolled in " + courseCode);
        } else {
            course.waitlist.addLast(studentId);
            System.out.println(studentId + " added to waitlist for "
                    + courseCode);
        }
    }

    // Promote the first eligible student from the waitlist
    static void promoteWaitlistedStudent(Course course) {
        Iterator<String> iterator = course.waitlist.iterator();

        while (iterator.hasNext()
                && course.enrolledStudents.size() < course.capacity) {

            String studentId = iterator.next();

            if (prerequisitesMet(studentId, course)) {
                iterator.remove();
                course.enrolledStudents.add(studentId);

                System.out.println(studentId
                        + " promoted from waitlist to "
                        + course.courseCode);
                return;
            }
        }

        System.out.println("No eligible student to promote.");
    }

    // Drop a course and promote the next eligible student
    static void dropCourse(String studentId, String courseCode) {
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.enrolledStudents.remove(studentId)) {
            System.out.println(studentId + " dropped " + courseCode);

            promoteWaitlistedStudent(course);
        } else if (course.waitlist.remove(studentId)) {
            System.out.println(studentId + " removed from waitlist.");
        } else {
            System.out.println("Student is not enrolled or waitlisted.");
        }
    }

    // Display all courses
    static void displayAllCourses() {
        for (Course course : courses.values()) {
            course.displayCourse();
        }
    }

    public static void main(String[] args) {

        // Create courses
        Course java = new Course("CS101", "Java Programming", 2);
        Course dsa = new Course("CS201", "Data Structures", 2);

        // Set prerequisites
        dsa.addPrerequisite("CS101");

        addCourse(java);
        addCourse(dsa);

        // Record completed prerequisites
        completeCourse("S101", "CS101");
        completeCourse("S102", "CS101");
        completeCourse("S103", "CS101");
        completeCourse("S104", "CS101");

        // Register students in Java
        registerStudent("S101", "CS101");
        registerStudent("S102", "CS101");
        registerStudent("S103", "CS101");

        // Register students in Data Structures
        registerStudent("S101", "CS201");
        registerStudent("S102", "CS201");
        registerStudent("S103", "CS201");

        // Drop a course to trigger promotion
        dropCourse("S101", "CS201");

        // Display final status
        displayAllCourses();
    }
}
