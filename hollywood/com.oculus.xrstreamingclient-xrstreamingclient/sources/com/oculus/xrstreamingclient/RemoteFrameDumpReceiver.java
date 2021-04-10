package com.oculus.xrstreamingclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RemoteFrameDumpReceiver extends BroadcastReceiver {
    public static final String DUMP_ACTION = "com.oculus.xrstreamingclient.intent.remoteframedump.DUMP";
    public static final String START_ACTION = "com.oculus.xrstreamingclient.intent.remoteframedump.START";
    private static final String TAG = "RemoteFrameDumpReceiver";

    public void onReceive(Context context, Intent intent) {
    }
}
