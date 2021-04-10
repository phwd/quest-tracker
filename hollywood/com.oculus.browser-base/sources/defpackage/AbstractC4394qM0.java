package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.util.SparseArray;
import java.util.Objects;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: qM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4394qM0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11136a;
    public final AbstractC4223pM0 b;

    public AbstractC4394qM0(int i, AbstractC4223pM0 pm0) {
        this.f11136a = i;
        this.b = pm0;
    }

    public abstract void a(int i);

    public void b(int i, AbstractC3197jM0 jm0) {
        Bitmap c;
        AbstractC4223pM0 pm0 = this.b;
        if (pm0 != null) {
            int i2 = this.f11136a;
            ResourceManager resourceManager = (ResourceManager) pm0;
            Objects.requireNonNull(resourceManager);
            if (jm0 != null && (c = jm0.c()) != null) {
                SparseArray sparseArray = (SparseArray) resourceManager.b.get(i2);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    resourceManager.b.put(i2, sparseArray);
                }
                sparseArray.put(i, new G70(resourceManager.c, jm0));
                long j = resourceManager.d;
                if (j != 0) {
                    N.MM7E4tBk(j, resourceManager, i2, i, c, jm0.a().width(), jm0.a().height(), jm0.d());
                }
            }
        }
    }

    public abstract void c(int i);
}
