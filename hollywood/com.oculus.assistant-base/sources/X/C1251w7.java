package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.w7  reason: case insensitive filesystem */
public final class C1251w7 implements W8 {
    public final /* synthetic */ WE A00;

    public C1251w7(WE we) {
        this.A00 = we;
    }

    @Override // X.W8
    public final void A3w() {
        String str;
        WE we = this.A00;
        ZC A002 = we.A01.A00();
        if (A002 == null) {
            str = "PapayaManager: Oculus Assistant credentials received are null";
        } else {
            String str2 = A002.A00;
            if (str2 == null) {
                str = "PapayaManager: Oculus Assistant access token is null";
            } else {
                we.A03.set(str2);
                return;
            }
        }
        onError(new RuntimeException(str));
    }

    @Override // X.W8
    public final void onError(Exception exc) {
        this.A00.A03.set(OacrConstants.AUTO_SPEECH_DOMAIN);
    }
}
