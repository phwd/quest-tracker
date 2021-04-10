package defpackage;

import java.util.Comparator;
import org.chromium.base.LocaleUtils;

/* renamed from: Ce  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0134Ce implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return LocaleUtils.b((String) obj).compareTo(LocaleUtils.b((String) obj2));
    }
}
