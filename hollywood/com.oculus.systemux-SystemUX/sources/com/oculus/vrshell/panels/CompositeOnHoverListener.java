package com.oculus.vrshell.panels;

import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CompositeOnHoverListener implements View.OnHoverListener {
    final List<View.OnHoverListener> listeners = new ArrayList();

    public void addListener(View.OnHoverListener onHoverListener) {
        this.listeners.add(onHoverListener);
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        Iterator<View.OnHoverListener> it = this.listeners.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean onHover = it.next().onHover(view, motionEvent);
                if (z || onHover) {
                    z = true;
                }
            }
        }
    }
}
