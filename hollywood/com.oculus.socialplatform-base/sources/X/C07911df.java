package X;

import android.media.MediaMetadataRetriever;
import android.os.ParcelFileDescriptor;

/* renamed from: X.1df  reason: invalid class name and case insensitive filesystem */
public final class C07911df implements AbstractC07921dg<ParcelFileDescriptor> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.media.MediaMetadataRetriever, java.lang.Object] */
    @Override // X.AbstractC07921dg
    public final void A5g(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
        mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
    }
}
