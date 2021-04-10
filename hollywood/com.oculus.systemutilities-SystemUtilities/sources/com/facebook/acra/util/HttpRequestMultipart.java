package com.facebook.acra.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

public class HttpRequestMultipart {
    private HttpConnectionProvider mConnectionProvider;
    private Map<String, String> mHeaders;

    public HttpRequestMultipart(HttpConnectionProvider connectionProvider) {
        this.mConnectionProvider = connectionProvider;
    }

    public void sendPost(URL url, Map<?, ?> textParameters, Map<?, InputStreamField> binParameters, ACRAResponse response, String userAgent, boolean useZstd) throws IOException {
        OutputStream targetOutputStream;
        HttpURLConnection urlConnection = this.mConnectionProvider.getConnection(url);
        String boundary = generateBoundary();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("User-Agent", userAgent);
        urlConnection.setRequestProperty("Content-Type", String.format("multipart/form-data;boundary=%s", boundary));
        if (this.mHeaders != null && !this.mHeaders.isEmpty()) {
            for (Map.Entry e : this.mHeaders.entrySet()) {
                urlConnection.setRequestProperty(e.getKey().toString(), e.getValue().toString());
            }
        }
        urlConnection.setDoOutput(true);
        urlConnection.setChunkedStreamingMode(0);
        try {
            OutputStream baseOutputStream = urlConnection.getOutputStream();
            try {
                OutputStream outputStream = AcraRadioMonitorBridge.createOutputDecorator(baseOutputStream);
                writeAcraData("acra_data", outputStream, boundary, textParameters);
                for (Map.Entry<?, InputStreamField> entry : binParameters.entrySet()) {
                    InputStreamField field = entry.getValue();
                    boolean sendCompressed = field.getSendCompressed();
                    outputStream.write(generateMultipartHeader(boundary, field.getSendAsFile() ? "form-data; filename=\"file\"; name=" : "form-data; name=", entry.getKey().toString()));
                    if (sendCompressed) {
                        targetOutputStream = new CompressionOutputStream(outputStream, 8192, useZstd);
                    } else {
                        targetOutputStream = outputStream;
                    }
                    copyStream(field.getInputStream(), targetOutputStream);
                    if (sendCompressed) {
                        ((CompressionOutputStream) targetOutputStream).finish();
                    }
                    outputStream.write("\r\n".getBytes());
                }
                outputStream.write(generateMultipartEndFooter(boundary));
                outputStream.flush();
                response.setStatusCode(urlConnection.getResponseCode());
                urlConnection.getInputStream().close();
                if (baseOutputStream != null) {
                    baseOutputStream.close();
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } finally {
            urlConnection.disconnect();
        }
    }

    private static void writeAcraData(String fieldName, OutputStream outputStream, String boundary, Map<?, ?> textParameters) throws IOException {
        outputStream.write(generateMultipartHeader(boundary, "form-data; name=", fieldName));
        GZIPOutputStream gzipStream = new GZIPOutputStream(outputStream);
        HttpRequest.encodeParameters(textParameters, gzipStream);
        gzipStream.finish();
        outputStream.write("\r\n".getBytes());
    }

    private static String generateBoundary() {
        return UUID.randomUUID().toString();
    }

    private static byte[] generateMultipartHeader(String boundary, String disposition, String name) {
        return String.format("--%s\r\nContent-Disposition: %s\"%s\"\r\nContent-Type: application/binary\r\nContent-Transfer-Encoding: binary\r\n\r\n", boundary, disposition, name).getBytes();
    }

    private static byte[] generateMultipartEndFooter(String boundary) {
        return String.format("--%s--\r\n", boundary).getBytes();
    }

    private static void copyStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[8192];
        while (true) {
            int bytesWritten = is.read(buffer);
            if (bytesWritten != -1) {
                os.write(buffer, 0, bytesWritten);
            } else {
                return;
            }
        }
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }
}
