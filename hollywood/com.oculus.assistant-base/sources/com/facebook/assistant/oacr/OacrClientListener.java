package com.facebook.assistant.oacr;

import X.C0813iG;
import X.C0814iH;
import X.C0817iM;
import X.C0818iN;
import X.C0819iO;
import X.C0820iQ;
import X.C0823iT;
import X.iK;

public interface OacrClientListener {
    void onDeviceTts(C0823iT iTVar);

    void onError(C0813iG iGVar);

    void onResponse(iK iKVar);

    void onTranscription(C0817iM iMVar);

    void onTtsAudioData(C0818iN iNVar);

    void onTtsEnd(C0819iO iOVar);

    void onTtsStart(C0820iQ iQVar);

    void onWakewordVerification(C0814iH iHVar);
}
