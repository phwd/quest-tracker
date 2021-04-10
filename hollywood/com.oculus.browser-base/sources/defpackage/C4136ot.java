package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: ot  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4136ot implements AbstractC3739ma1 {
    @Override // defpackage.AbstractC3739ma1
    public AbstractC3568la1 a(TabModel tabModel) {
        if (!AbstractC4772sd1.g() || AbstractC1680aa1.a() == null) {
            return new UK(tabModel);
        }
        return new I71(tabModel);
    }
}
