package com.facebook.assistant.clientplatform.keyboard.learning.util;

import X.C00628o;
import android.content.Context;

public final class LearningStore {
    public static LearningStore A02;
    public C00628o A00;
    public String A01;

    public static synchronized LearningStore A00(String str, Context context) {
        LearningStore learningStore;
        synchronized (LearningStore.class) {
            learningStore = A02;
            if (learningStore == null) {
                learningStore = new LearningStore(str, context.getApplicationContext());
                A02 = learningStore;
            }
        }
        return learningStore;
    }

    public LearningStore(String str, Context context) {
        this.A01 = str;
        this.A00 = new C00628o(str, context);
    }
}
