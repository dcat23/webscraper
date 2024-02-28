package life.macchiato;

import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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