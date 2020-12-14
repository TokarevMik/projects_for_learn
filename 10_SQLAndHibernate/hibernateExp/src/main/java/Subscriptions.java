import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(CompoundKeySubs.class)
public class Subscriptions {
    @EmbeddedId
    private CompoundKeySubs id;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    public CompoundKeySubs getId(){
        return id;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "students",
            joinColumns = {@JoinColumn(name= "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private Student student;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
