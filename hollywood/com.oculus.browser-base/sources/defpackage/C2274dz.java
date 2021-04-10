package defpackage;

import org.chromium.chrome.browser.contextmenu.ContextMenuHelper;

/* renamed from: dz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2274dz extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ContextMenuHelper f9822a;

    public C2274dz(ContextMenuHelper contextMenuHelper) {
        this.f9822a = contextMenuHelper;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ContextMenuHelper contextMenuHelper = this.f9822a;
        Integer num = (Integer) obj;
        AbstractC3128iz izVar = contextMenuHelper.d;
        if (izVar != null) {
            contextMenuHelper.m = true;
            izVar.d(num.intValue());
        }
    }
}
