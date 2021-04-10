package com.facebook.common.json;

import androidx.annotation.VisibleForTesting;
import com.facebook.proguard.annotations.DoNotStrip;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
public abstract class FbJsonField {
    protected final Field mField;
    protected final Method mSetter;

    @DoNotStrip
    public abstract void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public Field getField() {
        return this.mField;
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field) {
        return jsonField(field, (Class<?>) null, (TypeReference<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field, Class<?> cls) {
        return jsonField(field, cls, (TypeReference<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field, TypeReference<?> typeReference) {
        return jsonField(field, (Class<?>) null, typeReference);
    }

    @DoNotStrip
    private static FbJsonField jsonField(Field field, @Nullable Class<?> cls, @Nullable TypeReference<?> typeReference) {
        Class<?> type = field.getType();
        if (type == String.class) {
            return new StringJsonField(field, null);
        }
        if (type == Integer.TYPE) {
            return new IntJsonField(field, null);
        }
        if (type == Long.TYPE) {
            return new LongJsonField(field, null);
        }
        if (type == Boolean.TYPE) {
            return new BoolJsonField(field, null);
        }
        if (type == Float.TYPE) {
            return new FloatJsonField(field, null);
        }
        if (type == Double.TYPE) {
            return new DoubleJsonField(field, null);
        }
        if (type == ImmutableList.class) {
            return new ImmutableListJsonField(field, null, cls, typeReference);
        }
        if (type == List.class || type == ArrayList.class) {
            return new ListJsonField(field, null, cls, typeReference);
        }
        return new BeanJsonField(field, null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method) {
        return jsonField(method, (Class<?>) null, (TypeReference<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method, Class<?> cls) {
        return jsonField(method, cls, (TypeReference<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method, TypeReference<?> typeReference) {
        return jsonField(method, (Class<?>) null, typeReference);
    }

    @DoNotStrip
    private static FbJsonField jsonField(Method method, @Nullable Class<?> cls, @Nullable TypeReference<?> typeReference) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new RuntimeException("Invalid setter type " + method.getName() + " Only setter with on input parameter is supported.");
        } else if (parameterTypes[0] == String.class) {
            return new StringJsonField(null, method);
        } else {
            if (parameterTypes[0] == Integer.TYPE) {
                return new IntJsonField(null, method);
            }
            if (parameterTypes[0] == Long.TYPE) {
                return new LongJsonField(null, method);
            }
            if (parameterTypes[0] == Boolean.TYPE) {
                return new BoolJsonField(null, method);
            }
            if (parameterTypes[0] == Float.TYPE) {
                return new FloatJsonField(null, method);
            }
            if (parameterTypes[0] == Double.TYPE) {
                return new DoubleJsonField(null, method);
            }
            if (parameterTypes[0] == ImmutableList.class) {
                return new ImmutableListJsonField(null, method, cls, typeReference);
            }
            if (parameterTypes[0] == List.class || parameterTypes[0] == ArrayList.class) {
                return new ListJsonField(null, method, cls, typeReference);
            }
            return new BeanJsonField(null, method);
        }
    }

    @DoNotStrip
    public static FbJsonField jsonFieldWithCreator(Field field) {
        return new BeanJsonField(field, null);
    }

    protected FbJsonField(@Nullable Field field, @Nullable Method method) {
        this.mField = field;
        this.mSetter = method;
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class IntJsonField extends FbJsonField {
        public IntJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                int valueAsInt = jsonParser.getValueAsInt();
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, Integer.valueOf(valueAsInt));
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.setInt(obj, valueAsInt);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class LongJsonField extends FbJsonField {
        public LongJsonField(@Nullable Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                long valueAsLong = jsonParser.getValueAsLong();
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, Long.valueOf(valueAsLong));
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.setLong(obj, valueAsLong);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class FloatJsonField extends FbJsonField {
        public FloatJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            float f = 0.0f;
            try {
                if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                    jsonParser.skipChildren();
                } else if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING || !"NaN".equals(jsonParser.getValueAsString())) {
                    f = jsonParser.getFloatValue();
                } else {
                    f = Float.NaN;
                }
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, Float.valueOf(f));
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.setFloat(obj, f);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class DoubleJsonField extends FbJsonField {
        public DoubleJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            double d = 0.0d;
            try {
                if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                    jsonParser.skipChildren();
                } else if (jsonParser.getCurrentToken() != JsonToken.VALUE_STRING || !"NaN".equals(jsonParser.getValueAsString())) {
                    d = jsonParser.getValueAsDouble();
                } else {
                    d = Double.NaN;
                }
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, Double.valueOf(d));
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.setDouble(obj, d);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class BoolJsonField extends FbJsonField {
        public BoolJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                boolean valueAsBoolean = jsonParser.getValueAsBoolean();
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, Boolean.valueOf(valueAsBoolean));
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.setBoolean(obj, valueAsBoolean);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    public static final class StringJsonField extends FbJsonField {
        public StringJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                String readStringValue = AutoGenJsonHelper.readStringValue(jsonParser);
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, readStringValue);
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.set(obj, readStringValue);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    @DoNotStrip
    public static final class ListJsonField extends FbJsonField {
        private JsonDeserializer<List<?>> mDeserializer;
        private TypeReference<?> mGenericInnerListType;
        private Class<?> mSimpleInnerListType;

        public ListJsonField(@Nullable Field field, @Nullable Method method, Class<?> cls, @Nullable TypeReference<?> typeReference) {
            super(field, method);
            this.mSimpleInnerListType = cls;
            this.mGenericInnerListType = typeReference;
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            List<?> list;
            try {
                if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                    list = Lists.newArrayList();
                } else {
                    if (this.mDeserializer == null) {
                        if (this.mSimpleInnerListType != null) {
                            this.mDeserializer = new ArrayListDeserializer(this.mSimpleInnerListType);
                        } else if (this.mGenericInnerListType != null) {
                            this.mDeserializer = new ArrayListDeserializer(((FbObjectMapper) jsonParser.getCodec()).findDeserializer(deserializationContext, this.mGenericInnerListType.getType()));
                        } else {
                            throw new IllegalArgumentException("Need to set simple or generic inner list type!");
                        }
                    }
                    list = this.mDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, list);
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.set(obj, list);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    @DoNotStrip
    public static final class ImmutableListJsonField extends FbJsonField {
        private TypeReference<?> mGenericInnerListType;
        private Class<?> mSimpleInnerListType;

        public ImmutableListJsonField(Field field, @Nullable Method method, Class<?> cls, @Nullable TypeReference<?> typeReference) {
            super(field, method);
            this.mSimpleInnerListType = cls;
            this.mGenericInnerListType = typeReference;
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                ImmutableList<?> readImmutableListValue = AutoGenJsonHelper.readImmutableListValue(jsonParser, deserializationContext, this.mSimpleInnerListType, this.mGenericInnerListType);
                if (this.mSetter != null) {
                    this.mSetter.setAccessible(true);
                    this.mSetter.invoke(obj, readImmutableListValue);
                    return;
                }
                this.mField.setAccessible(true);
                this.mField.set(obj, readImmutableListValue);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }

    @DoNotStrip
    @VisibleForTesting
    public static class BeanJsonField extends FbJsonField {
        public BeanJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            try {
                Object readBeanObject = AutoGenJsonHelper.readBeanObject(this.mSetter != null ? this.mSetter.getGenericParameterTypes()[0] : this.mField.getGenericType(), jsonParser, deserializationContext);
                if (readBeanObject != null) {
                    if (this.mSetter != null) {
                        this.mSetter.setAccessible(true);
                        this.mSetter.invoke(obj, readBeanObject);
                        return;
                    }
                    this.mField.setAccessible(true);
                    this.mField.set(obj, readBeanObject);
                }
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                throw Throwables.propagate(e);
            }
        }
    }
}
