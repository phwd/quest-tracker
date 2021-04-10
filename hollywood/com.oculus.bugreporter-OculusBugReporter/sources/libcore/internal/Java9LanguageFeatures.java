package libcore.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Java9LanguageFeatures {

    public interface Person {
        String name();

        default boolean isPalindrome() {
            return name().equals(reverse(name()));
        }

        default boolean isPalindromeIgnoreCase() {
            return name().equalsIgnoreCase(reverse(name()));
        }

        private default String reverse(String s) {
            return new StringBuilder(s).reverse().toString();
        }
    }

    @SafeVarargs
    public static <T> String toListString(T... values) {
        return toString(values).toString();
    }

    @SafeVarargs
    private static <T> List<String> toString(T... values) {
        List<String> result = new ArrayList<>();
        for (T value : values) {
            result.add(value.toString());
        }
        return result;
    }

    public <T> AtomicReference<T> createReference(T content) {
        return new AtomicReference<T>(content) {
            /* class libcore.internal.Java9LanguageFeatures.AnonymousClass1 */
        };
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] copy(byte[] r5) throws java.io.IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r5)
        L_0x000b:
            int r2 = r1.read()     // Catch:{ all -> 0x001f }
            r3 = r2
            r4 = -1
            if (r2 == r4) goto L_0x0017
            r0.write(r3)     // Catch:{ all -> 0x001f }
            goto L_0x000b
        L_0x0017:
            r1.close()
            byte[] r2 = r0.toByteArray()
            return r2
        L_0x001f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x002a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.internal.Java9LanguageFeatures.copy(byte[]):byte[]");
    }
}
