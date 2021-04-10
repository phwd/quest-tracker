package com.oculus.panelapp.androiddialog.dialogs.integrity;

import android.net.Uri;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

public class ParticipantThumbnailLoader {
    private final ViewOutlineProvider mOutlineProvider;

    public ParticipantThumbnailLoader(ViewOutlineProvider viewOutlineProvider) {
        this.mOutlineProvider = viewOutlineProvider;
    }

    public void load(Uri uri, ImageView imageView) {
        imageView.setImageURI(uri);
        imageView.setOutlineProvider(this.mOutlineProvider);
        imageView.setClipToOutline(true);
    }
}
