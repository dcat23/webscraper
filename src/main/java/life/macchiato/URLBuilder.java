package life.macchiato;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class URLBuilder {

    private final List<String> path = new ArrayList<>();
    private final Set<String> params = new HashSet<>();
    private final String baseUrl;
    private String queryKey;
    private String query = "";

    public URLBuilder() {
        baseUrl = "/";
        queryKey = "?s=";
    }

    public URLBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
        this.queryKey = "?s=";
    }
    public URLBuilder(String baseUrl, String queryKey) {
        this.baseUrl = baseUrl;
        this.queryKey = "?" + queryKey + "=";
    }

    public URLBuilder setQuery(String query) {
        this.query = queryKey.concat(encode(query));
        return this;
    }
    public URLBuilder setQueryKey(String key) {
        this.queryKey = "?" + key.trim() + "=";
        return this;
    }

    public URLBuilder addParam(String p) {
        this.params.add("&" + p);
        return this;
    }
    public URLBuilder addParam(String key, String value) {
        this.params.add("&".concat(key).concat("=").concat(value));
        return this;
    }

    public URLBuilder addPath(String path)
    {
        this.path.add(path);
        return this;
    }

    public URL build()  {
        StringBuilder sb = new StringBuilder()
                .append(baseUrl)
                .append(query);

        params.forEach(sb::append);

        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }
    public static String encode(String s) {
        return URLEncoder.encode(s);
    }
    public static String decode(String encoded) {
        return URLDecoder.decode(encoded);
    }

}
