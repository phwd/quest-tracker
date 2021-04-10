package X;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.io.FileNotFoundException;
import javax.annotation.Nullable;

/* renamed from: X.1iv  reason: invalid class name and case insensitive filesystem */
public class C09591iv extends AbstractC09741jf<AbstractC00820Ju<AnonymousClass0VM>> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer$1";
    public final /* synthetic */ C10161kv A00;
    public final /* synthetic */ AnonymousClass1j1 A01;
    public final /* synthetic */ AnonymousClass1l6 A02;
    public final /* synthetic */ AnonymousClass1kA A03;

    @Override // X.AnonymousClass0IL
    @Nullable
    public final Object A06() throws Exception {
        String str;
        Bitmap bitmap;
        String str2;
        String str3;
        String[] strArr;
        try {
            AnonymousClass1j1 r3 = this.A01;
            AnonymousClass1kA r2 = this.A03;
            Uri uri = r2.A03;
            if (uri == null) {
                str2 = null;
            } else {
                str2 = uri.getScheme();
            }
            if ("file".equals(str2)) {
                str = r2.A00().getPath();
            } else {
                if (AnonymousClass0LJ.A01(uri)) {
                    if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        String documentId = DocumentsContract.getDocumentId(uri);
                        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        strArr = new String[]{documentId.split(":")[1]};
                        str3 = "_id=?";
                    } else {
                        str3 = null;
                        strArr = null;
                    }
                    Cursor query = r3.A00.query(uri, new String[]{"_data"}, str3, strArr, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                str = query.getString(query.getColumnIndexOrThrow("_data"));
                            }
                        } finally {
                            query.close();
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                }
                str = null;
            }
        } catch (IllegalArgumentException unused) {
            str = null;
        }
        if (str != null) {
            bitmap = ThumbnailUtils.createVideoThumbnail(str, 1);
        } else {
            try {
                ParcelFileDescriptor openFileDescriptor = this.A01.A00.openFileDescriptor(this.A03.A03, "r");
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openFileDescriptor.getFileDescriptor());
                bitmap = mediaMetadataRetriever.getFrameAtTime(-1);
            } catch (FileNotFoundException unused2) {
                bitmap = null;
            }
        }
        if (bitmap == null) {
            return null;
        }
        C03510nA r1 = C03510nA.A00;
        if (r1 == null) {
            r1 = new C03510nA();
            C03510nA.A00 = r1;
        }
        C002305g r32 = new C002305g(bitmap, r1, C03410mW.A03);
        C10161kv r22 = this.A00;
        r22.A05("image_format", "thumbnail");
        r32.A02(r22.A0C);
        return AbstractC00820Ju.A01(r32, AbstractC00820Ju.A04);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C09591iv(AnonymousClass1j1 r2, AbstractC10011kf r3, AnonymousClass1l6 r4, C10161kv r5, AnonymousClass1l6 r6, C10161kv r7, AnonymousClass1kA r8) {
        super(r3, r4, r5, "VideoThumbnailProducer");
        this.A01 = r2;
        this.A02 = r6;
        this.A00 = r7;
        this.A03 = r8;
    }

    @Override // X.AnonymousClass0IL, X.AbstractC09741jf
    public final void A03(Exception exc) {
        super.A03(exc);
        AnonymousClass1l6 r3 = this.A02;
        C10161kv r2 = this.A00;
        r3.A8F(r2, "VideoThumbnailProducer", false);
        r2.A06("local", "default");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass0IL, X.AbstractC09741jf
    public final void A05(AbstractC00820Ju<AnonymousClass0VM> r5) {
        super.A05(r5);
        AnonymousClass1l6 r3 = this.A02;
        C10161kv r2 = this.A00;
        boolean z = false;
        if (r5 != null) {
            z = true;
        }
        r3.A8F(r2, "VideoThumbnailProducer", z);
        r2.A06("local", "default");
    }
}
