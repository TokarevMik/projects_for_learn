import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
class CompoundKeyPurchase implements Serializable {
    private String studentName;
    private String courseName;

    public CompoundKeyPurchase(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

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
        if (!(o instanceof CompoundKeyPurchase)) return false;
        CompoundKeyPurchase that = (CompoundKeyPurchase) o;
        return Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
