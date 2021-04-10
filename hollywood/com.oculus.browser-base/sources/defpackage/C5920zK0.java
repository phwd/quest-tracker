package defpackage;

import android.database.Observable;

/* renamed from: zK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5920zK0 extends Observable {
    public boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }

    public void b() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AK0) ((Observable) this).mObservers.get(size)).a();
        }
    }

    public void c(int i, int i2) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AK0) ((Observable) this).mObservers.get(size)).e(i, i2, 1);
        }
    }

    public void d(int i, int i2, Object obj) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AK0) ((Observable) this).mObservers.get(size)).c(i, i2, obj);
        }
    }

    public void e(int i, int i2) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AK0) ((Observable) this).mObservers.get(size)).d(i, i2);
        }
    }

    public void f(int i, int i2) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AK0) ((Observable) this).mObservers.get(size)).f(i, i2);
        }
    }
}
