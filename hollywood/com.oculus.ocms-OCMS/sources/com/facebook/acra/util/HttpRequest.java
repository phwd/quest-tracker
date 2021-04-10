package com.facebook.acra.util;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.oktigon.OkHttpConstants;
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

    public HttpRequest(HttpConnectionProvider httpConnectionProvider) {
        this.mConnectionProvider = httpConnectionProvider;
    }

    public void sendPost(URL url, Map<?, ?> map, ACRAResponse aCRAResponse, String str) throws IOException {
        HttpURLConnection connection = this.mConnectionProvider.getConnection(url);
        connection.setRequestMethod(TigonRequest.POST);
        connection.setRequestProperty("User-Agent", str);
        connection.setRequestProperty("Content-Type", POST_CONTENT_TYPE_FORM_URLENCODED);
        connection.setRequestProperty("Content-Encoding", OkHttpConstants.GZIP);
        Map<String, String> map2 = this.mHeaders;
        if (map2 != null && !map2.isEmpty()) {
            for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
                connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        connection.setDoOutput(true);
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(AcraRadioMonitorBridge.createOutputDecorator(connection.getOutputStream()));
            encodeParameters(map, gZIPOutputStream);
            gZIPOutputStream.close();
            aCRAResponse.setStatusCode(connection.getResponseCode());
            connection.getInputStream().close();
        } finally {
            connection.disconnect();
        }
    }

    public static void encodeParameters(Map<?, ?> map, OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        UrlEncodingWriter urlEncodingWriter = new UrlEncodingWriter(bufferedWriter);
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (!z) {
                bufferedWriter.append('&');
            }
            Object value = entry.getValue();
            if (value == null) {
                value = "";
            }
            urlEncodingWriter.write(key.toString());
            bufferedWriter.write(61);
            if (value instanceof InputStream) {
                transferData((InputStream) value, urlEncodingWriter);
            } else {
                urlEncodingWriter.write(value.toString());
            }
            z = false;
        }
        bufferedWriter.flush();
    }

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }

    private static boolean transferData(InputStream inputStream, Writer writer) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[256];
        while (true) {
            try {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    return true;
                }
                writer.write(cArr, 0, read);
            } catch (IOException unused) {
                return false;
            }
        }
    }
}
