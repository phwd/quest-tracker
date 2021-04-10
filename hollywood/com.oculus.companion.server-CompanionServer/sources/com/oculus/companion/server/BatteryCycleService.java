package com.oculus.companion.server;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BatteryCycleService extends IntentService {
    private static final String TAG = "BatteryCycleService";

    public BatteryCycleService() {
        super(BatteryCycleService.class.getSimpleName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals("OC_BATTERY_PERSIST_BATTERY_CYCLE_COUNT")) {
            Log.e(TAG, "Saving Battery Cycle Count");
            persistBatteryCycleCount();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0127 A[SYNTHETIC, Splitter:B:76:0x0127] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x012f A[Catch:{ IOException -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0134 A[Catch:{ IOException -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0139 A[Catch:{ IOException -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void persistBatteryCycleCount() {
        /*
        // Method dump skipped, instructions count: 323
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.companion.server.BatteryCycleService.persistBatteryCycleCount():void");
    }
}
