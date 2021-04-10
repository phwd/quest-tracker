package defpackage;

import android.app.Activity;
import org.chromium.components.bookmarks.BookmarkId;

/* renamed from: Ti  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1182Ti extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Activity f8976a;
    public final /* synthetic */ BookmarkId b;

    public C1182Ti(Activity activity, BookmarkId bookmarkId) {
        this.f8976a = activity;
        this.b = bookmarkId;
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        AbstractC3535lK0.a("EnhancedBookmarks.EditAfterCreateButtonClicked");
        AbstractC1243Ui.g(this.f8976a, this.b);
    }

    @Override // defpackage.AbstractC4758sY0
    public void d(Object obj) {
        AbstractC3535lK0.a("EnhancedBookmarks.EditAfterCreateButtonNotClicked");
    }
}
