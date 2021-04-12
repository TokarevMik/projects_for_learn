import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class ParseNode extends RecursiveTask<List<String>> {
    private Node node;

    public ParseNode(Node note) {
        this.node = note;
    }
    public static Set<String> isAlreadyRead = new HashSet<>();
    public List<String> compute() {
        List<String> linksNames = new ArrayList<>();
        String adress = node.getUrl();
        if (node.levelOfPage>0){
            for (int i = 0;i<= node.getLevelOfPage();i++){
                adress = "   ".concat(adress);
            }
        }
        linksNames.add(adress);
        List<ParseNode> taskList = new ArrayList<>();
        for (Node child : node.getChildren()) {
            if (!isAlreadyRead.contains(child.getUrl())){
                isAlreadyRead.add(child.getUrl());
                ParseNode task = new ParseNode(child);
                task.fork();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                taskList.add(task);
            }
        }
        for (ParseNode task : taskList) {
            linksNames.addAll(task.join());
        }
        return linksNames;
    }


}
