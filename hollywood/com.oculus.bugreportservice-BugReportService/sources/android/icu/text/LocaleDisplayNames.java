package android.icu.text;

import android.icu.impl.ICUConfig;
import android.icu.util.ULocale;
import java.lang.reflect.Method;

public abstract class LocaleDisplayNames {
    private static final Method FACTORY_DIALECTHANDLING;
    private static final Method FACTORY_DISPLAYCONTEXT;

    public enum DialectHandling {
        STANDARD_NAMES,
        DIALECT_NAMES
    }

    public abstract String localeDisplayName(ULocale uLocale);

    public static LocaleDisplayNames getInstance(ULocale uLocale) {
        return getInstance(uLocale, DialectHandling.STANDARD_NAMES);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.icu.text.LocaleDisplayNames getInstance(android.icu.util.ULocale r4, android.icu.text.LocaleDisplayNames.DialectHandling r5) {
        /*
            java.lang.reflect.Method r0 = android.icu.text.LocaleDisplayNames.FACTORY_DIALECTHANDLING
            r1 = 0
            if (r0 == 0) goto L_0x0015
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0015 }
            r3 = 0
            r2[r3] = r4     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0015 }
            r3 = 1
            r2[r3] = r5     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0015 }
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0015 }
            android.icu.text.LocaleDisplayNames r0 = (android.icu.text.LocaleDisplayNames) r0     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x0015 }
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            if (r0 != 0) goto L_0x001d
            android.icu.text.LocaleDisplayNames$LastResortLocaleDisplayNames r0 = new android.icu.text.LocaleDisplayNames$LastResortLocaleDisplayNames
            r0.<init>(r4, r5)
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.LocaleDisplayNames.getInstance(android.icu.util.ULocale, android.icu.text.LocaleDisplayNames$DialectHandling):android.icu.text.LocaleDisplayNames");
    }

    protected LocaleDisplayNames() {
    }

    static {
        Method method;
        Method method2 = null;
        try {
            Class cls = Class.forName(ICUConfig.get("android.icu.text.LocaleDisplayNames.impl", "android.icu.impl.LocaleDisplayNamesImpl"));
            try {
                method = cls.getMethod("getInstance", ULocale.class, DialectHandling.class);
            } catch (NoSuchMethodException unused) {
                method = null;
            }
            try {
                method2 = cls.getMethod("getInstance", ULocale.class, DisplayContext[].class);
            } catch (ClassNotFoundException | NoSuchMethodException unused2) {
            }
        } catch (ClassNotFoundException unused3) {
            method = null;
        }
        FACTORY_DIALECTHANDLING = method;
        FACTORY_DISPLAYCONTEXT = method2;
    }

    /* access modifiers changed from: private */
    public static class LastResortLocaleDisplayNames extends LocaleDisplayNames {
        private DisplayContext[] contexts;
        private ULocale locale;

        private LastResortLocaleDisplayNames(ULocale uLocale, DialectHandling dialectHandling) {
            this.locale = uLocale;
            this.contexts = new DisplayContext[]{dialectHandling == DialectHandling.DIALECT_NAMES ? DisplayContext.DIALECT_NAMES : DisplayContext.STANDARD_NAMES};
        }

        @Override // android.icu.text.LocaleDisplayNames
        public String localeDisplayName(ULocale uLocale) {
            return uLocale.getName();
        }
    }
}
