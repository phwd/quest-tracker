package com.oculus.panelapp.people.views.holders;

import X.AnonymousClass1Ah;
import android.view.View;
import androidx.annotation.Nullable;

public abstract class PeopleUserRecyclerViewHolder extends AnonymousClass1Ah {
    @Nullable
    public AsyncSource mAsyncActionSource = null;

    public enum AsyncSource {
        MAIN_BUTTON,
        SECONDARY_BUTTON
    }

    public abstract void setCurrentAsyncAction(AsyncSource asyncSource);

    public PeopleUserRecyclerViewHolder(View view) {
        super(view);
    }
}
