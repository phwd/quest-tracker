package defpackage;

import J.N;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: P3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class P3 implements Runnable {
    public final S3 F;
    public final C2892hd G;
    public final Callback H;

    public P3(S3 s3, C2892hd hdVar, Callback callback) {
        this.F = s3;
        this.G = hdVar;
        this.H = callback;
    }

    public void run() {
        S3 s3 = this.F;
        C2892hd hdVar = this.G;
        Callback callback = this.H;
        s3.q = true;
        PersonalDataManager c = PersonalDataManager.c();
        Objects.requireNonNull(c);
        Object obj = ThreadUtils.f10596a;
        N.MCBooW5W(c.b);
        PersonalDataManager.AutofillProfile autofillProfile = s3.t;
        autofillProfile.m = s3.l.s.toString();
        autofillProfile.n = s3.n.s.toString();
        C4729sK sKVar = s3.o;
        if (sKVar != null) {
            autofillProfile.o = sKVar.s.toString();
        }
        C4729sK sKVar2 = s3.m;
        if (sKVar2 != null) {
            autofillProfile.d = sKVar2.s.toString();
        }
        autofillProfile.q = s3.k.f10615a;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < s3.p.size(); i++) {
            C3578le leVar = (C3578le) s3.p.get(i);
            hashSet.add(Integer.valueOf(leVar.f10359a));
            int i2 = leVar.f10359a;
            if (i2 != 0) {
                S3.h(autofillProfile, i2, ((C4729sK) s3.d.get(Integer.valueOf(i2))).s);
            }
        }
        for (Map.Entry entry : s3.d.entrySet()) {
            if (!hashSet.contains(entry.getKey())) {
                S3.h(autofillProfile, ((Integer) entry.getKey()).intValue(), "");
            }
        }
        if (s3.h) {
            PersonalDataManager c2 = PersonalDataManager.c();
            PersonalDataManager.AutofillProfile autofillProfile2 = s3.t;
            Objects.requireNonNull(c2);
            Object obj2 = ThreadUtils.f10596a;
            autofillProfile.f10613a = N.McRRW$S3(c2.b, c2, autofillProfile2);
        }
        if (autofillProfile.getGUID().isEmpty()) {
            autofillProfile.f10613a = UUID.randomUUID().toString();
        }
        autofillProfile.c = true;
        if (s3.g) {
            hdVar.p(s3.t);
        } else {
            hdVar.p(s3.t);
        }
        callback.onResult(hdVar);
    }
}
