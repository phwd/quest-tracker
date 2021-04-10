package defpackage;

import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: ji1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3250ji1 implements SZ {

    /* renamed from: a  reason: collision with root package name */
    public final C3592li1 f10230a;

    public C3250ji1(C3592li1 li1) {
        this.f10230a = li1;
    }

    @Override // defpackage.SZ
    public void a(QueryTile queryTile, Callback callback) {
        C3592li1 li1 = this.f10230a;
        Objects.requireNonNull(li1);
        li1.d.a(queryTile, new C3421ki1(li1, System.currentTimeMillis(), callback));
    }
}
