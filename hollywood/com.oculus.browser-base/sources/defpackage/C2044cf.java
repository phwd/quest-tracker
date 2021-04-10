package defpackage;

import android.app.AlarmManager;
import android.app.PendingIntent;

/* renamed from: cf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2044cf implements AbstractC1538Ze1 {

    /* renamed from: a  reason: collision with root package name */
    public AlarmManager f9621a;
    public PendingIntent b;

    public C2044cf(AlarmManager alarmManager, PendingIntent pendingIntent) {
        this.f9621a = alarmManager;
        this.b = pendingIntent;
    }

    @Override // defpackage.AbstractC1538Ze1
    public void a(C1477Ye1 ye1) {
        throw new RuntimeException("Periodic tasks should not be scheduled with AlarmManager.");
    }

    @Override // defpackage.AbstractC1538Ze1
    public void b(C1355We1 we1) {
        throw new RuntimeException("One-off tasks should not be scheduled with AlarmManager.");
    }

    @Override // defpackage.AbstractC1538Ze1
    public void c(C1233Ue1 ue1) {
        this.f9621a.setExactAndAllowWhileIdle(0, ue1.f9039a, this.b);
    }
}
