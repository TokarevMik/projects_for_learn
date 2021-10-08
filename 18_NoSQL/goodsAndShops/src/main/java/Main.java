import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import java.util.Arrays;

public class Main {
    static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    static MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
    static MongoCollection<Document> shopsCollection = mongoDatabase.getCollection("shops");
    static MongoCollection<Document> goodsCollection = mongoDatabase.getCollection("all_goods");


    public static void main(String[] args) {
//        shopsCollection.drop();
//        goodsCollection.drop();
//        Scanner scr = new Scanner(System.in);
//        while (true) {
//            System.out.println("Введите команду");
//            String s = scr.nextLine();
//            String[] strArr = s.split(" ");
//            String command = (strArr[0]);
//            if (command.equalsIgnoreCase("ДОБАВИТЬ_МАГАЗИН")) {
//                addShop(strArr[1]);
//            } else if (command.equalsIgnoreCase("ДОБАВИТЬ_ТОВАР")) {
//                addGood(strArr[1]);
//            } else if (command.equalsIgnoreCase("ВЫСТАВИТЬ_ТОВАР")) {
//                putTheGoods(s);
//            } else if (command.equalsIgnoreCase("СТАТИСТИКА_ТОВАРОВ")) {
//                getAnExtract();
//            }
//        }

        getAnExtract();
    }

    public static void addShop(String shopName) {
        String shopName1 = shopName;
        shopsCollection.insertOne(new Document()
                .append("shop_name", shopName1));
    }

    public static void addGood(String goodName) {
        String good = goodName.replaceAll("\\D+", "");
        int price = Integer.parseInt(goodName.replaceAll("\\d+", ""));
        goodsCollection.insertOne(new Document()
                .append("goods_name", good)
                .append("price", price));
    }

    public static void putTheGoods(String command) {
        MongoCursor<String> files = shopsCollection.distinct("shops_name", String.class).iterator();
        String shop;
        String goods;
        while (files.hasNext()) {
            String d = files.next();
            if (command.contains(files.next())) {
                shop = files.next();
                goods = command.replace(shop, "");
                BasicDBObject find = new BasicDBObject();
                find.put("shop_name", shop);
                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("goods", goods);
                BasicDBObject updateObject = new BasicDBObject();
                updateObject.put("$push", newDocument);
                shopsCollection.updateOne(find, updateObject);
            } else {
                System.out.println("Неверная команда: магазин не существует");
            }
        }

    }

    public static void getAnExtract() {
        Block<Document> printBlock = new Block<>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        shopsCollection.aggregate(Arrays.asList(
                Aggregates.lookup("shopsCollection", "goodsCollection", "goods_name", "GoodsPrice"),
                Aggregates.unwind("$GoodsPrice"),
                Aggregates.group("$shop_name", Accumulators.avg("avgPrice", "$GoodsPrice.price"))
                )).forEach(printBlock);
    }
}
