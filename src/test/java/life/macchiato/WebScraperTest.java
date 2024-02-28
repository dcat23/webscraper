package life.macchiato;

import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class WebScraperTest {

    URLBuilder urlBuilder;
    static String baseURL = "https://get.freecoursesonline.me/";


    @BeforeEach
    void setUp() {
        urlBuilder = new URLBuilder(baseURL);
    }

    @AfterEach
    void tearDown() {
        urlBuilder = null;
    }

    @Test
    void shouldFindPageTitle() {
        HtmlPage page = WebScraper.getPage(urlBuilder.build());
        final String expected = "Free Courses Online Download Torrents | [FCO] FreeCoursesOnline.Me";
        assertThat(page.getTitleText()).isEqualTo(expected);
    }
    @Test
    void shouldFindAnchorTags() {
        URL url = urlBuilder.setQuery("microservice").build();
        String selector = ".entry-title a[rel=\"bookmark\"]";
        NodeList nodes = WebScraper.selectAll(selector, url);
        assertThat(nodes.count()).isGreaterThan(0);
    }

    @Test
    void shouldCreateNodeList() {
        URL url = new URLBuilder("http://www.google.com").build();
        String selector = ".entry-title a[rel=\"bookmark\"]";
        NodeList nodes = WebScraper.selectAll(selector, url);
        assertThat(nodes.count()).isEqualTo(0);
    }

    @Test
    void shouldFindSingleElement() {
        URL url = new URLBuilder("https://get.freecoursesonline.me/oreilly-microservices-risk-management/").build();

        HtmlPage page = WebScraper.getPage(url);
        Optional<HtmlAnchor> torrent = page.getAnchors().stream()
                .filter(a -> a.getHrefAttribute().contains("torrent"))
                .findFirst();
        assertThat(torrent.isPresent());
    }
}