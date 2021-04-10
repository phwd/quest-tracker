package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* renamed from: H71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H71 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f8138a = new LinkedHashSet();
    public int b = -1;
    public int c;
    public final /* synthetic */ I71 d;

    public H71(I71 i71, int i) {
        this.d = i71;
        this.c = i;
    }

    public void a(int i) {
        this.f8138a.add(Integer.valueOf(i));
        if (this.b == -1) {
            this.b = i;
        }
        if (d() > 1) {
            this.d.b0(this.c);
        }
    }

    public boolean b(int i) {
        return this.f8138a.contains(Integer.valueOf(i));
    }

    public List c() {
        return Collections.unmodifiableList(new ArrayList(this.f8138a));
    }

    public int d() {
        return this.f8138a.size();
    }
}
