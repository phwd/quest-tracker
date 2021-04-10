package com.oculus.panelapp.social;

import X.AnonymousClass1Ah;
import android.view.View;
import androidx.annotation.Nullable;

public abstract class SocialUserRecyclerViewHolder extends AnonymousClass1Ah {
    @Nullable
    public AsyncSource mAsyncActionSource = null;

    public enum AsyncSource {
        MAIN_BUTTON,
        SECONDARY_BUTTON,
        START_PARTY_BUTTON
    }

    public abstract void setCurrentAsyncAction(AsyncSource asyncSource);

    public SocialUserRecyclerViewHolder(View view) {
        super(view);
    }
}
