package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;
import javax.annotation.Nullable;

public class X9 implements JavaBackedTigonService {
    public final String A00;
    public final LD A01;

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    @DoNotStrip
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        C0363dk dkVar = new C0363dk();
        dkVar.A01(tigonRequest.url());
        X8 x8 = null;
        String str = null;
        boolean z = false;
        for (Map.Entry<String, String> entry : tigonRequest.headers().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (HttpRequestMultipart.CONTENT_TYPE.equalsIgnoreCase(key)) {
                str = value;
            } else {
                dkVar.A02(key, value);
            }
            if ("User-Agent".equalsIgnoreCase(key)) {
                z = true;
            }
        }
        if (bArr != null) {
            x8 = new X8(bArr, str);
        }
        dkVar.A03(tigonRequest.method(), x8);
        if (!z) {
            String str2 = this.A00;
            C0370dt dtVar = dkVar.A03;
            C0370dt.A00("User-Agent", str2);
            dtVar.A02("User-Agent", str2);
        }
        LB A002 = this.A01.A00(dkVar.A00());
        okTigonRequestToken.mActiveCall = A002;
        synchronized (A002) {
            if (!A002.A00) {
                A002.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        A002.A03.A00 = C0324cr.A00.A01("response.body().close()");
        C0373dw dwVar = A002.A01.A0J;
        LC lc = new LC(A002, okTigonRequestToken);
        synchronized (dwVar) {
            Deque<LC> deque = dwVar.A02;
            if (deque.size() >= 64 || C0373dw.A00(dwVar, lc) >= 5) {
                dwVar.A01.add(lc);
            } else {
                deque.add(lc);
                C0373dw.A01(dwVar).execute(lc);
            }
        }
    }

    public X9(LD ld, @Nullable String str) {
        this.A01 = ld;
        this.A00 = str;
    }
}
