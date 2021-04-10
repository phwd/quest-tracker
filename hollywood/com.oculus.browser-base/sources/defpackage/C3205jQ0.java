package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: jQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3205jQ0 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f10204a;
    public final ViewGroup b;
    public final C3889nQ0 c;

    public C3205jQ0(Context context, ViewGroup viewGroup) {
        UH0 uh0 = new UH0(AbstractC4060oQ0.r);
        this.f10204a = uh0;
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.search_box);
        this.b = viewGroup2;
        this.c = new C3889nQ0(context, uh0, viewGroup2);
    }
}
