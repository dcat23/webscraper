package life.macchiato.webscraper;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;

public class WebClientFactory {

    public static HtmlPage getPage(String url) {
        HtmlPage page = null;
        try(WebClient client = new builder().build())
        {
            page = client.getPage(url);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }
    public static HtmlPage getPage(URL url) {
        return getPage(url.toString());
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