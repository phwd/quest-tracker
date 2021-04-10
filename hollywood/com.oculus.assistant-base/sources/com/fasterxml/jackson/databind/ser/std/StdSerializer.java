package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1012qg;
import X.AbstractC1020qp;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.AbstractC1072ro;
import X.AnonymousClass08;
import X.C1011qf;
import X.C1015qj;
import X.C1025qv;
import X.C1060ra;
import X.EnumC1030r1;
import X.JD;
import X.O5;
import X.O9;
import X.OB;
import X.Rw;
import X.VH;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.DOMSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;

public abstract class StdSerializer extends JsonSerializer {
    public final Class A00;

    public static final JsonSerializer A02(AbstractC1031r2 r2Var, O5 o5, JsonSerializer jsonSerializer) {
        JsonSerialize jsonSerialize;
        Class contentConverter;
        AbstractC1020qp A01 = r2Var._config.A01();
        if (!(A01 == null || o5 == null)) {
            AbstractC1044rJ A2e = o5.A2e();
            if (!(!(A01 instanceof Rw) || (jsonSerialize = (JsonSerialize) A2e.A0L(JsonSerialize.class)) == null || (contentConverter = jsonSerialize.contentConverter()) == AbstractC1072ro.class || contentConverter == null)) {
                r2Var.A06(contentConverter);
                throw new NullPointerException("getOutputType");
            }
        }
        return jsonSerializer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.EnumC1030r1.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A03(X.AbstractC1031r2 r2, java.lang.Throwable r3, java.lang.Object r4, int r5) {
        /*
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x003d
            if (r2 == 0) goto L_0x0020
            X.r1 r1 = X.EnumC1030r1.WRAP_EXCEPTIONS
            X.2H r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.C1025qv
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.O9 r0 = new X.O9
            r0.<init>(r4, r5)
            X.qv r0 = X.C1025qv.A01(r3, r0)
            throw r0
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A03(X.r2, java.lang.Throwable, java.lang.Object, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.EnumC1030r1.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A04(X.AbstractC1031r2 r2, java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5) {
        /*
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x003d
            if (r2 == 0) goto L_0x0020
            X.r1 r1 = X.EnumC1030r1.WRAP_EXCEPTIONS
            X.2H r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.C1025qv
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.O9 r0 = new X.O9
            r0.<init>(r4, r5)
            X.qv r0 = X.C1025qv.A01(r3, r0)
            throw r0
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A04(X.r2, java.lang.Throwable, java.lang.Object, java.lang.String):void");
    }

    public static final boolean A05(JsonSerializer jsonSerializer) {
        if (jsonSerializer == null || jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) {
            return false;
        }
        return true;
    }

    public StdSerializer(AbstractC1024qt qtVar) {
        this.A00 = qtVar._class;
    }

    public StdSerializer(Class cls) {
        this.A00 = cls;
    }

    public StdSerializer(Class cls, boolean z) {
        this.A00 = cls;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0B(Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        String str;
        String str2;
        long j;
        int i;
        boolean z;
        Number number;
        String obj2;
        boolean A06;
        BeanSerializerBase beanSerializerBase;
        boolean z2;
        if (!(this instanceof TokenBufferSerializer)) {
            if (!(this instanceof ToStringSerializer)) {
                if (!(this instanceof StdKeySerializers$StringKeySerializer)) {
                    if (!(this instanceof StdKeySerializers$DateKeySerializer)) {
                        if (this instanceof StdKeySerializers$CalendarKeySerializer) {
                            long timeInMillis = ((Calendar) obj).getTimeInMillis();
                            if (r2Var._config.A06(EnumC1030r1.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
                                str2 = String.valueOf(timeInMillis);
                            } else {
                                str2 = AbstractC1031r2.A00(r2Var).format(new Date(timeInMillis));
                            }
                        } else if (!(this instanceof StdKeySerializer)) {
                            if (this instanceof StdJdkSerializers$AtomicReferenceSerializer) {
                                Object obj3 = ((AtomicReference) obj).get();
                                if (obj3 == null) {
                                    r2Var._nullValueSerializer.A0B(null, qgVar, r2Var);
                                    return;
                                } else {
                                    r2Var.A0B(obj3.getClass(), true, null).A0B(obj3, qgVar, r2Var);
                                    return;
                                }
                            } else if (!(this instanceof StdDelegatingSerializer)) {
                                if (this instanceof StdArraySerializers$CharArraySerializer) {
                                    char[] cArr = (char[]) obj;
                                    if (r2Var._config.A06(EnumC1030r1.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                                        qgVar.A0B();
                                        int length = cArr.length;
                                        for (int i2 = 0; i2 < length; i2++) {
                                            qgVar.A0V(cArr, i2, 1);
                                        }
                                    } else {
                                        qgVar.A0V(cArr, 0, cArr.length);
                                        return;
                                    }
                                } else if (this instanceof StdArraySerializers$ByteArraySerializer) {
                                    byte[] bArr = (byte[]) obj;
                                    qgVar.A0I(r2Var._config._base._defaultBase64, bArr, 0, bArr.length);
                                    return;
                                } else if (this instanceof SerializableSerializer) {
                                    ((OB) obj).A4x(qgVar, r2Var);
                                    return;
                                } else if (this instanceof RawSerializer) {
                                    String obj4 = obj.toString();
                                    if (!(qgVar instanceof JD)) {
                                        VH vh = (VH) qgVar;
                                        vh.A0W("write raw value");
                                        vh.A0O(obj4);
                                        return;
                                    }
                                    throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
                                } else if (this instanceof NullSerializer) {
                                    qgVar.A0A();
                                    return;
                                } else if (!(this instanceof JsonValueSerializer)) {
                                    if (this instanceof TimeZoneSerializer) {
                                        str = ((TimeZone) obj).getID();
                                    } else if (this instanceof StdJdkSerializers$FileSerializer) {
                                        str = ((File) obj).getAbsolutePath();
                                    } else if (!(this instanceof StdJdkSerializers$ClassSerializer)) {
                                        if (!(this instanceof StdJdkSerializers$AtomicLongSerializer)) {
                                            if (!(this instanceof StdJdkSerializers$AtomicIntegerSerializer)) {
                                                if (this instanceof StdJdkSerializers$AtomicBooleanSerializer) {
                                                    z = ((AtomicBoolean) obj).get();
                                                } else if (!(this instanceof SqlTimeSerializer) && !(this instanceof SqlDateSerializer)) {
                                                    if (!(this instanceof NumberSerializers$ShortSerializer)) {
                                                        if (this instanceof NumberSerializers$NumberSerializer) {
                                                            number = (Number) obj;
                                                            if (number instanceof BigDecimal) {
                                                                if (!r2Var._config.A06(EnumC1030r1.WRITE_BIGDECIMAL_AS_PLAIN) || (qgVar instanceof JD)) {
                                                                    qgVar.A0Q((BigDecimal) number);
                                                                    return;
                                                                }
                                                                obj2 = ((BigDecimal) number).toPlainString();
                                                            } else if (number instanceof BigInteger) {
                                                                qgVar.A0R((BigInteger) number);
                                                                return;
                                                            } else {
                                                                if (!(number instanceof Integer)) {
                                                                    if (number instanceof Long) {
                                                                        j = number.longValue();
                                                                    } else {
                                                                        if (!(number instanceof Double)) {
                                                                            if (!(number instanceof Float)) {
                                                                                if (!(number instanceof Byte) && !(number instanceof Short)) {
                                                                                    obj2 = number.toString();
                                                                                }
                                                                            }
                                                                        }
                                                                        qgVar.A0E(number.doubleValue());
                                                                        return;
                                                                    }
                                                                }
                                                                i = number.intValue();
                                                            }
                                                            qgVar.A0N(obj2);
                                                            return;
                                                        } else if (!(this instanceof NumberSerializers$LongSerializer)) {
                                                            if (!(this instanceof NumberSerializers$IntLikeSerializer)) {
                                                                if (this instanceof NumberSerializers$FloatSerializer) {
                                                                    number = (Number) obj;
                                                                } else if (this instanceof InetAddressSerializer) {
                                                                    InetAddressSerializer.A00((InetAddress) obj, qgVar);
                                                                    return;
                                                                } else if (this instanceof EnumSerializer) {
                                                                    EnumSerializer enumSerializer = (EnumSerializer) this;
                                                                    Enum r6 = (Enum) obj;
                                                                    Boolean bool = enumSerializer.A01;
                                                                    if (bool != null) {
                                                                        A06 = bool.booleanValue();
                                                                    } else {
                                                                        A06 = r2Var._config.A06(EnumC1030r1.WRITE_ENUMS_USING_INDEX);
                                                                    }
                                                                    if (A06) {
                                                                        i = r6.ordinal();
                                                                    } else {
                                                                        qgVar.A0K((C1015qj) enumSerializer.A00.A00.get(r6));
                                                                        return;
                                                                    }
                                                                } else if (this instanceof DateSerializer) {
                                                                    DateTimeSerializerBase dateTimeSerializerBase = (DateTimeSerializerBase) this;
                                                                    Date date = (Date) obj;
                                                                    if (!dateTimeSerializerBase.A01) {
                                                                        DateFormat dateFormat = dateTimeSerializerBase.A00;
                                                                        if (dateFormat != null) {
                                                                            synchronized (dateFormat) {
                                                                                qgVar.A0P(dateFormat.format(date));
                                                                            }
                                                                            return;
                                                                        }
                                                                        r2Var.A0F(date, qgVar);
                                                                        return;
                                                                    } else if (date == null) {
                                                                        j = 0;
                                                                    } else {
                                                                        j = date.getTime();
                                                                    }
                                                                } else if (this instanceof CalendarSerializer) {
                                                                    ((CalendarSerializer) this).A0D((Calendar) obj, qgVar, r2Var);
                                                                    return;
                                                                } else if (this instanceof StringSerializer) {
                                                                    qgVar.A0P((String) obj);
                                                                    return;
                                                                } else if (!(this instanceof NumberSerializers$IntegerSerializer)) {
                                                                    if (this instanceof NumberSerializers$DoubleSerializer) {
                                                                        number = (Number) obj;
                                                                        qgVar.A0E(number.doubleValue());
                                                                        return;
                                                                    } else if (!(this instanceof BooleanSerializer)) {
                                                                        if (this instanceof MapSerializer) {
                                                                            MapSerializer mapSerializer = (MapSerializer) this;
                                                                            Map map = (Map) obj;
                                                                            qgVar.A0C();
                                                                            if (!map.isEmpty()) {
                                                                                if (r2Var._config.A06(EnumC1030r1.ORDER_MAP_ENTRIES_BY_KEYS) && !(map instanceof SortedMap)) {
                                                                                    map = new TreeMap(map);
                                                                                }
                                                                                JsonSerializer jsonSerializer = mapSerializer.A01;
                                                                                if (jsonSerializer != null) {
                                                                                    MapSerializer.A06(mapSerializer, map, qgVar, r2Var, jsonSerializer);
                                                                                } else {
                                                                                    mapSerializer.A0D(map, qgVar, r2Var);
                                                                                }
                                                                            }
                                                                        } else if (this instanceof EnumMapSerializer) {
                                                                            EnumMapSerializer enumMapSerializer = (EnumMapSerializer) this;
                                                                            EnumMap enumMap = (EnumMap) obj;
                                                                            qgVar.A0C();
                                                                            if (!enumMap.isEmpty()) {
                                                                                EnumMapSerializer.A01(enumMapSerializer, enumMap, qgVar, r2Var);
                                                                            }
                                                                        } else if (this instanceof AsArraySerializerBase) {
                                                                            AsArraySerializerBase asArraySerializerBase = (AsArraySerializerBase) this;
                                                                            if (!r2Var._config.A06(EnumC1030r1.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !asArraySerializerBase.A0C(obj)) {
                                                                                qgVar.A0B();
                                                                                asArraySerializerBase.A0F(obj, qgVar, r2Var);
                                                                            } else {
                                                                                asArraySerializerBase.A0F(obj, qgVar, r2Var);
                                                                                return;
                                                                            }
                                                                        } else if (this instanceof ArraySerializerBase) {
                                                                            ArraySerializerBase arraySerializerBase = (ArraySerializerBase) this;
                                                                            if (!r2Var._config.A06(EnumC1030r1.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !arraySerializerBase.A0C(obj)) {
                                                                                qgVar.A0B();
                                                                                arraySerializerBase.A0D(obj, qgVar, r2Var);
                                                                            } else {
                                                                                arraySerializerBase.A0D(obj, qgVar, r2Var);
                                                                                return;
                                                                            }
                                                                        } else if (this instanceof UnknownSerializer) {
                                                                            if (r2Var._config.A06(EnumC1030r1.FAIL_ON_EMPTY_BEANS)) {
                                                                                throw new C1025qv(AnonymousClass08.A05("No serializer found for class ", obj.getClass().getName(), " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )"));
                                                                            }
                                                                            qgVar.A0C();
                                                                        } else if (this instanceof StringCollectionSerializer) {
                                                                            StringCollectionSerializer stringCollectionSerializer = (StringCollectionSerializer) this;
                                                                            Collection collection = (Collection) obj;
                                                                            if (collection.size() == 1) {
                                                                                if (r2Var._config.A06(EnumC1030r1.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                                                                                    if (stringCollectionSerializer.A00 == null) {
                                                                                        StringCollectionSerializer.A01(stringCollectionSerializer, collection, qgVar, r2Var);
                                                                                        return;
                                                                                    } else {
                                                                                        StringCollectionSerializer.A00(stringCollectionSerializer, collection, qgVar, r2Var);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                            }
                                                                            qgVar.A0B();
                                                                            if (stringCollectionSerializer.A00 == null) {
                                                                                StringCollectionSerializer.A01(stringCollectionSerializer, collection, qgVar, r2Var);
                                                                            } else {
                                                                                StringCollectionSerializer.A00(stringCollectionSerializer, collection, qgVar, r2Var);
                                                                            }
                                                                        } else if (this instanceof IndexedStringListSerializer) {
                                                                            IndexedStringListSerializer indexedStringListSerializer = (IndexedStringListSerializer) this;
                                                                            List list = (List) obj;
                                                                            int size = list.size();
                                                                            if (size == 1) {
                                                                                if (r2Var._config.A06(EnumC1030r1.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                                                                                    if (indexedStringListSerializer.A00 == null) {
                                                                                        IndexedStringListSerializer.A01(list, qgVar, r2Var, 1);
                                                                                        return;
                                                                                    } else {
                                                                                        IndexedStringListSerializer.A00(indexedStringListSerializer, list, qgVar, r2Var, 1);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                            }
                                                                            qgVar.A0B();
                                                                            if (indexedStringListSerializer.A00 == null) {
                                                                                IndexedStringListSerializer.A01(list, qgVar, r2Var, size);
                                                                            } else {
                                                                                IndexedStringListSerializer.A00(indexedStringListSerializer, list, qgVar, r2Var, size);
                                                                            }
                                                                        } else if (!(this instanceof FailingSerializer)) {
                                                                            if (this instanceof UnwrappingBeanSerializer) {
                                                                                beanSerializerBase = (BeanSerializerBase) this;
                                                                                if (beanSerializerBase.A03 != null) {
                                                                                    z2 = false;
                                                                                } else if (beanSerializerBase.A04 != null) {
                                                                                    beanSerializerBase.A0D();
                                                                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                                                                } else {
                                                                                    beanSerializerBase.A0E(obj, qgVar, r2Var);
                                                                                    return;
                                                                                }
                                                                            } else if (this instanceof BeanAsArraySerializer) {
                                                                                BeanAsArraySerializer beanAsArraySerializer = (BeanAsArraySerializer) this;
                                                                                if (r2Var._config.A06(EnumC1030r1.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                                                                                    C1060ra[] raVarArr = beanAsArraySerializer.A05;
                                                                                    if (raVarArr == null || r2Var._serializationView == null) {
                                                                                        raVarArr = beanAsArraySerializer.A06;
                                                                                    }
                                                                                    if (raVarArr.length == 1) {
                                                                                        BeanAsArraySerializer.A00(beanAsArraySerializer, obj, qgVar, r2Var);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                                qgVar.A0B();
                                                                                BeanAsArraySerializer.A00(beanAsArraySerializer, obj, qgVar, r2Var);
                                                                            } else if (this instanceof BeanSerializer) {
                                                                                beanSerializerBase = (BeanSerializerBase) this;
                                                                                if (beanSerializerBase.A03 != null) {
                                                                                    z2 = true;
                                                                                } else {
                                                                                    qgVar.A0C();
                                                                                    if (beanSerializerBase.A04 != null) {
                                                                                        beanSerializerBase.A0D();
                                                                                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                                                                    }
                                                                                    beanSerializerBase.A0E(obj, qgVar, r2Var);
                                                                                }
                                                                            } else if (!(this instanceof DOMSerializer)) {
                                                                                CalendarSerializer.A00.A0D(((XMLGregorianCalendar) obj).toGregorianCalendar(), qgVar, r2Var);
                                                                                return;
                                                                            } else {
                                                                                Node node = (Node) obj;
                                                                                DOMImplementationLS dOMImplementationLS = ((DOMSerializer) this).A00;
                                                                                if (dOMImplementationLS != null) {
                                                                                    str = dOMImplementationLS.createLSSerializer().writeToString(node);
                                                                                } else {
                                                                                    throw new IllegalStateException("Could not find DOM LS");
                                                                                }
                                                                            }
                                                                            beanSerializerBase.A0F(obj, qgVar, r2Var, z2);
                                                                            return;
                                                                        } else {
                                                                            throw new C1011qf(((FailingSerializer) this).A00);
                                                                        }
                                                                        qgVar.A09();
                                                                        return;
                                                                    } else {
                                                                        z = ((Boolean) obj).booleanValue();
                                                                    }
                                                                }
                                                            }
                                                            i = ((Number) obj).intValue();
                                                        } else {
                                                            j = ((Number) obj).longValue();
                                                        }
                                                        qgVar.A0F(number.floatValue());
                                                        return;
                                                    }
                                                    qgVar.A0S(((Number) obj).shortValue());
                                                    return;
                                                }
                                                qgVar.A0T(z);
                                                return;
                                            }
                                            i = ((AtomicInteger) obj).get();
                                            qgVar.A0G(i);
                                            return;
                                        }
                                        j = ((AtomicLong) obj).get();
                                        qgVar.A0H(j);
                                        return;
                                    } else {
                                        str = ((Class) obj).getName();
                                    }
                                    qgVar.A0P(str);
                                    return;
                                } else {
                                    JsonValueSerializer jsonValueSerializer = (JsonValueSerializer) this;
                                    try {
                                        Object invoke = jsonValueSerializer.A02.invoke(obj, new Object[0]);
                                        if (invoke == null) {
                                            r2Var.A0D(qgVar);
                                            return;
                                        }
                                        JsonSerializer jsonSerializer2 = jsonValueSerializer.A01;
                                        if (jsonSerializer2 == null) {
                                            jsonSerializer2 = r2Var.A0B(invoke.getClass(), true, jsonValueSerializer.A00);
                                        }
                                        jsonSerializer2.A0B(invoke, qgVar, r2Var);
                                        return;
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
                                        throw C1025qv.A01(e, new O9(obj, AnonymousClass08.A04(jsonValueSerializer.A02.getName(), "()")));
                                    }
                                }
                                qgVar.A08();
                                return;
                            } else {
                                throw new NullPointerException("convert");
                            }
                        } else if (!(obj instanceof Date)) {
                            str2 = obj.toString();
                        }
                        qgVar.A0M(str2);
                        return;
                    }
                    r2Var.A0E((Date) obj, qgVar);
                    return;
                }
                qgVar.A0M((String) obj);
                return;
            }
            str = obj.toString();
            qgVar.A0P(str);
            return;
        }
        TokenBufferSerializer.A00((JD) obj, qgVar);
    }
}
