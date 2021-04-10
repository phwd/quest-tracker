package defpackage;

import android.util.LruCache;

/* renamed from: GH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GH1 extends LruCache {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0684Le0 f8082a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GH1(C0684Le0 le0, int i) {
        super(i);
        this.f8082a = le0;
    }

    @Override // android.util.LruCache
    public final /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        Integer num = (Integer) obj;
        if (z) {
            this.f8082a.h.add(num);
        }
    }
}
