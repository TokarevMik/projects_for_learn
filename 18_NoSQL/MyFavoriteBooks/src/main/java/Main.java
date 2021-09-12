import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collection = database.getCollection("MyFavoriteBooks");

        collection.drop();

        List<Document> listDocuments = new ArrayList<>();

        Document document1 = new Document()
                .append("name", "The Girl with the Dragon Tattoo")
                .append("author", "Stig Larsson")
                .append("publishing", 2005);
        listDocuments.add(document1);

        listDocuments.add(new Document()
                .append("name", "The Girl Who Kicked the Hornets' Nest")
                .append("author", "Stig Larsson")
                .append("publishing", 2007));

        listDocuments.add(new Document()
                .append("name", "A Hero of Our Time")
                .append("author", "Mikhail Lermontov")
                .append("publishing", 1839));

        listDocuments.add(new Document()
                .append("name", "Dune")
                .append("author", "Frank Herbert")
                .append("publishing", 1966));
        listDocuments.add(new Document()
                .append("name", "Thinking in Java")
                .append("author", "Bruce Eckel")
                .append("publishing", 1998));

        collection.insertMany(listDocuments);

        collection.find().limit(1).sort(new Document("publishing", 1)).forEach((Consumer<Document>) System.out::println);// самая старая книга
        System.out.println("************************");
        collection.find(new Document("author", "Stig Larsson")).forEach((Consumer<Document>) System.out::println); // выборка по автору
    }
}
