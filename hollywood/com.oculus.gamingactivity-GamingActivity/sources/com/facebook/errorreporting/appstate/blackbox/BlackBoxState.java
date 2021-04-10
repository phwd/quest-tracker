package com.facebook.errorreporting.appstate.blackbox;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class BlackBoxState {
    private static BlackBoxState sInstance;
    @Nullable
    private BlackBoxApi mBlackBoxApi;
    @Nullable
    private BlackBoxRecorderControl mBlackBoxRecorderControl;
    @Nullable
    private Object mContext;
    @Nullable
    private Map<String, String> mExtra;
    @Nullable
    private Map<String, String> mPreviousExtra;

    public interface BlackBoxApi {
        @Nullable
        String collectPersistentTrace(String str, String str2, Context context);

        String getBlackBoxTraceKey();
    }

    public static synchronized BlackBoxState getInstance() {
        BlackBoxState blackBoxState;
        synchronized (BlackBoxState.class) {
            if (sInstance == null) {
                sInstance = new BlackBoxState();
            }
            blackBoxState = sInstance;
        }
        return blackBoxState;
    }

    @Nullable
    public synchronized BlackBoxApi getBlackBoxApi() {
        return this.mBlackBoxApi;
    }

    public synchronized void setBlackBoxApi(BlackBoxApi blackBoxApi) {
        this.mBlackBoxApi = blackBoxApi;
    }

    @Nullable
    public synchronized BlackBoxRecorderControl getBlackBoxRecorderControl() {
        return this.mBlackBoxRecorderControl;
    }

    public synchronized void setBlackBoxRecorderControl(BlackBoxRecorderControl recorderControl) {
        this.mBlackBoxRecorderControl = recorderControl;
    }

    @Nullable
    public synchronized Object getContext() {
        return this.mContext;
    }

    public synchronized void setContext(@Nullable Object context) {
        this.mContext = context;
    }

    @Nullable
    public synchronized Map<String, String> getExtra() {
        return this.mExtra;
    }

    public synchronized void setExtra(Map<String, String> extra) {
        this.mExtra = extra;
    }

    @Nullable
    public synchronized Map<String, String> getPreviousExtra() {
        return this.mPreviousExtra;
    }

    public synchronized void setPreviousExtra(Map<String, String> extra) {
        this.mPreviousExtra = extra;
    }
}
