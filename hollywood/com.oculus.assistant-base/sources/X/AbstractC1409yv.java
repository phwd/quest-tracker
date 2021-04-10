package X;

import android.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.yv  reason: case insensitive filesystem */
public abstract class AbstractC1409yv implements AnonymousClass8U {
    public static final JSONObject A01 = new JSONObject();
    public final List A00 = Arrays.asList(new C1405yr(), new C1406ys());

    public void A01(AnonymousClass8F r4, String str) {
        JSONObject jSONObject;
        if (str != null) {
            jSONObject = new JSONObject(str);
        } else {
            try {
                jSONObject = A01;
            } catch (JSONException e) {
                C0139Dd.A0L("CustomOutputCallback", AnonymousClass08.A04("Error, invalid response format from ", r4.A02), e);
                return;
            }
        }
        A02(r4, jSONObject);
    }

    @Override // X.AnonymousClass8U
    public void A1I(AnonymousClass8F r5, AnonymousClass8H r6) {
        boolean z;
        String str;
        Iterator it = this.A00.iterator();
        loop0:
        while (true) {
            z = true;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                AbstractC0437Yg yg = (AbstractC0437Yg) it.next();
                if (yg.A54(r5)) {
                    boolean A1q = yg.A1q(r5, r6);
                    if (!z || !A1q) {
                        z = false;
                    }
                }
            }
        }
        if (z) {
            byte[] bArr = r6.A08;
            if (bArr != null) {
                str = new String(Base64.decode(bArr, 0), StandardCharsets.UTF_8);
            } else {
                str = null;
            }
            A01(r5, str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x030e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A02(X.AnonymousClass8F r10, org.json.JSONObject r11) {
        /*
        // Method dump skipped, instructions count: 1852
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1409yv.A02(X.8F, org.json.JSONObject):void");
    }
}
