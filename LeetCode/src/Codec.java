import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Codec {
    private String baseUrl = "http://tinyurl.com/";
    private Map<Integer, String> urls = new HashMap<>();

    public String encode(String longUrl) {
        byte[] bytes = longUrl.getBytes(StandardCharsets.UTF_8);
        String base36 = new BigInteger(1, bytes).toString(36);
        return baseUrl + base36;
    }

    public String decode(String shortUrl) {
        String base36 = shortUrl.substring(shortUrl.indexOf("m/") + 2);
        byte[] bytes = new BigInteger(base36, 36).toByteArray();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public String encode2(String longUrl) {
        urls.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode2(String shortUrl) {
        return urls.get((Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""))));
    }

}
