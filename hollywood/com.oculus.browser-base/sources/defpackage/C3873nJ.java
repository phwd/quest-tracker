package defpackage;

import android.util.Pair;
import java.util.Comparator;
import org.chromium.components.omnibox.AutocompleteMatch;

/* renamed from: nJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3873nJ implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        AutocompleteMatch autocompleteMatch = (AutocompleteMatch) ((Pair) obj).first;
        boolean z = autocompleteMatch.c;
        AutocompleteMatch autocompleteMatch2 = (AutocompleteMatch) ((Pair) obj2).first;
        if (z != autocompleteMatch2.c) {
            return z ? -1 : 1;
        }
        return autocompleteMatch2.m - autocompleteMatch.m;
    }
}
