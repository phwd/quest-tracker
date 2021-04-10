package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.oculus.common.build.BuildConfig;

public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode {
    protected final JsonNodeFactory _nodeFactory;

    @Override // com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return BuildConfig.PROVIDER_SUFFIX;
    }

    protected ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }
}
