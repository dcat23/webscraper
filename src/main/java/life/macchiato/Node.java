package life.macchiato;

import org.htmlunit.html.DomNode;

public record Node(DomNode domNode) {

    public String text() {
        return domNode.getTextContent();
    }

    public String attribute(String name) {
        return domNode.getAttributes()
                .getNamedItem(name)
                .getNodeValue();
    }
}
