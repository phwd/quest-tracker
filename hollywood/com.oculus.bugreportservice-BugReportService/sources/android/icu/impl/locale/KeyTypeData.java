package android.icu.impl.locale;

import android.icu.util.Output;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class KeyTypeData {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Map BCP47_KEYS;
    static Set DEPRECATED_KEYS = Collections.emptySet();
    static Map DEPRECATED_KEY_TYPES = Collections.emptyMap();
    private static final Map KEYMAP = new HashMap();
    private static final Object[][] KEY_DATA = new Object[0][];
    static Map VALUE_TYPES = Collections.emptyMap();

    /* access modifiers changed from: private */
    public enum KeyInfoType {
        deprecated,
        valueType
    }

    /* access modifiers changed from: private */
    public enum TypeInfoType {
        deprecated
    }

    public enum ValueType {
        single,
        multiple,
        incremental,
        any
    }

    /* access modifiers changed from: private */
    public static abstract class SpecialTypeHandler {
        /* access modifiers changed from: package-private */
        public abstract boolean isWellFormed(String str);

        private SpecialTypeHandler() {
        }

        /* synthetic */ SpecialTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        public String canonicalize(String str) {
            return AsciiUtil.toLowerString(str);
        }
    }

    private static class CodepointsTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[0-9a-fA-F]{4,6}(-[0-9a-fA-F]{4,6})*");

        private CodepointsTypeHandler() {
            super(null);
        }

        /* synthetic */ CodepointsTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String str) {
            return pat.matcher(str).matches();
        }
    }

    private static class ReorderCodeTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[a-zA-Z]{3,8}(-[a-zA-Z]{3,8})*");

        private ReorderCodeTypeHandler() {
            super(null);
        }

        /* synthetic */ ReorderCodeTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String str) {
            return pat.matcher(str).matches();
        }
    }

    private static class RgKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})[zZ]{4}");

        private RgKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ RgKeyValueTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String str) {
            return pat.matcher(str).matches();
        }
    }

    private static class SubdivisionKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})");

        private SubdivisionKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ SubdivisionKeyValueTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String str) {
            return pat.matcher(str).matches();
        }
    }

    private static class PrivateUseKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[a-zA-Z0-9]{3,8}(-[a-zA-Z0-9]{3,8})*");

        private PrivateUseKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ PrivateUseKeyValueTypeHandler(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String str) {
            return pat.matcher(str).matches();
        }
    }

    /* access modifiers changed from: private */
    public enum SpecialType {
        CODEPOINTS(new CodepointsTypeHandler(null)),
        REORDER_CODE(new ReorderCodeTypeHandler(null)),
        RG_KEY_VALUE(new RgKeyValueTypeHandler(null)),
        SUBDIVISION_CODE(new SubdivisionKeyValueTypeHandler(null)),
        PRIVATE_USE(new PrivateUseKeyValueTypeHandler(null));
        
        SpecialTypeHandler handler;

        private SpecialType(SpecialTypeHandler specialTypeHandler) {
            this.handler = specialTypeHandler;
        }
    }

    /* access modifiers changed from: private */
    public static class KeyData {
        String bcpId;
        String legacyId;
        EnumSet specialTypes;
        Map typeMap;

        KeyData(String str, String str2, Map map, EnumSet enumSet) {
            this.legacyId = str;
            this.bcpId = str2;
            this.typeMap = map;
            this.specialTypes = enumSet;
        }
    }

    /* access modifiers changed from: private */
    public static class Type {
        String bcpId;
        String legacyId;

        Type(String str, String str2) {
            this.legacyId = str;
            this.bcpId = str2;
        }
    }

    public static String toBcpKey(String str) {
        KeyData keyData = (KeyData) KEYMAP.get(AsciiUtil.toLowerString(str));
        if (keyData != null) {
            return keyData.bcpId;
        }
        return null;
    }

    public static String toLegacyKey(String str) {
        KeyData keyData = (KeyData) KEYMAP.get(AsciiUtil.toLowerString(str));
        if (keyData != null) {
            return keyData.legacyId;
        }
        return null;
    }

    public static String toBcpType(String str, String str2, Output output, Output output2) {
        if (output != null) {
            output.value = false;
        }
        if (output2 != null) {
            output2.value = false;
        }
        String lowerString = AsciiUtil.toLowerString(str);
        String lowerString2 = AsciiUtil.toLowerString(str2);
        KeyData keyData = (KeyData) KEYMAP.get(lowerString);
        if (keyData == null) {
            return null;
        }
        if (output != null) {
            output.value = Boolean.TRUE;
        }
        Type type = (Type) keyData.typeMap.get(lowerString2);
        if (type != null) {
            return type.bcpId;
        }
        EnumSet enumSet = keyData.specialTypes;
        if (enumSet == null) {
            return null;
        }
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            SpecialType specialType = (SpecialType) it.next();
            if (specialType.handler.isWellFormed(lowerString2)) {
                if (output2 != null) {
                    output2.value = true;
                }
                return specialType.handler.canonicalize(lowerString2);
            }
        }
        return null;
    }

    public static String toLegacyType(String str, String str2, Output output, Output output2) {
        if (output != null) {
            output.value = false;
        }
        if (output2 != null) {
            output2.value = false;
        }
        String lowerString = AsciiUtil.toLowerString(str);
        String lowerString2 = AsciiUtil.toLowerString(str2);
        KeyData keyData = (KeyData) KEYMAP.get(lowerString);
        if (keyData == null) {
            return null;
        }
        if (output != null) {
            output.value = Boolean.TRUE;
        }
        Type type = (Type) keyData.typeMap.get(lowerString2);
        if (type != null) {
            return type.legacyId;
        }
        EnumSet enumSet = keyData.specialTypes;
        if (enumSet == null) {
            return null;
        }
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            SpecialType specialType = (SpecialType) it.next();
            if (specialType.handler.isWellFormed(lowerString2)) {
                if (output2 != null) {
                    output2.value = true;
                }
                return specialType.handler.canonicalize(lowerString2);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bc A[SYNTHETIC, Splitter:B:37:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void initFromResourceBundle() {
        /*
        // Method dump skipped, instructions count: 518
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.locale.KeyTypeData.initFromResourceBundle():void");
    }

    static {
        initFromResourceBundle();
    }

    private static void getKeyInfo(UResourceBundle uResourceBundle) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        UResourceBundleIterator iterator = uResourceBundle.getIterator();
        while (iterator.hasNext()) {
            UResourceBundle next = iterator.next();
            KeyInfoType valueOf = KeyInfoType.valueOf(next.getKey());
            UResourceBundleIterator iterator2 = next.getIterator();
            while (iterator2.hasNext()) {
                UResourceBundle next2 = iterator2.next();
                String key = next2.getKey();
                String string = next2.getString();
                int i = AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType[valueOf.ordinal()];
                if (i == 1) {
                    linkedHashSet.add(key);
                } else if (i == 2) {
                    linkedHashMap.put(key, ValueType.valueOf(string));
                }
            }
        }
        DEPRECATED_KEYS = Collections.unmodifiableSet(linkedHashSet);
        VALUE_TYPES = Collections.unmodifiableMap(linkedHashMap);
    }

    private static void getTypeInfo(UResourceBundle uResourceBundle) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        UResourceBundleIterator iterator = uResourceBundle.getIterator();
        while (iterator.hasNext()) {
            UResourceBundle next = iterator.next();
            TypeInfoType valueOf = TypeInfoType.valueOf(next.getKey());
            UResourceBundleIterator iterator2 = next.getIterator();
            while (iterator2.hasNext()) {
                UResourceBundle next2 = iterator2.next();
                String key = next2.getKey();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                UResourceBundleIterator iterator3 = next2.getIterator();
                while (iterator3.hasNext()) {
                    String key2 = iterator3.next().getKey();
                    if (AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType[valueOf.ordinal()] == 1) {
                        linkedHashSet.add(key2);
                    }
                }
                linkedHashMap.put(key, Collections.unmodifiableSet(linkedHashSet));
            }
        }
        DEPRECATED_KEY_TYPES = Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.locale.KeyTypeData$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType = new int[KeyInfoType.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType = new int[TypeInfoType.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0027 */
        static {
            /*
                android.icu.impl.locale.KeyTypeData$TypeInfoType[] r0 = android.icu.impl.locale.KeyTypeData.TypeInfoType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.locale.KeyTypeData.AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType = r0
                r0 = 1
                int[] r1 = android.icu.impl.locale.KeyTypeData.AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.impl.locale.KeyTypeData$TypeInfoType r2 = android.icu.impl.locale.KeyTypeData.TypeInfoType.deprecated     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                android.icu.impl.locale.KeyTypeData$KeyInfoType[] r1 = android.icu.impl.locale.KeyTypeData.KeyInfoType.values()
                int r1 = r1.length
                int[] r1 = new int[r1]
                android.icu.impl.locale.KeyTypeData.AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType = r1
                int[] r1 = android.icu.impl.locale.KeyTypeData.AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType     // Catch:{ NoSuchFieldError -> 0x0027 }
                android.icu.impl.locale.KeyTypeData$KeyInfoType r2 = android.icu.impl.locale.KeyTypeData.KeyInfoType.deprecated     // Catch:{ NoSuchFieldError -> 0x0027 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0027 }
            L_0x0027:
                int[] r0 = android.icu.impl.locale.KeyTypeData.AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType     // Catch:{ NoSuchFieldError -> 0x0032 }
                android.icu.impl.locale.KeyTypeData$KeyInfoType r1 = android.icu.impl.locale.KeyTypeData.KeyInfoType.valueType     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.locale.KeyTypeData.AnonymousClass1.<clinit>():void");
        }
    }
}
