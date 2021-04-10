package com.facebook.secure.sanitizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class SanitizerConfig {
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
}
