import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("Students");
        collection.drop();

        String path = "src/main/resources/mongo.csv";
        List<String> students = Files.readAllLines(Paths.get(path));

        for (String line : students) {
            String[] s = line.split(",", 3);
            String courses = s[2].replace("\"", "");
            List<String> coursesArr = Arrays.stream((courses.split(","))).toList();
            Document document = new Document()
                    .append("name", s[0])
                    .append("age", Integer.parseInt(s[1]))
                    .append("courses", coursesArr);
            collection.insertOne(document);
        }

        System.out.println("Всего студентов в списке - " + collection.countDocuments() + "\n");
        long countGt = collection.countDocuments(Document.parse("{age : {$gt : 40}}"));

        System.out.println("Всего студентов старше сорока - " + countGt + "\n");

        for (Document document : collection.find().limit(1).sort(new Document("age", 1))) {
            System.out.println("Самый молодой студент - " + document.get("name").toString() + "\n");
        }

        for (Document document : collection.find().limit(1).sort(new Document("age", -1))) {
            System.out.println("Список курсов самого старшего студента - " + document.get("courses").toString());
        }
    }
}
