package defpackage;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* renamed from: Bc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0068Bc0 extends LinkedHashMap {
    public static final C0068Bc0 F;
    public boolean G = true;

    static {
        C0068Bc0 bc0 = new C0068Bc0();
        F = bc0;
        bc0.G = false;
    }

    public C0068Bc0() {
    }

    public static int a(Object obj) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            Charset charset = F30.f7992a;
            int length = bArr.length;
            int i = length;
            for (int i2 = 0; i2 < 0 + length; i2++) {
                i = (i * 31) + bArr[i2];
            }
            if (i == 0) {
                return 1;
            }
            return i;
        } else if (!(obj instanceof C30)) {
            return obj.hashCode();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public final void b() {
        if (!this.G) {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        b();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof java.util.Map
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x005e
            java.util.Map r7 = (java.util.Map) r7
            if (r6 != r7) goto L_0x000c
        L_0x000a:
            r7 = r1
            goto L_0x005b
        L_0x000c:
            int r0 = r6.size()
            int r3 = r7.size()
            if (r0 == r3) goto L_0x0018
        L_0x0016:
            r7 = r2
            goto L_0x005b
        L_0x0018:
            java.util.Set r0 = r6.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0020:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x000a
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            boolean r4 = r7.containsKey(r4)
            if (r4 != 0) goto L_0x0037
            goto L_0x0016
        L_0x0037:
            java.lang.Object r4 = r3.getValue()
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r7.get(r3)
            boolean r5 = r4 instanceof byte[]
            if (r5 == 0) goto L_0x0054
            boolean r5 = r3 instanceof byte[]
            if (r5 == 0) goto L_0x0054
            byte[] r4 = (byte[]) r4
            byte[] r3 = (byte[]) r3
            boolean r3 = java.util.Arrays.equals(r4, r3)
            goto L_0x0058
        L_0x0054:
            boolean r3 = r4.equals(r3)
        L_0x0058:
            if (r3 != 0) goto L_0x0020
            goto L_0x0016
        L_0x005b:
            if (r7 == 0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r1 = r2
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0068Bc0.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += a(entry.getValue()) ^ a(entry.getKey());
        }
        return i;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Object put(Object obj, Object obj2) {
        b();
        Charset charset = F30.f7992a;
        Objects.requireNonNull(obj);
        Objects.requireNonNull(obj2);
        return super.put(obj, obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public void putAll(Map map) {
        b();
        for (Object obj : map.keySet()) {
            Charset charset = F30.f7992a;
            Objects.requireNonNull(obj);
            Objects.requireNonNull(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Object remove(Object obj) {
        b();
        return super.remove(obj);
    }

    public C0068Bc0(Map map) {
        super(map);
    }
}
