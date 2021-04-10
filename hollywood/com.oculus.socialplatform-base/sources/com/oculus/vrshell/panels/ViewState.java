package com.oculus.vrshell.panels;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.Nullable;

@Deprecated
public final class ViewState {

    public interface Listener {
        void onViewClick(View view);

        void onViewEnter(View view);

        void onViewExit(View view);
    }

    public static void setViewStateListeners(View view, final Listener listener, final View.OnClickListener onClickListener, final View.OnHoverListener onHoverListener) {
        view.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.vrshell.panels.ViewState.AnonymousClass1 */

            public void onClick(View view) {
                Listener.this.onViewClick(view);
                View.OnClickListener onClickListener = onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
        view.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.vrshell.panels.ViewState.AnonymousClass2 */

            public boolean onHover(View view, MotionEvent motionEvent) {
                ViewState.handleOnHover(view, Listener.this, motionEvent);
                View.OnHoverListener onHoverListener = onHoverListener;
                if (onHoverListener != null) {
                    return onHoverListener.onHover(view, motionEvent);
                }
                return false;
            }
        });
    }

    @Nullable
    public static Listener findInParents(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof Listener) {
                return (Listener) parent;
            }
        }
        return null;
    }

    public static void handleOnHover(View view, Listener listener, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            listener.onViewEnter(view);
        } else if (motionEvent.getAction() == 10) {
            listener.onViewExit(view);
        }
    }

    public static void setupViewStateListeners(View view) {
        Listener findInParents = findInParents(view);
        if (findInParents != null) {
            setViewStateListeners(view, findInParents, null, null);
        }
    }

    public static void handleOnClick(View view, Listener listener) {
        listener.onViewClick(view);
    }
}
