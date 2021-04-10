package defpackage;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import org.chromium.chrome.browser.tasks.TasksView;

/* renamed from: Cf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0139Cf1 implements H7 {

    /* renamed from: a  reason: collision with root package name */
    public final TasksView f7827a;
    public final int b;
    public final int c;
    public final View d;

    public C0139Cf1(TasksView tasksView, int i, int i2, View view) {
        this.f7827a = tasksView;
        this.b = i;
        this.c = i2;
        this.d = view;
    }

    @Override // defpackage.F7
    public void a(AppBarLayout appBarLayout, int i) {
        this.f7827a.A(this.b, this.c, this.d, i);
    }
}
