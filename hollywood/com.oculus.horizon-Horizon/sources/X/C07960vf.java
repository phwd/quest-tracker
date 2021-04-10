package X;

import com.facebook.tigon.iface.TigonRequest;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.mediaupload.io.MediaUploaderNotifications;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0vf  reason: invalid class name and case insensitive filesystem */
public final class C07960vf {
    public static final Map<C07700vD, Integer> A00;
    public static final C07990vi[] A01;

    static {
        C07700vD r3 = C07990vi.A06;
        C07700vD r32 = C07990vi.A07;
        C07700vD r33 = C07990vi.A08;
        C07700vD r34 = C07990vi.A04;
        C07990vi[] r4 = {new C07990vi(C07990vi.A05, ""), new C07990vi(r3, TigonRequest.GET), new C07990vi(r3, TigonRequest.POST), new C07990vi(r32, "/"), new C07990vi(r32, "/index.html"), new C07990vi(r33, "http"), new C07990vi(r33, "https"), new C07990vi(r34, "200"), new C07990vi(r34, "204"), new C07990vi(r34, "206"), new C07990vi(r34, "304"), new C07990vi(r34, "400"), new C07990vi(r34, "404"), new C07990vi(r34, "500"), new C07990vi("accept-charset", ""), new C07990vi("accept-encoding", "gzip, deflate"), new C07990vi("accept-language", ""), new C07990vi("accept-ranges", ""), new C07990vi(MediaUploaderNotifications.ACTION_ACCEPT, ""), new C07990vi("access-control-allow-origin", ""), new C07990vi("age", ""), new C07990vi("allow", ""), new C07990vi("authorization", ""), new C07990vi("cache-control", ""), new C07990vi("content-disposition", ""), new C07990vi("content-encoding", ""), new C07990vi("content-language", ""), new C07990vi("content-length", ""), new C07990vi("content-location", ""), new C07990vi("content-range", ""), new C07990vi("content-type", ""), new C07990vi("cookie", ""), new C07990vi("date", ""), new C07990vi("etag", ""), new C07990vi("expect", ""), new C07990vi("expires", ""), new C07990vi("from", ""), new C07990vi("host", ""), new C07990vi("if-match", ""), new C07990vi("if-modified-since", ""), new C07990vi("if-none-match", ""), new C07990vi("if-range", ""), new C07990vi("if-unmodified-since", ""), new C07990vi("last-modified", ""), new C07990vi("link", ""), new C07990vi(LoggingKeys.NOTIFICATION_PREFERENCE_CHANGE_LOCATION, ""), new C07990vi("max-forwards", ""), new C07990vi("proxy-authenticate", ""), new C07990vi("proxy-authorization", ""), new C07990vi("range", ""), new C07990vi("referer", ""), new C07990vi("refresh", ""), new C07990vi("retry-after", ""), new C07990vi("server", ""), new C07990vi("set-cookie", ""), new C07990vi("strict-transport-security", ""), new C07990vi("transfer-encoding", ""), new C07990vi("user-agent", ""), new C07990vi("vary", ""), new C07990vi("via", ""), new C07990vi("www-authenticate", "")};
        A01 = r4;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i = 0;
        do {
            if (!linkedHashMap.containsKey(r4[i].A01)) {
                linkedHashMap.put(r4[i].A01, Integer.valueOf(i));
            }
            i++;
        } while (i < 61);
        A00 = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void A00(C07700vD r4) throws IOException {
        int A07 = r4.A07();
        for (int i = 0; i < A07; i++) {
            byte A06 = r4.A06(i);
            if (A06 >= 65 && A06 <= 90) {
                throw new IOException(AnonymousClass006.A05("PROTOCOL_ERROR response malformed: mixed case name: ", r4.A0A()));
            }
        }
    }
}
