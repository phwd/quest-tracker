package defpackage;

import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import android.widget.Checkable;
import java.util.HashMap;

/* renamed from: qD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4369qD0 {

    /* renamed from: a  reason: collision with root package name */
    public C4198pD0 f11124a;
    public HashMap b;

    public C4369qD0(ContentCaptureSession contentCaptureSession, AutofillId autofillId) {
        this.f11124a = new C4198pD0(contentCaptureSession, autofillId);
    }

    public static C4369qD0 a(View view) {
        ViewStructure newViewStructure;
        AutofillId autofillId;
        ContentCaptureSession contentCaptureSession = view.getContentCaptureSession();
        if (contentCaptureSession == null || (autofillId = (newViewStructure = contentCaptureSession.newViewStructure(view)).getAutofillId()) == null) {
            return null;
        }
        newViewStructure.setDimens(view.getLeft(), view.getTop(), 0, 0, view.getRight() - view.getLeft(), view.getBottom() - view.getTop());
        newViewStructure.setVisibility(view.getVisibility());
        newViewStructure.setEnabled(view.isEnabled());
        newViewStructure.setClickable(view.isClickable());
        newViewStructure.setFocusable(view.isFocusable());
        newViewStructure.setFocused(view.isFocused());
        newViewStructure.setAccessibilityFocused(view.isAccessibilityFocused());
        newViewStructure.setSelected(view.isSelected());
        newViewStructure.setActivated(view.isActivated());
        newViewStructure.setLongClickable(view.isLongClickable());
        if (view instanceof Checkable) {
            newViewStructure.setCheckable(true);
            if (((Checkable) view).isChecked()) {
                newViewStructure.setChecked(true);
            }
        }
        if (view.isOpaque()) {
            newViewStructure.setOpaque(true);
        }
        if (view.isContextClickable()) {
            newViewStructure.setContextClickable(true);
        }
        CharSequence accessibilityClassName = view.getAccessibilityClassName();
        if (accessibilityClassName != null) {
            newViewStructure.setClassName(accessibilityClassName.toString());
        }
        newViewStructure.setContentDescription(view.getContentDescription());
        return new C4369qD0(contentCaptureSession, autofillId);
    }

    public HashMap b() {
        if (this.b == null) {
            this.b = new HashMap();
        }
        return this.b;
    }

    public C4198pD0 c() {
        return this.f11124a;
    }
}
