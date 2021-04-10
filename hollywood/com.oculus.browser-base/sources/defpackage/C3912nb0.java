package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* renamed from: nb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3912nb0 {

    /* renamed from: a  reason: collision with root package name */
    public Context f10499a;
    public Rect b;
    public Tab c;
    public C0387Gh d;
    public int e;
    public Bitmap f;
    public C3399kb0 g;

    public C3912nb0(Context context, Tab tab, int i, int i2) {
        this.f10499a = context;
        this.c = tab;
        TL0 tl0 = ((WebContentsImpl) tab.l()).M;
        this.b = new Rect(0, i, (int) Math.floor((double) (((float) ((int) Math.ceil((double) tl0.a(tl0.c)))) / tl0.g)), (int) Math.floor((double) ((((float) i2) * tl0.g) + ((float) i))));
    }

    public static void a(C3912nb0 nb0, int i) {
        nb0.e = i;
        C3399kb0 kb0 = nb0.g;
        if (kb0 != null) {
            C3570lb0 lb0 = kb0.b;
            lb0.h = kb0.f10288a.f;
            lb0.d();
        }
    }
}
