package life.macchiato;

import life.macchiato.webscraper.URLBuilder;
import life.macchiato.webscraper.WebClientFactory;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WebClientFactoryTest {

    URLBuilder urlBuilder;
    static String baseURL = "https://www.example.com/";

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
        HtmlPage page = WebClientFactory.getPage(urlBuilder.build());
        final String expected = "Example Domain";
        assertThat(page.getTitleText()).isEqualTo(expected);
    }
}