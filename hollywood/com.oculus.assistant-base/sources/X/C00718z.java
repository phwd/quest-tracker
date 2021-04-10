package X;

import java.util.Arrays;
import java.util.HashMap;

/* renamed from: X.8z  reason: invalid class name and case insensitive filesystem */
public final class C00718z {
    public static final HashMap A00 = new HashMap();
    public static final String[] A01 = {"anytimeui.AnytimeUIAndroidPanelService", "explore.PanelService", "gamingactivity.PanelService", "home.ExploreService", "home.LibraryService", "home.SearchService", "home.StoreService"};
    public static final String[] A02 = {"browser.PanelService"};
    public static final String[] A03 = {"home.SocialDialogsService", "home.SocialService", "socialplatform.SocialDialogsService", "socialplatform.SocialService"};

    public static EnumC00708y A00(String str) {
        String str2;
        EnumC00708y r0;
        if (str == null) {
            return EnumC00708y.UNSUPPORTED;
        }
        HashMap hashMap = A00;
        EnumC00708y r02 = (EnumC00708y) hashMap.get(str);
        if (r02 != null) {
            return r02;
        }
        String[] split = str.split("\\.(?!.*[.].*[.])(?=.*[.])");
        if (split.length == 2) {
            str2 = split[1];
        } else {
            str2 = str;
        }
        if (Arrays.binarySearch(A01, str2) >= 0) {
            r0 = EnumC00708y.OCULUS_STORE;
        } else if (Arrays.binarySearch(A03, str2) >= 0) {
            r0 = EnumC00708y.OCULUS_SOCIAL;
        } else if (Arrays.binarySearch(A02, str2) >= 0) {
            r0 = EnumC00708y.OCULUS_BROWSER;
        } else {
            r0 = EnumC00708y.UNSUPPORTED;
        }
        hashMap.put(str, r0);
        return r0;
    }
}
