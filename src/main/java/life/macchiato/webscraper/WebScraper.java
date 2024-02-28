package life.macchiato.webscraper;

import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

public class WebScraper {

    public static HtmlPage getPage(URL url) {
        return getPage(url.toString());
    }
    public static HtmlPage getPage(String url) {
        HtmlPage page = null;
        try(WebClient client = new WebClientFactory.builder().build())
        {
            page = client.getPage(url);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }


    public static Node select(String selector, URL url) {
        HtmlPage page = getPage(url);
        DomNode domNode = page.querySelector(selector);
        return new Node(domNode);


    }

    public static NodeList selectAll(String selector, URL url) {
        HtmlPage page = getPage(url);
        DomNodeList<DomNode> domNodes = page.querySelectorAll(selector);
        return new NodeList(domNodes.stream()
                .map(Node::new)
                .collect(Collectors.toList()));


    }

    protected static class builder {
        private boolean cssEnabled = false;
        private boolean jsEnabled = false;

        public WebClient build() {
            WebClient client = new WebClient();
            client.getOptions().setCssEnabled(cssEnabled);
            client.getOptions().setJavaScriptEnabled(jsEnabled);
            return client;
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

}