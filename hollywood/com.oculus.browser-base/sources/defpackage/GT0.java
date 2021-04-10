package defpackage;

import J.N;
import android.app.Activity;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: GT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GT0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4448qj f8092a;
    public final M2 b;
    public final Q31 c;
    public final FT0 d;
    public final boolean e;
    public long f;

    public GT0(AbstractC4448qj qjVar, M2 m2, Q31 q31, FT0 ft0, boolean z) {
        this.f8092a = qjVar;
        this.b = m2;
        this.c = q31;
        this.d = ft0;
        this.e = z;
    }

    public boolean a() {
        return (!this.e && N.M09VlOh_("ChromeSharingHub")) && N.M09VlOh_("ChromeSharingHubV15");
    }

    public void b(C2189dU0 du0, C1915bt btVar, int i) {
        if (this.f == 0) {
            this.f = System.currentTimeMillis();
        }
        boolean z = ProfileSyncService.b() != null && ProfileSyncService.b().m();
        FT0 ft0 = this.d;
        AbstractC4448qj qjVar = this.f8092a;
        M2 m2 = this.b;
        Q31 q31 = this.c;
        AT0 at0 = new AT0(this);
        long j = this.f;
        boolean z2 = !this.e && N.M09VlOh_("ChromeSharingHub");
        Objects.requireNonNull(ft0);
        if (btVar.b) {
            LT0.i(du0);
        } else if (!z2 || btVar.f || q31.get() == null) {
            AbstractC3364kK0.g("Sharing.DefaultSharesheetAndroid.Opened", i, 8);
            LT0.j(du0, btVar.f9567a);
        } else {
            AbstractC3364kK0.g("Sharing.SharingHubAndroid.Opened", i, 8);
            CU0 cu0 = new CU0(qjVar, ContextUtils.getApplicationContext().getPackageManager());
            X60 x60 = new X60(Profile.b());
            C2528fT0 ft02 = new C2528fT0();
            Objects.requireNonNull(AppHooks.get());
            new View$OnLayoutChangeListenerC5940zU0(qjVar, m2, q31, cu0, at0, x60, ft02, z, null, Um1.a(Profile.b())).h(du0, btVar, j);
        }
        this.f = 0;
    }

    public void c(Tab tab, boolean z, int i) {
        this.f = System.currentTimeMillis();
        Activity activity = (Activity) tab.i().s0().get();
        boolean a2 = tab.a();
        ArrayList arrayList = new ArrayList(2);
        int i2 = TF0.U;
        if (!tab.isNativePage() && !((VF0) VF0.b()).n && N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "printing.enabled")) {
            arrayList.add(TF0.class);
        }
        int i3 = AS0.U;
        if (N.MfZDVYoo(tab.l())) {
            arrayList.add(AS0.class);
        }
        if (!arrayList.isEmpty()) {
            C3795mt0 mt0 = AbstractC3624lt0.f10382a;
            BT0 bt0 = new BT0(this, tab, i, z, a2);
            Objects.requireNonNull(mt0);
            Object obj = ThreadUtils.f10596a;
            ApplicationStatus.g.b(mt0.c);
            mt0.b.add(activity);
            mt0.f10454a.b(new RunnableC3111it0(mt0, arrayList, activity, bt0));
            return;
        }
        d(tab, i, z, a2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(org.chromium.chrome.browser.tab.Tab r16, int r17, boolean r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 248
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.GT0.d(org.chromium.chrome.browser.tab.Tab, int, boolean, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ad A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void e(org.chromium.ui.base.WindowAndroid r24, org.chromium.content_public.browser.WebContents r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, int r29, boolean r30, boolean r31) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.GT0.e(org.chromium.ui.base.WindowAndroid, org.chromium.content_public.browser.WebContents, java.lang.String, java.lang.String, java.lang.String, int, boolean, boolean):void");
    }
}
