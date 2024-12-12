package org.example.finalproject.controller;

import org.example.finalproject.model.Group;
import org.example.finalproject.model.Student;
import org.example.finalproject.model.Topic;

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
                    FOREIGN KEY (group_id) REFERENCES group(group_id) ON DELETE CASCADE;
                );
                """;

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            stmt.execute(groupSql);
            stmt.execute(groupStudentSql);
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

    // Query All
    /**
     * Retrieves all groups
     * @return List of Group
     */
    public static List<Group> queryAllGroups() {
        READ_LOCK.lock();
        List<Group> groups = new ArrayList<>();
        String sql =
                """ 
                SELECT * FROM group;
                """;
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int group_id = rs.getInt("group_id");
                int topic_id = rs.getInt("topic_id");
                List<Student> students= queryStudentGroupByGroupId(group_id);
                Topic topic = queryTopicByTopicId(topic_id);
                groups.add(new Group(group_id, students, topic));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            READ_LOCK.unlock();
        }
        return groups;
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

    // Student Queries

    // Teacher Queries

    // Question Queries

    // Quiz Queries

    // Test Queries
}
