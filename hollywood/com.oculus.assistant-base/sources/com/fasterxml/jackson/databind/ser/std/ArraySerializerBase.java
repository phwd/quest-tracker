package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0292Po;
import X.AbstractC1012qg;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.C0290Pm;
import X.C1025qv;
import X.O5;
import X.O9;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ArraySerializerBase extends ContainerSerializer {
    public final O5 A00;

    public final void A0D(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        if (this instanceof StdArraySerializers$IntArraySerializer) {
            for (int i : (int[]) obj) {
                qgVar.A0G(i);
            }
        } else if (this instanceof StdArraySerializers$ShortArraySerializer) {
            short[] sArr = (short[]) obj;
            PU pu = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
            int i2 = 0;
            if (pu != null) {
                int length = sArr.length;
                while (i2 < length) {
                    pu.A07(null, qgVar, Short.TYPE);
                    qgVar.A0S(sArr[i2]);
                    pu.A06(null, qgVar);
                    i2++;
                }
                return;
            }
            int length2 = sArr.length;
            while (i2 < length2) {
                qgVar.A0G(sArr[i2]);
                i2++;
            }
        } else if (this instanceof StdArraySerializers$LongArraySerializer) {
            long[] jArr = (long[]) obj;
            PU pu2 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
            int i3 = 0;
            if (pu2 != null) {
                int length3 = jArr.length;
                while (i3 < length3) {
                    pu2.A07(null, qgVar, Long.TYPE);
                    qgVar.A0H(jArr[i3]);
                    pu2.A06(null, qgVar);
                    i3++;
                }
                return;
            }
            int length4 = jArr.length;
            while (i3 < length4) {
                qgVar.A0H(jArr[i3]);
                i3++;
            }
        } else if (this instanceof StdArraySerializers$FloatArraySerializer) {
            float[] fArr = (float[]) obj;
            PU pu3 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
            int i4 = 0;
            if (pu3 != null) {
                int length5 = fArr.length;
                while (i4 < length5) {
                    pu3.A07(null, qgVar, Float.TYPE);
                    qgVar.A0F(fArr[i4]);
                    pu3.A06(null, qgVar);
                    i4++;
                }
                return;
            }
            int length6 = fArr.length;
            while (i4 < length6) {
                qgVar.A0F(fArr[i4]);
                i4++;
            }
        } else if (this instanceof StdArraySerializers$DoubleArraySerializer) {
            for (double d : (double[]) obj) {
                qgVar.A0E(d);
            }
        } else if (this instanceof StdArraySerializers$BooleanArraySerializer) {
            for (boolean z : (boolean[]) obj) {
                qgVar.A0T(z);
            }
        } else if (!(this instanceof ObjectArraySerializer)) {
            StringArraySerializer stringArraySerializer = (StringArraySerializer) this;
            String[] strArr = (String[]) obj;
            int length7 = strArr.length;
            if (length7 != 0) {
                JsonSerializer jsonSerializer = stringArraySerializer.A00;
                if (jsonSerializer != null) {
                    int i5 = 0;
                    do {
                        if (strArr[i5] == null) {
                            r2Var.A0D(qgVar);
                        } else {
                            jsonSerializer.A0B(strArr[i5], qgVar, r2Var);
                        }
                        i5++;
                    } while (i5 < length7);
                    return;
                }
                int i6 = 0;
                do {
                    if (strArr[i6] == null) {
                        qgVar.A0A();
                    } else {
                        qgVar.A0P(strArr[i6]);
                    }
                    i6++;
                } while (i6 < length7);
            }
        } else {
            ObjectArraySerializer objectArraySerializer = (ObjectArraySerializer) this;
            Object[] objArr = (Object[]) obj;
            int length8 = objArr.length;
            if (length8 != 0) {
                JsonSerializer jsonSerializer2 = objectArraySerializer.A00;
                if (jsonSerializer2 != null) {
                    PU pu4 = objectArraySerializer.A03;
                    int i7 = 0;
                    Object obj2 = null;
                    while (true) {
                        try {
                            obj2 = objArr[i7];
                            if (obj2 == null) {
                                r2Var.A0D(qgVar);
                            } else if (pu4 == null) {
                                jsonSerializer2.A0B(obj2, qgVar, r2Var);
                            } else {
                                jsonSerializer2.A09(obj2, qgVar, r2Var, pu4);
                            }
                            i7++;
                            if (i7 >= length8) {
                                return;
                            }
                        } catch (IOException e) {
                            throw e;
                        } catch (Exception e2) {
                            e = e2;
                            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                                e = e.getCause();
                            }
                            if (e instanceof Error) {
                                throw e;
                            }
                            throw C1025qv.A01(e, new O9(obj2, i7));
                        }
                    }
                } else {
                    PU pu5 = objectArraySerializer.A03;
                    if (pu5 != null) {
                        int i8 = 0;
                        try {
                            AbstractC0292Po po = objectArraySerializer.A01;
                            while (true) {
                                Object obj3 = objArr[i8];
                                if (obj3 == null) {
                                    r2Var.A0D(qgVar);
                                } else {
                                    Class<?> cls = obj3.getClass();
                                    JsonSerializer A002 = po.A00(cls);
                                    if (A002 == null) {
                                        JsonSerializer A0A = r2Var.A0A(cls, ((ArraySerializerBase) objectArraySerializer).A00);
                                        C0290Pm pm = new C0290Pm(A0A, po.A01(cls, A0A));
                                        AbstractC0292Po po2 = pm.A01;
                                        if (po != po2) {
                                            objectArraySerializer.A01 = po2;
                                        }
                                        A002 = pm.A00;
                                    }
                                    A002.A09(obj3, qgVar, r2Var, pu5);
                                }
                                i8++;
                                if (i8 >= length8) {
                                    return;
                                }
                            }
                        } catch (IOException e3) {
                            throw e3;
                        } catch (Exception e4) {
                            e = e4;
                            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                                e = e.getCause();
                            }
                            if (e instanceof Error) {
                                throw e;
                            }
                            throw C1025qv.A01(e, new O9((Object) null, 0));
                        }
                    } else {
                        int i9 = 0;
                        try {
                            AbstractC0292Po po3 = objectArraySerializer.A01;
                            while (true) {
                                Object obj4 = objArr[i9];
                                if (obj4 == null) {
                                    r2Var.A0D(qgVar);
                                } else {
                                    Class<?> cls2 = obj4.getClass();
                                    JsonSerializer A003 = po3.A00(cls2);
                                    if (A003 == null) {
                                        AbstractC1024qt qtVar = objectArraySerializer.A02;
                                        if (qtVar.A0F()) {
                                            AbstractC1024qt A03 = r2Var.A03(qtVar, cls2);
                                            JsonSerializer A08 = r2Var.A08(A03, ((ArraySerializerBase) objectArraySerializer).A00);
                                            C0290Pm pm2 = new C0290Pm(A08, po3.A01(A03._class, A08));
                                            AbstractC0292Po po4 = pm2.A01;
                                            if (po3 != po4) {
                                                objectArraySerializer.A01 = po4;
                                            }
                                            A003 = pm2.A00;
                                        } else {
                                            JsonSerializer A0A2 = r2Var.A0A(cls2, ((ArraySerializerBase) objectArraySerializer).A00);
                                            C0290Pm pm3 = new C0290Pm(A0A2, po3.A01(cls2, A0A2));
                                            AbstractC0292Po po5 = pm3.A01;
                                            if (po3 != po5) {
                                                objectArraySerializer.A01 = po5;
                                            }
                                            A003 = pm3.A00;
                                        }
                                    }
                                    A003.A0B(obj4, qgVar, r2Var);
                                }
                                i9++;
                                if (i9 >= length8) {
                                    return;
                                }
                            }
                        } catch (IOException e5) {
                            throw e5;
                        } catch (Exception e6) {
                            e = e6;
                            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                                e = e.getCause();
                            }
                            if (e instanceof Error) {
                                throw e;
                            }
                            throw C1025qv.A01(e, new O9((Object) null, 0));
                        }
                    }
                }
            }
        }
    }

    public ArraySerializerBase(ArraySerializerBase arraySerializerBase, O5 o5) {
        super(((StdSerializer) arraySerializerBase).A00, false);
        this.A00 = o5;
    }

    public ArraySerializerBase(Class cls) {
        super(cls);
        this.A00 = null;
    }

    public ArraySerializerBase(Class cls, O5 o5) {
        super(cls);
        this.A00 = null;
    }
}
