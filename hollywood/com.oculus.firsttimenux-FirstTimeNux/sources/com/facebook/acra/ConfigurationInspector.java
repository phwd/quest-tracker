package com.facebook.acra;

import android.content.res.Configuration;
import android.util.SparseArray;
import com.facebook.debug.log.BLog;
import com.facebook.debug.log.LoggingUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.TreeMap;

public class ConfigurationInspector {
    private static final String FIELD_MCC = "mcc";
    private static final String FIELD_MNC = "mnc";
    private static final String FIELD_SCREENLAYOUT = "screenLayout";
    private static final String FIELD_UIMODE = "uiMode";
    private static final String PREFIX_HARDKEYBOARDHIDDEN = "HARDKEYBOARDHIDDEN_";
    private static final String PREFIX_KEYBOARD = "KEYBOARD_";
    private static final String PREFIX_KEYBOARDHIDDEN = "KEYBOARDHIDDEN_";
    private static final String PREFIX_NAVIGATION = "NAVIGATION_";
    private static final String PREFIX_NAVIGATIONHIDDEN = "NAVIGATIONHIDDEN_";
    private static final String PREFIX_ORIENTATION = "ORIENTATION_";
    private static final String PREFIX_SCREENLAYOUT = "SCREENLAYOUT_";
    private static final String PREFIX_TOUCHSCREEN = "TOUCHSCREEN_";
    private static final String PREFIX_UI_MODE = "UI_MODE_";
    private static final String SUFFIX_MASK = "_MASK";
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
        mValueArrays.put(PREFIX_HARDKEYBOARDHIDDEN, mHardKeyboardHiddenValues);
        mValueArrays.put(PREFIX_KEYBOARD, mKeyboardValues);
        mValueArrays.put(PREFIX_KEYBOARDHIDDEN, mKeyboardHiddenValues);
        mValueArrays.put(PREFIX_NAVIGATION, mNavigationValues);
        mValueArrays.put(PREFIX_NAVIGATIONHIDDEN, mNavigationHiddenValues);
        mValueArrays.put(PREFIX_ORIENTATION, mOrientationValues);
        mValueArrays.put(PREFIX_SCREENLAYOUT, mScreenLayoutValues);
        mValueArrays.put(PREFIX_TOUCHSCREEN, mTouchScreenValues);
        mValueArrays.put(PREFIX_UI_MODE, mUiModeValues);
        Field[] fields = Configuration.class.getFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) {
                String fieldName = f.getName();
                try {
                    if (fieldName.startsWith(PREFIX_HARDKEYBOARDHIDDEN)) {
                        mHardKeyboardHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_KEYBOARD)) {
                        mKeyboardValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_KEYBOARDHIDDEN)) {
                        mKeyboardHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_NAVIGATION)) {
                        mNavigationValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_NAVIGATIONHIDDEN)) {
                        mNavigationHiddenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_ORIENTATION)) {
                        mOrientationValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_SCREENLAYOUT)) {
                        mScreenLayoutValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_TOUCHSCREEN)) {
                        mTouchScreenValues.put(f.getInt(null), fieldName);
                    } else if (fieldName.startsWith(PREFIX_UI_MODE)) {
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
                            result.append(LoggingUtil.NO_HASHCODE);
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
        if (fieldName.equals(FIELD_MCC) || fieldName.equals(FIELD_MNC)) {
            return Integer.toString(f.getInt(conf));
        }
        if (fieldName.equals(FIELD_UIMODE)) {
            return activeFlags(mValueArrays.get(PREFIX_UI_MODE), f.getInt(conf));
        }
        if (fieldName.equals(FIELD_SCREENLAYOUT)) {
            return activeFlags(mValueArrays.get(PREFIX_SCREENLAYOUT), f.getInt(conf));
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
            if (valueNames.get(maskValue).endsWith(SUFFIX_MASK) && (value = bitfield & maskValue) > 0) {
                if (result.length() > 0) {
                    result.append('+');
                }
                result.append(valueNames.get(value));
            }
        }
        return result.toString();
    }
}
