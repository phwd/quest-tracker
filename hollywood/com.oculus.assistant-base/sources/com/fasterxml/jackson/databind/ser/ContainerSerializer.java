package com.fasterxml.jackson.databind.ser;

import X.AbstractC1020qp;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.O5;
import X.OR;
import X.Rw;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumMapSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$BooleanArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$DoubleArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$FloatArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$IntArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$LongArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers$ShortArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class ContainerSerializer extends StdSerializer {
    public static final boolean A00(AbstractC1031r2 r2Var, O5 o5) {
        AbstractC1020qp A01;
        JsonSerialize jsonSerialize;
        Class contentAs;
        if (!(o5 == null || (A01 = r2Var._config.A01()) == null)) {
            AbstractC1044rJ A2e = o5.A2e();
            if (!(A01 instanceof Rw) || (jsonSerialize = (JsonSerialize) A2e.A0L(JsonSerialize.class)) == null || (contentAs = jsonSerialize.contentAs()) == OR.class || contentAs == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final boolean A0C(Object obj) {
        if (!(this instanceof MapSerializer)) {
            if (!(this instanceof EnumMapSerializer)) {
                if (!(this instanceof StdArraySerializers$IntArraySerializer)) {
                    if (!(this instanceof StdArraySerializers$ShortArraySerializer)) {
                        if (!(this instanceof StdArraySerializers$LongArraySerializer)) {
                            if (!(this instanceof StdArraySerializers$FloatArraySerializer)) {
                                if (!(this instanceof StdArraySerializers$DoubleArraySerializer)) {
                                    if (!(this instanceof StdArraySerializers$BooleanArraySerializer)) {
                                        if (!(this instanceof ObjectArraySerializer)) {
                                            if (!(this instanceof StringArraySerializer)) {
                                                if (this instanceof IterableSerializer) {
                                                    return false;
                                                }
                                                if (!(this instanceof EnumSetSerializer)) {
                                                    if (this instanceof CollectionSerializer) {
                                                        Iterator it = ((Collection) obj).iterator();
                                                        if (!it.hasNext()) {
                                                            return false;
                                                        }
                                                        it.next();
                                                        return !it.hasNext();
                                                    } else if ((this instanceof IteratorSerializer) || ((List) obj).size() != 1) {
                                                        return false;
                                                    } else {
                                                        return true;
                                                    }
                                                } else if (((AbstractCollection) obj).size() == 1) {
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            } else if (((String[]) obj).length == 1) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        } else if (((Object[]) obj).length == 1) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else if (((boolean[]) obj).length == 1) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else if (((double[]) obj).length == 1) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else if (((float[]) obj).length == 1) {
                                return true;
                            } else {
                                return false;
                            }
                        } else if (((long[]) obj).length == 1) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (((short[]) obj).length == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (((int[]) obj).length == 1) {
                    return true;
                } else {
                    return false;
                }
            } else if (((AbstractMap) obj).size() == 1) {
                return true;
            } else {
                return false;
            }
        } else if (((Map) obj).size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public ContainerSerializer(ContainerSerializer containerSerializer) {
        super(containerSerializer.A00, false);
    }

    public ContainerSerializer(Class cls) {
        super(cls);
    }

    public ContainerSerializer(Class cls, boolean z) {
        super(cls, false);
    }
}
