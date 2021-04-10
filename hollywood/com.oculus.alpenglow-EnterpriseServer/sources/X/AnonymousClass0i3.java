package X;

import com.facebook.tigon.iface.TigonRequest;

/* renamed from: X.0i3  reason: invalid class name */
public final class AnonymousClass0i3 {
    public static boolean A01(String str) {
        if (str.equals(TigonRequest.POST) || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT")) {
            return true;
        }
        return false;
    }

    public static boolean A00(String str) {
        if (A01(str) || str.equals("OPTIONS") || str.equals("DELETE") || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK")) {
            return true;
        }
        return false;
    }
}
