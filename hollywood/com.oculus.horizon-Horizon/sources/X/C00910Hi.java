package X;

import android.content.Context;
import android.content.IntentFilter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0Hi  reason: invalid class name and case insensitive filesystem */
public class C00910Hi extends AnonymousClass0i9 {
    public final C000602o<String, AnonymousClass0b8> mActionMap;
    @Nullable
    public final Collection<String> mActionRemoved;
    @Nullable
    public IntentFilter mCachedIntentFilter;

    @Override // X.AnonymousClass0i9
    @Nullable
    public final synchronized AnonymousClass0b8 findReceiverForIntent(Context context, String str) {
        return this.mActionMap.get(str);
    }

    @Override // X.AnonymousClass0i9
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
    @Override // X.AnonymousClass0i9
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
        throw new UnsupportedOperationException("Method not decompiled: X.C00910Hi.isActionRemoved(java.lang.String):boolean");
    }

    public C00910Hi(String str, AnonymousClass0b8 r4) {
        C000602o<String, AnonymousClass0b8> r0 = new C000602o<>(1);
        this.mActionMap = r0;
        AnonymousClass0i9.checkNotNull(str);
        AnonymousClass0i9.checkNotNull(r4);
        r0.put(str, r4);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: X.02o<java.lang.String, X.0b8> */
    /* JADX WARN: Multi-variable type inference failed */
    public C00910Hi(Iterator<? extends Map.Entry<String, ? extends AnonymousClass0b8>> it) {
        String str;
        AnonymousClass0i9.checkNotNull(it);
        this.mActionMap = new C000602o<>();
        while (true) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (this.mActionMap.put(entry.getKey(), entry.getValue()) != null) {
                    str = String.format("action '%s' found more than once in action map", entry.getKey());
                    break;
                }
            } else if (this.mActionMap.isEmpty()) {
                str = "Must include an entry for at least one action";
            } else {
                return;
            }
        }
        throw new IllegalArgumentException(str);
    }
}
