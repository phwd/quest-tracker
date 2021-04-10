package com.fasterxml.jackson.databind.util;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NameTransformer() {
        /* class com.fasterxml.jackson.databind.util.NameTransformer.AnonymousClass1 */

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String transform(String str) {
            return str;
        }
    };

    public abstract String transform(String str);

    protected NameTransformer() {
    }
}
