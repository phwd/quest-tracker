package defpackage;

import android.content.res.Resources;
import android.view.View;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.messages.MessageBannerView;

/* renamed from: lj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3594lj0 {

    /* renamed from: a  reason: collision with root package name */
    public final C4449qj0 f10366a;
    public final View b;
    public final UH0 c;

    public C3594lj0(MessageBannerView messageBannerView, UH0 uh0, Q31 q31, Resources resources, Runnable runnable, Callback callback) {
        this.b = messageBannerView;
        this.c = uh0;
        ZH0.a(uh0, messageBannerView, new C3252jj0());
        C4449qj0 qj0 = new C4449qj0(uh0, q31, resources, runnable, callback);
        this.f10366a = qj0;
        Objects.requireNonNull(messageBannerView);
        messageBannerView.Q = new C5299vj0(messageBannerView, messageBannerView.getContext(), qj0);
        AbstractC1920bu1.l(messageBannerView, A.f, null, new C3423kj0(runnable));
    }
}
