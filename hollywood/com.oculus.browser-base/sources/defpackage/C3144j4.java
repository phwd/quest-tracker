package defpackage;

import J.N;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.AllPasswordsBottomSheetBridge;

/* renamed from: j4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3144j4 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4511r4 f10184a;

    public C3144j4(C4511r4 r4Var) {
        this.f10184a = r4Var;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4511r4 r4Var = this.f10184a;
        Integer num = (Integer) obj;
        UH0 uh0 = r4Var.b;
        QH0 qh0 = AbstractC4852t4.f11319a;
        if (uh0.h(qh0)) {
            AbstractC3364kK0.g("PasswordManager.AllPasswordsBottomSheet.UserAction", 1, 3);
            r4Var.b.j(qh0, false);
            long j = ((AllPasswordsBottomSheetBridge) r4Var.f11181a).f10688a;
            if (j != 0) {
                N.M0obhfYM(j);
            }
        }
    }
}
