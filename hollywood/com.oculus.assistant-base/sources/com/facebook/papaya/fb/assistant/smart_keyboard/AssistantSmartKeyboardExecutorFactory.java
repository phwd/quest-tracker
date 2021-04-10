package com.facebook.papaya.fb.assistant.smart_keyboard;

import X.KJ;
import android.content.Context;
import android.os.Bundle;
import com.facebook.papaya.client.executor.IExecutorFactory;

public final class AssistantSmartKeyboardExecutorFactory extends IExecutorFactory {
    private native void initHybrid();

    static {
        KJ.A05("papaya-assistant-smart-keyboard-executor", 0);
    }

    public AssistantSmartKeyboardExecutorFactory(Context context, Bundle bundle) {
        initHybrid();
    }
}
