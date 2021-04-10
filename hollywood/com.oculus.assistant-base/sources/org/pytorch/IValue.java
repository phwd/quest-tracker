package org.pytorch;

import java.util.Locale;
import java.util.Map;

public class IValue {
    public Object mData;
    public final int mTypeCode;

    public static IValue optionalNull() {
        return new IValue(1);
    }

    public static IValue tupleFrom(IValue... iValueArr) {
        IValue iValue = new IValue(7);
        iValue.mData = iValueArr;
        return iValue;
    }

    public static IValue dictLongKeyFrom(Map map) {
        IValue iValue = new IValue(14);
        iValue.mData = map;
        return iValue;
    }

    public static IValue dictStringKeyFrom(Map map) {
        IValue iValue = new IValue(13);
        iValue.mData = map;
        return iValue;
    }

    private void preconditionType(int i, int i2) {
        if (i2 != i) {
            throw new IllegalStateException(String.format(Locale.US, "Expected IValue type %d, actual type %d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public boolean isBool() {
        if (3 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isBoolList() {
        if (8 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isDictLongKey() {
        if (14 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isDictStringKey() {
        if (13 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isDouble() {
        if (5 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isDoubleList() {
        if (10 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isList() {
        if (12 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isLong() {
        if (4 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isLongList() {
        if (9 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isNull() {
        if (1 != this.mTypeCode) {
            return false;
        }
        return true;
    }

    public boolean isString() {
        if (6 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isTensor() {
        if (2 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isTensorList() {
        if (11 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean isTuple() {
        if (7 == this.mTypeCode) {
            return true;
        }
        return false;
    }

    public boolean toBool() {
        preconditionType(3, this.mTypeCode);
        return ((Boolean) this.mData).booleanValue();
    }

    public boolean[] toBoolList() {
        preconditionType(8, this.mTypeCode);
        return (boolean[]) this.mData;
    }

    public Map toDictLongKey() {
        preconditionType(14, this.mTypeCode);
        return (Map) this.mData;
    }

    public Map toDictStringKey() {
        preconditionType(13, this.mTypeCode);
        return (Map) this.mData;
    }

    public double toDouble() {
        preconditionType(5, this.mTypeCode);
        return ((Number) this.mData).doubleValue();
    }

    public double[] toDoubleList() {
        preconditionType(10, this.mTypeCode);
        return (double[]) this.mData;
    }

    public IValue[] toList() {
        preconditionType(12, this.mTypeCode);
        return (IValue[]) this.mData;
    }

    public long toLong() {
        preconditionType(4, this.mTypeCode);
        return ((Number) this.mData).longValue();
    }

    public long[] toLongList() {
        preconditionType(9, this.mTypeCode);
        return (long[]) this.mData;
    }

    public String toStr() {
        preconditionType(6, this.mTypeCode);
        return (String) this.mData;
    }

    public Tensor toTensor() {
        preconditionType(2, this.mTypeCode);
        return (Tensor) this.mData;
    }

    public Tensor[] toTensorList() {
        preconditionType(11, this.mTypeCode);
        return (Tensor[]) this.mData;
    }

    public IValue[] toTuple() {
        preconditionType(7, this.mTypeCode);
        return (IValue[]) this.mData;
    }

    public IValue(int i) {
        this.mTypeCode = i;
    }

    public static IValue from(double d) {
        IValue iValue = new IValue(5);
        iValue.mData = Double.valueOf(d);
        return iValue;
    }

    public static IValue from(long j) {
        IValue iValue = new IValue(4);
        iValue.mData = Long.valueOf(j);
        return iValue;
    }

    public static IValue from(String str) {
        IValue iValue = new IValue(6);
        iValue.mData = str;
        return iValue;
    }

    public static IValue from(Tensor tensor) {
        IValue iValue = new IValue(2);
        iValue.mData = tensor;
        return iValue;
    }

    public static IValue from(boolean z) {
        IValue iValue = new IValue(3);
        iValue.mData = Boolean.valueOf(z);
        return iValue;
    }

    public static IValue listFrom(double... dArr) {
        IValue iValue = new IValue(10);
        iValue.mData = dArr;
        return iValue;
    }

    public static IValue listFrom(long... jArr) {
        IValue iValue = new IValue(9);
        iValue.mData = jArr;
        return iValue;
    }

    public static IValue listFrom(IValue... iValueArr) {
        int length = iValueArr.length;
        if (length > 0) {
            int i = iValueArr[0].mTypeCode;
            for (int i2 = 1; i2 < length; i2++) {
                if (i != iValueArr[i2].mTypeCode) {
                    throw new IllegalArgumentException("List must contain items of the same type");
                }
            }
        }
        IValue iValue = new IValue(12);
        iValue.mData = iValueArr;
        return iValue;
    }

    public static IValue listFrom(Tensor... tensorArr) {
        IValue iValue = new IValue(11);
        iValue.mData = tensorArr;
        return iValue;
    }

    public static IValue listFrom(boolean... zArr) {
        IValue iValue = new IValue(8);
        iValue.mData = zArr;
        return iValue;
    }
}
