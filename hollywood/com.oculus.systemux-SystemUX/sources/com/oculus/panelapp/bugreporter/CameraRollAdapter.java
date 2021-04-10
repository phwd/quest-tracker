package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCPlaceholderGlint;
import com.oculus.panelapp.bugreporter.common.ImageTile;
import com.oculus.panelapp.bugreporter.databinding.BugReportCameraRollImageBinding;
import com.oculus.panelapp.bugreporter.databinding.BugReportCameraRollPlaceholderBinding;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import java.io.File;
import java.util.List;

public class CameraRollAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int COLUMN_COUNT = 4;
    private static final int PLACEHOLDER_COUNT = 6;
    private static final int VIEW_TYPE_CAMERA_ROLL_ITEM = 0;
    private static final int VIEW_TYPE_PLACEHOLDER = 1;
    private Context mContext;
    private List<MediaFile> mFiles;
    private RequestBuilder<Bitmap> mGlideFetcher = null;
    private RequestManager mGlideManager = null;
    private boolean mInitializedCameraRoll = false;
    private MediaViewModel mMediaViewModel;
    private final OCEventHandler mOCEventHandler;

    public CameraRollAdapter(Context context, OCEventHandler oCEventHandler, BugReporterUtil bugReporterUtil) {
        this.mContext = context;
        this.mOCEventHandler = oCEventHandler;
        this.mMediaViewModel = bugReporterUtil.getMediaViewModel();
        this.mGlideManager = Glide.with(context);
        this.mGlideFetcher = this.mGlideManager.asBitmap();
    }

    public void initializeCameraRoll(List<MediaFile> list) {
        this.mInitializedCameraRoll = true;
        this.mFiles = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mInitializedCameraRoll ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (this.mInitializedCameraRoll) {
            return createCameraRollViewHolder(viewGroup);
        }
        return createCameraRollPlaceholderViewHolder(viewGroup);
    }

    private CameraRollViewHolder createCameraRollViewHolder(@NonNull ViewGroup viewGroup) {
        BugReportCameraRollImageBinding inflate = BugReportCameraRollImageBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        ((ImageTile) inflate.getRoot()).initialize(inflate);
        inflate.button.setEventHandler(this.mOCEventHandler);
        return new CameraRollViewHolder(inflate);
    }

    private CameraRollPlaceholderViewHolder createCameraRollPlaceholderViewHolder(@NonNull ViewGroup viewGroup) {
        return new CameraRollPlaceholderViewHolder(BugReportCameraRollPlaceholderBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (this.mInitializedCameraRoll) {
            BugReportCameraRollImageBinding bugReportCameraRollImageBinding = ((CameraRollViewHolder) viewHolder).mBinding;
            MediaFile mediaFile = this.mFiles.get(i);
            this.mGlideFetcher.load(Uri.fromFile(new File(mediaFile.getFilePath()))).thumbnail(0.25f).into(bugReportCameraRollImageBinding.image);
            bugReportCameraRollImageBinding.getRoot().setSelected(this.mMediaViewModel.isFileSelected(mediaFile));
            bugReportCameraRollImageBinding.button.setOnClickListener(new View.OnClickListener(bugReportCameraRollImageBinding, mediaFile) {
                /* class com.oculus.panelapp.bugreporter.$$Lambda$CameraRollAdapter$VFhVH4acp0ZqCkVfTfeC8Wy0oXY */
                private final /* synthetic */ BugReportCameraRollImageBinding f$1;
                private final /* synthetic */ MediaFile f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    CameraRollAdapter.this.lambda$onBindViewHolder$2$CameraRollAdapter(this.f$1, this.f$2, view);
                }
            });
            bugReportCameraRollImageBinding.timestamp.setText(mediaFile.getDuration());
            bugReportCameraRollImageBinding.timestamp.setVisibility(mediaFile.isVideo() ? 0 : 8);
            return;
        }
        OCPlaceholderGlint oCPlaceholderGlint = ((CameraRollPlaceholderViewHolder) viewHolder).mBinding.placeholder;
        oCPlaceholderGlint.setOffset((i % 4) * 100);
        oCPlaceholderGlint.startAnimation();
    }

    public /* synthetic */ void lambda$onBindViewHolder$2$CameraRollAdapter(BugReportCameraRollImageBinding bugReportCameraRollImageBinding, MediaFile mediaFile, View view) {
        boolean isSelected = bugReportCameraRollImageBinding.getRoot().isSelected();
        if (isSelected) {
            this.mMediaViewModel.deselectFile(mediaFile);
        } else {
            this.mMediaViewModel.selectFile(mediaFile);
        }
        bugReportCameraRollImageBinding.getRoot().setSelected(!isSelected);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof CameraRollViewHolder) {
            this.mGlideManager.clear(((CameraRollViewHolder) viewHolder).mBinding.image);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mInitializedCameraRoll) {
            return this.mFiles.size();
        }
        return 6;
    }

    public static class CameraRollViewHolder extends RecyclerView.ViewHolder {
        BugReportCameraRollImageBinding mBinding;

        public CameraRollViewHolder(BugReportCameraRollImageBinding bugReportCameraRollImageBinding) {
            super(bugReportCameraRollImageBinding.getRoot());
            this.mBinding = bugReportCameraRollImageBinding;
        }
    }

    public static class CameraRollPlaceholderViewHolder extends RecyclerView.ViewHolder {
        BugReportCameraRollPlaceholderBinding mBinding;

        public CameraRollPlaceholderViewHolder(BugReportCameraRollPlaceholderBinding bugReportCameraRollPlaceholderBinding) {
            super(bugReportCameraRollPlaceholderBinding.getRoot());
            this.mBinding = bugReportCameraRollPlaceholderBinding;
        }
    }
}
