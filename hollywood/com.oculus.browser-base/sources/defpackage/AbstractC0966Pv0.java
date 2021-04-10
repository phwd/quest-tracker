package defpackage;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: Pv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0966Pv0 {

    /* renamed from: a  reason: collision with root package name */
    public final DataSetObservable f8720a = new DataSetObservable();
    public DataSetObserver b;

    public abstract void d(ViewGroup viewGroup, int i, Object obj);

    public abstract int e();

    public int f(Object obj) {
        return -1;
    }

    public abstract Object g(ViewGroup viewGroup, int i);

    public abstract boolean h(View view, Object obj);

    public void i() {
        synchronized (this) {
            DataSetObserver dataSetObserver = this.b;
            if (dataSetObserver != null) {
                dataSetObserver.onChanged();
            }
        }
        this.f8720a.notifyChanged();
    }
}
