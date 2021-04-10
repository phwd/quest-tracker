package X;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem;

/* renamed from: X.1C8  reason: invalid class name */
public class AnonymousClass1C8 implements Parcelable.Creator<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem createFromParcel(Parcel parcel) {
        return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(parcel);
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem[] newArray(int i) {
        return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem[i];
    }
}
