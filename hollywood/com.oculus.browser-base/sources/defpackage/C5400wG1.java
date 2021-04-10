package defpackage;

import java.util.Objects;
import java.util.concurrent.Executor;

/* renamed from: wG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5400wG1 implements AbstractC4043oI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11534a;
    public final Object b = new Object();
    public AbstractC3754mf1 c;

    public C5400wG1(Executor executor, AbstractC3754mf1 mf1) {
        this.f11534a = executor;
        this.c = mf1;
    }

    @Override // defpackage.AbstractC4043oI1
    public final void a(OI1 oi1) {
        Objects.requireNonNull(oi1);
    }
}
