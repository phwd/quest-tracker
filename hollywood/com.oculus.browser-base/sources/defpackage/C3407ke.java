package defpackage;

import android.util.Pair;
import java.text.Collator;
import java.util.Comparator;

/* renamed from: ke  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3407ke implements Comparator {
    public final /* synthetic */ Collator F;

    public C3407ke(Collator collator) {
        this.F = collator;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return this.F.compare((CharSequence) ((Pair) ((C3749me) obj)).second, (CharSequence) ((Pair) ((C3749me) obj2)).second);
    }
}
