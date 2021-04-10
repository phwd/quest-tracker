package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;

public final class PropertyOptions extends Options {
    public static final int ARRAY = 512;
    public static final int ARRAY_ALTERNATE = 2048;
    public static final int ARRAY_ALT_TEXT = 4096;
    public static final int ARRAY_ORDERED = 1024;
    public static final int DELETE_EXISTING = 536870912;
    public static final int HAS_LANGUAGE = 64;
    public static final int HAS_QUALIFIERS = 16;
    public static final int HAS_TYPE = 128;
    public static final int NO_OPTIONS = 0;
    public static final int QUALIFIER = 32;
    public static final int SCHEMA_NODE = Integer.MIN_VALUE;
    public static final int STRUCT = 256;
    public static final int URI = 2;

    @Override // com.adobe.xmp.options.Options
    public String defineOptionName(int i) {
        switch (i) {
            case Integer.MIN_VALUE:
                return "SCHEMA_NODE";
            case 2:
                return "URI";
            case 16:
                return "HAS_QUALIFIER";
            case 32:
                return "QUALIFIER";
            case 64:
                return "HAS_LANGUAGE";
            case 128:
                return "HAS_TYPE";
            case 256:
                return "STRUCT";
            case 512:
                return "ARRAY";
            case 1024:
                return "ARRAY_ORDERED";
            case 2048:
                return "ARRAY_ALTERNATE";
            case 4096:
                return "ARRAY_ALT_TEXT";
            default:
                return null;
        }
    }

    @Override // com.adobe.xmp.options.Options
    public int getValidOptions() {
        return -2147475470;
    }

    public boolean isURI() {
        return getOption(2);
    }

    public PropertyOptions setURI(boolean z) {
        setOption(2, z);
        return this;
    }

    @Override // com.adobe.xmp.options.Options
    public void assertConsistency(int i) throws XMPException {
        if ((i & 256) > 0 && (i & 512) > 0) {
            throw new XMPException("IsStruct and IsArray options are mutually exclusive", 103);
        } else if ((i & 2) > 0 && (i & 768) > 0) {
            throw new XMPException("Structs and arrays can't have \"value\" options", 103);
        }
    }

    public boolean equalArrayTypes(PropertyOptions propertyOptions) {
        if (getOption(512) == propertyOptions.getOption(512) && getOption(1024) == propertyOptions.getOption(1024) && getOption(2048) == propertyOptions.getOption(2048) && getOption(4096) == propertyOptions.getOption(4096)) {
            return true;
        }
        return false;
    }

    public boolean getHasLanguage() {
        return getOption(64);
    }

    public boolean getHasQualifiers() {
        return getOption(16);
    }

    public boolean getHasType() {
        return getOption(128);
    }

    public boolean isArray() {
        return getOption(512);
    }

    public boolean isArrayAltText() {
        return getOption(4096);
    }

    public boolean isArrayAlternate() {
        return getOption(2048);
    }

    public boolean isArrayOrdered() {
        return getOption(1024);
    }

    public boolean isCompositeProperty() {
        if ((this.options & 768) > 0) {
            return true;
        }
        return false;
    }

    public boolean isOnlyArrayOptions() {
        if ((this.options & -7681) == 0) {
            return true;
        }
        return false;
    }

    public boolean isQualifier() {
        return getOption(32);
    }

    public boolean isSchemaNode() {
        return getOption(Integer.MIN_VALUE);
    }

    public boolean isSimple() {
        if ((this.options & 768) == 0) {
            return true;
        }
        return false;
    }

    public boolean isStruct() {
        return getOption(256);
    }

    public void mergeWith(PropertyOptions propertyOptions) throws XMPException {
        if (propertyOptions != null) {
            setOptions(propertyOptions.options | this.options);
        }
    }

    public PropertyOptions setArray(boolean z) {
        setOption(512, z);
        return this;
    }

    public PropertyOptions setArrayAltText(boolean z) {
        setOption(4096, z);
        return this;
    }

    public PropertyOptions setArrayAlternate(boolean z) {
        setOption(2048, z);
        return this;
    }

    public PropertyOptions setArrayOrdered(boolean z) {
        setOption(1024, z);
        return this;
    }

    public PropertyOptions setHasLanguage(boolean z) {
        setOption(64, z);
        return this;
    }

    public PropertyOptions setHasQualifiers(boolean z) {
        setOption(16, z);
        return this;
    }

    public PropertyOptions setHasType(boolean z) {
        setOption(128, z);
        return this;
    }

    public PropertyOptions setQualifier(boolean z) {
        setOption(32, z);
        return this;
    }

    public PropertyOptions setSchemaNode(boolean z) {
        setOption(Integer.MIN_VALUE, z);
        return this;
    }

    public PropertyOptions setStruct(boolean z) {
        setOption(256, z);
        return this;
    }

    public PropertyOptions() {
    }

    public PropertyOptions(int i) throws XMPException {
        super(i);
    }
}
