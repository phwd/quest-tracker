package com.facebook.common.util;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValueCoercer {
    private static final Class<?> TAG = ValueCoercer.class;

    private ValueCoercer() {
    }

    public static int getValueAsInteger(Object obj, int i) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return i;
            }
        } else if (obj == null) {
            return i;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return i;
        }
    }

    public static long getValueAsLong(Object obj, long j) {
        if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return j;
            }
        } else if (obj == null) {
            return j;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return j;
        }
    }

    public static float getValueAsFloat(Object obj, float f) {
        if (obj instanceof Double) {
            return ((Double) obj).floatValue();
        }
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        if (obj instanceof Integer) {
            return (float) ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (float) ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Float.parseFloat((String) obj);
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return f;
            }
        } else if (obj == null) {
            return f;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return f;
        }
    }

    public static double getValueAsDouble(Object obj, double d) {
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        }
        if (obj instanceof Float) {
            return (double) ((Float) obj).floatValue();
        }
        if (obj instanceof Integer) {
            return (double) ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return (double) ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return d;
            }
        } else if (obj == null) {
            return d;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return d;
        }
    }

    public static boolean getValueAsBoolean(Object obj, boolean z) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof String) {
            try {
                return Boolean.parseBoolean((String) obj);
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return z;
            }
        } else if (obj == null) {
            return z;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return z;
        }
    }

    public static TriState getValueAsTriState(Object obj) {
        if (obj instanceof Boolean) {
            return TriState.valueOf((Boolean) obj);
        }
        if (obj instanceof String) {
            try {
                return TriState.valueOf(Boolean.parseBoolean((String) obj));
            } catch (NumberFormatException e) {
                BLog.wtf(TAG, "Failed to parse value", e);
                return TriState.UNSET;
            }
        } else if (obj == null) {
            return TriState.UNSET;
        } else {
            Class<?> cls = TAG;
            BLog.wtf(cls, "Unexpected type: " + obj.getClass());
            return TriState.UNSET;
        }
    }
}
