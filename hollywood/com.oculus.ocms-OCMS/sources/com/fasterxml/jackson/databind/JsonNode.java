package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class JsonNode implements TreeNode, Iterable<JsonNode> {
    public boolean asBoolean(boolean z) {
        return z;
    }

    public double asDouble(double d) {
        return d;
    }

    public int asInt(int i) {
        return i;
    }

    public long asLong(long j) {
        return j;
    }

    public abstract String asText();

    public byte[] binaryValue() throws IOException {
        return null;
    }

    public boolean booleanValue() {
        return false;
    }

    public boolean canConvertToInt() {
        return false;
    }

    public boolean canConvertToLong() {
        return false;
    }

    public abstract <T extends JsonNode> T deepCopy();

    public double doubleValue() {
        return 0.0d;
    }

    public abstract boolean equals(Object obj);

    public abstract JsonNode findParent(String str);

    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);

    public abstract JsonNode findPath(String str);

    public abstract JsonNode findValue(String str);

    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);

    public abstract List<String> findValuesAsText(String str, List<String> list);

    public float floatValue() {
        return 0.0f;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public abstract JsonNode get(int i);

    @Override // com.fasterxml.jackson.core.TreeNode
    public JsonNode get(String str) {
        return null;
    }

    public abstract JsonNodeType getNodeType();

    public int intValue() {
        return 0;
    }

    public boolean isBigDecimal() {
        return false;
    }

    public boolean isBigInteger() {
        return false;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isFloatingPointNumber() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isIntegralNumber() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    public boolean isShort() {
        return false;
    }

    public long longValue() {
        return 0;
    }

    public Number numberValue() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public abstract JsonNode path(int i);

    @Override // com.fasterxml.jackson.core.TreeNode
    public abstract JsonNode path(String str);

    public short shortValue() {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public int size() {
        return 0;
    }

    public String textValue() {
        return null;
    }

    public abstract String toString();

    protected JsonNode() {
    }

    /* renamed from: com.fasterxml.jackson.databind.JsonNode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType = new int[JsonNodeType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.fasterxml.jackson.databind.node.JsonNodeType[] r0 = com.fasterxml.jackson.databind.node.JsonNodeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType = r0
                int[] r0 = com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.MISSING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isValueNode() {
        int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[getNodeType().ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isContainerNode() {
        JsonNodeType nodeType = getNodeType();
        return nodeType == JsonNodeType.OBJECT || nodeType == JsonNodeType.ARRAY;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isMissingNode() {
        return getNodeType() == JsonNodeType.MISSING;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isArray() {
        return getNodeType() == JsonNodeType.ARRAY;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public final boolean isObject() {
        return getNodeType() == JsonNodeType.OBJECT;
    }

    @Override // com.fasterxml.jackson.core.TreeNode
    public Iterator<String> fieldNames() {
        return EmptyIterator.instance();
    }

    public final boolean isPojo() {
        return getNodeType() == JsonNodeType.POJO;
    }

    public final boolean isNumber() {
        return getNodeType() == JsonNodeType.NUMBER;
    }

    public final boolean isTextual() {
        return getNodeType() == JsonNodeType.STRING;
    }

    public final boolean isBoolean() {
        return getNodeType() == JsonNodeType.BOOLEAN;
    }

    public final boolean isNull() {
        return getNodeType() == JsonNodeType.NULL;
    }

    public final boolean isBinary() {
        return getNodeType() == JsonNodeType.BINARY;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.ZERO;
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }

    public int asInt() {
        return asInt(0);
    }

    public long asLong() {
        return asLong(0);
    }

    public double asDouble() {
        return asDouble(0.0d);
    }

    public boolean asBoolean() {
        return asBoolean(false);
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    public boolean has(int i) {
        return get(i) != null;
    }

    public boolean hasNonNull(String str) {
        JsonNode jsonNode = get(str);
        return jsonNode != null && !jsonNode.isNull();
    }

    public boolean hasNonNull(int i) {
        JsonNode jsonNode = get(i);
        return jsonNode != null && !jsonNode.isNull();
    }

    @Override // java.lang.Iterable
    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public Iterator<JsonNode> elements() {
        return EmptyIterator.instance();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return EmptyIterator.instance();
    }

    public final List<JsonNode> findValues(String str) {
        List<JsonNode> findValues = findValues(str, null);
        return findValues == null ? Collections.emptyList() : findValues;
    }

    public final List<String> findValuesAsText(String str) {
        List<String> findValuesAsText = findValuesAsText(str, null);
        return findValuesAsText == null ? Collections.emptyList() : findValuesAsText;
    }

    public final List<JsonNode> findParents(String str) {
        List<JsonNode> findParents = findParents(str, null);
        return findParents == null ? Collections.emptyList() : findParents;
    }

    public JsonNode with(String str) {
        throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), can not call with() on it");
    }

    public JsonNode withArray(String str) {
        throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), can not call withArray() on it");
    }
}
