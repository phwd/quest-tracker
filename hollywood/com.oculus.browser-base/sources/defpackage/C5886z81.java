package defpackage;

import android.database.DataSetObserver;
import com.google.android.material.tabs.TabLayout;

/* renamed from: z81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5886z81 extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TabLayout f11726a;

    public C5886z81(TabLayout tabLayout) {
        this.f11726a = tabLayout;
    }

    public void onChanged() {
        this.f11726a.m();
    }

    public void onInvalidated() {
        this.f11726a.m();
    }
}
