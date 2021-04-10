package defpackage;

import org.chromium.base.UnguessableToken;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;

/* renamed from: hb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2887hb0 implements AD0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3228jb0 f10084a;

    public C2887hb0(C3228jb0 jb0) {
        this.f10084a = jb0;
    }

    @Override // defpackage.AD0
    public void a(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4) {
        C3228jb0 jb0 = this.f10084a;
        ((PlayerCompositorDelegateImpl) jb0.f10216a).a(null, jb0.c, 1.0f, jb0.b, new RunnableC3058ib0(jb0));
    }
}
