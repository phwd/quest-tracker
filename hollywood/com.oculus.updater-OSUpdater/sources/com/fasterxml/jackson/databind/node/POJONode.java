package com.fasterxml.jackson.databind.node;

public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        Object obj = this._value;
        return obj == null ? "null" : obj.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        POJONode pOJONode = (POJONode) obj;
        Object obj2 = this._value;
        if (obj2 != null) {
            return obj2.equals(pOJONode._value);
        }
        if (pOJONode._value == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.databind.node.ValueNode
    public String toString() {
        return String.valueOf(this._value);
    }
}
