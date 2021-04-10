package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.Address;
import org.chromium.components.payments.PayerData;

/* renamed from: P5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class P5 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Z5 f8664a;

    public P5(Z5 z5) {
        this.f8664a = z5;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Address address;
        String str;
        String str2;
        String str3;
        String str4;
        Z5 z5 = this.f8664a;
        T5 t5 = (T5) obj;
        Objects.requireNonNull(z5);
        Object obj2 = ThreadUtils.f10596a;
        int i = t5.f8937a;
        Intent intent = t5.b;
        Kx1 kx1 = z5.z;
        if (intent == null) {
            AbstractC2531fV.q(z5, "Payment app returned an invalid result. Missing intent data.", z5.l);
        } else if (intent.getExtras() == null) {
            AbstractC2531fV.q(z5, "Payment app returned an invalid result. Missing intent extras.", z5.l);
        } else if (i == 0) {
            AbstractC2531fV.q(z5, "Payment app returned RESULT_CANCELED code. This is how payment apps can close their activity programmatically.", z5.l);
        } else if (i != -1) {
            AbstractC2531fV.q(z5, String.format(Locale.US, "Payment app returned unrecognized activity result %d.", Integer.valueOf(i)), z5.l);
        } else {
            String string = intent.getExtras().getString("details");
            if (string == null) {
                string = intent.getExtras().getString("instrumentDetails");
            }
            if (TextUtils.isEmpty(string)) {
                AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"details\".", z5.l);
                return;
            }
            String string2 = intent.getExtras().getString("methodName");
            if (TextUtils.isEmpty(string2)) {
                AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"methodName\".", z5.l);
            } else if (kx1 == null) {
                ((EA0) z5.t).A(string2, string, new PayerData());
                z5.t = null;
            } else {
                if (kx1.d) {
                    Bundle bundle = intent.getExtras().getBundle("shippingAddress");
                    if (bundle == null || bundle.isEmpty()) {
                        AbstractC2531fV.q(z5, "Payment app returned invalid shipping address in response.", z5.l);
                        return;
                    }
                    address = Address.a(bundle);
                } else {
                    address = new Address();
                }
                if (kx1.f8396a) {
                    str = intent.getExtras().getString("payerName", "");
                } else {
                    str = "";
                }
                if (!kx1.f8396a || !TextUtils.isEmpty(str)) {
                    if (kx1.c) {
                        str2 = intent.getExtras().getString("payerPhone", "");
                    } else {
                        str2 = "";
                    }
                    if (!kx1.c || !TextUtils.isEmpty(str2)) {
                        if (kx1.b) {
                            str3 = intent.getExtras().getString("payerEmail", "");
                        } else {
                            str3 = "";
                        }
                        if (!kx1.b || !TextUtils.isEmpty(str3)) {
                            if (kx1.d) {
                                str4 = intent.getExtras().getString("shippingOptionId", "");
                            } else {
                                str4 = "";
                            }
                            if (!kx1.d || !TextUtils.isEmpty(str4)) {
                                ((EA0) z5.t).A(string2, string, new PayerData(str, str2, str3, address, str4));
                                z5.t = null;
                                return;
                            }
                            AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"shipping option\".", z5.l);
                            return;
                        }
                        AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"payerEmail\".", z5.l);
                        return;
                    }
                    AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"payerPhone\".", z5.l);
                    return;
                }
                AbstractC2531fV.q(z5, "Payment app returned invalid response. Missing field \"payerName\".", z5.l);
            }
        }
    }
}
