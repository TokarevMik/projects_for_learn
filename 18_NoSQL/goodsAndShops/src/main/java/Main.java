import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;
import org.bson.Document;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Filters.*;

public class Main {
    static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    static MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
    static MongoCollection<Document> shopsCollection = mongoDatabase.getCollection("shops");
    static MongoCollection<Document> goodsCollection = mongoDatabase.getCollection("all_goods");


    public static void main(String[] args) {
//        shopsCollection.drop();
//        goodsCollection.drop();
        Scanner scr = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду");
            String s = scr.nextLine();
            String[] tokens = s.split(" ");
            String command = (tokens[0]);
            if (command.equalsIgnoreCase("ДОБАВИТЬ_МАГАЗИН")) {
                shopsCollection.insertOne(Document.parse(
                        String.format("{shop_name: \"%s\"}", tokens[1]))
                );
            } else if (command.equalsIgnoreCase("ДОБАВИТЬ_ТОВАР")) {
                goodsCollection.insertOne(new Document()
                        .append("goods_name", tokens[1])
                        .append("price", Double.parseDouble(tokens[2])));
            } else if (command.equalsIgnoreCase("ВЫСТАВИТЬ_ТОВАР")) {
                shopsCollection.updateOne(
                        BsonDocument.parse(String.format("{shop_name: \"%s\"}", tokens[2])),
                        Updates.addToSet("goods", tokens[1])
                );
            } else if (command.equalsIgnoreCase("СТАТИСТИКА_ТОВАРОВ")) {
                getAnExtract();
            }
        }
    }

    public static void getAnExtract() {

        shopsCollection.aggregate(Arrays.asList(
                Aggregates.lookup("all_goods", "goods", "goods_name", "GoodsPrice"),
                Aggregates.unwind("$GoodsPrice"),
                Aggregates.sort(Sorts.descending("shop_name")),
                Aggregates.group("$shop_name",
                        avg("avgPrice", "$GoodsPrice.price"),
                        min("minPrice", "$GoodsPrice.price"),
                        max("maxPrice", "$GoodsPrice.price"),
                        sum("countCheap", 1)
                ),
                Aggregates.match(lt("countCheap", 100))
        )).forEach((Consumer<Document>) d -> {
            System.out.printf("Магазин: %s%n", d.get("_id"));
            System.out.printf("Средняя стоимость товаров в магазине: %.1f%n", d.get("avgPrice"));
            System.out.printf("Стоимость самого дешевого товрара в магазине: %.1f%n", d.get("minPrice"));
            System.out.printf("Стоимость самого дорогово товрара в магазине: %.1f%n", d.get("maxPrice"));
            System.out.printf("Количество товаров в магазине %s дешевле 100 р. - %d%n", d.get("_id"), d.get("countCheap"));
        });
        System.out.println("****************");
/*        shopsCollection.aggregate(Arrays.asList(
                Aggregates.lookup("all_goods", "goods", "goods_name", "GoodsPrice"),
                Aggregates.unwind("$GoodsPrice"),
                Aggregates.match(lt("GoodsPrice.price", 100)),
                Aggregates.sort(Sorts.descending("shop_name")),
                Aggregates.group("$shop_name",
                        sum("countCheap", 1)))).forEach((Consumer<Document>) d -> {
            System.out.printf("Количество товаров в магазине %s дешевле 100 р. - %d%n", d.get("_id"), d.get("countCheap"));
        });*/
    }//1
}//2
