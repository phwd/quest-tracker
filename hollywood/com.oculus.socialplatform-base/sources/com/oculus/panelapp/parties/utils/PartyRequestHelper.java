package com.oculus.panelapp.parties.utils;

import android.content.Context;
import com.oculus.horizoncontent.horizon.HorizonContentProviderHelper;
import com.oculus.partiescontent.PartiesCallbackHelper;
import com.oculus.partiescontent.PartiesCallbackObserver;

public class PartyRequestHelper {
    public Context mContext;

    public void fetchMicrophoneSelection(HorizonContentProviderHelper.GetPartyMicrophoneStateCallback getPartyMicrophoneStateCallback) {
        HorizonContentProviderHelper.getPartyMicrophoneState(this.mContext, getPartyMicrophoneStateCallback);
    }

    public void registerPartyObserver(PartiesCallbackObserver partiesCallbackObserver) {
        PartiesCallbackHelper.registerObserver(this.mContext, partiesCallbackObserver);
    }

    public void unregisterPartyObserver(PartiesCallbackObserver partiesCallbackObserver) {
        PartiesCallbackHelper.unregisterObserver(this.mContext, partiesCallbackObserver);
    }

    public PartyRequestHelper(Context context) {
        this.mContext = context;
    }
}
