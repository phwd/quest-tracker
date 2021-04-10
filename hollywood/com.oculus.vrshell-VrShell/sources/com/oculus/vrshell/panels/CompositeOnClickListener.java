package com.oculus.vrshell.panels;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.views.ShellButton;
import java.util.ArrayList;
import java.util.List;

public final class CompositeOnClickListener implements View.OnClickListener {
    private static final String TAG = LoggingUtil.tag(CompositeOnClickListener.class);
    private final List<View.OnClickListener> listeners = new ArrayList();
    private final ShellButton mButton;

    public CompositeOnClickListener(ShellButton shellButton) {
        this.mButton = shellButton;
    }

    public void addListener(@NonNull View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            String str = TAG;
            Log.e(str, "addListener() called with null listener for button id:" + this.mButton.getResources().getResourceName(this.mButton.getId()) + ", text:" + this.mButton.getText().toString());
            return;
        }
        this.listeners.add(onClickListener);
    }

    public void clearListeners() {
        this.listeners.clear();
    }

    public void onClick(View view) {
        for (View.OnClickListener onClickListener : this.listeners) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            } else {
                String str = TAG;
                Log.e(str, "onClick() has a null listener for button id:" + this.mButton.getResources().getResourceName(this.mButton.getId()) + ", text:" + this.mButton.getText().toString());
            }
        }
    }
}
