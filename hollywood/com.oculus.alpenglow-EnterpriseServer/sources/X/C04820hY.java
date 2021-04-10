package X;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0hY  reason: invalid class name and case insensitive filesystem */
public final class C04820hY {
    public static final Map<C04610h7, Integer> A00;
    public static final C04870hr[] A01;

    static {
        C04870hr[] r3 = new C04870hr[61];
        C04870hr r29 = new C04870hr(C04870hr.A05, "");
        C04610h7 r4 = C04870hr.A06;
        C04870hr r28 = new C04870hr(r4, TigonRequest.GET);
        C04870hr r27 = new C04870hr(r4, TigonRequest.POST);
        C04610h7 r42 = C04870hr.A07;
        C04870hr r26 = new C04870hr(r42, "/");
        C04870hr r25 = new C04870hr(r42, "/index.html");
        C04610h7 r43 = C04870hr.A08;
        C04870hr r24 = new C04870hr(r43, "http");
        C04870hr r23 = new C04870hr(r43, "https");
        C04610h7 r44 = C04870hr.A04;
        System.arraycopy(new C04870hr[]{r29, r28, r27, r26, r25, r24, r23, new C04870hr(r44, "200"), new C04870hr(r44, "204"), new C04870hr(r44, "206"), new C04870hr(r44, "304"), new C04870hr(r44, "400"), new C04870hr(r44, "404"), new C04870hr(r44, "500"), new C04870hr("accept-charset", ""), new C04870hr("accept-encoding", "gzip, deflate"), new C04870hr("accept-language", ""), new C04870hr("accept-ranges", ""), new C04870hr("accept", ""), new C04870hr("access-control-allow-origin", ""), new C04870hr("age", ""), new C04870hr("allow", ""), new C04870hr("authorization", ""), new C04870hr("cache-control", ""), new C04870hr("content-disposition", ""), new C04870hr("content-encoding", ""), new C04870hr("content-language", "")}, 0, r3, 0, 27);
        System.arraycopy(new C04870hr[]{new C04870hr("content-length", ""), new C04870hr("content-location", ""), new C04870hr("content-range", ""), new C04870hr("content-type", ""), new C04870hr("cookie", ""), new C04870hr("date", ""), new C04870hr("etag", ""), new C04870hr("expect", ""), new C04870hr("expires", ""), new C04870hr("from", ""), new C04870hr("host", ""), new C04870hr("if-match", ""), new C04870hr("if-modified-since", ""), new C04870hr("if-none-match", ""), new C04870hr("if-range", ""), new C04870hr("if-unmodified-since", ""), new C04870hr("last-modified", ""), new C04870hr("link", ""), new C04870hr("location", ""), new C04870hr("max-forwards", ""), new C04870hr("proxy-authenticate", ""), new C04870hr("proxy-authorization", ""), new C04870hr("range", ""), new C04870hr("referer", ""), new C04870hr("refresh", ""), new C04870hr("retry-after", ""), new C04870hr("server", "")}, 0, r3, 27, 27);
        System.arraycopy(new C04870hr[]{new C04870hr("set-cookie", ""), new C04870hr("strict-transport-security", ""), new C04870hr("transfer-encoding", ""), new C04870hr("user-agent", ""), new C04870hr("vary", ""), new C04870hr("via", ""), new C04870hr("www-authenticate", "")}, 0, r3, 54, 7);
        A01 = r3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i = 0;
        do {
            if (!linkedHashMap.containsKey(r3[i].A01)) {
                linkedHashMap.put(r3[i].A01, Integer.valueOf(i));
            }
            i++;
        } while (i < 61);
        A00 = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void A00(C04610h7 r4) throws IOException {
        int A07 = r4.A07();
        for (int i = 0; i < A07; i++) {
            byte A06 = r4.A06(i);
            if (A06 >= 65 && A06 <= 90) {
                throw new IOException(AnonymousClass006.A05("PROTOCOL_ERROR response malformed: mixed case name: ", r4.A0A()));
            }
        }
    }
}
