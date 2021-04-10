package defpackage;

import J.N;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.query_tiles.QueryTile;
import org.chromium.url.GURL;

/* renamed from: sc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4767sc extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0859Oc f11285a;

    public C4767sc(C0859Oc oc) {
        this.f11285a = oc;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        QueryTile queryTile = (QueryTile) obj;
        C2379ed edVar = this.f11285a.H;
        Objects.requireNonNull(edVar);
        int i = 0;
        if (!queryTile.e.isEmpty() || N.M09VlOh_("QueryTilesEnableQueryEditing")) {
            edVar.r(false);
            ((C3909na0) edVar.G).h(TextUtils.concat(queryTile.d, " ").toString());
            edVar.U = SystemClock.elapsedRealtime();
            edVar.T = 2;
            edVar.P.a(edVar.N.b(), edVar.N.i(), edVar.N.l(((C3909na0) edVar.G).f()), ((Oq1) edVar.H).h(), ((Oq1) edVar.H).f(), !((Oq1) edVar.H).m(), queryTile.f10884a, true);
            return;
        }
        int i2 = I31.b;
        AbstractC3364kK0.j("Omnibox.FocusToOpenTimeAnyPopupState3", System.currentTimeMillis() - edVar.Q);
        int i3 = -1;
        int j = edVar.j();
        AutocompleteMatch autocompleteMatch = null;
        while (true) {
            if (i >= j) {
                break;
            }
            autocompleteMatch = edVar.i(i);
            if (autocompleteMatch.f10861a == 28) {
                i3 = i;
                break;
            }
            i++;
        }
        if (autocompleteMatch != null) {
            GURL c = edVar.P.c(i3, autocompleteMatch.hashCode(), edVar.h(), queryTile.d, queryTile.g);
            edVar.p(i3, 1, autocompleteMatch);
            AbstractC0920Pc pc = edVar.G;
            ((C3909na0) pc).N.r(c.h(), 0, edVar.b0, null, null);
        }
    }
}
