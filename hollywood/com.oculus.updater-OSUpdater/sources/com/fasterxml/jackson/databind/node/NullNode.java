package com.fasterxml.jackson.databind.node;

public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return "null";
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    private NullNode() {
    }

    public static NullNode getInstance() {
        return instance;
    }
}
