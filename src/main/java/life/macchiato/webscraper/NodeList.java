package life.macchiato.webscraper;

import java.util.List;

public record NodeList(List<Node> nodeList) {

    @Override
    public String toString() {
        return "NodeList{" +
                nodeList +
                '}';
    }

    public int count() {
        return nodeList.size();
    }
}
