package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.cM  reason: case insensitive filesystem */
public final class C0581cM {
    public static final Map A00;
    public static final C0578cJ[] A01;

    static {
        C0603cm cmVar = C0578cJ.A06;
        C0603cm cmVar2 = C0578cJ.A07;
        C0603cm cmVar3 = C0578cJ.A08;
        C0603cm cmVar4 = C0578cJ.A04;
        C0578cJ[] cJVarArr = {new C0578cJ(C0578cJ.A05, OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ(cmVar, TigonRequest.GET), new C0578cJ(cmVar, TigonRequest.POST), new C0578cJ(cmVar2, "/"), new C0578cJ(cmVar2, "/index.html"), new C0578cJ(cmVar3, "http"), new C0578cJ(cmVar3, "https"), new C0578cJ(cmVar4, "200"), new C0578cJ(cmVar4, "204"), new C0578cJ(cmVar4, "206"), new C0578cJ(cmVar4, "304"), new C0578cJ(cmVar4, "400"), new C0578cJ(cmVar4, "404"), new C0578cJ(cmVar4, "500"), new C0578cJ("accept-charset", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("accept-encoding", "gzip, deflate"), new C0578cJ("accept-language", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("accept-ranges", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("accept", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("access-control-allow-origin", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("age", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("allow", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("authorization", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("cache-control", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-disposition", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-encoding", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-language", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-length", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-location", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-range", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("content-type", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("cookie", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("date", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("etag", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("expect", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("expires", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("from", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("host", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("if-match", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("if-modified-since", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("if-none-match", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("if-range", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("if-unmodified-since", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("last-modified", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("link", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("location", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("max-forwards", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("proxy-authenticate", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("proxy-authorization", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("range", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("referer", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("refresh", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("retry-after", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("server", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("set-cookie", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("strict-transport-security", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("transfer-encoding", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("user-agent", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("vary", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("via", OacrConstants.AUTO_SPEECH_DOMAIN), new C0578cJ("www-authenticate", OacrConstants.AUTO_SPEECH_DOMAIN)};
        A01 = cJVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        int i = 0;
        do {
            if (!linkedHashMap.containsKey(cJVarArr[i].A01)) {
                linkedHashMap.put(cJVarArr[i].A01, Integer.valueOf(i));
            }
            i++;
        } while (i < 61);
        A00 = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void A00(C0603cm cmVar) {
        int A05 = cmVar.A05();
        for (int i = 0; i < A05; i++) {
            byte A04 = cmVar.A04(i);
            if (A04 >= 65 && A04 <= 90) {
                throw new IOException(AnonymousClass08.A04("PROTOCOL_ERROR response malformed: mixed case name: ", cmVar.A08()));
            }
        }
    }
}
