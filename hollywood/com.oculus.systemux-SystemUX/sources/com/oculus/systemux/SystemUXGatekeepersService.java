package com.oculus.systemux;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.ArrayList;

public class SystemUXGatekeepersService extends IntentService {
    private static final String GATEKEEPERS_LIST_EXTRA = "GATEKEEPERS_LIST_RESULT";
    private static final String GATEKEEPERS_RESULT_EXTRA = "RESULT_RECEIVER";
    private static final int RESULT_CODE = 1;
    private static final String TAG = LoggingUtil.tag(SystemUXGatekeepersService.class);

    public SystemUXGatekeepersService() {
        super(SystemUXGatekeepersService.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "SystemUXGatekeepersService received an intent");
        if (intent != null) {
            ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra(GATEKEEPERS_RESULT_EXTRA);
            Bundle bundle = new Bundle();
            ArrayList<String> gatekeepers = Gatekeeper.getGatekeepers();
            String str = TAG;
            Log.d(str, "SystemUX GKs defined: " + gatekeepers.size());
            bundle.putStringArrayList(GATEKEEPERS_LIST_EXTRA, gatekeepers);
            if (resultReceiver != null) {
                Log.d(TAG, "Result receiver is not null, so sending result back.");
                resultReceiver.send(1, bundle);
            }
        }
    }
}
