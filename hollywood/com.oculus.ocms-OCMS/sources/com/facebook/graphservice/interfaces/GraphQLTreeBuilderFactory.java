package com.facebook.graphservice.interfaces;

public interface GraphQLTreeBuilderFactory {
    TreeBuilder newTreeBuilder(String str);

    <T extends TreeBuilder> T newTreeBuilder(String str, Class<T> cls, int i);

    <T extends TreeBuilder> T newTreeBuilder(String str, Class<T> cls, int i, Tree tree);

    <T extends TreeBuilder> T newUpdateBuilder(String str, Class<T> cls, int i, Tree tree);
}
