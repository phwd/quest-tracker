package android.icu.impl.locale;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.util.Output;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.regex.Pattern;

public class KeyTypeData {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Map<String, Set<String>> BCP47_KEYS;
    static Set<String> DEPRECATED_KEYS = Collections.emptySet();
    static Map<String, Set<String>> DEPRECATED_KEY_TYPES = Collections.emptyMap();
    private static final Map<String, KeyData> KEYMAP = new HashMap();
    private static final Object[][] KEY_DATA = new Object[0][];
    static Map<String, ValueType> VALUE_TYPES = Collections.emptyMap();

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

    static {
        initFromResourceBundle();
    }

    /* access modifiers changed from: private */
    public static abstract class SpecialTypeHandler {
        /* access modifiers changed from: package-private */
        public abstract boolean isWellFormed(String str);

        private SpecialTypeHandler() {
        }

        /* synthetic */ SpecialTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        public String canonicalize(String value) {
            return AsciiUtil.toLowerString(value);
        }
    }

    private static class CodepointsTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[0-9a-fA-F]{4,6}(-[0-9a-fA-F]{4,6})*");

        private CodepointsTypeHandler() {
            super(null);
        }

        /* synthetic */ CodepointsTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String value) {
            return pat.matcher(value).matches();
        }
    }

    private static class ReorderCodeTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[a-zA-Z]{3,8}(-[a-zA-Z]{3,8})*");

        private ReorderCodeTypeHandler() {
            super(null);
        }

        /* synthetic */ ReorderCodeTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String value) {
            return pat.matcher(value).matches();
        }
    }

    private static class RgKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})[zZ]{4}");

        private RgKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ RgKeyValueTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String value) {
            return pat.matcher(value).matches();
        }
    }

    private static class SubdivisionKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("([a-zA-Z]{2}|[0-9]{3})");

        private SubdivisionKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ SubdivisionKeyValueTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String value) {
            return pat.matcher(value).matches();
        }
    }

    private static class PrivateUseKeyValueTypeHandler extends SpecialTypeHandler {
        private static final Pattern pat = Pattern.compile("[a-zA-Z0-9]{3,8}(-[a-zA-Z0-9]{3,8})*");

        private PrivateUseKeyValueTypeHandler() {
            super(null);
        }

        /* synthetic */ PrivateUseKeyValueTypeHandler(AnonymousClass1 x0) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.locale.KeyTypeData.SpecialTypeHandler
        public boolean isWellFormed(String value) {
            return pat.matcher(value).matches();
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

        private SpecialType(SpecialTypeHandler handler2) {
            this.handler = handler2;
        }
    }

    /* access modifiers changed from: private */
    public static class KeyData {
        String bcpId;
        String legacyId;
        EnumSet<SpecialType> specialTypes;
        Map<String, Type> typeMap;

        KeyData(String legacyId2, String bcpId2, Map<String, Type> typeMap2, EnumSet<SpecialType> specialTypes2) {
            this.legacyId = legacyId2;
            this.bcpId = bcpId2;
            this.typeMap = typeMap2;
            this.specialTypes = specialTypes2;
        }
    }

    /* access modifiers changed from: private */
    public static class Type {
        String bcpId;
        String legacyId;

        Type(String legacyId2, String bcpId2) {
            this.legacyId = legacyId2;
            this.bcpId = bcpId2;
        }
    }

    public static String toBcpKey(String key) {
        KeyData keyData = KEYMAP.get(AsciiUtil.toLowerString(key));
        if (keyData != null) {
            return keyData.bcpId;
        }
        return null;
    }

    public static String toLegacyKey(String key) {
        KeyData keyData = KEYMAP.get(AsciiUtil.toLowerString(key));
        if (keyData != null) {
            return keyData.legacyId;
        }
        return null;
    }

    public static String toBcpType(String key, String type, Output<Boolean> isKnownKey, Output<Boolean> isSpecialType) {
        T t = (T) false;
        if (isKnownKey != null) {
            isKnownKey.value = t;
        }
        if (isSpecialType != null) {
            isSpecialType.value = t;
        }
        String key2 = AsciiUtil.toLowerString(key);
        String type2 = AsciiUtil.toLowerString(type);
        KeyData keyData = KEYMAP.get(key2);
        if (keyData == null) {
            return null;
        }
        if (isKnownKey != null) {
            isKnownKey.value = (T) Boolean.TRUE;
        }
        Type t2 = keyData.typeMap.get(type2);
        if (t2 != null) {
            return t2.bcpId;
        }
        if (keyData.specialTypes == null) {
            return null;
        }
        Iterator<E> it = keyData.specialTypes.iterator();
        while (it.hasNext()) {
            SpecialType st = it.next();
            if (st.handler.isWellFormed(type2)) {
                if (isSpecialType != null) {
                    isSpecialType.value = (T) true;
                }
                return st.handler.canonicalize(type2);
            }
        }
        return null;
    }

    public static String toLegacyType(String key, String type, Output<Boolean> isKnownKey, Output<Boolean> isSpecialType) {
        T t = (T) false;
        if (isKnownKey != null) {
            isKnownKey.value = t;
        }
        if (isSpecialType != null) {
            isSpecialType.value = t;
        }
        String key2 = AsciiUtil.toLowerString(key);
        String type2 = AsciiUtil.toLowerString(type);
        KeyData keyData = KEYMAP.get(key2);
        if (keyData == null) {
            return null;
        }
        if (isKnownKey != null) {
            isKnownKey.value = (T) Boolean.TRUE;
        }
        Type t2 = keyData.typeMap.get(type2);
        if (t2 != null) {
            return t2.legacyId;
        }
        if (keyData.specialTypes == null) {
            return null;
        }
        Iterator<E> it = keyData.specialTypes.iterator();
        while (it.hasNext()) {
            SpecialType st = it.next();
            if (st.handler.isWellFormed(type2)) {
                if (isSpecialType != null) {
                    isSpecialType.value = (T) true;
                }
                return st.handler.canonicalize(type2);
            }
        }
        return null;
    }

    /* JADX INFO: Multiple debug info for r4v18 'aliasSet'  java.util.Set<java.lang.String>: [D('typeAliasRes' android.icu.util.UResourceBundle), D('aliasSet' java.util.Set<java.lang.String>)] */
    private static void initFromResourceBundle() {
        boolean hasSameKey;
        String bcpKeyId;
        UResourceBundle keyMapRes;
        UResourceBundle keyTypeDataRes;
        UResourceBundle typeAliasRes;
        UResourceBundle bcpTypeAliasRes;
        UResourceBundle typeMapRes;
        EnumSet<SpecialType> specialTypeSet;
        String bcpTypeId;
        UResourceBundle typeAliasRes2;
        Set<String> aliasSet;
        UResourceBundle keyMapRes2;
        UResourceBundle keyTypeDataRes2;
        UResourceBundle keyTypeDataRes3 = ICUResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "keyTypeData", ICUResourceBundle.ICU_DATA_CLASS_LOADER, ICUResourceBundle.OpenType.DIRECT);
        getKeyInfo(keyTypeDataRes3.get("keyInfo"));
        getTypeInfo(keyTypeDataRes3.get("typeInfo"));
        UResourceBundle keyMapRes3 = keyTypeDataRes3.get("keyMap");
        UResourceBundle typeMapRes2 = keyTypeDataRes3.get("typeMap");
        UResourceBundle typeAliasRes3 = null;
        UResourceBundle bcpTypeAliasRes2 = null;
        try {
            typeAliasRes3 = keyTypeDataRes3.get("typeAlias");
        } catch (MissingResourceException e) {
        }
        try {
            bcpTypeAliasRes2 = keyTypeDataRes3.get("bcpTypeAlias");
        } catch (MissingResourceException e2) {
        }
        UResourceBundleIterator keyMapItr = keyMapRes3.getIterator();
        Map<String, Set<String>> _Bcp47Keys = new LinkedHashMap<>();
        while (keyMapItr.hasNext()) {
            UResourceBundle keyMapEntry = keyMapItr.next();
            String legacyKeyId = keyMapEntry.getKey();
            String bcpKeyId2 = keyMapEntry.getString();
            if (bcpKeyId2.length() == 0) {
                hasSameKey = true;
                bcpKeyId = legacyKeyId;
            } else {
                hasSameKey = false;
                bcpKeyId = bcpKeyId2;
            }
            LinkedHashSet<String> _bcp47Types = new LinkedHashSet<>();
            _Bcp47Keys.put(bcpKeyId, Collections.unmodifiableSet(_bcp47Types));
            boolean isTZ = legacyKeyId.equals("timezone");
            Map<String, Set<String>> typeAliasMap = null;
            if (typeAliasRes3 != null) {
                UResourceBundle typeAliasResByKey = null;
                try {
                    typeAliasResByKey = typeAliasRes3.get(legacyKeyId);
                } catch (MissingResourceException e3) {
                }
                if (typeAliasResByKey != null) {
                    typeAliasMap = new HashMap<>();
                    UResourceBundleIterator typeAliasResItr = typeAliasResByKey.getIterator();
                    while (typeAliasResItr.hasNext()) {
                        UResourceBundle typeAliasDataEntry = typeAliasResItr.next();
                        String from = typeAliasDataEntry.getKey();
                        String to = typeAliasDataEntry.getString();
                        if (isTZ) {
                            keyTypeDataRes2 = keyTypeDataRes3;
                            keyMapRes2 = keyMapRes3;
                            from = from.replace(':', '/');
                        } else {
                            keyTypeDataRes2 = keyTypeDataRes3;
                            keyMapRes2 = keyMapRes3;
                        }
                        Set<String> aliasSet2 = typeAliasMap.get(to);
                        if (aliasSet2 == null) {
                            aliasSet2 = new HashSet<>();
                            typeAliasMap.put(to, aliasSet2);
                        }
                        aliasSet2.add(from);
                        typeAliasResItr = typeAliasResItr;
                        keyTypeDataRes3 = keyTypeDataRes2;
                        keyMapRes3 = keyMapRes2;
                    }
                    keyTypeDataRes = keyTypeDataRes3;
                    keyMapRes = keyMapRes3;
                } else {
                    keyTypeDataRes = keyTypeDataRes3;
                    keyMapRes = keyMapRes3;
                }
            } else {
                keyTypeDataRes = keyTypeDataRes3;
                keyMapRes = keyMapRes3;
            }
            Map<String, Set<String>> bcpTypeAliasMap = null;
            if (bcpTypeAliasRes2 != null) {
                UResourceBundle bcpTypeAliasResByKey = null;
                try {
                    bcpTypeAliasResByKey = bcpTypeAliasRes2.get(bcpKeyId);
                } catch (MissingResourceException e4) {
                }
                if (bcpTypeAliasResByKey != null) {
                    bcpTypeAliasMap = new HashMap<>();
                    UResourceBundleIterator bcpTypeAliasResItr = bcpTypeAliasResByKey.getIterator();
                    while (bcpTypeAliasResItr.hasNext()) {
                        UResourceBundle bcpTypeAliasDataEntry = bcpTypeAliasResItr.next();
                        String from2 = bcpTypeAliasDataEntry.getKey();
                        String to2 = bcpTypeAliasDataEntry.getString();
                        Set<String> aliasSet3 = bcpTypeAliasMap.get(to2);
                        if (aliasSet3 == null) {
                            typeAliasRes2 = typeAliasRes3;
                            aliasSet = new HashSet<>();
                            bcpTypeAliasMap.put(to2, aliasSet);
                        } else {
                            typeAliasRes2 = typeAliasRes3;
                            aliasSet = aliasSet3;
                        }
                        aliasSet.add(from2);
                        bcpTypeAliasResItr = bcpTypeAliasResItr;
                        bcpTypeAliasResByKey = bcpTypeAliasResByKey;
                        typeAliasRes3 = typeAliasRes2;
                    }
                    typeAliasRes = typeAliasRes3;
                } else {
                    typeAliasRes = typeAliasRes3;
                }
            } else {
                typeAliasRes = typeAliasRes3;
            }
            Map<String, Type> typeDataMap = new HashMap<>();
            EnumSet<SpecialType> specialTypeSet2 = null;
            UResourceBundle typeMapResByKey = null;
            try {
                typeMapResByKey = typeMapRes2.get(legacyKeyId);
            } catch (MissingResourceException e5) {
            }
            if (typeMapResByKey != null) {
                UResourceBundleIterator typeMapResByKeyItr = typeMapResByKey.getIterator();
                while (typeMapResByKeyItr.hasNext()) {
                    UResourceBundle typeMapEntry = typeMapResByKeyItr.next();
                    String legacyTypeId = typeMapEntry.getKey();
                    String bcpTypeId2 = typeMapEntry.getString();
                    char first = legacyTypeId.charAt(0);
                    if ('9' < first && first < 'a' && bcpTypeId2.length() == 0) {
                        if (specialTypeSet2 == null) {
                            specialTypeSet2 = EnumSet.noneOf(SpecialType.class);
                        }
                        specialTypeSet2.add(SpecialType.valueOf(legacyTypeId));
                        _bcp47Types.add(legacyTypeId);
                        typeMapResByKeyItr = typeMapResByKeyItr;
                        typeMapRes2 = typeMapRes2;
                        bcpTypeAliasRes2 = bcpTypeAliasRes2;
                    } else {
                        if (isTZ) {
                            specialTypeSet = specialTypeSet2;
                            legacyTypeId = legacyTypeId.replace(':', '/');
                        } else {
                            specialTypeSet = specialTypeSet2;
                        }
                        boolean hasSameType = false;
                        if (bcpTypeId2.length() == 0) {
                            hasSameType = true;
                            bcpTypeId = legacyTypeId;
                        } else {
                            bcpTypeId = bcpTypeId2;
                        }
                        _bcp47Types.add(bcpTypeId);
                        Type t = new Type(legacyTypeId, bcpTypeId);
                        typeDataMap.put(AsciiUtil.toLowerString(legacyTypeId), t);
                        if (!hasSameType) {
                            typeDataMap.put(AsciiUtil.toLowerString(bcpTypeId), t);
                        }
                        if (typeAliasMap != null) {
                            Set<String> typeAliasSet = typeAliasMap.get(legacyTypeId);
                            if (typeAliasSet != null) {
                                for (String alias : typeAliasSet) {
                                    typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                                    legacyTypeId = legacyTypeId;
                                }
                            }
                        }
                        if (bcpTypeAliasMap != null) {
                            Set<String> bcpTypeAliasSet = bcpTypeAliasMap.get(bcpTypeId);
                            if (bcpTypeAliasSet != null) {
                                for (String alias2 : bcpTypeAliasSet) {
                                    typeDataMap.put(AsciiUtil.toLowerString(alias2), t);
                                    bcpTypeAliasSet = bcpTypeAliasSet;
                                }
                            }
                        }
                        specialTypeSet2 = specialTypeSet;
                        typeMapResByKeyItr = typeMapResByKeyItr;
                        typeMapRes2 = typeMapRes2;
                        bcpTypeAliasRes2 = bcpTypeAliasRes2;
                    }
                }
                typeMapRes = typeMapRes2;
                bcpTypeAliasRes = bcpTypeAliasRes2;
            } else {
                typeMapRes = typeMapRes2;
                bcpTypeAliasRes = bcpTypeAliasRes2;
            }
            KeyData keyData = new KeyData(legacyKeyId, bcpKeyId, typeDataMap, specialTypeSet2);
            KEYMAP.put(AsciiUtil.toLowerString(legacyKeyId), keyData);
            if (!hasSameKey) {
                KEYMAP.put(AsciiUtil.toLowerString(bcpKeyId), keyData);
            }
            keyTypeDataRes3 = keyTypeDataRes;
            keyMapRes3 = keyMapRes;
            typeAliasRes3 = typeAliasRes;
            typeMapRes2 = typeMapRes;
            bcpTypeAliasRes2 = bcpTypeAliasRes;
        }
        BCP47_KEYS = Collections.unmodifiableMap(_Bcp47Keys);
    }

    private static void getKeyInfo(UResourceBundle keyInfoRes) {
        Set<String> _deprecatedKeys = new LinkedHashSet<>();
        Map<String, ValueType> _valueTypes = new LinkedHashMap<>();
        UResourceBundleIterator keyInfoIt = keyInfoRes.getIterator();
        while (keyInfoIt.hasNext()) {
            UResourceBundle keyInfoEntry = keyInfoIt.next();
            KeyInfoType keyInfo = KeyInfoType.valueOf(keyInfoEntry.getKey());
            UResourceBundleIterator keyInfoIt2 = keyInfoEntry.getIterator();
            while (keyInfoIt2.hasNext()) {
                UResourceBundle keyInfoEntry2 = keyInfoIt2.next();
                String key2 = keyInfoEntry2.getKey();
                String value2 = keyInfoEntry2.getString();
                int i = AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType[keyInfo.ordinal()];
                if (i == 1) {
                    _deprecatedKeys.add(key2);
                } else if (i == 2) {
                    _valueTypes.put(key2, ValueType.valueOf(value2));
                }
            }
        }
        DEPRECATED_KEYS = Collections.unmodifiableSet(_deprecatedKeys);
        VALUE_TYPES = Collections.unmodifiableMap(_valueTypes);
    }

    private static void getTypeInfo(UResourceBundle typeInfoRes) {
        Map<String, Set<String>> _deprecatedKeyTypes = new LinkedHashMap<>();
        UResourceBundleIterator keyInfoIt = typeInfoRes.getIterator();
        while (keyInfoIt.hasNext()) {
            UResourceBundle keyInfoEntry = keyInfoIt.next();
            TypeInfoType typeInfo = TypeInfoType.valueOf(keyInfoEntry.getKey());
            UResourceBundleIterator keyInfoIt2 = keyInfoEntry.getIterator();
            while (keyInfoIt2.hasNext()) {
                UResourceBundle keyInfoEntry2 = keyInfoIt2.next();
                String key2 = keyInfoEntry2.getKey();
                Set<String> _deprecatedTypes = new LinkedHashSet<>();
                UResourceBundleIterator keyInfoIt3 = keyInfoEntry2.getIterator();
                while (keyInfoIt3.hasNext()) {
                    String key3 = keyInfoIt3.next().getKey();
                    if (AnonymousClass1.$SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType[typeInfo.ordinal()] == 1) {
                        _deprecatedTypes.add(key3);
                    }
                }
                _deprecatedKeyTypes.put(key2, Collections.unmodifiableSet(_deprecatedTypes));
            }
        }
        DEPRECATED_KEY_TYPES = Collections.unmodifiableMap(_deprecatedKeyTypes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.impl.locale.KeyTypeData$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType = new int[KeyInfoType.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType = new int[TypeInfoType.values().length];

        static {
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$TypeInfoType[TypeInfoType.deprecated.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType[KeyInfoType.deprecated.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$impl$locale$KeyTypeData$KeyInfoType[KeyInfoType.valueType.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Multiple debug info for r4v4 java.lang.String: [D('keyDataEntry' java.lang.Object[]), D('legacyTypeId' java.lang.String)] */
    private static void initFromTables() {
        Object[][] objArr;
        int i;
        String[][] typeAliasData;
        boolean hasSameType;
        String bcpTypeId;
        Set<String> aliasSet;
        Object[][] objArr2 = KEY_DATA;
        int length = objArr2.length;
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            Object[] keyDataEntry = objArr2[i3];
            String legacyKeyId = (String) keyDataEntry[i2];
            char c = 1;
            String bcpKeyId = (String) keyDataEntry[1];
            String[][] typeData = (String[][]) keyDataEntry[2];
            String[][] typeAliasData2 = (String[][]) keyDataEntry[3];
            String[][] bcpTypeAliasData = (String[][]) keyDataEntry[4];
            boolean hasSameKey = false;
            if (bcpKeyId == null) {
                bcpKeyId = legacyKeyId;
                hasSameKey = true;
            }
            Map<String, Set<String>> typeAliasMap = null;
            if (typeAliasData2 != null) {
                typeAliasMap = new HashMap<>();
                int length2 = typeAliasData2.length;
                int i4 = i2;
                while (i4 < length2) {
                    String[] typeAliasDataEntry = typeAliasData2[i4];
                    String from = typeAliasDataEntry[i2];
                    String to = typeAliasDataEntry[c];
                    Set<String> aliasSet2 = typeAliasMap.get(to);
                    if (aliasSet2 == null) {
                        aliasSet = new HashSet<>();
                        typeAliasMap.put(to, aliasSet);
                    } else {
                        aliasSet = aliasSet2;
                    }
                    aliasSet.add(from);
                    i4++;
                    objArr2 = objArr2;
                    i2 = 0;
                    c = 1;
                }
                objArr = objArr2;
            } else {
                objArr = objArr2;
            }
            Map<String, Set<String>> bcpTypeAliasMap = null;
            if (bcpTypeAliasData != null) {
                bcpTypeAliasMap = new HashMap<>();
                int length3 = bcpTypeAliasData.length;
                int i5 = 0;
                while (i5 < length3) {
                    String[] bcpTypeAliasDataEntry = bcpTypeAliasData[i5];
                    String from2 = bcpTypeAliasDataEntry[0];
                    String to2 = bcpTypeAliasDataEntry[1];
                    Set<String> aliasSet3 = bcpTypeAliasMap.get(to2);
                    if (aliasSet3 == null) {
                        aliasSet3 = new HashSet<>();
                        bcpTypeAliasMap.put(to2, aliasSet3);
                    }
                    aliasSet3.add(from2);
                    i5++;
                    length = length;
                }
                i = length;
            } else {
                i = length;
            }
            Map<String, Type> typeDataMap = new HashMap<>();
            int length4 = typeData.length;
            Set<SpecialType> specialTypeSet = null;
            int i6 = 0;
            while (i6 < length4) {
                String[] typeDataEntry = typeData[i6];
                String legacyTypeId = typeDataEntry[0];
                String bcpTypeId2 = typeDataEntry[1];
                boolean isSpecialType = false;
                SpecialType[] values = SpecialType.values();
                int length5 = values.length;
                int i7 = 0;
                while (true) {
                    if (i7 >= length5) {
                        typeAliasData = typeAliasData2;
                        break;
                    }
                    SpecialType st = values[i7];
                    typeAliasData = typeAliasData2;
                    if (legacyTypeId.equals(st.toString())) {
                        isSpecialType = true;
                        if (specialTypeSet == null) {
                            specialTypeSet = new HashSet<>();
                        }
                        specialTypeSet.add(st);
                    } else {
                        i7++;
                        length5 = length5;
                        typeAliasData2 = typeAliasData;
                    }
                }
                if (!isSpecialType) {
                    if (bcpTypeId2 == null) {
                        hasSameType = true;
                        bcpTypeId = legacyTypeId;
                    } else {
                        hasSameType = false;
                        bcpTypeId = bcpTypeId2;
                    }
                    Type t = new Type(legacyTypeId, bcpTypeId);
                    typeDataMap.put(AsciiUtil.toLowerString(legacyTypeId), t);
                    if (!hasSameType) {
                        typeDataMap.put(AsciiUtil.toLowerString(bcpTypeId), t);
                    }
                    Set<String> typeAliasSet = typeAliasMap.get(legacyTypeId);
                    if (typeAliasSet != null) {
                        for (String alias : typeAliasSet) {
                            typeDataMap.put(AsciiUtil.toLowerString(alias), t);
                            legacyTypeId = legacyTypeId;
                        }
                    }
                    Set<String> bcpTypeAliasSet = bcpTypeAliasMap.get(bcpTypeId);
                    if (bcpTypeAliasSet != null) {
                        for (String alias2 : bcpTypeAliasSet) {
                            typeDataMap.put(AsciiUtil.toLowerString(alias2), t);
                            bcpTypeAliasMap = bcpTypeAliasMap;
                        }
                    }
                }
                i6++;
                keyDataEntry = keyDataEntry;
                length4 = length4;
                typeData = typeData;
                typeAliasData2 = typeAliasData;
                bcpTypeAliasMap = bcpTypeAliasMap;
            }
            EnumSet<SpecialType> specialTypes = null;
            if (specialTypeSet != null) {
                specialTypes = EnumSet.copyOf(specialTypeSet);
            }
            KeyData keyData = new KeyData(legacyKeyId, bcpKeyId, typeDataMap, specialTypes);
            KEYMAP.put(AsciiUtil.toLowerString(legacyKeyId), keyData);
            if (!hasSameKey) {
                KEYMAP.put(AsciiUtil.toLowerString(bcpKeyId), keyData);
            }
            i3++;
            objArr2 = objArr;
            length = i;
            i2 = 0;
        }
    }

    public static Set<String> getBcp47Keys() {
        return BCP47_KEYS.keySet();
    }

    public static Set<String> getBcp47KeyTypes(String key) {
        return BCP47_KEYS.get(key);
    }

    public static boolean isDeprecated(String key) {
        return DEPRECATED_KEYS.contains(key);
    }

    public static boolean isDeprecated(String key, String type) {
        Set<String> deprecatedTypes = DEPRECATED_KEY_TYPES.get(key);
        if (deprecatedTypes == null) {
            return false;
        }
        return deprecatedTypes.contains(type);
    }

    public static ValueType getValueType(String key) {
        ValueType type = VALUE_TYPES.get(key);
        return type == null ? ValueType.single : type;
    }
}
