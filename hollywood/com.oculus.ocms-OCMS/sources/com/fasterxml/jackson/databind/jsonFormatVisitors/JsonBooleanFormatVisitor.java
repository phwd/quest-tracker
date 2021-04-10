package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormatVisitor;

public interface JsonBooleanFormatVisitor extends JsonValueFormatVisitor {

    public static class Base extends JsonValueFormatVisitor.Base implements JsonBooleanFormatVisitor {
    }
}
