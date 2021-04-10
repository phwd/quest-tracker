package com.oculus.vrshell.panels;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

public final class CompositeOnClickListener implements View.OnClickListener {
    public final List<View.OnClickListener> listeners = new ArrayList();

    public void addListener(View.OnClickListener onClickListener) {
        this.listeners.add(onClickListener);
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    public void onClick(View view) {
        for (View.OnClickListener onClickListener : this.listeners) {
            onClickListener.onClick(view);
        }
    }
}
