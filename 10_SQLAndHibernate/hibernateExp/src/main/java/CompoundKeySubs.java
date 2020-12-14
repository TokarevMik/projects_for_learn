import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class CompoundKeySubs implements Serializable {
    public CompoundKeySubs() {
    }

    public CompoundKeySubs(int studentId, int courseId) {
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
    public int getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompoundKeySubs)) return false;
        CompoundKeySubs that = (CompoundKeySubs) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
