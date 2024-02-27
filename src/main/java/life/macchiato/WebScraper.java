package life.macchiato;

import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class WebScraper {

    private final WebClient client;

    public WebScraper() {
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setCssEnabled(false);
    }

    public WebScraper(builder b) {
        client = new WebClient();
        client.getOptions().setCssEnabled(b.cssEnabled);
        client.getOptions().setCssEnabled(b.jsEnabled);
    }

    public static class builder {
        private boolean cssEnabled = false;
        private boolean jsEnabled = false;

        public WebScraper build() {
            return new WebScraper(this);
        }

        public builder enableCss() {
            cssEnabled = true;
            return this;
        }
        public builder enableJs() {
            jsEnabled = true;
            return this;
        }
    }

    public Node select(String selector, URL url) throws IOException {
        HtmlPage page = client.getPage(url);
        DomNode domNode = page.querySelector(selector);
        return new Node(domNode);


    }

    public List<Node> selectAll(String selector, URL url) throws IOException {
        HtmlPage page = client.getPage(url);
        return page.querySelectorAll(selector).stream()
                .map(Node::new)
                .collect(Collectors.toList());


    }
    @Override
    protected void finalize() {
        client.close();
    }
}