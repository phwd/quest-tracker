package defpackage;

import android.util.SparseArray;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ua  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1219Ua implements J00 {

    /* renamed from: a  reason: collision with root package name */
    public final C1280Va f9033a;

    public C1219Ua(C1280Va va, AbstractC1158Ta ta) {
        this.f9033a = va;
    }

    @Override // defpackage.J00
    public void a() {
        SparseArray sparseArray = this.f9033a.f9093a;
        for (int i = 0; i < sparseArray.size(); i++) {
            Tab m = ((AbstractC1097Sa) sparseArray.valueAt(i)).m();
            if (m != null && m.a()) {
                this.f9033a.d(m.getId());
            }
        }
    }

    @Override // defpackage.J00
    public boolean b() {
        SparseArray sparseArray = this.f9033a.f9093a;
        for (int i = 0; i < sparseArray.size(); i++) {
            Tab m = ((AbstractC1097Sa) sparseArray.valueAt(i)).m();
            if (m != null && m.a()) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.J00
    public boolean isActiveModel() {
        return false;
    }
}
