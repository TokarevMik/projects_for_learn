import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    private LinkedPurKey id;

    public LinkedPurKey getId() {
        return id;
    }

    public void setId(LinkedPurKey id) {
        this.id = id;
    }
}
