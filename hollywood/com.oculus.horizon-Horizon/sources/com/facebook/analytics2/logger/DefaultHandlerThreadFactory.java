package com.facebook.analytics2.logger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.HandlerThread;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultHandlerThreadFactory implements HandlerThreadFactory {
    @Override // com.facebook.analytics2.logger.HandlerThreadFactory
    public final boolean A7m() {
        return true;
    }

    @Override // com.facebook.analytics2.logger.HandlerThreadFactory
    @SuppressLint({"BadMethodUse-android.os.HandlerThread._Constructor"})
    public final HandlerThread A20(String str, int i) {
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        return handlerThread;
    }

    public DefaultHandlerThreadFactory(Context context) {
    }
}
