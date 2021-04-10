package defpackage;

import android.util.Pair;
import java.text.Collator;
import java.util.Comparator;

/* renamed from: je  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3236je implements Comparator {
    public final /* synthetic */ Collator F;

    public C3236je(Collator collator) {
        this.F = collator;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        C3749me meVar = (C3749me) obj;
        C3749me meVar2 = (C3749me) obj2;
        int compare = this.F.compare((CharSequence) ((Pair) meVar).second, (CharSequence) ((Pair) meVar2).second);
        return compare == 0 ? ((String) ((Pair) meVar).first).compareTo((String) ((Pair) meVar2).first) : compare;
    }
}
