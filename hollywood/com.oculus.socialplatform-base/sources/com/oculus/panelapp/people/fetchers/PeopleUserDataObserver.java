package com.oculus.panelapp.people.fetchers;

import androidx.annotation.Nullable;

public interface PeopleUserDataObserver {
    void onUserUpdated(@Nullable String str);
}
