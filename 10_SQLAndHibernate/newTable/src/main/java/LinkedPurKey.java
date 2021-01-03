import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class LinkedPurKey implements Serializable {
    public LinkedPurKey() {
    }

    public LinkedPurKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Column(name = "student_id")

    private int studentId;

    @Column(name = "course_id")

    private int courseId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedPurKey)) return false;
        LinkedPurKey that = (LinkedPurKey) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
