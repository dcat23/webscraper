package life.macchiato;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;

class WebScraperTest {


    URLBuilder urlBuilder;
    static String baseURL = "https://get.freecoursesonline.me/";

    WebScraper scraper;

    @BeforeEach
    void setUp() {
//        scraper = new WebScraper();
        urlBuilder = new URLBuilder(baseURL);
    }

    @AfterEach
    void tearDown() {
//        scraper = null;
        urlBuilder = null;
    }

    @Test @Disabled
    void shouldFindPageTitle() {
        HtmlPage page = WebScraper.getPage(urlBuilder.build());
        final String expected = "Free Courses Online Download Torrents | [FCO] FreeCoursesOnline.Me";
        assertThat(page.getTitleText()).isEqualTo(expected);
    }
    @Test @Disabled
    void shouldFindAnchorTags() {
        URL url = urlBuilder.setQuery("microservice").build();
        String selector = ".entry-title a[rel=\"bookmark\"]";
        NodeList nodes = WebScraper.selectAll(selector, url);
        assertThat(nodes.count()).isGreaterThan(0);
    }

    @Test
    void shouldFindCreateNodeList() {
        URL url = new URLBuilder("http://www.google.com").build();
        String selector = ".entry-title a[rel=\"bookmark\"]";
        NodeList nodes = WebScraper.selectAll(selector, url);
        assertThat(nodes.count()).isEqualTo(0);
    }

}