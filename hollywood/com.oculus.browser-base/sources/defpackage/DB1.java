package defpackage;

import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: DB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DB1 implements FB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ EB1 f7873a;

    public DB1(EB1 eb1) {
        this.f7873a = eb1;
    }

    @Override // defpackage.FB1
    public final void a(BasePendingResult basePendingResult) {
        this.f7873a.c.remove(basePendingResult);
    }
}
