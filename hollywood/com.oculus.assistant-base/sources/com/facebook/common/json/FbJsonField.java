package com.facebook.common.json;

import X.AbstractC0257Nv;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass08;
import X.C1013qh;
import X.NX;
import X.VD;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class FbJsonField {
    public final Field A00;
    public final Method A01;

    public class BeanJsonField extends FbJsonField {
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0055 A[Catch:{ Exception -> 0x0061 }] */
        @Override // com.facebook.common.json.FbJsonField
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void deserialize(java.lang.Object r8, X.AbstractC1014qi r9, X.AbstractC1022qr r10) {
            /*
            // Method dump skipped, instructions count: 114
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.json.FbJsonField.BeanJsonField.deserialize(java.lang.Object, X.qi, X.qr):void");
        }

        public BeanJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public final class BoolJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            boolean A0f;
            try {
                if (!(qiVar instanceof VD)) {
                    A0f = qiVar.A0k(false);
                } else {
                    A0f = ((VD) qiVar).A00.A0f();
                }
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Boolean.valueOf(A0f));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setBoolean(obj, A0f);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public BoolJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public final class DoubleJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            double d = 0.0d;
            try {
                NX A0U = qiVar.A0U();
                if (A0U == NX.VALUE_NULL) {
                    qiVar.A0T();
                } else if (A0U == NX.VALUE_STRING && "NaN".equals(qiVar.A0n())) {
                    d = Double.NaN;
                } else if (!(qiVar instanceof VD)) {
                    d = qiVar.A0H(0.0d);
                } else {
                    d = ((VD) qiVar).A00.A0G();
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

        public DoubleJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public final class ImmutableListJsonField extends FbJsonField {
        public AbstractC0257Nv A00;
        public Class A01;

        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            ImmutableList immutableList;
            try {
                Class cls = this.A01;
                if (qiVar.A0U() == NX.VALUE_NULL) {
                    immutableList = ImmutableList.of();
                } else if (cls != null) {
                    immutableList = (ImmutableList) new ImmutableListDeserializer().A0C(qiVar, qrVar);
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

        public ImmutableListJsonField(Field field, Method method, Class cls, AbstractC0257Nv nv) {
            super(field, method);
            this.A01 = cls;
            this.A00 = nv;
        }
    }

    public static FbJsonField jsonFieldWithCreator(Field field) {
        return new BeanJsonField(field, null);
    }

    public abstract void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar);

    public final class IntJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            try {
                int A0M = qiVar.A0M();
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Integer.valueOf(A0M));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setInt(obj, A0M);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public IntJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public final class ListJsonField extends FbJsonField {
        public AbstractC0257Nv A00;
        public JsonDeserializer A01;
        public Class A02;

        public ListJsonField(Field field, Method method, Class cls, AbstractC0257Nv nv) {
            super(field, method);
            this.A02 = cls;
            this.A00 = nv;
        }

        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            Object obj2;
            try {
                if (qiVar.A0U() == NX.VALUE_NULL) {
                    obj2 = new ArrayList();
                } else {
                    JsonDeserializer jsonDeserializer = this.A01;
                    if (jsonDeserializer == null) {
                        if (this.A02 != null) {
                            jsonDeserializer = new ArrayListDeserializer();
                            this.A01 = jsonDeserializer;
                        } else {
                            throw new IllegalArgumentException("Need to set simple or generic inner list type!");
                        }
                    }
                    obj2 = (List) jsonDeserializer.A0C(qiVar, qrVar);
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

    public final class LongJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            try {
                long A0P = qiVar.A0P();
                Method method = this.A01;
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(obj, Long.valueOf(A0P));
                    return;
                }
                Field field = this.A00;
                field.setAccessible(true);
                field.setLong(obj, A0P);
            } catch (Exception e) {
                Throwables.propagateIfPossible(e, IOException.class);
                Throwables.propagate(e);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }

        public LongJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public final class StringJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            String str;
            try {
                if (qiVar.A0U() == NX.VALUE_NULL) {
                    qiVar.A0T();
                    str = null;
                } else {
                    str = qiVar.A0p();
                    if (str == null) {
                        throw new C1013qh("Failed to read text from Json stream", qiVar.A0R());
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

        public StringJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public FbJsonField(Field field, Method method) {
        this.A00 = field;
        this.A01 = method;
    }

    public final class FloatJsonField extends FbJsonField {
        @Override // com.facebook.common.json.FbJsonField
        public void deserialize(Object obj, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            float f = 0.0f;
            try {
                NX A0U = qiVar.A0U();
                if (A0U == NX.VALUE_NULL) {
                    qiVar.A0T();
                } else if (A0U != NX.VALUE_STRING || !"NaN".equals(qiVar.A0n())) {
                    f = qiVar.A0I();
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

        public FloatJsonField(Field field, Method method) {
            super(field, method);
        }
    }

    public static FbJsonField jsonField(Field field) {
        return jsonField(field, (Class) null, (AbstractC0257Nv) null);
    }

    public static FbJsonField jsonField(Field field, AbstractC0257Nv nv) {
        return jsonField(field, (Class) null, nv);
    }

    public static FbJsonField jsonField(Field field, Class cls) {
        return jsonField(field, cls, (AbstractC0257Nv) null);
    }

    public static FbJsonField jsonField(Field field, Class cls, AbstractC0257Nv nv) {
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
            return new ImmutableListJsonField(field, null, cls, nv);
        }
        if (type == List.class || type == ArrayList.class) {
            return new ListJsonField(field, null, cls, nv);
        }
        return new BeanJsonField(field, null);
    }

    public static FbJsonField jsonField(Method method) {
        return jsonField(method, (Class) null, (AbstractC0257Nv) null);
    }

    public static FbJsonField jsonField(Method method, AbstractC0257Nv nv) {
        return jsonField(method, (Class) null, nv);
    }

    public static FbJsonField jsonField(Method method, Class cls) {
        return jsonField(method, cls, (AbstractC0257Nv) null);
    }

    public static FbJsonField jsonField(Method method, Class cls, AbstractC0257Nv nv) {
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
                return new ImmutableListJsonField(null, method, cls, nv);
            }
            if (cls2 == List.class || cls2 == ArrayList.class) {
                return new ListJsonField(null, method, cls, nv);
            }
            return new BeanJsonField(null, method);
        }
        throw new RuntimeException(AnonymousClass08.A05("Invalid setter type ", method.getName(), " Only setter with on input parameter is supported."));
    }
}
