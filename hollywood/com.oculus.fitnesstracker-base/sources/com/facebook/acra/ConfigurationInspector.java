package com.facebook.acra;

import android.content.res.Configuration;
import android.util.SparseArray;
import com.facebook.debug.log.BLog;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.TreeMap;

public final class ConfigurationInspector {
    private static SparseArray<String> mHardKeyboardHiddenValues = new SparseArray<>();
    private static SparseArray<String> mKeyboardHiddenValues = new SparseArray<>();
    private static SparseArray<String> mKeyboardValues = new SparseArray<>();
    private static SparseArray<String> mNavigationHiddenValues = new SparseArray<>();
    private static SparseArray<String> mNavigationValues = new SparseArray<>();
    private static SparseArray<String> mOrientationValues = new SparseArray<>();
    private static SparseArray<String> mScreenLayoutValues = new SparseArray<>();
    private static SparseArray<String> mTouchScreenValues = new SparseArray<>();
    private static SparseArray<String> mUiModeValues = new SparseArray<>();
    private static final TreeMap<String, SparseArray<String>> mValueArrays;

    static {
        TreeMap<String, SparseArray<String>> treeMap = new TreeMap<>();
        mValueArrays = treeMap;
        treeMap.put("HARDKEYBOARDHIDDEN_", mHardKeyboardHiddenValues);
        mValueArrays.put("KEYBOARD_", mKeyboardValues);
        mValueArrays.put("KEYBOARDHIDDEN_", mKeyboardHiddenValues);
        mValueArrays.put("NAVIGATION_", mNavigationValues);
        mValueArrays.put("NAVIGATIONHIDDEN_", mNavigationHiddenValues);
        mValueArrays.put("ORIENTATION_", mOrientationValues);
        mValueArrays.put("SCREENLAYOUT_", mScreenLayoutValues);
        mValueArrays.put("TOUCHSCREEN_", mTouchScreenValues);
        mValueArrays.put("UI_MODE_", mUiModeValues);
        Field[] fields = Configuration.class.getFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                String name = field.getName();
                try {
                    if (name.startsWith("HARDKEYBOARDHIDDEN_")) {
                        mHardKeyboardHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith("KEYBOARD_")) {
                        mKeyboardValues.put(field.getInt(null), name);
                    } else if (name.startsWith("KEYBOARDHIDDEN_")) {
                        mKeyboardHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATION_")) {
                        mNavigationValues.put(field.getInt(null), name);
                    } else if (name.startsWith("NAVIGATIONHIDDEN_")) {
                        mNavigationHiddenValues.put(field.getInt(null), name);
                    } else if (name.startsWith("ORIENTATION_")) {
                        mOrientationValues.put(field.getInt(null), name);
                    } else if (name.startsWith("SCREENLAYOUT_")) {
                        mScreenLayoutValues.put(field.getInt(null), name);
                    } else if (name.startsWith("TOUCHSCREEN_")) {
                        mTouchScreenValues.put(field.getInt(null), name);
                    } else if (name.startsWith("UI_MODE_")) {
                        mUiModeValues.put(field.getInt(null), name);
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    BLog.w(ACRA.LOG_TAG, e, "Error while inspecting device configuration: ");
                }
            }
        }
    }

    public static String toString(Configuration configuration) {
        String str;
        StringBuilder sb = new StringBuilder();
        Field[] fields = configuration.getClass().getFields();
        for (Field field : fields) {
            try {
                if (!Modifier.isStatic(field.getModifiers())) {
                    sb.append(field.getName());
                    sb.append('=');
                    if (field.getType().equals(Integer.TYPE)) {
                        String name = field.getName();
                        if (!name.equals("mcc")) {
                            if (!name.equals("mnc")) {
                                if (name.equals("uiMode")) {
                                    str = activeFlags(mValueArrays.get("UI_MODE_"), field.getInt(configuration));
                                } else if (name.equals("screenLayout")) {
                                    str = activeFlags(mValueArrays.get("SCREENLAYOUT_"), field.getInt(configuration));
                                } else {
                                    SparseArray<String> sparseArray = mValueArrays.get(name.toUpperCase() + '_');
                                    if (sparseArray == null) {
                                        str = Integer.toString(field.getInt(configuration));
                                    } else {
                                        String str2 = sparseArray.get(field.getInt(configuration));
                                        str = str2 == null ? Integer.toString(field.getInt(configuration)) : str2;
                                    }
                                }
                                sb.append(str);
                            }
                        }
                        str = Integer.toString(field.getInt(configuration));
                        sb.append(str);
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

    private static String activeFlags(SparseArray<String> sparseArray, int i) {
        int i2;
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            int keyAt = sparseArray.keyAt(i3);
            if (sparseArray.get(keyAt).endsWith("_MASK") && (i2 = keyAt & i) > 0) {
                if (sb.length() > 0) {
                    sb.append('+');
                }
                sb.append(sparseArray.get(i2));
            }
        }
        return sb.toString();
    }
}
