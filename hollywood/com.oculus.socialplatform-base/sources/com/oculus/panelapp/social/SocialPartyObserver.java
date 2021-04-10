package com.oculus.panelapp.social;

import com.oculus.horizoncontent.social.SocialParty;
import javax.annotation.Nullable;

public interface SocialPartyObserver {
    void onUpdateParty(@Nullable SocialParty socialParty);
}
