package com.oculus.assistant.api;

import android.os.Bundle;
import android.os.IInterface;

public interface IOculusAssistantService extends IInterface {
    void A4X(String str, Bundle bundle);

    String A5D(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber);

    void A5K(String str, String str2);
}
