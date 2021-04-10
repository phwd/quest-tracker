package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.View;

/* renamed from: qR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4403qR implements AbstractC0424Gy0 {

    /* renamed from: a  reason: collision with root package name */
    public final View f11140a;
    public final C4064oS0 b;
    public final Context c;
    public ActionMode d;
    public Rect e;

    public C4403qR(Context context, View view, C4064oS0 os0, ActionMode.Callback callback) {
        this.f11140a = view;
        this.b = os0;
        this.c = context;
    }

    public void a(Rect rect) {
        ActionMode startActionMode;
        this.e = rect;
        ActionMode actionMode = this.d;
        if (actionMode != null) {
            actionMode.invalidateContentRect();
        } else if (actionMode == null && (startActionMode = this.f11140a.startActionMode(new C4232pR(this, null), 1)) != null) {
            AbstractC4518r60.b(this.c, startActionMode);
            this.d = startActionMode;
        }
    }

    @Override // defpackage.AbstractC0424Gy0
    public void b() {
        ActionMode actionMode = this.d;
        if (actionMode != null) {
            actionMode.finish();
            this.d = null;
        }
    }
}
