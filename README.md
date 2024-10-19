# ProgPattProject

This is the final project for the Programming Patterns class at Vanier College.

<img src="https://pbs.twimg.com/profile_images/1639345927696572424/Vy4-nVk__400x400.jpg" alt="Vanier College Logo" width="100" height="100">


## Online Quiz Management System

The Online Quiz Management System (OOMS) is a small-scale
where teachers create, edit and remove topics and add and
remove quizzes and tests. They can also manage students,
grade their tests and see their grades. Students will be 
to take optional quizzes to practice for a test at the end
of a Topic.

### Requirements:

The application provides different services based on the user type (Teacher or Student)

1. Teacher
   1. Add, edit and remove topics
   2. Add, edit and remove quizzes and tests for each topic
   3. Add, edit and remove questions from quizzes and tests
   4. Add, edit and remove students from groups
   5. Review and Grade the tests
   6. View the grade of each individual student
   7. View the grade of an entire topic
2. Student
   1. Take quizzes over and over again
   2. Take tests once they are ready
   3. Check their grades
   4. Check the average for their group

### Database Structure

1. Teacher table
    ```
    | ID | FIRSTNAME | LASTNAME | 
    EMAIL | PASSWORD | TYPE | 
    GROUPS | TOPICS |
    ```
   * `ID`: Identifier, INT, primary key
   * `FIRSTNAME`: Teacher's firstname, STRING, primary key
   * `LASTNAME`: Teacher's lastname, STRING, primary key
   * `EMAIL`: Teacher's email, STRING, primary key
   * `PASSWORD`: Teacher's email, STRING, primary key
2. Student table

This application is a quiz management system used by teachers where students can take quizes and tests.
This application will be used by teachers and students.<br>
<br>
The teacher will be able to manage students and manage topics. 
This emplies that teachers can add, remove or edit students and topics.
A topic is composed of quizes and tests.

### The quizes are:

  * optional and their main purpose is practice
  * comprised of mutliple choice questions and true or false questions
  * are graded automatically so that the student knows how well 
    they did and they can always retake them to do better

### The tests are:

  * required for the student to complete
  * only taken once and they are timed
  * reviewed and graded by the teacher

<br>
The student will be able to take quizes and take tests.<br>


## How to use
Download the project and open the FinalProject folder in you're ***favorite*** IDE, then run the main file.
Or download the jar file and run it?


## 6.1 Deliverable 1 - Project Description:
Submit a project idea, addressing the following points:
  * Scenario: Explain the scenario under which your project will operate.
  * Design Paradigm: List the functionalities you plan to demonstrate.
  * Expected Output: Describe the expected results and the actions the user can perform with your application.
  * Git Repository: Initialize a Maven project with valid .gitignore, and a README.md file for a project description. Create a doc folder which contains diagrams and the the Deliverable 1 PDF.
