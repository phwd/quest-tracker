package defpackage;

import android.accounts.Account;
import android.view.View;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: mv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3800mv {

    /* renamed from: a  reason: collision with root package name */
    public final Account f10459a;
    public final Set b;
    public final Set c;
    public final Map d;
    public final String e;
    public final String f;
    public final C5092uV0 g;
    public final boolean h;
    public Integer i;

    public C3800mv(Account account, Set set, Map map, int i2, View view, String str, String str2, C5092uV0 uv0, boolean z) {
        this.f10459a = account;
        Set emptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.b = emptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.d = map;
        this.e = str;
        this.f = str2;
        this.g = uv0;
        this.h = z;
        HashSet hashSet = new HashSet(emptySet);
        Iterator it = map.values().iterator();
        if (!it.hasNext()) {
            this.c = Collections.unmodifiableSet(hashSet);
            return;
        }
        C5859z.a(it.next());
        Objects.requireNonNull(null);
        throw null;
    }
}
