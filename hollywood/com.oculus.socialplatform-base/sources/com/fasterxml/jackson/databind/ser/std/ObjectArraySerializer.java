package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AbstractC04690qz;
import X.C01810h8;
import X.C02180iD;
import X.C02310iT;
import X.C04030p3;
import X.C04670qx;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@JacksonStdImpl
public class ObjectArraySerializer extends ArraySerializerBase<Object[]> implements AbstractC04600qk {
    public JsonSerializer<Object> A00;
    public AbstractC04690qz A01;
    public final AbstractC04550qd A02;
    public final AbstractC02190iF A03;
    public final boolean A04;

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r5) {
        return new ObjectArraySerializer(this.A03, this.A04, r5, this.A00);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((Object[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A05(Object[] objArr, AbstractC02300iS r10, AbstractC02120i3 r11) throws IOException, C02310iT {
        Object[] objArr2 = objArr;
        int length = objArr2.length;
        if (length != 0) {
            JsonSerializer<Object> jsonSerializer = this.A00;
            if (jsonSerializer != null) {
                AbstractC04550qd r0 = this.A02;
                int i = 0;
                Object obj = null;
                while (true) {
                    try {
                        obj = objArr2[i];
                        if (obj == null) {
                            r11.A0E(r10);
                        } else if (r0 == null) {
                            jsonSerializer.serialize(obj, r10, r11);
                        } else {
                            jsonSerializer.serializeWithType(obj, r10, r11, r0);
                        }
                        i++;
                        if (i >= length) {
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
                        throw C02180iD.A01(e, new C04030p3(obj, i));
                    }
                }
            } else {
                AbstractC04550qd r7 = this.A02;
                if (r7 != null) {
                    int i2 = 0;
                    try {
                        AbstractC04690qz r5 = this.A01;
                        while (true) {
                            Object obj2 = objArr2[i2];
                            if (obj2 == null) {
                                r11.A0E(r10);
                            } else {
                                Class<?> cls = obj2.getClass();
                                JsonSerializer<Object> A002 = r5.A00(cls);
                                if (A002 == null) {
                                    JsonSerializer<Object> A0B = r11.A0B(cls, ((ArraySerializerBase) this).A00);
                                    C04670qx r1 = new C04670qx(A0B, r5.A01(cls, A0B));
                                    AbstractC04690qz r02 = r1.A01;
                                    if (r5 != r02) {
                                        this.A01 = r02;
                                    }
                                    A002 = r1.A00;
                                }
                                A002.serializeWithType(obj2, r10, r11, r7);
                            }
                            i2++;
                            if (i2 >= length) {
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
                        throw C02180iD.A01(e, new C04030p3((Object) null, 0));
                    }
                } else {
                    int i3 = 0;
                    try {
                        AbstractC04690qz r52 = this.A01;
                        while (true) {
                            Object obj3 = objArr2[i3];
                            if (obj3 == null) {
                                r11.A0E(r10);
                            } else {
                                Class<?> cls2 = obj3.getClass();
                                JsonSerializer<Object> A003 = r52.A00(cls2);
                                if (A003 == null) {
                                    AbstractC02190iF r12 = this.A03;
                                    if (r12.A0H()) {
                                        AbstractC02190iF A042 = r11.A04(r12, cls2);
                                        JsonSerializer<Object> A09 = r11.A09(A042, ((ArraySerializerBase) this).A00);
                                        C04670qx r13 = new C04670qx(A09, r52.A01(A042._class, A09));
                                        AbstractC04690qz r03 = r13.A01;
                                        if (r52 != r03) {
                                            this.A01 = r03;
                                        }
                                        A003 = r13.A00;
                                    } else {
                                        JsonSerializer<Object> A0B2 = r11.A0B(cls2, ((ArraySerializerBase) this).A00);
                                        C04670qx r14 = new C04670qx(A0B2, r52.A01(cls2, A0B2));
                                        AbstractC04690qz r04 = r14.A01;
                                        if (r52 != r04) {
                                            this.A01 = r04;
                                        }
                                        A003 = r14.A00;
                                    }
                                }
                                A003.serialize(obj3, r10, r11);
                            }
                            i3++;
                            if (i3 >= length) {
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
                        throw C02180iD.A01(e, new C04030p3((Object) null, 0));
                    }
                }
            }
        }
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r6, AbstractC02220iI r7) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        AbstractC01990hm A4N;
        Object A0U;
        AbstractC04550qd r3 = this.A02;
        if (r3 != null) {
            r3 = r3.A00(r7);
        }
        if (r7 == null || (A4N = r7.A4N()) == null || (A0U = r6._config.A01().A0U(A4N)) == null || (jsonSerializer = r6.A0A(A4N, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A032 = StdSerializer.A03(r6, r7, jsonSerializer);
        if (A032 == null) {
            AbstractC02190iF r1 = this.A03;
            if (r1 != null && (this.A04 || ContainerSerializer.A02(r6, r7))) {
                A032 = r6.A09(r1, r7);
            }
        } else if (A032 instanceof AbstractC04600qk) {
            A032 = ((AbstractC04600qk) A032).A2P(r6, r7);
        }
        if (((ArraySerializerBase) this).A00 == r7 && A032 == this.A00 && r3 == r3) {
            return this;
        }
        return new ObjectArraySerializer(this, r7, r3, A032);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectArrayFormat");
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        Object[] objArr = (Object[]) obj;
        if (objArr == null || objArr.length == 0) {
            return true;
        }
        return false;
    }

    public ObjectArraySerializer(AbstractC02190iF r3, boolean z, AbstractC04550qd r5, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class, (AbstractC02220iI) null);
        this.A03 = r3;
        this.A04 = z;
        this.A02 = r5;
        this.A01 = C01810h8.A00;
        this.A00 = jsonSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, AbstractC02220iI r3, AbstractC04550qd r4, JsonSerializer<?> jsonSerializer) {
        super(objectArraySerializer, r3);
        this.A03 = objectArraySerializer.A03;
        this.A02 = r4;
        this.A04 = objectArraySerializer.A04;
        this.A01 = objectArraySerializer.A01;
        this.A00 = jsonSerializer;
    }
}
