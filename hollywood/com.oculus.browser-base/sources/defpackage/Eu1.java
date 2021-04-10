package defpackage;

import android.database.DataSetObserver;
import androidx.viewpager.widget.ViewPager;

/* renamed from: Eu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Eu1 extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewPager f7984a;

    public Eu1(ViewPager viewPager) {
        this.f7984a = viewPager;
    }

    public void onChanged() {
        this.f7984a.f();
    }

    public void onInvalidated() {
        this.f7984a.f();
    }
}
