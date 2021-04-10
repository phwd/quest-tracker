package X;

import android.content.Context;
import android.content.IntentFilter;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class Fa extends XR {
    public final C00062o<String, AbstractC0385gk> mActionMap;
    @Nullable
    public final Collection<String> mActionRemoved;
    @Nullable
    public IntentFilter mCachedIntentFilter;

    @Override // X.XR
    @Nullable
    public final synchronized AbstractC0385gk A03(Context context, String str) {
        return this.mActionMap.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r1 == false) goto L_0x000c;
     */
    @Override // X.XR
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
        throw new UnsupportedOperationException("Method not decompiled: X.Fa.A07(java.lang.String):boolean");
    }

    public Fa(String str, AbstractC0385gk gkVar) {
        C00062o<String, AbstractC0385gk> r0 = new C00062o<>(1);
        this.mActionMap = r0;
        XR.A00(str);
        XR.A00(gkVar);
        r0.put(str, gkVar);
    }

    public Fa(AbstractC0385gk gkVar, AbstractC0385gk gkVar2) {
        C00062o<String, AbstractC0385gk> r0 = new C00062o<>(2);
        this.mActionMap = r0;
        XR.A00(gkVar);
        r0.put(ServiceContract.BROADCAST_LOGIN, gkVar);
        C00062o<String, AbstractC0385gk> r02 = this.mActionMap;
        XR.A00(gkVar2);
        r02.put(ServiceContract.BROADCAST_LOGOUT, gkVar2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: X.2o<java.lang.String, X.gk> */
    /* JADX WARN: Multi-variable type inference failed */
    public Fa(Iterator<? extends Map.Entry<String, ? extends AbstractC0385gk>> it) {
        String str;
        XR.A00(it);
        this.mActionMap = new C00062o<>();
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
