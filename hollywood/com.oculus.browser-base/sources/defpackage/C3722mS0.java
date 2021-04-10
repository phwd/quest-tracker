package defpackage;

import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: mS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3722mS0 {

    /* renamed from: a  reason: collision with root package name */
    public final SelectionPopupControllerImpl f10421a;

    public C3722mS0(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.f10421a = selectionPopupControllerImpl;
    }

    public View a() {
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.f10421a;
        if (!SelectionPopupControllerImpl.G) {
            return selectionPopupControllerImpl.Q;
        }
        WindowAndroid windowAndroid = selectionPopupControllerImpl.f10931J;
        if (windowAndroid == null) {
            return null;
        }
        return windowAndroid.w0();
    }
}
