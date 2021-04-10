package defpackage;

import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: jE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3174jE extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3858nE f10197a;

    public C3174jE(C3858nE nEVar) {
        this.f10197a = nEVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3858nE nEVar = this.f10197a;
        Objects.requireNonNull(nEVar);
        PostTask.c(Zo1.c, new RunnableC3345kE(nEVar, (Boolean) obj));
    }
}
