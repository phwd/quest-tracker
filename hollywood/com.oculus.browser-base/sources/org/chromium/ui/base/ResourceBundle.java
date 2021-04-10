package org.chromium.ui.base;

import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import java.util.Arrays;
import org.chromium.base.ContextUtils;
import org.chromium.base.LocaleUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ResourceBundle {

    /* renamed from: a  reason: collision with root package name */
    public static String[] f11019a;
    public static String[] b;

    public static String getLocalePakResourcePath(String str, boolean z, boolean z2) {
        String str2;
        String[] strArr = b;
        if (strArr == null || Arrays.binarySearch(strArr, str) < 0) {
            return null;
        }
        if (!z) {
            str2 = "assets/stored-locales/";
        } else if (str.equals("en-US")) {
            str2 = "assets/fallback-locales/";
        } else {
            String b2 = LocaleUtils.b(str);
            b2.hashCode();
            b2.hashCode();
            char c = 65535;
            switch (b2.hashCode()) {
                case 3325:
                    if (b2.equals("he")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3355:
                    if (b2.equals("id")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3856:
                    if (b2.equals("yi")) {
                        c = 2;
                        break;
                    }
                    break;
                case 101385:
                    if (b2.equals("fil")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    b2 = "iw";
                    break;
                case 1:
                    b2 = "in";
                    break;
                case 2:
                    b2 = "ji";
                    break;
                case 3:
                    b2 = "tl";
                    break;
            }
            str2 = AbstractC2531fV.g("assets/locales#lang_", b2, "/");
        }
        String g = AbstractC2531fV.g(str2, str, ".pak");
        try {
            AssetFileDescriptor openNonAssetFd = ContextUtils.getApplicationContext().getAssets().openNonAssetFd(g);
            if (openNonAssetFd != null) {
                openNonAssetFd.close();
            }
            return g;
        } catch (IOException e) {
            if (z2) {
                AbstractC1220Ua0.a("ResourceBundle", "path=%s", g, e);
            }
            return null;
        }
    }

    public static void setNoAvailableLocalePaks() {
        f11019a = new String[0];
        b = new String[0];
    }
}
