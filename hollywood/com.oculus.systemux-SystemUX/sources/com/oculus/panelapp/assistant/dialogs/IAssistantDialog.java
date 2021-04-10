package com.oculus.panelapp.assistant.dialogs;

import android.os.Bundle;

public interface IAssistantDialog extends ISurface {
    int getHeight();

    void getResultData(Bundle bundle);

    int getWidth();

    void hide(Runnable runnable);

    void setBackButtonEnabled(boolean z);

    boolean shouldRemoveOnHide();

    void show();
}
