package com.google.android.gms.vision.clearcut;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DynamiteClearcutLogger {
    static {
        new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new ThreadPoolExecutor.DiscardPolicy());
    }
}
