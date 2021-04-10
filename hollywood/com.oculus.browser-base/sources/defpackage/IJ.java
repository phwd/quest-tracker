package defpackage;

import J.N;
import android.util.SparseArray;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: IJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IJ extends AbstractC4394qM0 {
    public final SparseArray c = new SparseArray();

    public IJ(int i, AbstractC4223pM0 pm0) {
        super(i, pm0);
    }

    @Override // defpackage.AbstractC4394qM0
    public void a(int i) {
        HJ hj = (HJ) this.c.get(i);
        if (hj != null && hj.e()) {
            b(i, hj);
        }
    }

    @Override // defpackage.AbstractC4394qM0
    public void c(int i) {
    }

    public void d(int i) {
        this.c.remove(i);
        AbstractC4223pM0 pm0 = this.b;
        if (pm0 != null) {
            int i2 = this.f11136a;
            ResourceManager resourceManager = (ResourceManager) pm0;
            if (i2 == 2 || i2 == 1) {
                long j = resourceManager.d;
                if (j != 0) {
                    N.MxwZmAzJ(j, resourceManager, i2, i);
                }
            }
        }
    }
}
