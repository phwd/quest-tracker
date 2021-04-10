package com.facebook.acra.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

public final class HttpRequestMultipart {
    private HttpConnectionProvider mConnectionProvider;
    public Map<String, String> mHeaders;

    public HttpRequestMultipart(HttpConnectionProvider httpConnectionProvider) {
        this.mConnectionProvider = httpConnectionProvider;
    }

    public final void sendPost(URL url, Map<?, ?> map, Map<?, InputStreamField> map2, ACRAResponse aCRAResponse, String str, boolean z) throws IOException {
        String str2;
        HttpURLConnection connection = this.mConnectionProvider.getConnection(url);
        String uuid = UUID.randomUUID().toString();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", str);
        connection.setRequestProperty("Content-Type", String.format("multipart/form-data;boundary=%s", uuid));
        Map<String, String> map3 = this.mHeaders;
        if (map3 != null && !map3.isEmpty()) {
            for (Map.Entry<String, String> entry : this.mHeaders.entrySet()) {
                connection.setRequestProperty(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        try {
            OutputStream outputStream = connection.getOutputStream();
            try {
                OutputStream createOutputDecorator = AcraRadioMonitorBridge.createOutputDecorator(outputStream);
                createOutputDecorator.write(generateMultipartHeader(uuid, "form-data; name=", "acra_data"));
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(createOutputDecorator);
                HttpRequest.encodeParameters(map, gZIPOutputStream);
                gZIPOutputStream.finish();
                createOutputDecorator.write("\r\n".getBytes());
                for (Map.Entry<?, InputStreamField> entry2 : map2.entrySet()) {
                    InputStreamField value = entry2.getValue();
                    boolean z2 = value.mSendCompressed;
                    boolean z3 = value.mSendAsAFile;
                    Object key = entry2.getKey();
                    if (z3) {
                        str2 = "form-data; filename=\"file\"; name=";
                    } else {
                        str2 = "form-data; name=";
                    }
                    createOutputDecorator.write(generateMultipartHeader(uuid, str2, key.toString()));
                    OutputStream compressionOutputStream = z2 ? new CompressionOutputStream(createOutputDecorator, 8192, z) : createOutputDecorator;
                    copyStream(value.mInputStream, compressionOutputStream);
                    if (z2) {
                        ((CompressionOutputStream) compressionOutputStream).finish();
                    }
                    createOutputDecorator.write("\r\n".getBytes());
                }
                createOutputDecorator.write(String.format("--%s--\r\n", uuid).getBytes());
                createOutputDecorator.flush();
                aCRAResponse.mStatus = connection.getResponseCode();
                connection.getInputStream().close();
                if (outputStream != null) {
                    outputStream.close();
                }
                return;
            } catch (Throwable unused) {
            }
            throw th;
        } finally {
            connection.disconnect();
        }
    }

    private static byte[] generateMultipartHeader(String str, String str2, String str3) {
        return String.format("--%s\r\nContent-Disposition: %s\"%s\"\r\nContent-Type: application/binary\r\nContent-Transfer-Encoding: binary\r\n\r\n", str, str2, str3).getBytes();
    }

    private static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
