package defpackage;

import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: uR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5084uR0 {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f11410a;
    public final int b;
    public int c;
    public boolean d;
    public String e;

    public C5084uR0(int i, C1997cK cKVar) {
        this(i, 0, null);
    }

    public void a(C1997cK cKVar) {
        int i = 0;
        while (i < this.f11410a.size() && !TextUtils.equals(((C1997cK) this.f11410a.get(i)).g, cKVar.g)) {
            i++;
        }
        if (i < this.f11410a.size()) {
            this.f11410a.set(i, cKVar);
            if (this.c == i && !cKVar.d()) {
                this.c = -1;
                return;
            }
            return;
        }
        this.f11410a.add(0, cKVar);
        if (cKVar.d()) {
            this.c = 0;
        } else {
            this.c = -1;
        }
    }

    public int b() {
        int i = this.b;
        if (i == 1) {
            return R.string.f58310_resource_name_obfuscated_RES_2131953148;
        }
        if (i == 3) {
            return R.string.f58340_resource_name_obfuscated_RES_2131953151;
        }
        if (i == 4) {
            return R.string.f58330_resource_name_obfuscated_RES_2131953150;
        }
        return 0;
    }

    public C1997cK c(int i) {
        if (this.f11410a.isEmpty() || i < 0 || i >= this.f11410a.size()) {
            return null;
        }
        return (C1997cK) this.f11410a.get(i);
    }

    public C1997cK d() {
        return c(this.c);
    }

    public int e() {
        return this.f11410a.size();
    }

    public boolean f() {
        return this.f11410a.isEmpty();
    }

    public void g(C1997cK cKVar) {
        for (int i = 0; i < this.f11410a.size(); i++) {
            if (this.f11410a.get(i) == cKVar) {
                this.c = i;
                return;
            }
        }
    }

    public void h(int i, Collection collection) {
        this.f11410a.clear();
        if (collection == null || collection.isEmpty()) {
            this.c = -1;
            return;
        }
        this.c = i;
        this.f11410a.addAll(collection);
    }

    public C5084uR0(int i, int i2, Collection collection) {
        this.f11410a = new ArrayList();
        this.d = true;
        this.b = i;
        h(i2, collection);
    }
}
