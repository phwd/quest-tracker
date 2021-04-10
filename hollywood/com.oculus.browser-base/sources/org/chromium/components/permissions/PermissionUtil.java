package org.chromium.components.permissions;

import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f10879a = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] b = {"android.permission.CAMERA"};
    public static final String[] c = {"android.permission.RECORD_AUDIO"};
    public static final String[] d = new String[0];

    public static String[] getAndroidPermissionsForContentSetting(int i) {
        if (i != 5) {
            if (i != 58) {
                if (i == 9) {
                    String[] strArr = c;
                    return (String[]) Arrays.copyOf(strArr, strArr.length);
                } else if (i != 10) {
                    return d;
                }
            }
            String[] strArr2 = b;
            return (String[]) Arrays.copyOf(strArr2, strArr2.length);
        }
        String[] strArr3 = f10879a;
        return (String[]) Arrays.copyOf(strArr3, strArr3.length);
    }
}
