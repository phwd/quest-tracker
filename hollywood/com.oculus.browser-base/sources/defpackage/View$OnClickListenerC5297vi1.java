package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.components.query_tiles.QueryTile;

/* renamed from: vi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5297vi1 implements View.OnClickListener {
    public final UH0 F;
    public final QueryTile G;

    public View$OnClickListenerC5297vi1(UH0 uh0, QueryTile queryTile) {
        this.F = uh0;
        this.G = queryTile;
    }

    public void onClick(View view) {
        UH0 uh0 = this.F;
        ((Callback) uh0.g(AbstractC1704ai1.f9445a)).onResult(this.G);
    }
}
