package com.facebook.common.json;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC03920on;
import X.AnonymousClass006;
import X.C02290iR;
import X.EnumC03640oE;
import androidx.annotation.VisibleForTesting;
import com.facebook.proguard.annotations.DoNotStrip;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
public abstract class FbJsonField {
    public final Field A00;
    public final Method A01;

    @DoNotStrip
    @VisibleForTesting
    public static class BeanJsonField extends FbJsonField {
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0057 A[Catch:{ Exception -> 0x0082 }] */
        @Override // com.facebook.common.json.FbJsonField
        @com.facebook.proguard.annotations.DoNotStrip
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void deserialize(java.lang.Object r9, X.AbstractC02280iQ r10, X.AbstractC02210iH r11) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 147
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.json.FbJsonField.BeanJsonField.deserialize(java.lang.Object, X.0iQ, X.0iH):void");
        }

        public BeanJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static final class DoubleJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException {
            double d = 0.0d;
            try {
                EnumC03640oE A0i = r7.A0i();
                if (A0i == EnumC03640oE.VALUE_NULL) {
                    r7.A0h();
                } else if (A0i != EnumC03640oE.VALUE_STRING || !"NaN".equals(r7.A0P())) {
                    d = r7.A0F();
                } else {
                    d = Double.NaN;
                }
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Double.valueOf(d));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setDouble(obj, d);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public DoubleJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static final class ImmutableListJsonField extends FbJsonField {
        public AbstractC03920on<?> A00;
        public Class<?> A01;

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            ImmutableList immutableList;
            try {
                Class<?> cls = this.A01;
                if (r6.A0i() == EnumC03640oE.VALUE_NULL) {
                    immutableList = ImmutableList.of();
                } else if (cls != null) {
                    immutableList = (ImmutableList) new ImmutableListDeserializer(cls).A0A(r6, r7);
                } else {
                    throw new IllegalArgumentException("Need to set simple or generic inner list type!");
                }
                Method method = super.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, immutableList);
                    return;
                }
                Field field = super.A00;
                field.setAccessible(true);
                field.set(obj, immutableList);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public ImmutableListJsonField(Field field, @Nullable Method method, Class<?> cls, @Nullable AbstractC03920on<?> r4) {
            super(field, method);
            this.A01 = cls;
            this.A00 = r4;
        }
    }

    @DoNotStrip
    public static FbJsonField jsonFieldWithCreator(Field field) {
        return new BeanJsonField(field, null);
    }

    @DoNotStrip
    public abstract void deserialize(Object obj, AbstractC02280iQ v, AbstractC02210iH v2) throws IOException;

    @DoNotStrip
    public static final class BoolJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            try {
                boolean A0J = r6.A0J();
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Boolean.valueOf(A0J));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setBoolean(obj, A0J);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public BoolJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static final class IntJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            try {
                int A0G = r6.A0G();
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Integer.valueOf(A0G));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setInt(obj, A0G);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public IntJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static final class ListJsonField extends FbJsonField {
        public AbstractC03920on<?> A00;
        public JsonDeserializer<List<?>> A01;
        public Class<?> A02;

        public ListJsonField(@Nullable Field field, @Nullable Method method, Class<?> cls, @Nullable AbstractC03920on<?> r4) {
            super(field, method);
            this.A02 = cls;
            this.A00 = r4;
        }

        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            Object obj2;
            try {
                if (r6.A0i() == EnumC03640oE.VALUE_NULL) {
                    obj2 = new ArrayList();
                } else {
                    JsonDeserializer jsonDeserializer = this.A01;
                    if (jsonDeserializer == null) {
                        Class<?> cls = this.A02;
                        if (cls != null) {
                            jsonDeserializer = new ArrayListDeserializer(cls);
                            this.A01 = jsonDeserializer;
                        } else {
                            throw new IllegalArgumentException("Need to set simple or generic inner list type!");
                        }
                    }
                    obj2 = (List) jsonDeserializer.A0A(r6, r7);
                }
                Method method = super.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, obj2);
                    return;
                }
                Field field = super.A00;
                field.setAccessible(true);
                field.set(obj, obj2);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @DoNotStrip
    public static final class LongJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException {
            try {
                long A0H = r7.A0H();
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Long.valueOf(A0H));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setLong(obj, A0H);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public LongJsonField(@Nullable Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static final class StringJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            String str;
            try {
                if (r6.A0i() == EnumC03640oE.VALUE_NULL) {
                    r6.A0h();
                    str = null;
                } else {
                    str = r6.A0m();
                    if (str == null) {
                        throw new C02290iR("Failed to read text from Json stream", r6.A0V());
                    }
                }
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, str);
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.set(obj, str);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public StringJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    public FbJsonField(@Nullable Field field, @Nullable Method method) {
        this.A00 = field;
        this.A01 = method;
    }

    @DoNotStrip
    public static final class FloatJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        @DoNotStrip
        public void deserialize(Object obj, AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException {
            float f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            try {
                EnumC03640oE A0i = r6.A0i();
                if (A0i == EnumC03640oE.VALUE_NULL) {
                    r6.A0h();
                } else if (A0i != EnumC03640oE.VALUE_STRING || !"NaN".equals(r6.A0P())) {
                    f = r6.A0S();
                } else {
                    f = Float.NaN;
                }
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Float.valueOf(f));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setFloat(obj, f);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public FloatJsonField(Field field, @Nullable Method method) {
            super(field, method);
        }
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field) {
        return jsonField(field, (Class<?>) null, (AbstractC03920on<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field, AbstractC03920on<?> r2) {
        return jsonField(field, (Class<?>) null, r2);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field, Class<?> cls) {
        return jsonField(field, cls, (AbstractC03920on<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Field field, @Nullable Class<?> cls, @Nullable AbstractC03920on<?> r5) {
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
            return new ImmutableListJsonField(field, null, cls, r5);
        }
        if (type == List.class || type == ArrayList.class) {
            return new ListJsonField(field, null, cls, r5);
        }
        return new BeanJsonField(field, null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method) {
        return jsonField(method, (Class<?>) null, (AbstractC03920on<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method, AbstractC03920on<?> r2) {
        return jsonField(method, (Class<?>) null, r2);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method, Class<?> cls) {
        return jsonField(method, cls, (AbstractC03920on<?>) null);
    }

    @DoNotStrip
    public static FbJsonField jsonField(Method method, @Nullable Class<?> cls, @Nullable AbstractC03920on<?> r5) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
            Class<?> cls2 = parameterTypes[0];
            if (cls2 == String.class) {
                return new StringJsonField(null, method);
            }
            if (cls2 == Integer.TYPE) {
                return new IntJsonField(null, method);
            }
            if (cls2 == Long.TYPE) {
                return new LongJsonField(null, method);
            }
            if (cls2 == Boolean.TYPE) {
                return new BoolJsonField(null, method);
            }
            if (cls2 == Float.TYPE) {
                return new FloatJsonField(null, method);
            }
            if (cls2 == Double.TYPE) {
                return new DoubleJsonField(null, method);
            }
            if (cls2 == ImmutableList.class) {
                return new ImmutableListJsonField(null, method, cls, r5);
            }
            if (cls2 == List.class || cls2 == ArrayList.class) {
                return new ListJsonField(null, method, cls, r5);
            }
            return new BeanJsonField(null, method);
        }
        throw new RuntimeException(AnonymousClass006.A09("Invalid setter type ", method.getName(), " Only setter with on input parameter is supported."));
    }
}
