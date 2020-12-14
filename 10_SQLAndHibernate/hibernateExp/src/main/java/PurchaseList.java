import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@IdClass(CompoundKeyPurchase.class)
public class PurchaseList {
    PurchaseList() {
    }

    PurchaseList(CompoundKeyPurchase compoundKey) {
        studentName = compoundKey.getStudentName();
        courseName = compoundKey.getCourseName();
    }

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "studentName",
                    column = @Column(name = "student_name")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "course_name"))
    })
    private String studentName;
    private String courseName;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

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
