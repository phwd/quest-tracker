package X;

import android.content.Context;
import android.content.IntentFilter;
import java.util.Collection;
import javax.annotation.Nullable;

/* renamed from: X.0LT  reason: invalid class name */
public class AnonymousClass0LT extends AbstractC02800aq {
    public final AnonymousClass06D<String, AbstractC04990iH> mActionMap;
    @Nullable
    public final Collection<String> mActionRemoved;
    @Nullable
    public IntentFilter mCachedIntentFilter;

    @Override // X.AbstractC02800aq
    @Nullable
    public final synchronized AbstractC04990iH A03(Context context, String str) {
        return this.mActionMap.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r1 == false) goto L_0x000c;
     */
    @Override // X.AbstractC02800aq
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A07(java.lang.String r3) {
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0LT.A07(java.lang.String):boolean");
    }

    public AnonymousClass0LT(String str, AbstractC04990iH r4) {
        AnonymousClass06D<String, AbstractC04990iH> r0 = new AnonymousClass06D<>(1);
        this.mActionMap = r0;
        if (str == null || r4 == null) {
            throw new NullPointerException("Object is null!");
        }
        r0.put(str, r4);
    }

    public AnonymousClass0LT(AbstractC04990iH r5, AbstractC04990iH r6) {
        AnonymousClass06D<String, AbstractC04990iH> r0 = new AnonymousClass06D<>(2);
        this.mActionMap = r0;
        if (r5 != null) {
            r0.put("android.intent.action.USER_UNLOCKED", r5);
            AnonymousClass06D<String, AbstractC04990iH> r02 = this.mActionMap;
            if (r6 != null) {
                r02.put("android.intent.action.USER_PRESENT", r6);
                return;
            }
        }
        throw new NullPointerException("Object is null!");
    }
}
