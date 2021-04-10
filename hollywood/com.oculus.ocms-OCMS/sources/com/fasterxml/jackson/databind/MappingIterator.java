package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
    protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator<>(null, null, null, null, false, null);
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected boolean _hasNextChecked;
    protected JsonParser _parser;
    protected final JavaType _type;
    protected final T _updatedValue;

    @Deprecated
    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, jsonParser, deserializationContext, jsonDeserializer, true, null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z, Object obj) {
        this._type = javaType;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        this._closeParser = z;
        if (obj == 0) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj;
        }
        if (z && jsonParser != null && jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            jsonParser.clearCurrentToken();
        }
    }

    protected static <T> MappingIterator<T> emptyIterator() {
        return (MappingIterator<T>) EMPTY_ITERATOR;
    }

    public boolean hasNext() {
        try {
            return hasNextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            return nextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        JsonParser jsonParser = this._parser;
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    public boolean hasNextValue() throws IOException {
        JsonToken nextToken;
        JsonParser jsonParser = this._parser;
        if (jsonParser == null) {
            return false;
        }
        if (!this._hasNextChecked) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            this._hasNextChecked = true;
            if (currentToken == null && ((nextToken = this._parser.nextToken()) == null || nextToken == JsonToken.END_ARRAY)) {
                JsonParser jsonParser2 = this._parser;
                this._parser = null;
                if (this._closeParser) {
                    jsonParser2.close();
                }
                return false;
            }
        }
        return true;
    }

    public T nextValue() throws IOException {
        T t;
        if (this._hasNextChecked || hasNextValue()) {
            JsonParser jsonParser = this._parser;
            if (jsonParser != null) {
                this._hasNextChecked = false;
                T t2 = this._updatedValue;
                if (t2 == null) {
                    t = this._deserializer.deserialize(jsonParser, this._context);
                } else {
                    this._deserializer.deserialize(jsonParser, this._context, t2);
                    t = this._updatedValue;
                }
                this._parser.clearCurrentToken();
                return t;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public List<T> readAll() throws IOException {
        return readAll(new ArrayList());
    }

    public List<T> readAll(List<T> list) throws IOException {
        while (hasNextValue()) {
            list.add(nextValue());
        }
        return list;
    }

    public JsonParser getParser() {
        return this._parser;
    }

    public FormatSchema getParserSchema() {
        return this._parser.getSchema();
    }

    public JsonLocation getCurrentLocation() {
        return this._parser.getCurrentLocation();
    }
}
