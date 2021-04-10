package com.oculus.partiescontent;

import android.content.Context;

public class PartiesCallbackHelper {
    public static void registerObserver(Context context, PartiesCallbackObserver partiesCallbackObserver) {
        context.getContentResolver().registerContentObserver(PartiesContent.CONTENT_AUTHORITY_BASE_URI, true, partiesCallbackObserver);
    }

    public static void unregisterObserver(Context context, PartiesCallbackObserver partiesCallbackObserver) {
        context.getContentResolver().unregisterContentObserver(partiesCallbackObserver);
    }
}
