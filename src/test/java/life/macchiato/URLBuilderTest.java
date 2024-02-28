package life.macchiato;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class URLBuilderTest {

    private URLBuilder urlBuilder;

    private static final String BASE_URL = "https://get.freecoursesonline.me/";
    @BeforeEach
    void setUp() {
        urlBuilder = new URLBuilder(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        urlBuilder = null;
    }

    @Test
    void shouldBuildURL() {
        URL url = urlBuilder.build();
        assertThat(url.toString()).isEqualTo(BASE_URL);

    }
    @Test
    void shouldSetQuery() {
        URL url = urlBuilder
                .setQuery("amigos")
                .build();
        final String expected = "https://get.freecoursesonline.me/?s=amigos";
        assertThat(url.toString()).isEqualTo(expected);
    }
    @Test
    void shouldSetQueryKey() {
        URL url = urlBuilder
                .setQueryKey("search")
                .setQuery("amigos")
                .build();

        final String expected = "https://get.freecoursesonline.me/?search=amigos";
        assertThat(url.toString()).isEqualTo(expected);
    }
    @Test
    void shouldAddParam() {
        URL url = urlBuilder.setQuery("amigos")
                .addParam("asl_gen[]=title")
                .addParam("customset[]", "post")
                .build();

        final String expected = "https://get.freecoursesonline.me/?s=amigos&asl_gen[]=title&customset[]=post";
        assertThat(url.toString()).isEqualTo(expected);
    }

    @Test
    void shouldEncodeQuery() {
        URL url = urlBuilder.setQuery("full stack front end").build();
        final String expected = "https://get.freecoursesonline.me/?s=full+stack+front+end";
        assertThat(url.toString()).isEqualTo(expected);
    }
}