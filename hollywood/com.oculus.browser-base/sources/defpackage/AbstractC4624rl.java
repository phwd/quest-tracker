package defpackage;

import java.util.Set;

/* renamed from: rl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4624rl {
    public static Set a(Set set, WT wt) {
        if (set == null) {
            return null;
        }
        C5271va vaVar = new C5271va(set.size());
        for (Object obj : set) {
            vaVar.add(wt.apply(obj));
        }
        return vaVar;
    }
}
