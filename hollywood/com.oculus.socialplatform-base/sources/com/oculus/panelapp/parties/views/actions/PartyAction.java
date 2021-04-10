package com.oculus.panelapp.parties.views.actions;

import android.content.Context;
import androidx.annotation.Nullable;

public abstract class PartyAction {
    public abstract PartyActionType getType();

    public abstract boolean isRelevant();

    public abstract void performAction(Context context, PartyActionHandler partyActionHandler);

    public void execute(Context context, @Nullable final PartyActionHandler partyActionHandler) {
        performAction(context, new PartyActionHandler() {
            /* class com.oculus.panelapp.parties.views.actions.PartyAction.AnonymousClass1 */

            @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
            public void onError() {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onError();
                }
            }

            @Override // com.oculus.panelapp.parties.views.actions.PartyActionHandler
            public void onSuccess() {
                PartyActionHandler partyActionHandler = partyActionHandler;
                if (partyActionHandler != null) {
                    partyActionHandler.onSuccess();
                }
            }
        });
    }
}
