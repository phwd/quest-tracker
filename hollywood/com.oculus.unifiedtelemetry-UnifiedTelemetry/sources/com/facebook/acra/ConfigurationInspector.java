package com.facebook.acra;

import X.Mu;
import android.content.res.Configuration;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.TreeMap;

public class ConfigurationInspector {
    public static final String FIELD_MCC = "mcc";
    public static final String FIELD_MNC = "mnc";
    public static final String FIELD_SCREENLAYOUT = "screenLayout";
    public static final String FIELD_UIMODE = "uiMode";
    public static final String PREFIX_HARDKEYBOARDHIDDEN = "HARDKEYBOARDHIDDEN_";
    public static final String PREFIX_KEYBOARD = "KEYBOARD_";
    public static final String PREFIX_KEYBOARDHIDDEN = "KEYBOARDHIDDEN_";
    public static final String PREFIX_NAVIGATION = "NAVIGATION_";
    public static final String PREFIX_NAVIGATIONHIDDEN = "NAVIGATIONHIDDEN_";
    public static final String PREFIX_ORIENTATION = "ORIENTATION_";
    public static final String PREFIX_SCREENLAYOUT = "SCREENLAYOUT_";
    public static final String PREFIX_TOUCHSCREEN = "TOUCHSCREEN_";
    public static final String PREFIX_UI_MODE = "UI_MODE_";
    public static final String SUFFIX_MASK = "_MASK";
    public static SparseArray<String> mHardKeyboardHiddenValues = new SparseArray<>();
    public static SparseArray<String> mKeyboardHiddenValues = new SparseArray<>();
    public static SparseArray<String> mKeyboardValues = new SparseArray<>();
    public static SparseArray<String> mNavigationHiddenValues = new SparseArray<>();
    public static SparseArray<String> mNavigationValues = new SparseArray<>();
    public static SparseArray<String> mOrientationValues = new SparseArray<>();
    public static SparseArray<String> mScreenLayoutValues = new SparseArray<>();
    public static SparseArray<String> mTouchScreenValues = new SparseArray<>();
    public static SparseArray<String> mUiModeValues = new SparseArray<>();
    public static final TreeMap<String, SparseArray<String>> mValueArrays;

    static {
        TreeMap<String, SparseArray<String>> treeMap = new TreeMap<>();
        mValueArrays = treeMap;
        treeMap.put(PREFIX_HARDKEYBOARDHIDDEN, mHardKeyboardHiddenValues);
        TreeMap<String, SparseArray<String>> treeMap2 = mValueArrays;
        treeMap2.put(PREFIX_KEYBOARD, mKeyboardValues);
        treeMap2.put(PREFIX_KEYBOARDHIDDEN, mKeyboardHiddenValues);
        treeMap2.put(PREFIX_NAVIGATION, mNavigationValues);
        treeMap2.put(PREFIX_NAVIGATIONHIDDEN, mNavigationHiddenValues);
        treeMap2.put(PREFIX_ORIENTATION, mOrientationValues);
        treeMap2.put(PREFIX_SCREENLAYOUT, mScreenLayoutValues);
        treeMap2.put(PREFIX_TOUCHSCREEN, mTouchScreenValues);
        treeMap2.put(PREFIX_UI_MODE, mUiModeValues);
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
                    Mu.A09("ACRA", e, "Error while inspecting device configuration: ");
                }
            }
        }
    }

    public static String activeFlags(SparseArray<String> sparseArray, int i) {
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
                Mu.A08("ACRA", e, "Error while inspecting device configuration: ");
            }
        }
        return sb.toString();
    }

    public static String getFieldValueName(Configuration configuration, Field field) throws IllegalArgumentException, IllegalAccessException {
        String str;
        TreeMap<String, SparseArray<String>> treeMap;
        String str2;
        String name = field.getName();
        if (!name.equals(FIELD_MCC) && !name.equals(FIELD_MNC)) {
            if (name.equals(FIELD_UIMODE)) {
                treeMap = mValueArrays;
                str2 = PREFIX_UI_MODE;
            } else if (name.equals(FIELD_SCREENLAYOUT)) {
                treeMap = mValueArrays;
                str2 = PREFIX_SCREENLAYOUT;
            } else {
                TreeMap<String, SparseArray<String>> treeMap2 = mValueArrays;
                StringBuilder sb = new StringBuilder();
                sb.append(name.toUpperCase());
                sb.append('_');
                SparseArray<String> sparseArray = treeMap2.get(sb.toString());
                if (!(sparseArray == null || (str = sparseArray.get(field.getInt(configuration))) == null)) {
                    return str;
                }
            }
            return activeFlags(treeMap.get(str2), field.getInt(configuration));
        }
        return Integer.toString(field.getInt(configuration));
    }
}
