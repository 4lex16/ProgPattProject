package org.example.finalproject.controller;

import org.example.finalproject.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DatabaseController {
    private static final String DATABASE_URL = "jdbc:sqlite:./src/main/resources/onlineQuizManagementSystemDatabase.db";
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = LOCK.readLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = LOCK.writeLock();


    private static Connection connect() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates all the tables in the database
     * Runs multiple sqlite queries
     * Group : group_id, teacher_id, student_id, topic_id
     */
    public static void createTables() {
        // Group table
        String groupSql =
                """
                CREATE TABLE IF NOT EXISTS group (
                    group_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    teacher_id INTEGER,
                    topic_id INTEGER,
                    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id) ON DELETE CASCADE,
                    FOREIGN KEY (topic_id) REFERENCES topic(topic_id) ON DELETE SET NULL
                );
                """;
        String groupStudentSql =
                """
                CREATE TABLE IF NOT EXISTS groupStudent (
                    group_id INTEGER,
                    student_id INTEGER,
                    PRIMARY KEY (group_id, student_id),
                    FOREIGN KEY (group_id) REFERENCES group(group_id) ON DELETE CASCADE;
                    );
                """;
        String teacherSql =
                """
                CREATE TABLE IF NOT EXISTS teacher (
                    teacher_id INTEGER PRIMARY KEY,
                    fname TEXT NOT NULL,
                    lname TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL
                );
                """;
        String studentSql =
                """
                CREATE TABLE IF NOT EXISTS student (
                    student_id INTEGER PRIMARY KEY,
                    fname TEXT NOT NULL,
                    lname TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL
                );
                """;

        String topicSql =
                """
                CREATE TABLE IF NOT EXISTS topic (
                    topic_id INTEGER PRIMARY KEY,
                    topic_name TEXT NOT NULL
                );
                """;

        String testSql =
                """
                CREATE TABLE IF NOT EXISTS test (
                    test_id INTEGER PRIMARY KEY,
                    topic_id INTEGER NOT NULL,
                    test_name TEXT NOT NULL,
                    FOREIGN KEY (topic_id) REFERENCES topic(topic_id) ON DELETE CASCADE
                );
                """;

        String quizSql =
                """
                CREATE TABLE IF NOT EXISTS quiz (
                    quiz_id INTEGER PRIMARY KEY,
                    topic_id INTEGER,
                    quiz_name TEXT,
                    FOREIGN KEY (topic_id) REFERENCES topic(topic_id) ON DELETE CASCADE
                );
                """;

        String questionSql =
                """
                CREATE TABLE IF NOT EXISTS question (
                    question_id INTEGER PRIMARY KEY,
                    question_question TEXT NOT NULL,
                    question_answer TEXT NOT NULL,
                    question_type TEXT NOT NULL,
                    option_id INTEGER,
                    topic_id INTEGER,
                    FOREIGN KEY (option_id) REFERENCES questionOption(option_id),
                    FOREIGN KEY (topic_id) REFERENCES topic(topic_id)
                );
                """;

        String questionOptionSql =
                """
                CREATE TABLE IF NOT EXISTS questionOption (
                    option_id INTEGER PRIMARY KEY,
                    option_a TEXT NOT NULL,
                    option_b TEXT NOT NULL,
                    option_c TEXT NOT NULL,
                    option_d TEXT NOT NULL,
                );
                """;

        String quizQuestionSql =
                """
                CREATE TABLE IF NOT EXISTS quizQuestion (
                    question_grade INTEGER,
                    quiz_id INTEGER,
                    question_id INTEGER,
                    student_id INTEGER,
                    PRIMARY KEY (quiz_id, question_id, student_id),
                    FOREIGN KEY (quiz_id) REFERENCES quiz(quiz_id),
                    FOREIGN KEY (question_id) REFERENCES question(question_id),
                    FOREIGN KEY (student) REFERENCES student(student_id)
                );
                """;

        String testQuestionSql =
                """
                CREATE TABLE IF NOT EXISTS testQuestion (
                    question_grade INTEGER,                        
                    test_id INTEGER,
                    question_id INTEGER,
                    student_id INTEGER,
                    PRIMARY KEY (test_id, question_id, student_id),
                    FOREIGN KEY (test_id) REFERENCES test(test_id),
                    FOREIGN KEY (question_id) REFERENCES question(question_id),
                    FOREIGN KEY (student_id) REFERENCES student(student_id)
                );
                """;

        String studentTestSql =
                """
                CREATE TABLE IF NOT EXISTS studentTest (
                    test_id INTEGER,
                    student_id INTEGER,
                    times_taken INTEGER,                        
                    grade INTEGER,
                    PRIMARY KEY (test_id, student_id),
                    FOREIGN KEY (test_id) REFERENCES test(test_id),
                    FOREIGN KEY (student_id) REFERENCES student(student_id)
                );
                """;

        String studentQuizSql =
                """
                """;

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.execute(groupSql);
            stmt.execute(groupStudentSql);
            stmt.execute(studentSql);
            stmt.execute(teacherSql);
            stmt.execute(topicSql);
            stmt.execute(testSql);
            stmt.execute(quizSql);
            stmt.execute(questionSql);
            stmt.execute(questionOptionSql);
            stmt.execute(quizQuestionSql);
            stmt.execute(testQuestionSql);
            stmt.execute(studentQuizSql);
            stmt.execute(studentTestSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Group Database Methods
    /**
     * Inserts the group inside the group table
     * @param groupId int input
     * @param teacherId int input
     * @param topicId int input
     */
    public static void insertGroup(int groupId, int teacherId, int topicId) {
        WRITE_LOCK.lock();
        String sql =
                """
                INSERT INTO group(group_id, teacher_id, topic_id) VALUES(?, ?, ?);
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, teacherId);
            pstmt.setInt(3, topicId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * Adds a student to a group by inserting it in groupStudent table
     * @param groupId int input
     * @param studentId intinput
     */
    public static void groupAddStudent(int groupId, int studentId) {
        WRITE_LOCK.lock();
        String sql =
                """
                INSERT INTO groupStudent(group_id, student_id) VALUES(?, ?);
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, studentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * Remove a student to a group by deleting from groupStudent table
     * @param groupId int input
     * @param studentId int input
     */
    public static void groupRemoveStudent(int groupId, int studentId) {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupId);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    /**
     * Updates the topic of the group by changing topic_id
     * @param groupId int input
     * @param topicId int input
     */
    public static void groupEditTopic(int groupId, int topicId) {
        WRITE_LOCK.lock();
        String sql =
                """
                UPDATE group
                SET topic_id = ?
                WHERE group_id = ?;
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, topicId);
            pstmt.setInt(2, groupId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    // Topic Database Methods
    /**
     * Adds a record to the topic table
     * @param topicId int input
     * @param topicName String input
     */
    public static void insertTopic(int topicId, String topicName) {
        WRITE_LOCK.lock();
        String sql =
                """
                INSERT INTO topic(topic_id, topic_name) VALUES(?, ?);
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, topicId);
            pstmt.setString(2, topicName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicAddQuiz() {
        WRITE_LOCK.lock();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicAddQuiz
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicAddTest() {
        WRITE_LOCK.lock();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicAddTest
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicRemoveQuiz() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicRemoveQuiz
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicRemoveTest() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicRemoveTest
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicAddQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicAddQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void topicRemoveQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement topicRemoveQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    // Test Database Methods
    public static void insertTest(int testId, int topicId, String testName) {
        WRITE_LOCK.lock();
        String sql =
                """
                INSERT INTO test(test_id, topic_id, test_name) VALUES(?, ?, ?);
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, testId);
            pstmt.setInt(2, topicId);
            pstmt.setString(3, testName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void testAddQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement testAddQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void testRemoveQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement testAddQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    // Quiz Database Methods
    public static void instertQuiz(int quizId, int topicId, String quizName) {
        WRITE_LOCK.lock();
        String sql =
                """
                INSERT INTO test(quiz_id, topic_id, quiz_name) VALUES(?, ?, ?);
                """;
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quizId);
            pstmt.setInt(2, topicId);
            pstmt.setString(3, quizName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void quizAddQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement quizAddQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void quizRemoveQuestion() {
        WRITE_LOCK.lock();
        String sql =
                """
                DELETE FROM groupStudent
                WHERE group_id = ? AND student_id = ?;
                """;
        try (Connection conn = connect()) {
            //TODO: Implement quizRemoveQuestion
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            WRITE_LOCK.unlock();
        }
    }


    // Query All
    /**
     * Retrieves all Student from student
     * @return List of Student
     */
    public static List<Student> queryAllStudent() {
        READ_LOCK.lock();
        List<Student> students = new ArrayList<>();
        String sql =
                """ 
                SELECT * FROM student;
                """;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int student_id = rs.getInt("student_id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                List<Group> groups = queryAllGroupByStudentId(student_id);
                List<Quiz> quizzes = queryAllQuizByStudentId(student_id);
                List<Test> tests = queryAllTestByStudentId(student_id);
                students.add(new Student(student_id, fname, lname, email, password, "student", groups, quizzes, tests));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return students;
    }

    /**
     * Retrieve all Teacher from student
     * @return List of Teacher
     */
    public static List<Teacher> queryAllTeacher() {
        READ_LOCK.lock();
        List<Teacher> teachers = new ArrayList<>();
        String sql =
                """ 
                SELECT * FROM teacher;
                """;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int teacher_id = rs.getInt("student_id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                List<Group> groups = queryAllGroupByTeacherId(teacher_id);
                List<Topic> topics = queryAllTopicByTeacherId(teacher_id);
                teachers.add(new Teacher(teacher_id, fname, lname, email, password, "student", groups, topics));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return teachers;
    }

    /**
     * Retrieve all Topics from topic
     * @return List of Topic
     */
    public static List<Topic> queryAllTopics() {
        READ_LOCK.lock();
        List<Topic> topics = new ArrayList<>();
        String sql =
                """ 
                SELECT * FROM topic;
                """;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int topic_id = rs.getInt("topic_id");
                String topic_name = rs.getString("topic_name");
                List<Quiz> quizzes = queryAllQuizByTopicId(topic_id);
                List<Test> tests = queryAllTestByTopicId(topic_id);
                List<Question> questions = queryAllQuestionByTopicId(topic_id);
                topics.add(new Topic(topic_id, topic_name, quizzes, tests, questions));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return topics;
    }
    // Group Queries

    /**
     * Retrieve all students from a group of the specified groupId
     * @param groupId int input
     * @return List of Students
     */
    public static List<Student> queryStudentGroupByGroupId(int groupId) {
        READ_LOCK.lock();
        List<Student> students = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryStudentGroupByGroupId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return students;
    }

    /**
     * Retrieve a group via a teacher_id
     * @param teacherId int input
     * @return Group output
     */
    public static Group queryGroupByTeacherId(int teacherId) {
        READ_LOCK.lock();
        Group group = null;
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryGroupByTeacherId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return group;
    }

    /**
     * Retrieve all of a student's groups via a student_id
     * @param studentId int input
     * @return List of Groups output
     */
    public static List<Group> queryAllGroupByStudentId(int studentId) {
        READ_LOCK.lock();
        List<Group> groups = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllGroupByStudentId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return groups;
    }

    /**
     * Retrieves all Group via the teacher's teacher_id
     * @param teacherId int input
     * @return List of Groups output
     */
    public static List<Group> queryAllGroupByTeacherId(int teacherId) {
        READ_LOCK.lock();
        List<Group> groups = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllGroupByTeacherId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return groups;
    }
    // Topic Queries

    /**
     * Retrieve a Topic based on it's topicId
     * @param topicId int input
     * @return Topic output
     */
    public static Topic queryTopicByTopicId(int topicId) {
        READ_LOCK.lock();
        Topic topic = null;
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryTopicByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return topic;
    }

    /**
     * Retrieve all Topic via the teacher's teacher_ids
     * @param teacherId int input
     * @return List of Groups output
     */
    public static List<Topic> queryAllTopicByTeacherId(int teacherId) {
        READ_LOCK.lock();
        List<Topic> topics = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllTopicByTeacherId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return topics;
    }

    // Student Queries

    // Teacher Queries

    // Question Queries
    public static List<Question> queryAllQuestionByTopicId(int topicId) {
        READ_LOCK.lock();
        List<Question> questions = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement getAllQuestionByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return questions;
    }

    public static List<Question> queryAllQuestionByTestId(int testId) {
        READ_LOCK.lock();
        List<Question> questions = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement getAllQuestionByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return questions;
    }

    public static List<Question> queryAllQuestionByQuizId(int quizId) {
        READ_LOCK.lock();
        List<Question> questions = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement getAllQuestionByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return questions;
    }
    // Quiz Queries

    /**
     * Retrieve all quizzes via the student's student_id
     * @param studentId int input
     * @return List of Quiz
     */
    public static List<Quiz> queryAllQuizByStudentId(int studentId) {
        READ_LOCK.lock();
        List<Quiz> quizzes = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllQuizzesByStudentId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return quizzes;
    }

    /**
     * Retrieve all quizzes via the student's student_id
     * @param topicId int input
     * @return List of Quiz
     */
    public static List<Quiz> queryAllQuizByTopicId(int topicId) {
        READ_LOCK.lock();
        List<Quiz> quizzes = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllQuizByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return quizzes;
    }

    // Test Queries

    /**
     * Retrieve all tests via the student's test_id
     * @param studentId int input
     * @return List of Test
     */
    public static List<Test> queryAllTestByStudentId(int studentId) {
        READ_LOCK.lock();
        List<Test> tests = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllTestByStudentId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return tests;
    }

    /**
     * Retrieve all tests via the student's test_id
     * @param studentId int input
     * @return List of Test
     */
    public static List<Test> queryAllTestByTopicId(int studentId) {
        READ_LOCK.lock();
        List<Test> tests = new ArrayList<>();
        String sql =
                """
                """;
        try (Connection conn = connect()) {
            //TODO: Implement queryAllTestByTopicId
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return tests;
    }
}
