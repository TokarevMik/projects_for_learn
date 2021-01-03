import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {
    PurchaseList() {
    }

    @EmbeddedId
    private Key id;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public void setPrice(int price) {
        this.price = price;
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
