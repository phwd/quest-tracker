package X;

import android.app.Application;
import android.text.TextUtils;
import com.facebook.proxygen.TraceFieldType;
import org.json.JSONObject;

/* renamed from: X.x1  reason: case insensitive filesystem */
public final class C1307x1 extends XA {
    public static final XH A03 = new XH();
    public int A00;
    public int A01;
    public String A02;

    public C1307x1() {
        super(null, 1, null);
        Application A002 = BX.A00();
        C0514bB.A01(A002, "ApplicationHolder.get()");
        this.A02 = A002.getPackageName();
    }

    @Override // X.XA
    public final void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "jsonObject");
        if (this.A01 != 0 || !TextUtils.isEmpty(null) || !TextUtils.isEmpty(null)) {
            if (!TextUtils.isEmpty(this.A02)) {
                jSONObject.put("package", this.A02);
            }
            int i = this.A01;
            if (i != 0) {
                jSONObject.put("res", i);
            }
            int i2 = this.A00;
            if (i2 != 0) {
                jSONObject.put("background-res", i2);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("asset", (Object) null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put("background-asset", (Object) null);
            }
            if (!TextUtils.isEmpty(null)) {
                jSONObject.put(TraceFieldType.Uri, (Object) null);
                return;
            }
            return;
        }
        throw new IllegalAccessError("Image incomplete, make sure you have a package and an image resource or image asset");
    }
}
