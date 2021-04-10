package com.facebook.acra;

import android.content.res.Configuration;
import android.util.SparseArray;
import com.facebook.debug.log.BLog;
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
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                String name = field.getName();
                try {
                    if (name.startsWith(PREFIX_HARDKEYBOARDHIDDEN)) {
                        mHardKeyboardHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_KEYBOARD)) {
                        mKeyboardValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_KEYBOARDHIDDEN)) {
                        mKeyboardHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_NAVIGATION)) {
                        mNavigationValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_NAVIGATIONHIDDEN)) {
                        mNavigationHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_ORIENTATION)) {
                        mOrientationValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_SCREENLAYOUT)) {
                        mScreenLayoutValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_TOUCHSCREEN)) {
                        mTouchScreenValues.put(field.getInt(null), name);
                    } else if (name.startsWith(PREFIX_UI_MODE)) {
                        mUiModeValues.put(field.getInt(null), name);
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    BLog.w(ACRA.LOG_TAG, e, "Error while inspecting device configuration: ");
                }
            }
        }
    }

    public static String toString(Configuration configuration) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = configuration.getClass().getFields();
        for (Field field : fields) {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    sb.append(field.getName());
                    sb.append('=');
                    if (field.getType().equals(Integer.TYPE)) {
                        sb.append(getFieldValueName(configuration, field));
                    } else {
                        Object obj = field.get(configuration);
                        if (obj == null) {
                            sb.append("null");
                        } else if (obj instanceof Object[]) {
                            sb.append(Arrays.deepToString((Object[]) obj));
                        } else {
                            sb.append(field.get(configuration).toString());
                        }
                    }
                    sb.append('\n');
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                BLog.e(ACRA.LOG_TAG, e, "Error while inspecting device configuration: ");
            }
        }
        return sb.toString();
    }

    private static String getFieldValueName(Configuration configuration, Field field) throws IllegalArgumentException, IllegalAccessException {
        String name = field.getName();
        if (name.equals(FIELD_MCC) || name.equals(FIELD_MNC)) {
            return Integer.toString(field.getInt(configuration));
        }
        if (name.equals(FIELD_UIMODE)) {
            return activeFlags(mValueArrays.get(PREFIX_UI_MODE), field.getInt(configuration));
        }
        if (name.equals(FIELD_SCREENLAYOUT)) {
            return activeFlags(mValueArrays.get(PREFIX_SCREENLAYOUT), field.getInt(configuration));
        }
        TreeMap<String, SparseArray<String>> treeMap = mValueArrays;
        SparseArray<String> sparseArray = treeMap.get(name.toUpperCase() + '_');
        if (sparseArray == null) {
            return Integer.toString(field.getInt(configuration));
        }
        String str = sparseArray.get(field.getInt(configuration));
        return str == null ? Integer.toString(field.getInt(configuration)) : str;
    }

    private static String activeFlags(SparseArray<String> sparseArray, int i) {
        int i2;
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            int keyAt = sparseArray.keyAt(i3);
            if (sparseArray.get(keyAt).endsWith(SUFFIX_MASK) && (i2 = keyAt & i) > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(sparseArray.get(i2));
            }
        }
        return sb.toString();
    }
}
