package com.oculus.panelapp.parties.views;

import androidx.annotation.Nullable;
import com.oculus.horizoncontent.social.SocialParty;

public interface PartyDataObserver {
    void onUpdateParty(@Nullable SocialParty socialParty);
}
