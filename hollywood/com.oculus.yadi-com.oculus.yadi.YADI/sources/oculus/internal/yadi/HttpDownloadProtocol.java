package oculus.internal.yadi;

import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.security.net.config.ApplicationConfig;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.os.yadi.RemoteResource;
import com.oculus.os.yadi.YadiStatus;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oculus.internal.yadi.IDownloadProtocol;

class HttpDownloadProtocol implements IDownloadProtocol {
    private static final Pattern CONTENT_RANGE_PATTERN = Pattern.compile("(\\w+) ((?:(\\d+)-(\\d+))|\\*)/(\\d+|\\*)");
    private static final String USER_AGENT;

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface IOFunction<T, R> {
        R apply(T t) throws IOException;
    }

    HttpDownloadProtocol() {
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("YADI");
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE)) {
            sb.append("/");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append(" (Linux; U; Android");
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE)) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        if (!TextUtils.isEmpty(Build.MODEL)) {
            sb.append(";");
            sb.append(Build.MODEL);
        }
        if (!TextUtils.isEmpty(Build.ID)) {
            sb.append(" ");
            sb.append(Build.ID);
        }
        sb.append(")");
        USER_AGENT = sb.toString();
    }

    @Override // oculus.internal.yadi.IDownloadProtocol
    public void download(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource, File file, Bundle bundle, IDownloadProtocol.ProgressFunc progressFunc) throws IOException {
        ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 939524096);
        try {
            httpGet(network, applicationConfig, remoteResource, open, bundle, progressFunc);
        } finally {
            open.close();
        }
    }

    private void httpGet(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, IDownloadProtocol.ProgressFunc progressFunc) throws IOException {
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        HttpURLConnection redirector = redirector(Utils.uriToUrl(remoteResource.downloadUri), new IOFunction(network, applicationConfig, remoteResource, fileDescriptor, bundle) {
            /* class oculus.internal.yadi.$$Lambda$HttpDownloadProtocol$Xn7oJdXHN5G9IExAD2XNg6bMhX0 */
            private final /* synthetic */ Network f$0;
            private final /* synthetic */ ApplicationConfig f$1;
            private final /* synthetic */ RemoteResource f$2;
            private final /* synthetic */ FileDescriptor f$3;
            private final /* synthetic */ Bundle f$4;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // oculus.internal.yadi.HttpDownloadProtocol.IOFunction
            public final Object apply(Object obj) {
                return HttpDownloadProtocol.lambda$httpGet$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (URL) obj);
            }
        });
        try {
            handleGetResponse(redirector, parcelFileDescriptor, fileDescriptor, progressFunc);
        } finally {
            redirector.disconnect();
        }
    }

    static /* synthetic */ HttpURLConnection lambda$httpGet$0(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource, FileDescriptor fileDescriptor, Bundle bundle, URL url) throws IOException {
        HttpURLConnection httpURLConnection;
        GeneralSecurityException e;
        try {
            httpURLConnection = Utils.createConnection(network, applicationConfig, url);
            configure(httpURLConnection, remoteResource);
            try {
                httpURLConnection.setRequestMethod("GET");
            } catch (GeneralSecurityException e2) {
                e = e2;
            }
        } catch (GeneralSecurityException e3) {
            e = e3;
            httpURLConnection = null;
            Log.e("YadiDownload-HTTP", "Unable to load SSLContext", e);
            configureResume(httpURLConnection, Os.fstat(fileDescriptor).st_size, bundle);
            return httpURLConnection;
        }
        configureResume(httpURLConnection, Os.fstat(fileDescriptor).st_size, bundle);
        return httpURLConnection;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0080 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.io.FileOutputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void handleGetResponse(java.net.HttpURLConnection r17, android.os.ParcelFileDescriptor r18, java.io.FileDescriptor r19, oculus.internal.yadi.IDownloadProtocol.ProgressFunc r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 302
        */
        throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.HttpDownloadProtocol.handleGetResponse(java.net.HttpURLConnection, android.os.ParcelFileDescriptor, java.io.FileDescriptor, oculus.internal.yadi.IDownloadProtocol$ProgressFunc):void");
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    private static void throwReschedule(HttpURLConnection httpURLConnection) throws DownloadException {
        if ("bytes".equalsIgnoreCase(getHeaderField(httpURLConnection, "Accept-Ranges"))) {
            Bundle bundle = new Bundle();
            String headerField = getHeaderField(httpURLConnection, "ETag");
            String headerField2 = getHeaderField(httpURLConnection, "Last-Modified");
            if (!headerField.isEmpty()) {
                bundle.putString("ETag", headerField);
            }
            if (!headerField2.isEmpty()) {
                bundle.putString("Last-Modified", headerField2);
            }
            throw DownloadException.ResumableReschedule(bundle);
        }
        throw DownloadException.Reschedule();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: oculus.internal.yadi.HttpDownloadProtocol$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$yadi$YadiStatus = new int[YadiStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.os.yadi.YadiStatus[] r0 = com.oculus.os.yadi.YadiStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                oculus.internal.yadi.HttpDownloadProtocol.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus = r0
                int[] r0 = oculus.internal.yadi.HttpDownloadProtocol.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.yadi.YadiStatus r1 = com.oculus.os.yadi.YadiStatus.Paused     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = oculus.internal.yadi.HttpDownloadProtocol.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.yadi.YadiStatus r1 = com.oculus.os.yadi.YadiStatus.Terminated     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: oculus.internal.yadi.HttpDownloadProtocol.AnonymousClass1.<clinit>():void");
        }
    }

    private static void sendUpdate(IDownloadProtocol.ProgressFunc progressFunc, HttpURLConnection httpURLConnection, long j, long j2, long j3) throws DownloadException {
        int i = AnonymousClass1.$SwitchMap$com$oculus$os$yadi$YadiStatus[progressFunc.update(j + j2, j3).ordinal()];
        if (i == 1) {
            throwReschedule(httpURLConnection);
            throw null;
        } else if (i == 2) {
            throw DownloadException.Client("Canceled");
        }
    }

    static Matcher matchContentRange(HttpURLConnection httpURLConnection) throws DownloadException {
        return matchContentRange(getHeaderField(httpURLConnection, "Content-Range"));
    }

    static Matcher matchContentRange(String str) throws DownloadException {
        Matcher matcher = CONTENT_RANGE_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw DownloadException.Server("Expected Content-Range");
        } else if (!"bytes".equalsIgnoreCase(matcher.group(1))) {
            throw DownloadException.Client("Unsupported Content-Range");
        } else if (!"*".equals(matcher.group(2))) {
            return matcher;
        } else {
            throw DownloadException.Client("Unsatisfiable Content-Range");
        }
    }

    static long rangeStart(Matcher matcher) throws DownloadException {
        try {
            return Long.parseLong(matcher.group(3));
        } catch (NumberFormatException unused) {
            throw DownloadException.Server("Invalid Content-Range");
        }
    }

    static long rangeEnd(Matcher matcher) throws DownloadException {
        try {
            return Long.parseLong(matcher.group(4));
        } catch (NumberFormatException unused) {
            throw DownloadException.Server("Invalid Content-Range");
        }
    }

    static long entitySize(Matcher matcher) throws DownloadException {
        String group = matcher.group(5);
        if ("*".equals(group)) {
            return -1;
        }
        try {
            return Long.parseLong(group);
        } catch (NumberFormatException unused) {
            throw DownloadException.Server("Invalid Content-Range");
        }
    }

    static String getHeaderField(HttpURLConnection httpURLConnection, String str) {
        String headerField = httpURLConnection.getHeaderField(str);
        return headerField == null ? "" : headerField;
    }

    static boolean isFiniteResult(HttpURLConnection httpURLConnection) {
        return "close".equalsIgnoreCase(httpURLConnection.getHeaderField("Connection")) || "chunked".equalsIgnoreCase(httpURLConnection.getHeaderField("Transfer-Encoding")) || -1 != httpURLConnection.getHeaderFieldLong("Content-Length", -1);
    }

    private static HttpURLConnection configureResume(HttpURLConnection httpURLConnection, long j, Bundle bundle) {
        String string = bundle.getString("ETag", "");
        String string2 = bundle.getString("Last-Modified", "");
        boolean z = !string.isEmpty();
        boolean z2 = !string2.isEmpty();
        if (!(j == 0 || httpURLConnection == null)) {
            httpURLConnection.setRequestProperty("Range", String.format("bytes=%d-", Long.valueOf(j)));
            if (z) {
                httpURLConnection.setRequestProperty("If-Range", string);
            } else if (z2) {
                httpURLConnection.setRequestProperty("If-Range", string2);
            }
        }
        return httpURLConnection;
    }

    @Override // oculus.internal.yadi.IDownloadProtocol
    public long sizeOf(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource) throws IOException {
        HttpURLConnection redirector = redirector(Utils.uriToUrl(remoteResource.downloadUri), new IOFunction(network, applicationConfig, remoteResource) {
            /* class oculus.internal.yadi.$$Lambda$HttpDownloadProtocol$jUa2ld3lhZ_vPGy6qhGZp8yLfk */
            private final /* synthetic */ Network f$0;
            private final /* synthetic */ ApplicationConfig f$1;
            private final /* synthetic */ RemoteResource f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // oculus.internal.yadi.HttpDownloadProtocol.IOFunction
            public final Object apply(Object obj) {
                return HttpDownloadProtocol.lambda$sizeOf$1(this.f$0, this.f$1, this.f$2, (URL) obj);
            }
        });
        try {
            int responseCode = redirector.getResponseCode();
            int i = responseCode / 100;
            if (i == 2) {
                return redirector.getHeaderFieldLong("Content-Length", -1);
            }
            if (i != 4) {
                throw DownloadException.Server(Integer.toString(responseCode));
            }
            throw DownloadException.Client(Integer.toString(responseCode));
        } finally {
            redirector.disconnect();
        }
    }

    static /* synthetic */ HttpURLConnection lambda$sizeOf$1(Network network, ApplicationConfig applicationConfig, RemoteResource remoteResource, URL url) throws IOException {
        HttpURLConnection httpURLConnection;
        GeneralSecurityException e;
        try {
            httpURLConnection = Utils.createConnection(network, applicationConfig, url);
            configure(httpURLConnection, remoteResource);
            try {
                httpURLConnection.setRequestMethod("HEAD");
            } catch (GeneralSecurityException e2) {
                e = e2;
            }
        } catch (GeneralSecurityException e3) {
            e = e3;
            httpURLConnection = null;
            Log.e("YadiDownload-HTTP", "Unable to load SSLContext", e);
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    private static HttpURLConnection configure(HttpURLConnection httpURLConnection, RemoteResource remoteResource) {
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setReadTimeout(20000);
        for (Map.Entry entry : remoteResource.headers.entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        return httpURLConnection;
    }

    private static HttpURLConnection redirector(URL url, IOFunction<URL, HttpURLConnection> iOFunction) throws IOException {
        URL url2 = url;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i <= 5) {
                HttpURLConnection apply = iOFunction.apply(url2);
                if (apply != null) {
                    apply.setInstanceFollowRedirects(false);
                    int responseCode = apply.getResponseCode();
                    if (responseCode != 307) {
                        switch (responseCode) {
                            default:
                                return apply;
                            case 301:
                            case 302:
                            case 303:
                                break;
                        }
                    }
                    URL url3 = new URL(url2, getHeaderField(apply, "Location"));
                    apply.disconnect();
                    i = i2;
                    url2 = url3;
                } else {
                    throw DownloadException.InternalError();
                }
            } else {
                throw DownloadException.Server("Too many redirects");
            }
        }
    }
}
