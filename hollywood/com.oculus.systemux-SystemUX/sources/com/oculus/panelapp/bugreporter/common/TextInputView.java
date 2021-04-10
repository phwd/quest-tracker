package com.oculus.panelapp.bugreporter.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.oculus.vrshell.panels.KeyboardHandler;

public class TextInputView extends EditText implements KeyboardHandler.KeyboardListener {
    public TextInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLongClickable(false);
        setTextIsSelectable(false);
        setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            /* class com.oculus.panelapp.bugreporter.common.TextInputView.AnonymousClass1 */

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }

    @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
    public void onKeyboardActionKey() {
        clearFocus();
    }
}
