package defpackage;

import J.N;
import java.util.Objects;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: oy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4151oy implements Runnable {
    public final C4663ry F;
    public final C5960zd G;
    public final C4729sK H;
    public final C4729sK I;

    /* renamed from: J  reason: collision with root package name */
    public final C4729sK f11039J;
    public final Callback K;

    public RunnableC4151oy(C4663ry ryVar, C5960zd zdVar, C4729sK sKVar, C4729sK sKVar2, C4729sK sKVar3, Callback callback) {
        this.F = ryVar;
        this.G = zdVar;
        this.H = sKVar;
        this.I = sKVar2;
        this.f11039J = sKVar3;
        this.K = callback;
    }

    public void run() {
        String str;
        String str2;
        C4663ry ryVar = this.F;
        C5960zd zdVar = this.G;
        C4729sK sKVar = this.H;
        C4729sK sKVar2 = this.I;
        C4729sK sKVar3 = this.f11039J;
        Callback callback = this.K;
        Objects.requireNonNull(ryVar);
        PersonalDataManager.AutofillProfile autofillProfile = zdVar.k;
        String str3 = null;
        if (sKVar != null) {
            str = sKVar.s.toString();
            autofillProfile.e = str;
        } else {
            str = null;
        }
        if (sKVar2 != null) {
            str2 = sKVar2.s.toString();
            autofillProfile.n = str2;
        } else {
            str2 = null;
        }
        if (sKVar3 != null) {
            str3 = sKVar3.s.toString();
            autofillProfile.o = str3;
        }
        if (ryVar.f) {
            PersonalDataManager c = PersonalDataManager.c();
            Objects.requireNonNull(c);
            Object obj = ThreadUtils.f10596a;
            autofillProfile.f10613a = N.McRRW$S3(c.b, c, autofillProfile);
        }
        if (autofillProfile.getGUID().isEmpty()) {
            autofillProfile.f10613a = UUID.randomUUID().toString();
        }
        autofillProfile.c = true;
        zdVar.h(autofillProfile.getGUID(), str, str2, str3);
        zdVar.i(0);
        callback.onResult(zdVar);
    }
}
