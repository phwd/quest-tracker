package defpackage;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.chromium.base.Callback;

/* renamed from: sK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4729sK {
    public boolean A;

    /* renamed from: a  reason: collision with root package name */
    public final int f11266a;
    public List b;
    public List c;
    public List d;
    public HashMap e;
    public HashMap f;
    public Set g;
    public List h;
    public TextWatcher i;
    public AbstractC4559rK j;
    public C4286pm k;
    public CharSequence l;
    public CharSequence m;
    public CharSequence n;
    public CharSequence o;
    public CharSequence p;
    public CharSequence q;
    public CharSequence r;
    public CharSequence s;
    public CharSequence t;
    public Callback u;
    public Runnable v;
    public int w;
    public int x;
    public int y;
    public boolean z = true;

    public C4729sK(int i2) {
        this.f11266a = i2;
    }

    public static C4729sK a(CharSequence charSequence, List list, CharSequence charSequence2) {
        C4729sK sKVar = new C4729sK(9);
        sKVar.p = charSequence;
        sKVar.t = charSequence2;
        sKVar.f(list);
        return sKVar;
    }

    public static C4729sK b() {
        return new C4729sK(0);
    }

    public static C4729sK c(int i2, CharSequence charSequence, Set set, TextWatcher textWatcher, AbstractC4559rK rKVar, C4286pm pmVar, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        ArrayList arrayList;
        C4729sK sKVar = new C4729sK(i2);
        if (set == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(set);
        }
        sKVar.h = arrayList;
        sKVar.i = textWatcher;
        sKVar.j = rKVar;
        sKVar.k = pmVar;
        sKVar.m = charSequence3;
        sKVar.l = charSequence2;
        sKVar.p = charSequence;
        sKVar.s = charSequence4;
        return sKVar;
    }

    public boolean d() {
        return !TextUtils.isEmpty(this.l);
    }

    public boolean e() {
        if (!TextUtils.isEmpty(this.n)) {
            this.o = this.n;
            return false;
        } else if (!d() || (!TextUtils.isEmpty(this.s) && TextUtils.getTrimmedLength(this.s) != 0)) {
            AbstractC4559rK rKVar = this.j;
            if (rKVar == null || rKVar.a(this.s)) {
                this.o = null;
                return true;
            }
            this.o = this.m;
            return false;
        } else {
            this.o = this.l;
            return false;
        }
    }

    public void f(List list) {
        this.d = list;
        this.g = new HashSet();
        this.e = new HashMap();
        this.f = new HashMap();
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            this.g.add((String) ((Pair) ((C3749me) this.d.get(i2))).first);
            this.f.put(((CharSequence) ((Pair) ((C3749me) this.d.get(i2))).second).toString(), (String) ((Pair) ((C3749me) this.d.get(i2))).first);
            this.e.put((String) ((Pair) ((C3749me) this.d.get(i2))).first, (CharSequence) ((Pair) ((C3749me) this.d.get(i2))).second);
        }
    }
}
