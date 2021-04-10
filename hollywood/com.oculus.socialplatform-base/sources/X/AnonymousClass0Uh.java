package X;

import android.content.Context;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0Uh  reason: invalid class name */
public class AnonymousClass0Uh extends AbstractC02970kf {
    public final C000502v<String, AbstractC02700jf> mActionMap;
    @Nullable
    public Collection<String> mActionRemoved;
    @Nullable
    public IntentFilter mCachedIntentFilter;

    @Override // X.AbstractC02970kf
    @Nullable
    public final synchronized AbstractC02700jf findReceiverForIntent(Context context, String str) {
        return this.mActionMap.get(str);
    }

    @Override // X.AbstractC02970kf
    public final synchronized IntentFilter getIntentFilter() {
        if (this.mCachedIntentFilter == null) {
            this.mCachedIntentFilter = new IntentFilter();
            int size = this.mActionMap.size();
            for (int i = 0; i < size; i++) {
                this.mCachedIntentFilter.addAction((String) this.mActionMap.A02[i << 1]);
            }
        }
        return this.mCachedIntentFilter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r1 == false) goto L_0x000c;
     */
    @Override // X.AbstractC02970kf
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean isActionRemoved(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Collection<java.lang.String> r0 = r2.mActionRemoved     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000c
            boolean r1 = r0.contains(r3)     // Catch:{ all -> 0x000f }
            r0 = 1
            if (r1 != 0) goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            monitor-exit(r2)
            return r0
        L_0x000f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Uh.isActionRemoved(java.lang.String):boolean");
    }

    public final synchronized boolean removeActionAndCheckEmpty(String str) {
        this.mActionMap.remove(str);
        Collection collection = this.mActionRemoved;
        if (collection == null) {
            collection = new ArrayList();
            this.mActionRemoved = collection;
        }
        if (!collection.contains(str)) {
            this.mActionRemoved.add(str);
        }
        this.mCachedIntentFilter = null;
        return this.mActionMap.isEmpty();
    }

    public AnonymousClass0Uh(String str, AbstractC02700jf r4) {
        C000502v<String, AbstractC02700jf> r0 = new C000502v<>(1);
        this.mActionMap = r0;
        AbstractC02970kf.checkNotNull(str);
        AbstractC02970kf.checkNotNull(r4);
        r0.put(str, r4);
    }

    public AnonymousClass0Uh(String str, AbstractC02700jf r4, String str2, AbstractC02700jf r6) {
        C000502v<String, AbstractC02700jf> r0 = new C000502v<>(2);
        this.mActionMap = r0;
        AbstractC02970kf.checkNotNull(str);
        AbstractC02970kf.checkNotNull(r4);
        r0.put(str, r4);
        C000502v<String, AbstractC02700jf> r02 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str2);
        AbstractC02970kf.checkNotNull(r6);
        r02.put(str2, r6);
    }

    public AnonymousClass0Uh(String str, AbstractC02700jf r4, String str2, AbstractC02700jf r6, String str3, AbstractC02700jf r8) {
        C000502v<String, AbstractC02700jf> r0 = new C000502v<>(3);
        this.mActionMap = r0;
        AbstractC02970kf.checkNotNull(str);
        AbstractC02970kf.checkNotNull(r4);
        r0.put(str, r4);
        C000502v<String, AbstractC02700jf> r02 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str2);
        AbstractC02970kf.checkNotNull(r6);
        r02.put(str2, r6);
        C000502v<String, AbstractC02700jf> r03 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str3);
        AbstractC02970kf.checkNotNull(r8);
        r03.put(str3, r8);
    }

    public AnonymousClass0Uh(String str, AbstractC02700jf r4, String str2, AbstractC02700jf r6, String str3, AbstractC02700jf r8, String str4, AbstractC02700jf r10) {
        C000502v<String, AbstractC02700jf> r0 = new C000502v<>(4);
        this.mActionMap = r0;
        AbstractC02970kf.checkNotNull(str);
        AbstractC02970kf.checkNotNull(r4);
        r0.put(str, r4);
        C000502v<String, AbstractC02700jf> r02 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str2);
        AbstractC02970kf.checkNotNull(r6);
        r02.put(str2, r6);
        C000502v<String, AbstractC02700jf> r03 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str3);
        AbstractC02970kf.checkNotNull(r8);
        r03.put(str3, r8);
        C000502v<String, AbstractC02700jf> r04 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str4);
        AbstractC02970kf.checkNotNull(r10);
        r04.put(str4, r10);
    }

    public AnonymousClass0Uh(String str, AbstractC02700jf r4, String str2, AbstractC02700jf r6, String str3, AbstractC02700jf r8, String str4, AbstractC02700jf r10, String str5, AbstractC02700jf r12) {
        C000502v<String, AbstractC02700jf> r0 = new C000502v<>(5);
        this.mActionMap = r0;
        AbstractC02970kf.checkNotNull(str);
        AbstractC02970kf.checkNotNull(r4);
        r0.put(str, r4);
        C000502v<String, AbstractC02700jf> r02 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str2);
        AbstractC02970kf.checkNotNull(r6);
        r02.put(str2, r6);
        C000502v<String, AbstractC02700jf> r03 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str3);
        AbstractC02970kf.checkNotNull(r8);
        r03.put(str3, r8);
        C000502v<String, AbstractC02700jf> r04 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str4);
        AbstractC02970kf.checkNotNull(r10);
        r04.put(str4, r10);
        C000502v<String, AbstractC02700jf> r05 = this.mActionMap;
        AbstractC02970kf.checkNotNull(str5);
        AbstractC02970kf.checkNotNull(r12);
        r05.put(str5, r12);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: X.02v<java.lang.String, X.0jf> */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0Uh(Iterator<? extends Map.Entry<String, ? extends AbstractC02700jf>> it) {
        AbstractC02970kf.checkNotNull(it);
        this.mActionMap = new C000502v<>();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (this.mActionMap.put(entry.getKey(), entry.getValue()) != null) {
                throw new IllegalArgumentException(String.format("action '%s' found more than once in action map", entry.getKey()));
            }
        }
        if (this.mActionMap.isEmpty()) {
            throw new IllegalArgumentException("Must include an entry for at least one action");
        }
    }
}
