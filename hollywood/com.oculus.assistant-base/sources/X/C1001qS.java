package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.util.Map;

/* renamed from: X.qS  reason: case insensitive filesystem */
public class C1001qS implements JavaBackedTigonService {
    public final String A00;
    public final String A01;
    public final C0548bl A02;

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        C0550bn bnVar = new C0550bn();
        String url = tigonRequest.url();
        String str = this.A00;
        if (str != null) {
            url = AnonymousClass08.A07(url, "?", "access_token", "=", str);
        }
        bnVar.A01(url);
        C1002qT qTVar = null;
        String str2 = null;
        boolean z = false;
        for (Map.Entry entry : tigonRequest.headers().entrySet()) {
            String str3 = (String) entry.getKey();
            String str4 = (String) entry.getValue();
            if (HttpRequestMultipart.CONTENT_TYPE.equalsIgnoreCase(str3)) {
                str2 = str4;
            } else {
                bnVar.A02(str3, str4);
            }
            if (HttpRequestMultipart.USER_AGENT.equalsIgnoreCase(str3)) {
                z = true;
            }
        }
        if (bArr != null) {
            qTVar = new C1002qT(bArr, str2);
        }
        bnVar.A03(tigonRequest.method(), qTVar);
        if (!z) {
            String str5 = this.A01;
            C0541be beVar = bnVar.A03;
            C0541be.A00(HttpRequestMultipart.USER_AGENT, str5);
            beVar.A02(HttpRequestMultipart.USER_AGENT, str5);
        }
        C1146tm tmVar = new C1146tm(this.A02, bnVar.A00(), false);
        okTigonRequestToken.mActiveCall = tmVar;
        tmVar.A02(okTigonRequestToken);
    }

    public C1001qS(C0548bl blVar, String str, String str2) {
        this.A02 = blVar;
        this.A00 = str;
        this.A01 = str2;
    }
}
