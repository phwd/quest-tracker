package com.facebook.secure.sanitizer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SanitizerConfig {
    private static final String KEEP_ALL_VALUE = "keep_all_value";
    private static final String KEEP_PATH_WITH_CONFIG = "keep_path_with_config";
    private static final String KEEP_VALUE_WITH_CONFIG = "keep_value_with_config";
    private static final String MATCHERS = "matchers";
    public static final int NUM_FIELD_EXTRA = 3;
    public static final int NUM_FIELD_URL_QUERY_PARAM = 2;
    private static final String PATH_MATCHERS = "path_matchers";
    private static final String PRESERVE_ENCODING = "preserve_encoding";
    private final List<List<Pattern>> mFieldMatchers;
    private final boolean mKeepAllValue;
    private final List<List<Pattern>> mKeepUriPathMatchers;
    private final boolean mKeepUriPathWithConfig;
    private final boolean mKeepValueWithConfig;
    private final boolean mPreserveEncoding;

    private SanitizerConfig(boolean z, boolean z2, List<List<Pattern>> list, boolean z3, boolean z4, List<List<Pattern>> list2) {
        this.mKeepAllValue = z;
        this.mKeepValueWithConfig = z2;
        this.mFieldMatchers = list;
        this.mPreserveEncoding = z3;
        this.mKeepUriPathWithConfig = z4;
        this.mKeepUriPathMatchers = list2;
    }

    public boolean isKeepAllValue() {
        return this.mKeepAllValue;
    }

    public boolean isKeepValueWithConfig() {
        return this.mKeepValueWithConfig;
    }

    public List<List<Pattern>> getFieldMatchers() {
        return Collections.unmodifiableList(this.mFieldMatchers);
    }

    public boolean isPreserveEncoding() {
        return this.mPreserveEncoding;
    }

    public boolean isKeepUriPathWithConfig() {
        return this.mKeepUriPathWithConfig;
    }

    public List<List<Pattern>> getKeepUriPathMatchers() {
        return Collections.unmodifiableList(this.mKeepUriPathMatchers);
    }

    public static class Builder {
        private List<List<Pattern>> mFieldMatchers = Collections.emptyList();
        private boolean mKeepAllValue = false;
        private List<List<Pattern>> mKeepUriPathMatchers = new ArrayList();
        private boolean mKeepUriPathWithConfig = false;
        private boolean mKeepValueWithConfig = false;
        private boolean mPreserveEncoding = false;

        public Builder setKeepAllValue(boolean z) {
            this.mKeepAllValue = z;
            return this;
        }

        public Builder setKeepValueWithConfig(boolean z) {
            this.mKeepValueWithConfig = z;
            return this;
        }

        public Builder setFieldMatchers(@Nullable String[][] strArr) {
            if (!(strArr == null || strArr.length == 0)) {
                try {
                    this.mFieldMatchers = new ArrayList(strArr.length);
                    for (String[] strArr2 : strArr) {
                        ArrayList arrayList = new ArrayList(strArr2.length);
                        for (String str : strArr2) {
                            arrayList.add(Pattern.compile(str));
                        }
                        this.mFieldMatchers.add(arrayList);
                    }
                } catch (PatternSyntaxException unused) {
                }
            }
            return this;
        }

        public Builder setFieldMatchers(List<List<Pattern>> list) {
            this.mFieldMatchers = list;
            return this;
        }

        public Builder setKeepAllUriParamsMatchingName(String[] strArr) {
            setKeepValueWithConfig(true);
            int length = strArr.length;
            String[][] strArr2 = (String[][]) Array.newInstance(String.class, length, 2);
            for (int i = 0; i < length; i++) {
                strArr2[i] = new String[]{strArr[i], ".*"};
            }
            setFieldMatchers(strArr2);
            return this;
        }

        public Builder setKeepAllExtrasMatchingName(String[] strArr) {
            setKeepValueWithConfig(true);
            int length = strArr.length;
            String[][] strArr2 = (String[][]) Array.newInstance(String.class, length, 3);
            for (int i = 0; i < length; i++) {
                strArr2[i] = new String[]{strArr[i], ".*", ".*"};
            }
            setFieldMatchers(strArr2);
            return this;
        }

        public Builder setPreserveEncoding(boolean z) {
            this.mPreserveEncoding = z;
            return this;
        }

        public Builder setKeepUriPathWithConfig(boolean z) {
            this.mKeepUriPathWithConfig = z;
            return this;
        }

        public Builder setKeepUriPathMatchers(List<List<Pattern>> list) {
            this.mKeepUriPathMatchers = list;
            return this;
        }

        public Builder addKeepUriPathMatchers(String str, String str2, String str3) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Pattern.compile(str));
            arrayList.add(Pattern.compile(str2));
            arrayList.add(Pattern.compile(str3));
            this.mKeepUriPathMatchers.add(arrayList);
            return this;
        }

        public SanitizerConfig build() {
            if (this.mKeepValueWithConfig) {
                if (this.mKeepAllValue) {
                    throw new IllegalStateException("We cannot keep all value and keep value based on config.");
                } else if (this.mFieldMatchers.isEmpty()) {
                    throw new IllegalStateException("Field matchers cannot be empty when we are keeping value baesd on config.");
                }
            }
            return new SanitizerConfig(this.mKeepAllValue, this.mKeepValueWithConfig, this.mFieldMatchers, this.mPreserveEncoding, this.mKeepUriPathWithConfig, this.mKeepUriPathMatchers);
        }
    }

    @Nullable
    public static SanitizerConfig parse(String str, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean z = true;
            boolean z2 = jSONObject.has(KEEP_ALL_VALUE) && jSONObject.getBoolean(KEEP_ALL_VALUE);
            boolean z3 = jSONObject.has(KEEP_VALUE_WITH_CONFIG) && jSONObject.getBoolean(KEEP_VALUE_WITH_CONFIG);
            boolean z4 = jSONObject.has(PRESERVE_ENCODING) && jSONObject.getBoolean(PRESERVE_ENCODING);
            if (!jSONObject.has(KEEP_PATH_WITH_CONFIG) || !jSONObject.getBoolean(KEEP_PATH_WITH_CONFIG)) {
                z = false;
            }
            List<List<Pattern>> emptyList = Collections.emptyList();
            if (jSONObject.has(MATCHERS)) {
                emptyList = parseMatchers(jSONObject.getJSONArray(MATCHERS), i);
            }
            List<List<Pattern>> emptyList2 = Collections.emptyList();
            if (jSONObject.has(PATH_MATCHERS)) {
                emptyList2 = parseMatchers(jSONObject.getJSONArray(PATH_MATCHERS), 3);
            }
            return new Builder().setKeepAllValue(z2).setKeepValueWithConfig(z3).setFieldMatchers(emptyList).setPreserveEncoding(z4).setKeepUriPathWithConfig(z).setKeepUriPathMatchers(emptyList2).build();
        } catch (JSONException unused) {
            return null;
        }
    }

    private static List<List<Pattern>> parseMatchers(JSONArray jSONArray, int i) throws JSONException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i2);
            if (jSONArray2.length() != i) {
                break;
            }
            ArrayList arrayList2 = new ArrayList(i);
            for (int i3 = 0; i3 < i; i3++) {
                arrayList2.add(Pattern.compile(jSONArray2.getString(i3)));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }
}
