package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization = false;
    private String datePattern;
    private int dateStyle = 2;
    private boolean escapeHtmlChars = true;
    private Excluder excluder = Excluder.DEFAULT;
    private final List<TypeAdapterFactory> factories = new ArrayList();
    private FieldNamingStrategy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private boolean generateNonExecutableJson = false;
    private final List<TypeAdapterFactory> hierarchyFactories = new ArrayList();
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();
    private boolean lenient = false;
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private boolean prettyPrinting = false;
    private boolean serializeNulls = false;
    private boolean serializeSpecialFloatingPointValues = false;
    private int timeStyle = 2;

    public GsonBuilder() {
    }

    GsonBuilder(Gson gson) {
        this.excluder = gson.excluder;
        this.fieldNamingPolicy = gson.fieldNamingStrategy;
        this.instanceCreators.putAll(gson.instanceCreators);
        this.serializeNulls = gson.serializeNulls;
        this.complexMapKeySerialization = gson.complexMapKeySerialization;
        this.generateNonExecutableJson = gson.generateNonExecutableJson;
        this.escapeHtmlChars = gson.htmlSafe;
        this.prettyPrinting = gson.prettyPrinting;
        this.lenient = gson.lenient;
        this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson.longSerializationPolicy;
        this.datePattern = gson.datePattern;
        this.dateStyle = gson.dateStyle;
        this.timeStyle = gson.timeStyle;
        this.factories.addAll(gson.builderFactories);
        this.hierarchyFactories.addAll(gson.builderHierarchyFactories);
    }

    public GsonBuilder setVersion(double ignoreVersionsAfter) {
        this.excluder = this.excluder.withVersion(ignoreVersionsAfter);
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
        this.excluder = this.excluder.withModifiers(modifiers);
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
        this.longSerializationPolicy = serializationPolicy;
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
        this.fieldNamingPolicy = namingConvention;
        return this;
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
        for (ExclusionStrategy strategy : strategies) {
            this.excluder = this.excluder.withExclusionStrategy(strategy, true, true);
        }
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, true, false);
        return this;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, false, true);
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public GsonBuilder setLenient() {
        this.lenient = true;
        return this;
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder setDateFormat(String pattern) {
        this.datePattern = pattern;
        return this;
    }

    public GsonBuilder setDateFormat(int style) {
        this.dateStyle = style;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int dateStyle2, int timeStyle2) {
        this.dateStyle = dateStyle2;
        this.timeStyle = timeStyle2;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type type, Object typeAdapter) {
        C$Gson$Preconditions.checkArgument((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof InstanceCreator) || (typeAdapter instanceof TypeAdapter));
        if (typeAdapter instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator) typeAdapter);
        }
        if ((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer)) {
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        this.factories.add(factory);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> baseType, Object typeAdapter) {
        C$Gson$Preconditions.checkArgument((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof TypeAdapter));
        if ((typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof JsonSerializer)) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            this.factories.add(TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public Gson create() {
        List<TypeAdapterFactory> factories2 = new ArrayList<>(this.factories.size() + this.hierarchyFactories.size() + 3);
        factories2.addAll(this.factories);
        Collections.reverse(factories2);
        List<TypeAdapterFactory> hierarchyFactories2 = new ArrayList<>(this.hierarchyFactories);
        Collections.reverse(hierarchyFactories2);
        factories2.addAll(hierarchyFactories2);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, factories2);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, this.factories, this.hierarchyFactories, factories2);
    }

    private void addTypeAdaptersForDate(String datePattern2, int dateStyle2, int timeStyle2, List<TypeAdapterFactory> factories2) {
        DefaultDateTypeAdapter dateTypeAdapter;
        TypeAdapter<Timestamp> timestampTypeAdapter;
        TypeAdapter<Date> javaSqlDateTypeAdapter;
        if (datePattern2 != null && !"".equals(datePattern2.trim())) {
            dateTypeAdapter = new DefaultDateTypeAdapter(java.util.Date.class, datePattern2);
            timestampTypeAdapter = new DefaultDateTypeAdapter(Timestamp.class, datePattern2);
            javaSqlDateTypeAdapter = new DefaultDateTypeAdapter(Date.class, datePattern2);
        } else if (dateStyle2 != 2 && timeStyle2 != 2) {
            dateTypeAdapter = new DefaultDateTypeAdapter(java.util.Date.class, dateStyle2, timeStyle2);
            timestampTypeAdapter = new DefaultDateTypeAdapter(Timestamp.class, dateStyle2, timeStyle2);
            javaSqlDateTypeAdapter = new DefaultDateTypeAdapter(Date.class, dateStyle2, timeStyle2);
        } else {
            return;
        }
        factories2.add(TypeAdapters.newFactory(java.util.Date.class, dateTypeAdapter));
        factories2.add(TypeAdapters.newFactory(Timestamp.class, timestampTypeAdapter));
        factories2.add(TypeAdapters.newFactory(Date.class, javaSqlDateTypeAdapter));
    }
}
