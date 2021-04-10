package defpackage;

import java.util.Objects;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: ii1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3079ii1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3592li1 f10156a;

    public C3079ii1(C3592li1 li1) {
        this.f10156a = li1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3592li1 li1 = this.f10156a;
        Objects.requireNonNull(li1);
        AbstractC3535lK0.a("Search." + li1.b.f8779a + ".Tile.Clicked");
        li1.c.onResult((QueryTile) obj);
    }
}
