package com.facebook.secure.intentparser;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.facebook.secure.intentparser.IntentParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FieldFilterConfig {
    @Nullable
    private final List<List<Matcher>> mFilters;

    private static class Matcher {
        private static final String MATCHER_FIELD_ACTION = "action";
        private static final String MATCHER_FIELD_CATEGORIES = "categories";
        private static final String MATCHER_FIELD_CLIPDATA = "clipdata";
        private static final String MATCHER_FIELD_COMPONENT_NAME = "component";
        private static final String MATCHER_FIELD_DATA = "data";
        private static final String MATCHER_FIELD_EXTRAS = "extras";
        private static final String MATCHER_FIELD_NAME = "field_name";
        private static final String MATCHER_FIELD_PACKAGE = "package";
        private static final String MATCHER_FIELD_SELECTOR = "selector";
        private static final String MATCHER_FIELD_SOURCE_BOUNDS = "source_bounds";
        private static final String MATCHER_FIELD_TYPE = "type";
        private static final String MATCHER_REGEX = "regex";
        private static final String MATCHER_REGEX_ANY = "_any_";
        private static final String MATCHER_REGEX_NULL = "_null_";
        String TAG = "FieldFilterConfigMatcher";
        private final String fieldName;
        @Nullable
        private final Pattern pattern;

        public Matcher(String str, @Nullable String str2) {
            this.fieldName = str;
            if (str2 == null) {
                this.pattern = null;
            } else {
                this.pattern = Pattern.compile(str2);
            }
        }

        @Nullable
        public static Matcher parse(@Nullable JSONObject jSONObject) throws JSONException {
            String str = null;
            if (jSONObject == null || !jSONObject.has(MATCHER_FIELD_NAME)) {
                return null;
            }
            String string = jSONObject.getString(MATCHER_FIELD_NAME);
            if (jSONObject.has(MATCHER_REGEX)) {
                str = jSONObject.getString(MATCHER_REGEX);
            }
            return new Matcher(string, str);
        }

        public Boolean matches(Intent intent) {
            Pattern pattern2 = this.pattern;
            if (pattern2 == null || pattern2.toString().equals(MATCHER_REGEX_NULL)) {
                return matchNull(intent);
            }
            if (this.pattern.toString().equals(MATCHER_REGEX_ANY)) {
                return Boolean.valueOf(!matchNull(intent).booleanValue());
            }
            return matchPattern(intent);
        }

        private Boolean matchPattern(Intent intent) {
            String fieldContentByName;
            if (this.pattern == null || (fieldContentByName = getFieldContentByName(intent)) == null) {
                return false;
            }
            return Boolean.valueOf(this.pattern.matcher(fieldContentByName).matches());
        }

        private Boolean matchNull(Intent intent) {
            String fieldContentByName = getFieldContentByName(intent);
            return Boolean.valueOf(fieldContentByName == null || fieldContentByName.isEmpty());
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        @Nullable
        private String getFieldContentByName(Intent intent) {
            char c;
            String str = this.fieldName;
            switch (str.hashCode()) {
                case -1422950858:
                    if (str.equals("action")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1399907075:
                    if (str.equals(MATCHER_FIELD_COMPONENT_NAME)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -1289032093:
                    if (str.equals(MATCHER_FIELD_EXTRAS)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -807062458:
                    if (str.equals(MATCHER_FIELD_PACKAGE)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -244824231:
                    if (str.equals(MATCHER_FIELD_SOURCE_BOUNDS)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 3076010:
                    if (str.equals("data")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 3575610:
                    if (str.equals(MATCHER_FIELD_TYPE)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 918252282:
                    if (str.equals(MATCHER_FIELD_CLIPDATA)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1191572447:
                    if (str.equals(MATCHER_FIELD_SELECTOR)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 1296516636:
                    if (str.equals(MATCHER_FIELD_CATEGORIES)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    return intent.getPackage();
                case 1:
                    return intent.getDataString();
                case 2:
                    return intent.getAction();
                case 3:
                    return intent.getType();
                case 4:
                    if (intent.getExtras() == null || intent.getExtras().isEmpty()) {
                        return null;
                    }
                    return intent.getExtras().toString();
                case 5:
                    if (Build.VERSION.SDK_INT >= 16 && IntentParser.Api16IUtils.getClipData(intent) != null) {
                        return IntentParser.Api16IUtils.getClipData(intent).toString();
                    }
                    return null;
                case 6:
                    if (intent.getSelector() != null) {
                        return intent.getSelector().toString();
                    }
                    return null;
                case 7:
                    if (intent.getSourceBounds() != null) {
                        return intent.getSourceBounds().toString();
                    }
                    return null;
                case '\b':
                    if (intent.getCategories() != null) {
                        return intent.getCategories().toString();
                    }
                    return null;
                case '\t':
                    if (intent.getComponent() != null) {
                        return intent.getComponent().toString();
                    }
                    return null;
                default:
                    String str2 = this.TAG;
                    Log.d(str2, "Invalid field_name: " + this.fieldName);
                    return null;
            }
        }
    }

    public FieldFilterConfig(List<List<Matcher>> list) {
        this.mFilters = list;
    }

    @Nullable
    public static FieldFilterConfig parse(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                ArrayList arrayList2 = new ArrayList(jSONArray2.length());
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    Matcher parse = Matcher.parse(jSONArray2.getJSONObject(i2));
                    if (parse != null) {
                        arrayList2.add(parse);
                    }
                }
                arrayList.add(arrayList2);
            }
            return new FieldFilterConfig(arrayList);
        } catch (JSONException unused) {
            return null;
        }
    }

    public Boolean hasMatcher(String str, String str2) {
        List<List<Matcher>> list = this.mFilters;
        if (!(list == null || str == null)) {
            for (List<Matcher> list2 : list) {
                Iterator<Matcher> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Matcher next = it.next();
                        if (str.equals(next.fieldName)) {
                            if (str2 == null && next.pattern == null) {
                                return true;
                            }
                            if (!(str2 == null || next.pattern == null || !str2.equals(next.pattern.pattern()))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean matches(Intent intent) {
        List<List<Matcher>> list = this.mFilters;
        if (list == null) {
            return false;
        }
        for (List<Matcher> list2 : list) {
            Boolean bool = true;
            Iterator<Matcher> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!it.next().matches(intent).booleanValue()) {
                        bool = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (bool.booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
