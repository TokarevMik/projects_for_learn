import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class ParseNode extends RecursiveTask<Set<String>> {
    private Node node;

    public ParseNode(Node note) {
        this.node = note;
    }

    public static Set<String> isAlreadyRead = new CopyOnWriteArraySet<>(); //список названий (прочитанных)

    public Set<String> compute() {
        Set<String> linksNames = new CopyOnWriteArraySet<>();
        String address = node.getUrl();
        if (node.levelOfPage > 0) {
            for (int i = 0; i <= node.getLevelOfPage(); i++) {
                address = "   ".concat(address);
            }
        }
        linksNames.add(address);
        Set<ParseNode> taskList = new CopyOnWriteArraySet<>();
        for (Node child : node.getChildren()) {
            if (!isAlreadyRead.contains(child.getUrl())) {
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
