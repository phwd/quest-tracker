package defpackage;

import org.chromium.base.ThreadUtils;

/* renamed from: Xe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1414Xe implements AbstractC0804Ne {

    /* renamed from: a  reason: collision with root package name */
    public final C1475Ye f9223a;

    public C1414Xe(C1475Ye ye) {
        this.f9223a = ye;
    }

    @Override // defpackage.AbstractC0804Ne
    public void a(boolean z) {
        ThreadUtils.h(new RunnableC1353We(this, z));
    }
}
