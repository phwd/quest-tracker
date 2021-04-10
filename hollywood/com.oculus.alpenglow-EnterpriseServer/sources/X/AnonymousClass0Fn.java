package X;

import X.AnonymousClass0Fr;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.oculus.alpenglow.database.AbstractApplicationDatabase;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;

/* renamed from: X.0Fn  reason: invalid class name */
public class AnonymousClass0Fn<T extends AnonymousClass0Fr> {
    public AnonymousClass0Fp A00 = AnonymousClass0Fp.AUTOMATIC;
    public AnonymousClass0GU A01;
    public Set<Integer> A02;
    public Executor A03;
    public Executor A04;
    public final Context A05;
    public final AnonymousClass0Fq A06 = new AnonymousClass0Fq();
    public final Class<T> A07 = AbstractApplicationDatabase.class;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/lang/Class<TT;>;Ljava/lang/String;)V */
    public AnonymousClass0Fn(@NonNull Context context) {
        this.A05 = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006b, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0094  */
    @androidx.annotation.NonNull
    @android.annotation.SuppressLint({"RestrictedApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T A00() {
        /*
        // Method dump skipped, instructions count: 224
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Fn.A00():X.0Fr");
    }

    /* JADX WARN: Incorrect return type in method signature: ([LX/0G5;)LX/0Fn<TT;>; */
    @NonNull
    public final void A01(@NonNull AnonymousClass0G5... r10) {
        if (this.A02 == null) {
            this.A02 = new HashSet();
        }
        for (AnonymousClass0G5 r2 : r10) {
            this.A02.add(Integer.valueOf(r2.startVersion));
            this.A02.add(Integer.valueOf(r2.endVersion));
        }
        AnonymousClass0Fq r7 = this.A06;
        for (AnonymousClass0G5 r5 : r10) {
            int i = r5.startVersion;
            int i2 = r5.endVersion;
            TreeMap<Integer, AnonymousClass0G5> treeMap = r7.A00.get(Integer.valueOf(i));
            if (treeMap == null) {
                treeMap = new TreeMap<>();
                r7.A00.put(Integer.valueOf(i), treeMap);
            }
            Integer valueOf = Integer.valueOf(i2);
            AnonymousClass0G5 r22 = treeMap.get(valueOf);
            if (r22 != null) {
                Log.w("ROOM", "Overriding migration " + r22 + " with " + r5);
            }
            treeMap.put(valueOf, r5);
        }
    }
}
