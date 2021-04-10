package defpackage;

import android.graphics.Rect;
import android.os.Handler;
import android.util.Size;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ED0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ED0 {

    /* renamed from: a  reason: collision with root package name */
    public Size f7945a;
    public C2952hx[][] b;
    public Rect c = new Rect();
    public Rect d = new Rect();
    public Rect e = new Rect();
    public Runnable f;
    public Runnable g;
    public Handler h = new Handler();
    public Set i = new HashSet();
    public Set j = new HashSet();
    public Set k = new HashSet();

    public ED0(Runnable runnable, Runnable runnable2) {
        this.f = runnable;
        this.g = runnable2;
    }
}
