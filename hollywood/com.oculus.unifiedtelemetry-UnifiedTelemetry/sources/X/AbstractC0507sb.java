package X;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.sb  reason: case insensitive filesystem */
public abstract class AbstractC0507sb {
    public final int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        Object obj;
        int A08;
        if (!(this instanceof C0485rr)) {
            return ((C0492sB) this).A02.A05(str, i, threadPolicy);
        }
        C0485rr rrVar = (C0485rr) this;
        if (!(rrVar instanceof AbstractC0486rs)) {
            return rrVar.A08(str, i, rrVar.A00, threadPolicy);
        }
        AbstractC0486rs rsVar = (AbstractC0486rs) rrVar;
        Map<String, Object> map = rsVar.A03;
        synchronized (map) {
            obj = map.get(str);
            if (obj == null) {
                obj = new Object();
                map.put(str, obj);
            }
        }
        synchronized (obj) {
            A08 = rsVar.A08(str, i, ((C0485rr) rsVar).A00, threadPolicy);
        }
        return A08;
    }

    @Nullable
    public final String A06(String str) throws IOException {
        if (this instanceof C0485rr) {
            File file = new File(((C0485rr) this).A00, str);
            if (file.exists()) {
                return file.getCanonicalPath();
            }
            return null;
        } else if (this instanceof C0492sB) {
            return ((C0492sB) this).A02.A06(str);
        } else {
            return null;
        }
    }

    public String toString() {
        return getClass().getName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0, types: [X.sc] */
    /* JADX WARN: Type inference failed for: r12v14, types: [X.sC] */
    /* JADX WARN: Type inference failed for: r12v15, types: [X.ry] */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:84|85) */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0458, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:?, code lost:
        r18.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0140, code lost:
        if (r4 != 1) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0177, code lost:
        if ((r34 & 2) == 0) goto L_0x040e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        r17 = new X.C0518t2(new X.C0519t9[0]);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x01f7 */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x035d  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x043e  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0395 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0350 A[EDGE_INSN: B:240:0x0350->B:147:0x0350 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01f7 A[SYNTHETIC, Splitter:B:84:0x01f7] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x020a A[Catch:{ all -> 0x0458, all -> 0x045c }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A07(int r34) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1144
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC0507sb.A07(int):void");
    }
}
