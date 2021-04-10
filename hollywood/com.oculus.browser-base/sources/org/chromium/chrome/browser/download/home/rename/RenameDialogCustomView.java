package org.chromium.chrome.browser.download.home.rename;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RenameDialogCustomView extends ScrollView {
    public static final /* synthetic */ int F = 0;
    public AlertDialogEditText G;

    public RenameDialogCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.error_message);
        AlertDialogEditText alertDialogEditText = (AlertDialogEditText) findViewById(R.id.file_name);
        this.G = alertDialogEditText;
        alertDialogEditText.addTextChangedListener(new RL0(this));
    }
}
