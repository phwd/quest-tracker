package com.facebook.acra;

import android.content.res.Configuration;
import android.util.SparseArray;
import com.facebook.debug.log.BLog;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.TreeMap;

public class ConfigurationInspector {
    private static SparseArray<String> mHardKeyboardHiddenValues = new SparseArray<>();
    private static SparseArray<String> mKeyboardHiddenValues = new SparseArray<>();
    private static SparseArray<String> mKeyboardValues = new SparseArray<>();
    private static SparseArray<String> mNavigationHiddenValues = new SparseArray<>();
    private static SparseArray<String> mNavigationValues = new SparseArray<>();
    private static SparseArray<String> mOrientationValues = new SparseArray<>();
    private static SparseArray<String> mScreenLayoutValues = new SparseArray<>();
    private static SparseArray<String> mTouchScreenValues = new SparseArray<>();
    private static SparseArray<String> mUiModeValues = new SparseArray<>();
    private static final TreeMap<String, SparseArray<String>> mValueArrays = new TreeMap<>();

    static {
        mValueArrays.put("HARDKEYBOARDHIDDEN_", mHardKeyboardHiddenValues);
        mValueArrays.put("KEYBOARD_", mKeyboardValues);
        mValueArrays.put("KEYBOARDHIDDEN_", mKeyboardHiddenValues);
        mValueArrays.put("NAVIGATION_", mNavigationValues);
        mValueArrays.put("NAVIGATIONHIDDEN_", mNavigationHiddenValues);
        mValueArrays.put("ORIENTATION_", mOrientationValues);
        mValueArrays.put("SCREENLAYOUT_", mScreenLayoutValues);
        mValueArrays.put("TOUCHSCREEN_", mTouchScreenValues);
        mValueArrays.put("UI_MODE_", mUiModeValues);
        Field[] fields = Configuration.class.getFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) {
                String fieldName = f.getName();
                try {
                    if (fieldName.startsWith("HARDKEYBOARDHIDDEN_")) {
                        mHardKeyboardHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("KEYBOARD_")) {
                        mKeyboardValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("KEYBOARDHIDDEN_")) {
                        mKeyboardHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("NAVIGATION_")) {
                        mNavigationValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("NAVIGATIONHIDDEN_")) {
                        mNavigationHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("ORIENTATION_")) {
                        mOrientationValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("SCREENLAYOUT_")) {
                        mScreenLayoutValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("TOUCHSCREEN_")) {
                        mTouchScreenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith("UI_MODE_")) {
                        mUiModeValues.put(f.getInt(null), fieldName);
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    BLog.w(ACRA.LOG_TAG, e, "Error while inspecting device configuration: ");
                }
            }
        }
    }

    public static String toString(Configuration conf) {
        StringBuilder result = new StringBuilder();
        Field[] fields = conf.getClass().getFields();
        for (Field f : fields) {
            try {
                if (!Modifier.isStatic(f.getModifiers())) {
                    result.append(f.getName()).append('=');
                    if (f.getType().equals(Integer.TYPE)) {
                        result.append(getFieldValueName(conf, f));
                    } else {
                        Object val = f.get(conf);
                        if (val == null) {
                            result.append("null");
                        } else if (val instanceof Object[]) {
                            result.append(Arrays.deepToString((Object[]) val));
                        } else {
                            result.append(f.get(conf).toString());
                        }
                    }
                    result.append('\n');
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                BLog.e(ACRA.LOG_TAG, e, "Error while inspecting device configuration: ");
            }
        }
        return result.toString();
    }

    private static String getFieldValueName(Configuration conf, Field f) throws IllegalArgumentException, IllegalAccessException {
        String fieldName = f.getName();
        if (fieldName.equals("mcc") || fieldName.equals("mnc")) {
            return Integer.toString(f.getInt(conf));
        }
        if (fieldName.equals("uiMode")) {
            return activeFlags(mValueArrays.get("UI_MODE_"), f.getInt(conf));
        }
        if (fieldName.equals("screenLayout")) {
            return activeFlags(mValueArrays.get("SCREENLAYOUT_"), f.getInt(conf));
        }
        SparseArray<String> values = mValueArrays.get(fieldName.toUpperCase() + '_');
        if (values == null) {
            return Integer.toString(f.getInt(conf));
        }
        String value = values.get(f.getInt(conf));
        if (value == null) {
            return Integer.toString(f.getInt(conf));
        }
        return value;
    }

    private static String activeFlags(SparseArray<String> valueNames, int bitfield) {
        int value;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < valueNames.size(); i++) {
            int maskValue = valueNames.keyAt(i);
            if (valueNames.get(maskValue).endsWith("_MASK") && (value = bitfield & maskValue) > 0) {
                if (result.length() > 0) {
                    result.append('+');
                }
                result.append(valueNames.get(value));
            }
        }
        return result.toString();
    }
}
