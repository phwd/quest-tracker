package org.chromium.chrome.browser.autofill.prefeditor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EditorDialogToolbar extends Toolbar {
    public boolean v0 = true;

    public EditorDialogToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t(R.menu.f42480_resource_name_obfuscated_RES_2131689482);
        MenuItem findItem = ((C4616ri0) p()).findItem(R.id.delete_menu_id);
        if (findItem != null) {
            findItem.setVisible(this.v0);
        }
    }
}
