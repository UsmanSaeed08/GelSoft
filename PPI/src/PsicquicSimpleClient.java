/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usman
 */
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PsicquicSimpleClient {

    public static final String XML25 = "xml25";
    public static final String MITAB25 = "tab25";
    public static final String MITAB25_COMPRESSED = "tab25-bin";
    public static final String COUNT = "count";


    private String serviceRestUrl;

    public PsicquicSimpleClient(String serviceRestUrl) {
        this.serviceRestUrl = serviceRestUrl;
    }

    public InputStream getByQuery(String query) throws IOException {
        return getByQuery(query, "tab25");
    }

    public InputStream getByInteractor(String query) throws IOException {
        return getByQuery(query, "tab25");
    }

    public InputStream getByInteraction(String query) throws IOException {
        return getByQuery(query, "tab25");
    }

    public InputStream getByQuery(String query, String format) throws IOException {
        return getBy("query", query, format);
    }

    public InputStream getByInteractor(String query, String format) throws IOException {
        return getBy("interactor", query, format);
    }

    public InputStream getByInteraction(String query, String format) throws IOException {
        return getBy("interaction", query, format);
    }

    public long countByQuery(String query) throws IOException {
        return countBy("query", query);
    }

    public long countByInteractor(String query) throws IOException {
        return countBy("interactor", query);
    }

    public long countByInteraction(String query) throws IOException {
        return countBy("interaction", query);
    }

    private InputStream getBy(String queryType, String query, String format) throws IOException {
        final String encodedQuery = encodeQuery(query);

        URL url = createUrl(queryType, encodedQuery, format);

        return url.openStream();
    }

    private long countBy(String queryType, String query) throws IOException {
        InputStream result = getBy(queryType, query, COUNT);

        String strCount = streamToString(result);
        strCount = strCount.replaceAll("\n", "");

        return Long.parseLong(strCount);
    }

    private String encodeQuery(String query) {
        String encodedQuery;

        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
            encodedQuery = encodedQuery.replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 should be supported");
        }
        return encodedQuery;
    }


    private URL createUrl(String queryType, String encodedQuery, String format) {
        String strUrl = serviceRestUrl+"/"+queryType+"/"+encodedQuery+"?format="+format;
        strUrl = strUrl.replaceAll("//"+queryType, "/"+queryType);

        URL url;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Problem creating URL: "+strUrl, e);
        }
        return url;
    }


    private String streamToString(InputStream is) throws IOException {
        if (is == null) return "";

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } finally {
            is.close();
        }
        return sb.toString();
    }

}
