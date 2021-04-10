package org.chromium.components.payments;

import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CanMakePaymentQuery {
    public static String[] getMethodIdentifiers(Map map) {
        return (String[]) map.keySet().toArray(new String[map.size()]);
    }

    public static String getStringifiedMethodData(Map map, String str) {
        return ((C1401Wz0) map.get(str)).e;
    }
}
