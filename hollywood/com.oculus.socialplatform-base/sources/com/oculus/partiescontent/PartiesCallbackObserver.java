package com.oculus.partiescontent;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.partiescontent.PartiesContent;

public abstract class PartiesCallbackObserver extends ContentObserver {
    public static final String TAG = LoggingUtil.tag(PartiesCallbackObserver.class);

    public abstract void onAppChatAvailabilityChanged();

    public abstract void onPartyMicrophoneStateChanged();

    public abstract void onPartyVoipConnectionStatusChanged();

    public final void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        uri.toString();
        String path = uri.getPath();
        int hashCode = path.hashCode();
        if (hashCode != -1810259990 && hashCode == 370019812 && path.equals(PartiesContent.Callbacks.PARTY_MIC_STATE_CHANGED)) {
            onPartyMicrophoneStateChanged();
        }
    }

    public PartiesCallbackObserver(Handler handler) {
        super(handler);
    }
}
