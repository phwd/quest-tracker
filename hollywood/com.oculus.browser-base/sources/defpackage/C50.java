package defpackage;

import android.view.View;

/* renamed from: C50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C50 implements View.OnClickListener {
    public final F50 F;
    public final C2636g50 G;

    public C50(F50 f50, C2636g50 g50) {
        this.F = f50;
        this.G = g50;
    }

    public void onClick(View view) {
        Tm1 b;
        F50 f50 = this.F;
        C2636g50 g50 = this.G;
        String str = f50.d;
        if (!(str == null || (b = AbstractC3832n50.b()) == null)) {
            char c = 65535;
            switch (str.hashCode()) {
                case -2144335197:
                    if (str.equals("IPH_KeyboardAccessoryPaymentOffer")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1315293096:
                    if (str.equals("IPH_KeyboardAccessoryAddressFilling")) {
                        c = 1;
                        break;
                    }
                    break;
                case 532938391:
                    if (str.equals("IPH_KeyboardAccessoryPasswordFilling")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1572211206:
                    if (str.equals("IPH_KeyboardAccessoryPaymentFilling")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 3:
                    b.notifyEvent("keyboard_accessory_payment_suggestion_accepted");
                    break;
                case 1:
                    b.notifyEvent("keyboard_accessory_address_suggestion_accepted");
                    break;
                case 2:
                    b.notifyEvent("keyboard_accessory_password_suggestion_accepted");
                    break;
            }
        }
        g50.b.onResult(g50);
    }
}
