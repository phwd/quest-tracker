package com.facebook.acra.util;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

public class HttpRequestMultipart {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_DISPOSITION_FILE = "form-data; filename=\"file\"; name=";
    private static final String CONTENT_DISPOSITION_FORM_DATA = "form-data; name=";
    private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    private static final String CONTENT_TRANSFER_ENCODING_BINARY = "binary";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_APP_BINARY = "application/binary";
    private static final String CONTENT_TYPE_FORM_MULTIPART_FORMAT = "multipart/form-data;boundary=%s";
    private static final String LINE_FEED = "\r\n";
    private static final int STREAM_BLOCK_SIZE = 8192;
    private static final String USER_AGENT = "User-Agent";
    private HttpConnectionProvider mConnectionProvider;
    private Map<String, String> mHeaders;

    public HttpRequestMultipart(HttpConnectionProvider httpConnectionProvider) {
        this.mConnectionProvider = httpConnectionProvider;
    }

    public void sendPost(URL url, Map<?, ?> map, Map<?, InputStreamField> map2, ACRAResponse aCRAResponse, String str, boolean z) throws IOException {
        HttpURLConnection connection = this.mConnectionProvider.getConnection(url);
        String generateBoundary = generateBoundary();
        connection.setRequestMethod(TigonRequest.POST);
        connection.setRequestProperty("User-Agent", str);
        connection.setRequestProperty("Content-Type", String.format(CONTENT_TYPE_FORM_MULTIPART_FORMAT, generateBoundary));
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
                writeAcraData("acra_data", createOutputDecorator, generateBoundary, map);
                for (Map.Entry<?, InputStreamField> entry2 : map2.entrySet()) {
                    InputStreamField value = entry2.getValue();
                    boolean sendCompressed = value.getSendCompressed();
                    createOutputDecorator.write(generateMultipartHeader(generateBoundary, value.getSendAsFile() ? CONTENT_DISPOSITION_FILE : CONTENT_DISPOSITION_FORM_DATA, entry2.getKey().toString()));
                    OutputStream compressionOutputStream = sendCompressed ? new CompressionOutputStream(createOutputDecorator, 8192, z) : createOutputDecorator;
                    copyStream(value.getInputStream(), compressionOutputStream);
                    if (sendCompressed) {
                        ((CompressionOutputStream) compressionOutputStream).finish();
                    }
                    createOutputDecorator.write(LINE_FEED.getBytes());
                }
                createOutputDecorator.write(generateMultipartEndFooter(generateBoundary));
                createOutputDecorator.flush();
                aCRAResponse.setStatusCode(connection.getResponseCode());
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

    private static void writeAcraData(String str, OutputStream outputStream, String str2, Map<?, ?> map) throws IOException {
        outputStream.write(generateMultipartHeader(str2, CONTENT_DISPOSITION_FORM_DATA, str));
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        HttpRequest.encodeParameters(map, gZIPOutputStream);
        gZIPOutputStream.finish();
        outputStream.write(LINE_FEED.getBytes());
    }

    private static String generateBoundary() {
        return UUID.randomUUID().toString();
    }

    private static byte[] generateMultipartHeader(String str, String str2, String str3) {
        return String.format("--%s\r\nContent-Disposition: %s\"%s\"\r\nContent-Type: application/binary\r\nContent-Transfer-Encoding: binary\r\n\r\n", str, str2, str3).getBytes();
    }

    private static byte[] generateMultipartEndFooter(String str) {
        return String.format("--%s--\r\n", str).getBytes();
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

    public void setHeaders(Map<String, String> map) {
        this.mHeaders = map;
    }
}
