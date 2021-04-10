package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyLog;
import com.google.common.net.HttpHeaders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

public class HttpHeaderParser {
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String RFC1123_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static Cache.Entry parseCacheHeaders(NetworkResponse response) {
        long lastModified;
        long now = System.currentTimeMillis();
        Map<String, String> headers = response.headers;
        long serverDate = 0;
        long serverExpires = 0;
        long softExpire = 0;
        long finalExpire = 0;
        long maxAge = 0;
        long staleWhileRevalidate = 0;
        boolean mustRevalidate = false;
        boolean hasCacheControl = false;
        String headerValue = headers.get(HttpHeaders.DATE);
        if (headerValue != null) {
            serverDate = parseDateAsEpoch(headerValue);
        }
        String headerValue2 = headers.get(HttpHeaders.CACHE_CONTROL);
        if (headerValue2 != null) {
            hasCacheControl = true;
            String[] tokens = headerValue2.split(",", 0);
            long staleWhileRevalidate2 = 0;
            long maxAge2 = 0;
            int i = 0;
            while (i < tokens.length) {
                String token = tokens[i].trim();
                if (token.equals("no-cache") || token.equals("no-store")) {
                    return null;
                }
                if (token.startsWith("max-age=")) {
                    try {
                        maxAge2 = Long.parseLong(token.substring(8));
                    } catch (Exception e) {
                    }
                } else if (token.startsWith("stale-while-revalidate=")) {
                    try {
                        staleWhileRevalidate2 = Long.parseLong(token.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (token.equals("must-revalidate") || token.equals("proxy-revalidate")) {
                    mustRevalidate = true;
                }
                i++;
                headerValue2 = headerValue2;
            }
            maxAge = maxAge2;
            staleWhileRevalidate = staleWhileRevalidate2;
        }
        String headerValue3 = headers.get(HttpHeaders.EXPIRES);
        if (headerValue3 != null) {
            serverExpires = parseDateAsEpoch(headerValue3);
        }
        String headerValue4 = headers.get(HttpHeaders.LAST_MODIFIED);
        if (headerValue4 != null) {
            lastModified = parseDateAsEpoch(headerValue4);
        } else {
            lastModified = 0;
        }
        String serverEtag = headers.get(HttpHeaders.ETAG);
        if (hasCacheControl) {
            softExpire = now + (maxAge * 1000);
            finalExpire = mustRevalidate ? softExpire : softExpire + (1000 * staleWhileRevalidate);
        } else if (serverDate > 0 && serverExpires >= serverDate) {
            softExpire = now + (serverExpires - serverDate);
            finalExpire = softExpire;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = response.data;
        entry.etag = serverEtag;
        entry.softTtl = softExpire;
        entry.ttl = finalExpire;
        entry.serverDate = serverDate;
        entry.lastModified = lastModified;
        entry.responseHeaders = headers;
        entry.allResponseHeaders = response.allHeaders;
        return entry;
    }

    public static long parseDateAsEpoch(String dateStr) {
        try {
            return newRfc1123Formatter().parse(dateStr).getTime();
        } catch (ParseException e) {
            VolleyLog.e(e, "Unable to parse dateStr: %s, falling back to 0", dateStr);
            return 0;
        }
    }

    static String formatEpochAsRfc1123(long epoch) {
        return newRfc1123Formatter().format(new Date(epoch));
    }

    private static SimpleDateFormat newRfc1123Formatter() {
        SimpleDateFormat formatter = new SimpleDateFormat(RFC1123_FORMAT, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter;
    }

    public static String parseCharset(Map<String, String> headers, String defaultCharset) {
        String contentType = headers.get("Content-Type");
        if (contentType != null) {
            String[] params = contentType.split(";", 0);
            for (int i = 1; i < params.length; i++) {
                String[] pair = params[i].trim().split("=", 0);
                if (pair.length == 2 && pair[0].equals("charset")) {
                    return pair[1];
                }
            }
        }
        return defaultCharset;
    }

    public static String parseCharset(Map<String, String> headers) {
        return parseCharset(headers, DEFAULT_CONTENT_CHARSET);
    }

    static Map<String, String> toHeaderMap(List<Header> allHeaders) {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Header header : allHeaders) {
            headers.put(header.getName(), header.getValue());
        }
        return headers;
    }

    static List<Header> toAllHeaderList(Map<String, String> headers) {
        List<Header> allHeaders = new ArrayList<>(headers.size());
        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders.add(new Header(header.getKey(), header.getValue()));
        }
        return allHeaders;
    }
}
