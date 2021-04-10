package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: rn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4632rn1 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f11223a = new HashMap();
    public View b;
    public final ArrayList c = new ArrayList();

    public C4632rn1(View view) {
        this.b = view;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C4632rn1)) {
            return false;
        }
        C4632rn1 rn1 = (C4632rn1) obj;
        return this.b == rn1.b && this.f11223a.equals(rn1.f11223a);
    }

    public int hashCode() {
        return this.f11223a.hashCode() + (this.b.hashCode() * 31);
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("TransitionValues@");
        i.append(Integer.toHexString(hashCode()));
        i.append(":\n");
        StringBuilder j = AbstractC2531fV.j(i.toString(), "    view = ");
        j.append(this.b);
        j.append("\n");
        String f = AbstractC2531fV.f(j.toString(), "    values:");
        for (String str : this.f11223a.keySet()) {
            f = f + "    " + str + ": " + this.f11223a.get(str) + "\n";
        }
        return f;
    }
}
