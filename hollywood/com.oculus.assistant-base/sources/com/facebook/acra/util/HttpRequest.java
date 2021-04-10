package com.facebook.acra.util;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.tigon.iface.TigonRequest;
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
    public static final int CHAR_ARRAY_BUFFER_SIZE = 256;
    public static final String POST_CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public HttpConnectionProvider mConnectionProvider;
    public Map mHeaders;

    public static void encodeParameters(Map map, OutputStream outputStream) {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        UrlEncodingWriter urlEncodingWriter = new UrlEncodingWriter(bufferedWriter);
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            if (!z) {
                bufferedWriter.append('&');
            }
            Object value = entry.getValue();
            if (value == null) {
                value = OacrConstants.AUTO_SPEECH_DOMAIN;
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

    public static boolean transferData(InputStream inputStream, Writer writer) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[CHAR_ARRAY_BUFFER_SIZE];
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

    public void sendPost(URL url, Map map, ACRAResponse aCRAResponse, String str) {
        HttpURLConnection connection = this.mConnectionProvider.getConnection(url);
        connection.setRequestMethod(TigonRequest.POST);
        connection.setRequestProperty(HttpRequestMultipart.USER_AGENT, str);
        connection.setRequestProperty(HttpRequestMultipart.CONTENT_TYPE, POST_CONTENT_TYPE_FORM_URLENCODED);
        connection.setRequestProperty("Content-Encoding", "gzip");
        Map map2 = this.mHeaders;
        if (map2 != null && !map2.isEmpty()) {
            for (Map.Entry entry : this.mHeaders.entrySet()) {
                connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        connection.setDoOutput(true);
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(AcraRadioMonitorBridge.createOutputDecorator(connection.getOutputStream()));
            encodeParameters(map, gZIPOutputStream);
            gZIPOutputStream.close();
            aCRAResponse.mStatus = connection.getResponseCode();
            connection.getInputStream().close();
        } finally {
            connection.disconnect();
        }
    }

    public HttpRequest(HttpConnectionProvider httpConnectionProvider) {
        this.mConnectionProvider = httpConnectionProvider;
    }

    public void setHeaders(Map map) {
        this.mHeaders = map;
    }
}
