package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.ui.emoji.model.BasicEmoji;
import com.facebook.ui.emoji.model.Emoji;

/* renamed from: X.1ol  reason: invalid class name and case insensitive filesystem */
public class C10561ol implements Parcelable.Creator<Emoji> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final Emoji createFromParcel(Parcel parcel) {
        return new BasicEmoji(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final Emoji[] newArray(int i) {
        return new Emoji[i];
    }
}
