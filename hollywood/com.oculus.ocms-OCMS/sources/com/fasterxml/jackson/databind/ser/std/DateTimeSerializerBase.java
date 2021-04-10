package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T> extends StdScalarSerializer<T> implements ContextualSerializer {
    protected final DateFormat _customFormat;
    protected final boolean _useTimestamp;

    /* access modifiers changed from: protected */
    public abstract long _timestamp(T t);

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    public abstract DateTimeSerializerBase<T> withFormat(boolean z, DateFormat dateFormat);

    protected DateTimeSerializerBase(Class<T> cls, boolean z, DateFormat dateFormat) {
        super(cls);
        this._useTimestamp = z;
        this._customFormat = dateFormat;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value findFormat;
        DateFormat dateFormat;
        if (!(beanProperty == null || (findFormat = serializerProvider.getAnnotationIntrospector().findFormat((Annotated) beanProperty.getMember())) == null)) {
            if (findFormat.getShape().isNumeric()) {
                return withFormat(true, null);
            }
            TimeZone timeZone = findFormat.getTimeZone();
            String pattern = findFormat.getPattern();
            if (pattern.length() > 0) {
                Locale locale = findFormat.getLocale();
                if (locale == null) {
                    locale = serializerProvider.getLocale();
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
                if (timeZone == null) {
                    timeZone = serializerProvider.getTimeZone();
                }
                simpleDateFormat.setTimeZone(timeZone);
                return withFormat(false, simpleDateFormat);
            } else if (timeZone != null) {
                DateFormat dateFormat2 = serializerProvider.getConfig().getDateFormat();
                if (dateFormat2.getClass() == StdDateFormat.class) {
                    dateFormat = StdDateFormat.getISO8601Format(timeZone);
                } else {
                    dateFormat = (DateFormat) dateFormat2.clone();
                    dateFormat.setTimeZone(timeZone);
                }
                return withFormat(false, dateFormat);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(T t) {
        return t == null || _timestamp(t) == 0;
    }

    @Override // com.fasterxml.jackson.databind.jsonschema.SchemaAware, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        boolean z = this._useTimestamp;
        if (!z && this._customFormat == null) {
            z = serializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return createSchemaNode(z ? "number" : "string", true);
    }

    @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        boolean z = this._useTimestamp;
        if (!z && this._customFormat == null) {
            z = jsonFormatVisitorWrapper.getProvider().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        if (z) {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.LONG);
                expectIntegerFormat.format(JsonValueFormat.UTC_MILLISEC);
                return;
            }
            return;
        }
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType);
        if (expectStringFormat != null) {
            expectStringFormat.format(JsonValueFormat.DATE_TIME);
        }
    }
}
