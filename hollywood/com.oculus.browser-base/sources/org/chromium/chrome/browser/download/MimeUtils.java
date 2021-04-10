package org.chromium.chrome.browser.download;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MimeUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final HashSet f10663a = new HashSet(Arrays.asList("text/plain", "application/octet-stream", "binary/octet-stream", "octet/stream", "application/download", "application/force-download", "application/unknown"));
    public static final List b = new ArrayList(Arrays.asList("application/vnd.oma.dd+xml", "application/pdf", "application/x-x509-ca-cert", "application/x-x509-user-cert", "application/x-x509-server-cert", "application/x-pkcs12", "application/application/x-pem-file", "application/pkix-cert", "application/x-wifi-config"));

    public static boolean canAutoOpenMimeType(String str) {
        return b.contains(str);
    }

    public static boolean isOMADownloadDescription(String str) {
        return "application/vnd.oma.dd+xml".equalsIgnoreCase(str);
    }

    public static String remapGenericMimeType(String str, String str2, String str3) {
        String str4;
        int lastIndexOf;
        if (TextUtils.isEmpty(str)) {
            str = "application/unknown";
        }
        if (!f10663a.contains(str)) {
            return str;
        }
        if (TextUtils.isEmpty(str3) || (lastIndexOf = str3.lastIndexOf(".")) <= 0) {
            str4 = MimeTypeMap.getFileExtensionFromUrl(str2);
        } else {
            str4 = str3.substring(lastIndexOf + 1);
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str4);
        if (mimeTypeFromExtension != null) {
            return mimeTypeFromExtension;
        }
        if (str4.equals("dm")) {
            return "application/vnd.oma.drm.message";
        }
        return str4.equals("dd") ? "application/vnd.oma.dd+xml" : str;
    }
}
