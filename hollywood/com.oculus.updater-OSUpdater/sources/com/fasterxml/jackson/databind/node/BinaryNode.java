package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variants;
import java.util.Arrays;

public final class BinaryNode extends ValueNode {
    static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
    final byte[] _data;

    public BinaryNode(byte[] bArr) {
        this._data = bArr;
    }

    public static BinaryNode valueOf(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BINARY_NODE;
        }
        return new BinaryNode(bArr);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return Base64Variants.getDefaultVariant().encode(this._data, false);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return Arrays.equals(((BinaryNode) obj)._data, this._data);
        }
        return false;
    }

    public int hashCode() {
        byte[] bArr = this._data;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.databind.node.ValueNode
    public String toString() {
        return Base64Variants.getDefaultVariant().encode(this._data, true);
    }
}
