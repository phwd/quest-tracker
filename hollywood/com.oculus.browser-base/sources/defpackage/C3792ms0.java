package defpackage;

import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: ms0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3792ms0 implements SZ {

    /* renamed from: a  reason: collision with root package name */
    public final C4305ps0 f10453a;

    public C3792ms0(C4305ps0 ps0) {
        this.f10453a = ps0;
    }

    @Override // defpackage.SZ
    public void a(QueryTile queryTile, Callback callback) {
        C4305ps0 ps0 = this.f10453a;
        if (ps0.f == null) {
            ps0.f = Integer.valueOf(ps0.f11094a.getResources().getDimensionPixelSize(R.dimen.f26100_resource_name_obfuscated_RES_2131166229));
        }
        if (queryTile.f.isEmpty()) {
            PostTask.b(Zo1.f9374a, new RunnableC3963ns0(callback), 0);
            return;
        }
        LZ lz = new LZ((String) queryTile.f.get(0), "QueryTiles", ps0.f.intValue(), ps0.f.intValue(), 1440);
        if (ps0.e == null) {
            Profile b = Profile.b();
            ps0.e = QZ.a(3, PZ.a(b), CV.f7814a, 512000);
        }
        ps0.e.d(lz, new C4134os0(callback));
    }
}
