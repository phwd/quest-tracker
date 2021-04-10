package X;

import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;

/* renamed from: X.1de  reason: invalid class name and case insensitive filesystem */
public final class C07901de implements AbstractC07921dg<AssetFileDescriptor> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.media.MediaMetadataRetriever, java.lang.Object] */
    @Override // X.AbstractC07921dg
    public final void A5g(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
        AssetFileDescriptor assetFileDescriptor2 = assetFileDescriptor;
        mediaMetadataRetriever.setDataSource(assetFileDescriptor2.getFileDescriptor(), assetFileDescriptor2.getStartOffset(), assetFileDescriptor2.getLength());
    }
}
