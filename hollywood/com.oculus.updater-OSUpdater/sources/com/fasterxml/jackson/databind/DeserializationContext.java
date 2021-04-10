package com.fasterxml.jackson.databind;

import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = -7727373309391091315L;
    protected transient ArrayBuilders _arrayBuilders;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    @Override // com.fasterxml.jackson.databind.DatabindContext
    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (deserializationFeature.getMask() & this._featureFlags) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        InjectableValues injectableValues = this._injectableValues;
        if (injectableValues != null) {
            return injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
        }
        throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return (findValueDeserializer == null || !(findValueDeserializer instanceof ContextualDeserializer)) ? findValueDeserializer : ((ContextualDeserializer) findValueDeserializer).createContextual(this, beanProperty);
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<?> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer == null) {
            return null;
        }
        if (findValueDeserializer instanceof ContextualDeserializer) {
            findValueDeserializer = ((ContextualDeserializer) findValueDeserializer).createContextual(this, null);
        }
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return findTypeDeserializer != null ? new TypeWrappedDeserializer(findTypeDeserializer.forProperty(null), findValueDeserializer) : findValueDeserializer;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer findKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        return findKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) findKeyDeserializer).createContextual(this, beanProperty) : findKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return ClassUtil.findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse Date value '" + str + "': " + e.getMessage());
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        instance.setTime(date);
        return instance;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers == null) {
            return false;
        }
        while (problemHandlers != null) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
            problemHandlers = problemHandlers.next();
        }
        return false;
    }

    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Collection<Object> collection;
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            if (jsonDeserializer == null) {
                collection = null;
            } else {
                collection = jsonDeserializer.getKnownPropertyNames();
            }
            throw UnrecognizedPropertyException.from(this._parser, obj, str, collection);
        }
    }

    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        String _calcName = _calcName(cls);
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not deserialize instance of " + _calcName + " out of " + jsonToken + " token");
    }

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + ", problem: " + th.getMessage(), th);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + ", problem: " + str);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        JsonParser jsonParser = this._parser;
        return InvalidFormatException.from(jsonParser, "Can not construct instance of " + cls.getName() + " from String value '" + _valueDesc() + "': " + str2, str, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        return InvalidFormatException.from(jsonParser, "Can not construct instance of " + cls.getName() + " from number value (" + _valueDesc() + "): " + str, null, cls);
    }

    public JsonMappingException endOfInputException(Class<?> cls) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Unexpected end-of-input when trying to deserialize a " + cls.getName());
    }

    /* access modifiers changed from: protected */
    public DateFormat getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }

    /* access modifiers changed from: protected */
    public String _calcName(Class<?> cls) {
        if (!cls.isArray()) {
            return cls.getName();
        }
        return _calcName(cls.getComponentType()) + "[]";
    }

    /* access modifiers changed from: protected */
    public String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception unused) {
            return "[N/A]";
        }
    }

    /* access modifiers changed from: protected */
    public String _desc(String str) {
        if (str.length() <= 500) {
            return str;
        }
        return str.substring(0, ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS) + "]...[" + str.substring(str.length() - ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
    }
}
