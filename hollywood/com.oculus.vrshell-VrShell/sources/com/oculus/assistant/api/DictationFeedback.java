package com.oculus.assistant.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface DictationFeedback {
    public static final String NEGATIVE = "negative";
    public static final String POSITIVE = "positive";
}
