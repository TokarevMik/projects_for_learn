import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Subscriptions {
    @EmbeddedId
    private CompoundKeySubs id;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public CompoundKeySubs getId() {
        return id;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }


}
