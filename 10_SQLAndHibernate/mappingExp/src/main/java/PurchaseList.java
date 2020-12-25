import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList implements Serializable {
    PurchaseList() {
    }

    @Id
    @Column(name = "student_name")
    private String studentName;
    @Id
    @Column(name = "course_name")
    private String courseName;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "student_name"),
            @JoinColumn(name = "name")
    })

    private Student student;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "course_name"),
            @JoinColumn(name = "name")
    })

    private Course course;

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getPrice() {
        return price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }


}
