package com.oculus.vrshell.home;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class OverlaysInputBlocker {
    private static final String BLOCK_INPUT_FOR_OVERLAY = "com.oculus.vrdriver.block_input_for_overlay";
    private static final String KEY_INPUT_BLOCKING_TYPE = "input_blocking_type";
    private static final String VALUE_NO_INPUT_BLOCKING = "no_input_blocking";

    public static void startBlockingInput(Context context, String inputBlockingType) {
        Intent intent = new Intent(BLOCK_INPUT_FOR_OVERLAY);
        intent.putExtra(KEY_INPUT_BLOCKING_TYPE, inputBlockingType);
        intent.putExtra("_ci_", PendingIntent.getService(context, 0, intent, 0));
        context.sendBroadcast(intent);
    }

    public static void stopBlockingInput(Context context) {
        Intent unblockInputIntent = new Intent(BLOCK_INPUT_FOR_OVERLAY);
        unblockInputIntent.putExtra(KEY_INPUT_BLOCKING_TYPE, VALUE_NO_INPUT_BLOCKING);
        unblockInputIntent.putExtra("_ci_", PendingIntent.getService(context, 0, unblockInputIntent, 0));
        context.sendBroadcast(unblockInputIntent);
    }
}
