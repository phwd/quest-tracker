package defpackage;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: iE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3004iE implements AbstractC0543Ix {
    public final Set F = Collections.newSetFromMap(new WeakHashMap());

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        for (AbstractC0543Ix ix : this.F) {
            ix.Y(wl0);
        }
    }
}
