package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.oculus.vrshell.panels.KeyboardHandler;

public final class ProfileEditText extends EditText implements KeyboardHandler.KeyboardListener {
    public ProfileEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLongClickable(false);
        setTextIsSelectable(false);
        AnonymousClass1 r1 = new ActionMode.Callback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileEditText.AnonymousClass1 */

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
        };
        setCustomSelectionActionModeCallback(r1);
        setCustomInsertionActionModeCallback(r1);
    }

    @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
    public void onKeyboardActionKey() {
        clearFocus();
    }
}
