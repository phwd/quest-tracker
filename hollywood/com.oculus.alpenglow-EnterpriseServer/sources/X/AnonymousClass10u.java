package X;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import com.facebook.tigon.javaservice.JavaBackedTigonService;
import com.facebook.tigon.oktigon.OkTigonRequestToken;
import java.io.IOException;
import java.util.Deque;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.10u  reason: invalid class name */
public class AnonymousClass10u implements JavaBackedTigonService {
    public final String A00;
    public final AnonymousClass0Qs A01;

    @Override // com.facebook.tigon.javaservice.JavaBackedTigonService
    @DoNotStrip
    public void submitHttpRequest(AbstractRequestToken abstractRequestToken, TigonRequest tigonRequest, byte[] bArr) throws IOException {
        OkTigonRequestToken okTigonRequestToken = (OkTigonRequestToken) abstractRequestToken;
        C05710kf r5 = new C05710kf();
        r5.A01(tigonRequest.url());
        AnonymousClass10x r7 = null;
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
            r7 = new AnonymousClass10x(bArr, str);
        }
        r5.A03(tigonRequest.method(), r7);
        if (!z) {
            String str2 = this.A00;
            C06030lq r0 = r5.A03;
            C06030lq.A00("User-Agent", str2);
            r0.A02("User-Agent", str2);
        }
        AnonymousClass0Qd A002 = this.A01.A00(r5.A00());
        okTigonRequestToken.mActiveCall = A002;
        synchronized (A002) {
            if (!A002.A00) {
                A002.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        A002.A03.A00 = C04670hG.A00.A01("response.body().close()");
        C06110ly r52 = A002.A01.A0H;
        AnonymousClass0Qo r4 = new AnonymousClass0Qo(A002, okTigonRequestToken);
        synchronized (r52) {
            Deque<AnonymousClass0Qo> deque = r52.A02;
            if (deque.size() >= 64 || C06110ly.A00(r52, r4) >= 5) {
                r52.A01.add(r4);
            } else {
                deque.add(r4);
                C06110ly.A01(r52).execute(r4);
            }
        }
    }

    public AnonymousClass10u(AnonymousClass0Qs r1, @Nullable String str) {
        this.A01 = r1;
        this.A00 = str;
    }
}
