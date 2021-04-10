package defpackage;

import org.chromium.chrome.browser.datareduction.DataReductionMainMenuItem;

/* renamed from: IC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class IC extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tm1 f8205a;

    public IC(Tm1 tm1) {
        this.f8205a = tm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tm1 tm1 = this.f8205a;
        int i = DataReductionMainMenuItem.F;
        if (((Boolean) obj).booleanValue()) {
            tm1.notifyEvent("overflow_opened_data_saver_shown");
        }
    }
}
