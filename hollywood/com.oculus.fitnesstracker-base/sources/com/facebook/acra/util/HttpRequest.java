package com.facebook.acra.util;

import com.oculus.common.build.BuildConfig;
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

public final class HttpRequest {
    private HttpConnectionProvider mConnectionProvider;
    public Map<String, String> mHeaders;

    public HttpRequest(HttpConnectionProvider httpConnectionProvider) {
        this.mConnectionProvider = httpConnectionProvider;
    }

    public final void sendPost(URL url, Map<?, ?> map, ACRAResponse aCRAResponse, String str) throws IOException {
        HttpURLConnection connection = this.mConnectionProvider.getConnection(url);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", str);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Encoding", "gzip");
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
            aCRAResponse.mStatus = connection.getResponseCode();
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
                value = BuildConfig.PROVIDER_SUFFIX;
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
