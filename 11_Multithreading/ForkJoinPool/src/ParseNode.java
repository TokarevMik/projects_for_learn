import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParseNode extends RecursiveTask<List<String>> {
    private Node node;

    public ParseNode(Node note) {
        this.node = note;
    }

    //List<String> linksNames = new ArrayList<>();
    public List<String> compute() {
        List<String> linksNames = new ArrayList<>();
        linksNames.add(node.name);
        List<ParseNode> taskList = new ArrayList<>();
        for (Node child : node.getChildren()) {
            ParseNode task = new ParseNode(child);
            task.fork();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            taskList.add(task);
        }
        for (ParseNode task : taskList) {
            linksNames.addAll(task.join());
        }
        return linksNames;
    }


}
