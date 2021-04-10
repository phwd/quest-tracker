package X;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1Re  reason: invalid class name */
public class AnonymousClass1Re implements JavaBackedTigonService {
    public final String A00;
    public final AnonymousClass0N1 A01;

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    @DoNotStrip
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        C08340wO r5 = new C08340wO();
        r5.A01(tigonRequest.url());
        AnonymousClass1Lk r7 = null;
        String str = null;
        boolean z = false;
        for (Map.Entry<String, String> entry : tigonRequest.headers().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ("Content-Type".equalsIgnoreCase(key)) {
                str = value;
            } else {
                r5.A02(key, value);
            }
            if ("User-Agent".equalsIgnoreCase(key)) {
                z = true;
            }
        }
        if (bArr != null) {
            r7 = new AnonymousClass1Lk(bArr, str);
        }
        r5.A03(tigonRequest.method(), r7);
        if (!z) {
            String str2 = this.A00;
            C08420wY r0 = r5.A03;
            C08420wY.A00("User-Agent", str2);
            r0.A02("User-Agent", str2);
        }
        AnonymousClass0Mz A002 = this.A01.A00(r5.A00());
        okTigonRequestToken.mActiveCall = A002;
        A002.A03(okTigonRequestToken);
    }

    public AnonymousClass1Re(AnonymousClass0N1 r1, @Nullable String str) {
        this.A01 = r1;
        this.A00 = str;
    }
}
