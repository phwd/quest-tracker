package oculus.internal.yadi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Network;
import android.net.Uri;
import android.os.Binder;
import android.security.NetworkSecurityPolicy;
import android.security.net.config.ApplicationConfig;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import oculus.internal.yadi.DownloadException;

/* access modifiers changed from: package-private */
public final class Utils {
    private static final Random RNG = new Random();

    static ApplicationConfig getAppNetsecConfig(Context context, String str) {
        try {
            return NetworkSecurityPolicy.getApplicationConfigForPackage(context, str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    static ApplicationConfig getCallingNetsecConfig(Context context) {
        context.getPackageManager();
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null || packagesForUid.length == 0) {
            return null;
        }
        return getAppNetsecConfig(context, packagesForUid[0]);
    }

    static boolean isLoopbackHost(Uri uri) {
        try {
            String host = new URI(uri.toString()).getHost();
            if (!host.isEmpty() && host.charAt(0) == '[' && host.charAt(host.length() - 1) == ']') {
                host = host.substring(1, host.length() - 1);
            }
            return InetAddress.getByName(host).isLoopbackAddress();
        } catch (URISyntaxException | UnknownHostException unused) {
            return false;
        }
    }

    static HttpURLConnection createConnection(Network network, ApplicationConfig applicationConfig, URL url) throws IOException, GeneralSecurityException {
        return configureSsl(network.openConnection(url), applicationConfig);
    }

    private static HttpURLConnection configureSsl(URLConnection uRLConnection, ApplicationConfig applicationConfig) throws IOException {
        SSLContext sSLContext;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            throw new DownloadException.Client("Invalid protocol");
        } else if (uRLConnection instanceof HttpsURLConnection) {
            if (applicationConfig == null) {
                try {
                    sSLContext = SSLContext.getDefault();
                } catch (GeneralSecurityException unused) {
                    throw new DownloadException("Internal error");
                }
            } else {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, new TrustManager[]{applicationConfig.getTrustManager()}, null);
                sSLContext = instance;
            }
            ((HttpsURLConnection) uRLConnection).setSSLSocketFactory(sSLContext.getSocketFactory());
            return (HttpURLConnection) uRLConnection;
        } else if (applicationConfig == null || applicationConfig.isCleartextTrafficPermitted()) {
            return (HttpURLConnection) uRLConnection;
        } else {
            throw new DownloadException.Client("Secure connection required");
        }
    }

    static URL uriToUrl(Uri uri) throws DownloadException {
        try {
            return new URI(uri.getScheme(), uri.getEncodedSchemeSpecificPart(), uri.getEncodedFragment()).toURL();
        } catch (MalformedURLException | URISyntaxException unused) {
            throw new DownloadException.Client("Invalid URI");
        }
    }

    public static boolean emptyOrNull(String str) {
        return str == null || "".equals(str);
    }

    public static String hexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02x", Integer.valueOf((bArr[i] + 256) & 255)));
        }
        return sb.toString();
    }

    public static String hexString(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.capacity() == 0) {
            return "";
        }
        byte[] bArr = new byte[byteBuffer.capacity()];
        ((ByteBuffer) byteBuffer.rewind()).get(bArr);
        return hexString(bArr);
    }
}
