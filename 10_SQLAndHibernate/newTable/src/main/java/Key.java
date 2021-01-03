import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class Key implements Serializable {
    public Key() {
    }

    @Column(name = "student_name")

    private String studentName;

    @Column(name = "course_name")

    private String courseName;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return Objects.equals(studentName, key.studentName) && Objects.equals(courseName, key.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
