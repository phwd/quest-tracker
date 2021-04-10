package com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters;

import androidx.recyclerview.widget.DiffUtil;

public interface ILibraryUpdate {
    void afterDispatchUpdates();

    void beforeDispatchUpdates();

    DiffUtil.Callback getDiffUtilCallback();

    void onStart();
}
