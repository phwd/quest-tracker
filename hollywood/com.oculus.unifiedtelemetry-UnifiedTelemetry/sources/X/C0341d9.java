package X;

import com.facebook.tigon.iface.TigonRequest;
import com.oculus.auth.service.contract.ServiceContract;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.d9  reason: case insensitive filesystem */
public final class C0341d9 {
    public static final Map<ci, Integer> A00;
    public static final C0343dC[] A01;

    static {
        C0343dC[] dCVarArr = new C0343dC[61];
        C0343dC dCVar = new C0343dC(C0343dC.A05, "");
        ci ciVar = C0343dC.A06;
        C0343dC dCVar2 = new C0343dC(ciVar, TigonRequest.GET);
        C0343dC dCVar3 = new C0343dC(ciVar, TigonRequest.POST);
        ci ciVar2 = C0343dC.A07;
        C0343dC dCVar4 = new C0343dC(ciVar2, "/");
        C0343dC dCVar5 = new C0343dC(ciVar2, "/index.html");
        ci ciVar3 = C0343dC.A08;
        C0343dC dCVar6 = new C0343dC(ciVar3, "http");
        C0343dC dCVar7 = new C0343dC(ciVar3, "https");
        ci ciVar4 = C0343dC.A04;
        System.arraycopy(new C0343dC[]{dCVar, dCVar2, dCVar3, dCVar4, dCVar5, dCVar6, dCVar7, new C0343dC(ciVar4, "200"), new C0343dC(ciVar4, "204"), new C0343dC(ciVar4, "206"), new C0343dC(ciVar4, "304"), new C0343dC(ciVar4, "400"), new C0343dC(ciVar4, "404"), new C0343dC(ciVar4, "500"), new C0343dC("accept-charset", ""), new C0343dC("accept-encoding", "gzip, deflate"), new C0343dC("accept-language", ""), new C0343dC("accept-ranges", ""), new C0343dC("accept", ""), new C0343dC("access-control-allow-origin", ""), new C0343dC("age", ""), new C0343dC("allow", ""), new C0343dC("authorization", ""), new C0343dC("cache-control", ""), new C0343dC("content-disposition", ""), new C0343dC("content-encoding", ""), new C0343dC("content-language", "")}, 0, dCVarArr, 0, 27);
        System.arraycopy(new C0343dC[]{new C0343dC("content-length", ""), new C0343dC("content-location", ""), new C0343dC("content-range", ""), new C0343dC("content-type", ""), new C0343dC("cookie", ""), new C0343dC("date", ""), new C0343dC("etag", ""), new C0343dC("expect", ""), new C0343dC("expires", ""), new C0343dC("from", ""), new C0343dC("host", ""), new C0343dC("if-match", ""), new C0343dC("if-modified-since", ""), new C0343dC("if-none-match", ""), new C0343dC("if-range", ""), new C0343dC("if-unmodified-since", ""), new C0343dC("last-modified", ""), new C0343dC(ServiceContract.FOLLOW_UP_LINK, ""), new C0343dC("location", ""), new C0343dC("max-forwards", ""), new C0343dC("proxy-authenticate", ""), new C0343dC("proxy-authorization", ""), new C0343dC("range", ""), new C0343dC("referer", ""), new C0343dC("refresh", ""), new C0343dC("retry-after", ""), new C0343dC("server", "")}, 0, dCVarArr, 27, 27);
        System.arraycopy(new C0343dC[]{new C0343dC("set-cookie", ""), new C0343dC("strict-transport-security", ""), new C0343dC("transfer-encoding", ""), new C0343dC("user-agent", ""), new C0343dC("vary", ""), new C0343dC("via", ""), new C0343dC("www-authenticate", "")}, 0, dCVarArr, 54, 7);
        A01 = dCVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i = 0;
        do {
            if (!linkedHashMap.containsKey(dCVarArr[i].A01)) {
                linkedHashMap.put(dCVarArr[i].A01, Integer.valueOf(i));
            }
            i++;
        } while (i < 61);
        A00 = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void A00(ci ciVar) throws IOException {
        int A07 = ciVar.A07();
        for (int i = 0; i < A07; i++) {
            byte A06 = ciVar.A06(i);
            if (A06 >= 65 && A06 <= 90) {
                throw new IOException(AnonymousClass06.A04("PROTOCOL_ERROR response malformed: mixed case name: ", ciVar.A0A()));
            }
        }
    }
}
