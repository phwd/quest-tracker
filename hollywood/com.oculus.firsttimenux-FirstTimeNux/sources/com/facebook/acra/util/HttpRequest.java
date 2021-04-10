package com.facebook.acra.util;

import com.google.common.net.HttpHeaders;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class HttpRequest {
    private static final int CHAR_ARRAY_BUFFER_SIZE = 256;
    public static final String POST_CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    private HttpConnectionProvider mConnectionProvider;
    private Map<String, String> mHeaders;

    public HttpRequest(HttpConnectionProvider connectionProvider) {
        this.mConnectionProvider = connectionProvider;
    }

    public void sendPost(URL url, Map<?, ?> parameters, ACRAResponse response, String userAgent) throws IOException {
        HttpURLConnection urlConnection = this.mConnectionProvider.getConnection(url);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty(HttpHeaders.USER_AGENT, userAgent);
        urlConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, POST_CONTENT_TYPE_FORM_URLENCODED);
        urlConnection.setRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
        if (this.mHeaders != null && !this.mHeaders.isEmpty()) {
            for (Map.Entry e : this.mHeaders.entrySet()) {
                urlConnection.setRequestProperty(e.getKey().toString(), e.getValue().toString());
            }
        }
        urlConnection.setDoOutput(true);
        try {
            GZIPOutputStream gzipStream = new GZIPOutputStream(AcraRadioMonitorBridge.createOutputDecorator(urlConnection.getOutputStream()));
            encodeParameters(parameters, gzipStream);
            gzipStream.close();
            response.setStatusCode(urlConnection.getResponseCode());
            urlConnection.getInputStream().close();
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void encodeParameters(Map<?, ?> parameters, OutputStream output) throws IOException {
        boolean first = true;
        Writer writer = new BufferedWriter(new OutputStreamWriter(output));
        UrlEncodingWriter encodingWriter = new UrlEncodingWriter(writer);
        for (Map.Entry<?, ?> entry : parameters.entrySet()) {
            Object key = entry.getKey();
            if (!first) {
                writer.append('&');
            }
            Object value = entry.getValue();
            if (value == null) {
                value = "";
            }
            encodingWriter.write(key.toString());
            writer.write(61);
            if (value instanceof InputStream) {
                transferData((InputStream) value, encodingWriter);
            } else {
                encodingWriter.write(value.toString());
            }
            first = false;
        }
        writer.flush();
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    private static boolean transferData(InputStream input, Writer writer) {
        InputStreamReader reader = new InputStreamReader(input);
        char[] data = new char[256];
        while (true) {
            try {
                int read = reader.read(data);
                if (read == -1) {
                    return true;
                }
                writer.write(data, 0, read);
            } catch (IOException e) {
                return false;
            }
        }
    }
}
