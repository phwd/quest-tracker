package androidx.core.os;

import android.support.v4.media.MediaDescriptionCompat;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.Locale;

/* access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface LocaleListInterface {
    boolean equals(Object obj);

    Locale get(int i);

    @Nullable
    Locale getFirstMatch(String[] strArr);

    Object getLocaleList();

    int hashCode();

    @IntRange(from = -1)
    int indexOf(Locale locale);

    boolean isEmpty();

    void setLocaleList(@NonNull Locale... localeArr);

    @IntRange(from = MediaDescriptionCompat.BT_FOLDER_TYPE_MIXED)
    int size();

    String toLanguageTags();

    String toString();
}
