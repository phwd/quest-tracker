package X;

import android.media.MediaMetadataRetriever;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;

@RequiresApi(23)
/* renamed from: X.1da  reason: invalid class name and case insensitive filesystem */
public final class C07861da implements AbstractC07921dg<ByteBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [android.media.MediaMetadataRetriever, java.lang.Object] */
    @Override // X.AbstractC07921dg
    public final /* bridge */ /* synthetic */ void A5g(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
        mediaMetadataRetriever.setDataSource(new C07871db(this, byteBuffer));
    }
}
