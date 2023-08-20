import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    String name;
    String rollNumber;
    String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
}

class StudentManagementSystem {
    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(String rollNumber) {
        students.removeIf(student -> student.rollNumber.equals(rollNumber));
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.rollNumber.equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagement extends JFrame {
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea outputArea;
    private StudentManagementSystem system;

    public StudentManagement() {
        system = new StudentManagementSystem();
        JLabel nameLabel = new JLabel("Name:");
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        JLabel gradeLabel = new JLabel("Grade:");

        nameField = new JTextField(20);
        rollNumberField = new JTextField(10);
        gradeField = new JTextField(5);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");

        outputArea = new JTextArea(30, 80);
        Color bgColor = new Color(144, 238, 144);
        outputArea.setBackground(bgColor);
        outputArea.setEditable(false);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        JPanel controlPanel = new JPanel();
        controlPanel.add(searchButton);
        controlPanel.add(displayButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollNumber = rollNumberField.getText();
                String grade = gradeField.getText();
                if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentManagement.this, "All fields are required.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student student = new Student(name, rollNumber, grade);
                system.addStudent(student);

                outputArea.append("Student added: " + student.name + "\n");
                clearFields();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollNumber = rollNumberField.getText();
                system.removeStudent(rollNumber);

                outputArea.append("Student removed: " + rollNumber + "\n");
                clearFields();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rollNumber = rollNumberField.getText();
                Student student = system.searchStudent(rollNumber);

                if (student != null) {
                    outputArea.append("Student found: " + student.name + ", Grade: " + student.grade + "\n");
                } else {
                    outputArea.append("Student not found.\n");
                }
                clearFields();
            }
        });
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                ArrayList<Student> students = system.getAllStudents();
                for (Student student : students) {
                    outputArea.append("Name: " + student.name + ", Roll Number: " + student.rollNumber + ", Grade: "
                            + student.grade + "\n");
                }
            }
        });
        getContentPane().setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagement().setVisible(true);
            }
        });
    }
}
