package X;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1YC  reason: invalid class name */
public final class AnonymousClass1YC extends AnonymousClass1S7<InputStream> {
    public static final UriMatcher A00;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        A00 = uriMatcher;
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        UriMatcher uriMatcher2 = A00;
        uriMatcher2.addURI("com.android.contacts", "contacts/lookup/*", 1);
        uriMatcher2.addURI("com.android.contacts", "contacts/#/photo", 2);
        uriMatcher2.addURI("com.android.contacts", "contacts/#", 3);
        uriMatcher2.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        uriMatcher2.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }

    public AnonymousClass1YC(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }
}
