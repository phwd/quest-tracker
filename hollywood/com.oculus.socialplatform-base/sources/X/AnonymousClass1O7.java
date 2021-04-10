package X;

import com.facebook.msys.mci.network.common.DataTaskListener;
import com.facebook.msys.mci.network.common.UrlRequest;
import com.facebook.msys.mci.network.common.UrlResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: X.1O7  reason: invalid class name */
public final class AnonymousClass1O7 {
    public int A00;
    public final DataTaskListener A01 = new AnonymousClass1O4(this);
    public final File A02;
    public final String A03;
    public final ExecutorService A04 = Executors.newFixedThreadPool(6, new AnonymousClass1OP(this));

    public AnonymousClass1O7(String str, File file) {
        this.A03 = str;
        this.A02 = file;
    }

    public static UrlResponse A00(AnonymousClass1O7 r11, String str, UrlRequest urlRequest, AnonymousClass1O1 r14, OutputStream outputStream, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(urlRequest.getUrl()).openConnection();
        httpURLConnection.setDoInput(true);
        int i = r11.A00;
        if (i > 0) {
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(r11.A00);
        }
        try {
            byte[] httpBody = urlRequest.getHttpBody();
            Map<String, String> httpHeaders = urlRequest.getHttpHeaders();
            if (httpBody != null) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setFixedLengthStreamingMode(httpBody.length);
            }
            httpURLConnection.setRequestMethod(urlRequest.getHttpMethod());
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection.setRequestProperty("User-Agent", r11.A03);
            if (httpBody != null && httpURLConnection.getDoOutput()) {
                OutputStream outputStream2 = httpURLConnection.getOutputStream();
                try {
                    int length = httpBody.length;
                    int i2 = length;
                    int i3 = 0;
                    while (i3 < length) {
                        int min = Math.min(10240, i2);
                        outputStream2.write(httpBody, i3, min);
                        i2 -= min;
                        i3 += min;
                        if (z) {
                            r14.executeInNetworkContext(new AnonymousClass1O5(r11, r14, str, min, i3, httpBody));
                        }
                    }
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                } catch (Throwable unused) {
                }
            }
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    int contentLength = httpURLConnection.getContentLength();
                    byte[] bArr = new byte[10240];
                    int i4 = 0;
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                        i4 += read;
                        if (z2) {
                            r14.executeInNetworkContext(new AnonymousClass1O6(r11, r14, str, read, i4, contentLength));
                        }
                    }
                    inputStream.close();
                } catch (Throwable unused2) {
                }
            } catch (IOException unused3) {
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode >= 400 && responseCode <= 500) {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    String format = String.format(null, "[HTTP status=%d] Error Content = ", Integer.valueOf(responseCode));
                    if (errorStream != null) {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read2 = errorStream.read(bArr2);
                                if (read2 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr2, 0, read2);
                            }
                            format = AnonymousClass006.A07(format, byteArrayOutputStream.toString());
                        } catch (IOException unused4) {
                        } catch (Throwable th) {
                            errorStream.close();
                            throw th;
                        }
                    }
                    errorStream.close();
                    throw new IOException(format);
                }
            }
            int responseCode2 = httpURLConnection.getResponseCode();
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, List<String>> entry2 : headerFields.entrySet()) {
                if (entry2.getKey() != null) {
                    List<String> value = entry2.getValue();
                    if (value.size() == 1) {
                        hashMap.put(entry2.getKey(), entry2.getValue().get(0));
                    } else if (value.size() > 1) {
                        StringBuilder sb = new StringBuilder(value.size() << 4);
                        for (int i5 = 1; i5 < value.size(); i5++) {
                            sb.append(',');
                            sb.append(value.get(i5));
                        }
                        hashMap.put(entry2.getKey(), sb.toString());
                    }
                }
            }
            UrlResponse urlResponse = new UrlResponse(urlRequest, responseCode2, hashMap);
            httpURLConnection.getResponseCode();
            return urlResponse;
            throw th;
            throw th;
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
