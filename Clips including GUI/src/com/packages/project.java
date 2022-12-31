package com.packages;

import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.CaptureRouter;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class project {
    static JFrame mainFrame = null;
    static String depName = "";
    static String stuName = "";
    static String fc = "";
    static String fl = "";
    static String univ = "";
    public static void startGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize(3000, 3000);
        JPanel panel = new JPanel(null);


        JLabel i1 = new JLabel("Enter Department Name");
        i1.setBounds(50, 50, 200, 30);
        JTextField tf1 = new JTextField();
        tf1.setBounds(50, 100, 500, 30);

        JLabel i2 = new JLabel("Enter Student Name");
        i2.setBounds(50, 150, 200, 30);
        JTextField tf2 = new JTextField();
        tf2.setBounds(50, 200, 500, 30);

        JLabel i3 = new JLabel("Enter Faculty Name's to get its courses");
        i3.setBounds(50, 250, 500, 30);
        JTextField tf3 = new JTextField();
        tf3.setBounds(50, 300, 500, 30);

        JLabel i4 = new JLabel("Enter Faculty Name's to get its lectures");
        i4.setBounds(50, 350, 500, 30);
        JTextField tf4 = new JTextField();
        tf4.setBounds(50, 400, 500, 30);

        JLabel i5 = new JLabel("Enter University Name's to get its faculties");
        i5.setBounds(50, 450, 500, 30);
        JTextField tf5 = new JTextField();
        tf5.setBounds(50, 500, 500, 30);

        JButton startButton = new JButton("Start");
        startButton.setSize(500, 50);
        startButton.setLocation(350, 600);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                depName = tf1.getText();
                stuName = tf2.getText();
                fc = tf3.getText();
                fl = tf4.getText();
                univ = tf5.getText();
                try {
                    start();
                } catch (CLIPSException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(i1);
        panel.add(tf1);
        panel.add(i2);
        panel.add(tf2);
        panel.add(i3);
        panel.add(tf3);
        panel.add(i4);
        panel.add(tf4);
        panel.add(i5);
        panel.add(tf5);
        panel.add(startButton);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
    }

    public static void start() throws CLIPSException {
        Environment clips = new Environment();
        clips.clear();
        CaptureRouter router = new CaptureRouter(clips, new String[] {Router.STDOUT});
        clips.loadFromString(
                "(defclass faculty\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot hasRooms\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot offerCourses\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot containDepartments\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot facultyName\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot isDivisonOf\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot consistsLecturers\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass thesis\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot Description\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass coursework\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot mark\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass NamedIndividual\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(defclass department\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot hasStudent\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot departmentName\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot hasTA\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass course\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot courseCode\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot hasCourseWork\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot courseName\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot hasExams\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot hasLectures\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot offeredBy\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass person\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot personName\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot ID\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot address\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot email\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass academicStaff\n" +
                        "\t(is-a person)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot employeOf\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass TA\n" +
                        "\t(is-a academicStaff)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot writeThesis\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot isAssignedTo\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass lecturer\n" +
                        "\t(is-a academicStaff)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot teachAt\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass student\n" +
                        "\t(is-a person)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot study\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot enrolledIn\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass lecture\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(defclass exam\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot examMark\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass university\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete)\n" +
                        "\t(multislot universityName\n" +
                        "\t\t(type STRING)\n" +
                        "\t\t(create-accessor read-write))\n" +
                        "\t(multislot consistsFaculties\n" +
                        "\t\t(type INSTANCE)\n" +
                        "\t\t(create-accessor read-write)))\n" +
                        "\n" +
                        "(defclass room\n" +
                        "\t(is-a USER)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(defclass labroom\n" +
                        "\t(is-a room)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(defclass lecturehall\n" +
                        "\t(is-a room)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(defclass library\n" +
                        "\t(is-a room)\n" +
                        "\t(role concrete))\n" +
                        "\n" +
                        "(definstances facts \n" +
                        "   ([Cairo] of university     \n" +
                        "\t(consistsFaculties\n" +
                        "\t\t[ FacultyofComputersandArtificialIntelligence]\n" +
                        "\t\t[ FacultyofEngineering]\n" +
                        "\t\t[ FacultyofMedicine]\n" +
                        "\t\t[ FacultyofScience])\n" +
                        "\t(universityName \"cairo\"))\n" +
                        "\n" +
                        "( [course1] of course     \n" +
                        "\t(courseCode \"ML\")\n" +
                        "\t(courseName \"machineLearning\")\n" +
                        ")\n" +
                        "\n" +
                        "([course2] of course     \n" +
                        "\t(courseCode \"WD\")\n" +
                        "\t(courseName \"webDesign\")\n" +
                        ")\n" +
                        "\n" +
                        "([course3] of course     \n" +
                        "\t(courseName \"semanticWeb\")\n" +
                        ")\n" +
                        "\n" +
                        "([course4] of course     \n" +
                        "\t(courseCode \"CG\")\n" +
                        "\t(courseName \"computerGraphics\")\n" +
                        ")\n" +
                        "\n" +
                        "([Department1] of department     \n" +
                        "\n" +
                        "\t(departmentName \"CS\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([Department2] of department     \n" +
                        "\n" +
                        "\t(departmentName \"IS\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([Department3] of department     \n" +
                        "\n" +
                        "\t(departmentName \"IT\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([Department4] of department     \n" +
                        "\t(departmentName \"AI\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([Department5] of department     \n" +
                        "\t(departmentName \"DS\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([FacultyofComputersandArtificialIntelligence] of faculty     \n" +
                        "\n" +
                        "\t(consistsLecturers\n" +
                        "\t\t[Lecturer1]\n" +
                        "\t\t[Lecturer2]\n" +
                        "\t\t[Lecturer3])\n" +
                        "\t(facultyName \"ComputersandArtificialIntelligence\")\n" +
                        "\t(offerCourses\n" +
                        "\t\t[course1]\n" +
                        "\t\t[course2]\n" +
                        "\t\t[course3]\n" +
                        "\t\t[course4])\n" +
                        "\t)\n" +
                        "\n" +
                        "([FacultyofEngineering] of faculty     \n" +
                        "\t(facultyName \"Engineering\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([FacultyofMedicine] of faculty     \n" +
                        "\t(facultyName \"Medicine\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([FacultyofScience] of faculty     \n" +
                        "\t(facultyName \"Science\")\n" +
                        "\t)\n" +
                        "\n" +
                        "([Lecturer1] of lecturer     \n" +
                        "\t(personName \"Dr.Ahmed\")\n" +
                        ")\n" +
                        "\n" +
                        "([Lecturer2] of lecturer     \n" +
                        "\n" +
                        "\t(personName \"Dr.doaa\")\n" +
                        ")\n" +
                        "\n" +
                        "([Lecturer3] of lecturer     \n" +
                        "\t(personName \"Dr.reda\")\n" +
                        ")\n" +
                        "([student1] of student     \n" +
                        "\n" +
                        "\t(enrolledIn [Department1])\n" +
                        "\t(personName \"bassant\")\n" +
                        "\t(study\n" +
                        "\t\t[course1]\n" +
                        "\t\t[course2])\n" +
                        "\t)\n" +
                        "\n" +
                        "([student2] of  student     \n" +
                        "\t(enrolledIn [Department2])\n" +
                        "\t(personName \"rowan\")\n" +
                        "\t(study\n" +
                        "\t\t[course2]\n" +
                        "\t\t[course3])\n" +
                        ")\n" +
                        "\n" +
                        "([student3] of student     \n" +
                        "\t(enrolledIn [Department3])\n" +
                        "\t(personName \"ehab\")\n" +
                        "\t(study\n" +
                        "\t\t[course1]\n" +
                        "\t\t[course4])\n" +
                        "\t)\n" +
                        "\n" +
                        "([student4] of student     \n" +
                        "\n" +
                        "\t(enrolledIn [Department4])\n" +
                        "\t(personName \"Ahmed\")\n" +
                        "\t(study\n" +
                        "\t\t[course1]\n" +
                        "\t\t[course4])\n" +
                        ")\n" +
                        "\n" +
                        "\n" +
                        "([TA1] of TA     \n" +
                        "\n" +
                        "\t(address \"haramStreet\")\n" +
                        "\t(email \"rami@gmail.com\")\n" +
                        "\t(isAssignedTo [Department1])\n" +
                        "\t(personName \"Rami\")\n" +
                        ")\n" +
                        "\n" +
                        "([TA2] of TA     \n" +
                        "\t(address \"faisalStreet\")\n" +
                        "\t(email \"rania@gmail.com\")\n" +
                        "\t(isAssignedTo [Department2])\n" +
                        "\t(personName \"Rania\")\n" +
                        ")\n" +
                        "\n" +
                        "([TA3] of TA     \n" +
                        "\t(address \"dokkiStreet\")\n" +
                        "\t(email \"reko@yahoo.com\")\n" +
                        "\t(isAssignedTo [Department3])\n" +
                        "\t(personName \"Reem\")\n" +
                        "))\n" +
                        "\n" +
                        "\n\n" +
                "\n" +
                "(defrule list-tas-for-department \n" +
                "(object(is-a department) (name ?dep) (departmentName \""+depName+"\"))\n" +
                "(object(is-a TA) (isAssignedTo ?dep) (personName ?name) )\n" +
                "=> (printout t ?name \" is assigned to \" \""+depName+"\" crlf )\n" +
                ")\n" +
                "(defrule list-courses-for-student  \n" +
                "(object (is-a student)  (personName \""+stuName+"\") (study $?courses) )\n" +
                "=> (printout t \""+stuName+"\" \" studies \" $?courses crlf)\n" +
                "   \n" +
                ")\n" +
                "   \n" +
                "\n" +
                "(defrule list-faculty-offers \n" +
                "(object(is-a faculty) (name ?fac) (facultyName \""+fc+"\")(offerCourses $?courses) )\n" +
                "=> (printout t \"faculty\" \""+fc+"\" \"offers\" $?courses crlf  )\n" +
                ")\n" +
                "\n" +
                "\n" +
                "\n" +
                "(defrule list-faculty-Lecturers \n" +
                "(object(is-a faculty) (name ?fac) (facultyName \""+fl+"\")(consistsLecturers $?lecs) )\n" +
                "=> (printout t \""+fl+"\" \" are\" $?lecs crlf  )\n" +
                ")\n" +
                "\n" +
                "\n" +
                "(defrule list-university-faculties\n" +
                "(object(is-a university) (name ?uni) (universityName \""+univ+"\")(consistsFaculties $?facs) )\n" +
                "=> (printout t \""+univ+"\" \" consists of \" $?facs crlf )\n" +
                ")");
        clips.reset();
        clips.run();
        String returnedString = router.getOutput();
        String[] list = returnedString.split("\n");
        mainFrame = new JFrame();
        mainFrame.setSize(2000, 2000);
        JPanel panel = new JPanel(new GridLayout(20, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        panel.add(new JLabel("Output", SwingConstants.CENTER));
        for (String l : list) {
            panel.add(new JLabel(l));
        }
        mainFrame.setVisible(true);
        mainFrame.add(panel);
    }

    public static void main(String[] args) {
        startGUI();
    }
}
