package com.fasterxml.jackson.databind.node;

public abstract class ValueNode extends BaseJsonNode {
    protected ValueNode() {
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String toString() {
        return asText();
    }
}
