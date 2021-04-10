package X;

import com.facebook.tigon.iface.TigonRequest;
import com.oculus.auth.service.contract.ServiceContract;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.Wn  reason: case insensitive filesystem */
public final class C0162Wn {
    public static final Map<WM, Integer> A00;
    public static final C0165Wq[] A01;

    static {
        C0165Wq[] wqArr = new C0165Wq[61];
        C0165Wq wq = new C0165Wq(C0165Wq.A05, "");
        WM wm = C0165Wq.A06;
        C0165Wq wq2 = new C0165Wq(wm, TigonRequest.GET);
        C0165Wq wq3 = new C0165Wq(wm, TigonRequest.POST);
        WM wm2 = C0165Wq.A07;
        C0165Wq wq4 = new C0165Wq(wm2, "/");
        C0165Wq wq5 = new C0165Wq(wm2, "/index.html");
        WM wm3 = C0165Wq.A08;
        C0165Wq wq6 = new C0165Wq(wm3, "http");
        C0165Wq wq7 = new C0165Wq(wm3, "https");
        WM wm4 = C0165Wq.A04;
        System.arraycopy(new C0165Wq[]{wq, wq2, wq3, wq4, wq5, wq6, wq7, new C0165Wq(wm4, "200"), new C0165Wq(wm4, "204"), new C0165Wq(wm4, "206"), new C0165Wq(wm4, "304"), new C0165Wq(wm4, "400"), new C0165Wq(wm4, "404"), new C0165Wq(wm4, "500"), new C0165Wq("accept-charset", ""), new C0165Wq("accept-encoding", "gzip, deflate"), new C0165Wq("accept-language", ""), new C0165Wq("accept-ranges", ""), new C0165Wq("accept", ""), new C0165Wq("access-control-allow-origin", ""), new C0165Wq("age", ""), new C0165Wq("allow", ""), new C0165Wq("authorization", ""), new C0165Wq("cache-control", ""), new C0165Wq("content-disposition", ""), new C0165Wq("content-encoding", ""), new C0165Wq("content-language", "")}, 0, wqArr, 0, 27);
        System.arraycopy(new C0165Wq[]{new C0165Wq("content-length", ""), new C0165Wq("content-location", ""), new C0165Wq("content-range", ""), new C0165Wq("content-type", ""), new C0165Wq("cookie", ""), new C0165Wq("date", ""), new C0165Wq("etag", ""), new C0165Wq("expect", ""), new C0165Wq("expires", ""), new C0165Wq("from", ""), new C0165Wq("host", ""), new C0165Wq("if-match", ""), new C0165Wq("if-modified-since", ""), new C0165Wq("if-none-match", ""), new C0165Wq("if-range", ""), new C0165Wq("if-unmodified-since", ""), new C0165Wq("last-modified", ""), new C0165Wq(ServiceContract.FOLLOW_UP_LINK, ""), new C0165Wq("location", ""), new C0165Wq("max-forwards", ""), new C0165Wq("proxy-authenticate", ""), new C0165Wq("proxy-authorization", ""), new C0165Wq("range", ""), new C0165Wq("referer", ""), new C0165Wq("refresh", ""), new C0165Wq("retry-after", ""), new C0165Wq("server", "")}, 0, wqArr, 27, 27);
        System.arraycopy(new C0165Wq[]{new C0165Wq("set-cookie", ""), new C0165Wq("strict-transport-security", ""), new C0165Wq("transfer-encoding", ""), new C0165Wq("user-agent", ""), new C0165Wq("vary", ""), new C0165Wq("via", ""), new C0165Wq("www-authenticate", "")}, 0, wqArr, 54, 7);
        A01 = wqArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i = 0;
        do {
            if (!linkedHashMap.containsKey(wqArr[i].A01)) {
                linkedHashMap.put(wqArr[i].A01, Integer.valueOf(i));
            }
            i++;
        } while (i < 61);
        A00 = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void A00(WM wm) throws IOException {
        int A07 = wm.A07();
        for (int i = 0; i < A07; i++) {
            byte A06 = wm.A06(i);
            if (A06 >= 65 && A06 <= 90) {
                throw new IOException(AnonymousClass06.A03("PROTOCOL_ERROR response malformed: mixed case name: ", wm.A0A()));
            }
        }
    }
}
