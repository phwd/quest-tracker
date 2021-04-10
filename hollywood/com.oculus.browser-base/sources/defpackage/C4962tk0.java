package defpackage;

import J.N;
import org.chromium.components.messages.MessageWrapper;

/* renamed from: tk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4962tk0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final MessageWrapper f11364a;

    public C4962tk0(MessageWrapper messageWrapper) {
        this.f11364a = messageWrapper;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        MessageWrapper messageWrapper = this.f11364a;
        int intValue = ((Integer) obj).intValue();
        long j = messageWrapper.f10854a;
        if (j != 0) {
            N.M6PkOWjr(j, intValue);
        }
    }
}
