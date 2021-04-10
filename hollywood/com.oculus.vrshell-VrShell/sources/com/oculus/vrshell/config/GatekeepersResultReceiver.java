package com.oculus.vrshell.config;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;

public class GatekeepersResultReceiver extends ResultReceiver {
    private static final String GATEKEEPERS_LIST_EXTRA = "GATEKEEPERS_LIST_RESULT";
    private static final String TAG = LoggingUtil.tag(GatekeepersResultReceiver.class);
    private final String mAction;
    private final Callback mCallback;
    private final GatekeepersConfigHolder mHolder;

    public interface Callback {
        void updateGatekeepersHolder(GatekeepersConfiguration gatekeepersConfiguration, ArrayList<String> arrayList);
    }

    public interface GatekeepersConfigHolder {
        void finalizeGatekeepersConfiguration();

        GatekeepersConfiguration getGatekeepersConfiguration();

        Object getSyncInstance();
    }

    public GatekeepersResultReceiver(GatekeepersConfigHolder gatekeepersConfigHolder, String str, Callback callback) {
        super(null);
        this.mHolder = gatekeepersConfigHolder;
        this.mAction = str;
        this.mCallback = callback;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(GATEKEEPERS_LIST_EXTRA);
        String str = TAG;
        Log.d(str, "Received " + this.mAction + " gatekeepers: " + stringArrayList);
        GatekeepersConfiguration gatekeepersConfiguration = this.mHolder.getGatekeepersConfiguration();
        synchronized (this.mHolder.getSyncInstance()) {
            this.mCallback.updateGatekeepersHolder(gatekeepersConfiguration, stringArrayList);
            this.mHolder.finalizeGatekeepersConfiguration();
        }
    }

    public ResultReceiver getCrossPackageReceiver() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver;
    }
}
