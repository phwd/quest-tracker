package org.chromium.chrome.browser.translate;

import J.N;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TranslateBridge {
    public static List a() {
        ArrayList arrayList = new ArrayList();
        N.MMAgYJ7_(arrayList);
        return arrayList;
    }

    public static void addModelLanguageToSet(LinkedHashSet linkedHashSet, String str) {
        linkedHashSet.add(str);
    }

    public static void addNewLanguageItemToList(List list, String str, String str2, String str3, boolean z) {
        list.add(new B60(str, str2, str3, z));
    }

    public static void copyStringArrayToList(List list, String[] strArr) {
        list.addAll(Arrays.asList(strArr));
    }
}
