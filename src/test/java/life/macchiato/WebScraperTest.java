package life.macchiato;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

class WebScraperTest {

    URL url;
    static String baseURL = "https://htmlunit.sourceforge.io/";

    WebScraper scraper;

    @BeforeEach
    void setUp() {
        scraper = new WebScraper();

        try {
            url = new URL(baseURL);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        scraper = null;
        url = null;
    }

    @Test
    void select() {
        String selector = "#bodyColumn > section:nth-child(1) > h2";
        try {
            Node node = scraper.select(selector, url);
            assertThat(node.text()).isEqualTo("HtmlUnit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void selectAll() {
    }
}