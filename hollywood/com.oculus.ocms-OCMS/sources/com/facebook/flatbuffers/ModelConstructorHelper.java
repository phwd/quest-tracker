package com.facebook.flatbuffers;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ModelConstructorHelper {
    @Deprecated
    public static <T> T newTypeModelInstance(Class<T> cls, int i) {
        try {
            return cls.getDeclaredConstructor(Integer.TYPE, int[].class).newInstance(Integer.valueOf(i), null);
        } catch (Exception e) {
            throw new RuntimeException("Can't create model object", e);
        }
    }

    @Deprecated
    public static <T> T newFragmentModelInstance(Class<T> cls, int i) {
        try {
            return cls.newInstance();
        } catch (Exception unused) {
            try {
                return cls.getDeclaredConstructor(Integer.TYPE).newInstance(Integer.valueOf(i));
            } catch (Exception e) {
                throw new RuntimeException("Can't init flattenable object", e);
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:0:0x0000 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.lang.Class<T> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.lang.Class */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        return (T) newFragmentModelInstance(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        throw new java.lang.RuntimeException(r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T newInstance(java.lang.Class<T> r0, int r1) {
        /*
            java.lang.Object r0 = newTypeModelInstance(r0, r1)     // Catch:{ Exception -> 0x0005 }
            return r0
        L_0x0005:
            java.lang.Object r0 = newFragmentModelInstance(r0, r1)     // Catch:{ Exception -> 0x000a }
            return r0
        L_0x000a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.flatbuffers.ModelConstructorHelper.newInstance(java.lang.Class, int):java.lang.Object");
    }
}
