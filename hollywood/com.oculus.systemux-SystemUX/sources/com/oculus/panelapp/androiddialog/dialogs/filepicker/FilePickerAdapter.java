package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.localfilemanager.FileModel;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.androiddialog.databinding.FilePickerRowBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder;
import java.util.function.Consumer;

/* access modifiers changed from: package-private */
public class FilePickerAdapter extends RecyclerView.Adapter<FilePickerViewHolder> {
    private final Context mContext;
    private final OCEventHandler mEventHandler;
    private final ThumbnailLoader mThumbnailLoader;
    private final FilePickerViewModel mViewModel;

    protected FilePickerAdapter(Context context, FilePickerViewModel filePickerViewModel, OCEventHandler oCEventHandler, ThumbnailLoader thumbnailLoader) {
        this.mContext = context;
        this.mViewModel = filePickerViewModel;
        this.mThumbnailLoader = thumbnailLoader;
        this.mEventHandler = oCEventHandler;
    }

    /* access modifiers changed from: package-private */
    public void refresh(boolean z, int i, int i2) {
        if (z) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(i, i2);
        }
    }

    @VisibleForTesting
    static void onFileSelected(Context context, FileModel.FileData fileData, FilePickerViewModel filePickerViewModel, Consumer<Integer> consumer) {
        FileModel.FileData selectedFile = filePickerViewModel.getSelectedFile();
        if (selectedFile == fileData) {
            filePickerViewModel.setSelectedFile(null, context);
        } else {
            filePickerViewModel.setSelectedFile(fileData, context);
            if (selectedFile != null) {
                consumer.accept(Integer.valueOf(selectedFile.index));
            }
        }
        consumer.accept(Integer.valueOf(fileData.index));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public FilePickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FilePickerViewHolder(FilePickerRowBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false));
    }

    public void onBindViewHolder(@NonNull FilePickerViewHolder filePickerViewHolder, int i) {
        FileModel.FileData file = this.mViewModel.getFile(i);
        filePickerViewHolder.setFile(this.mThumbnailLoader, new FilePickerViewHolder.OnFileClicked(file) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerAdapter$jzlqmYccaZ4VabQfUCyW5Eu0 */
            private final /* synthetic */ FileModel.FileData f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder.OnFileClicked
            public final void run() {
                FilePickerAdapter.this.lambda$onBindViewHolder$13$FilePickerAdapter(this.f$1);
            }
        }, file, this.mViewModel.getSelectedFile(), this.mEventHandler);
    }

    public /* synthetic */ void lambda$onBindViewHolder$13$FilePickerAdapter(FileModel.FileData fileData) {
        onFileSelected(this.mContext, fileData, this.mViewModel, new Consumer() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$MSQvZN4WlyFFfkiofY1Bbmwcpc */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FilePickerAdapter.this.notifyItemChanged(((Integer) obj).intValue());
            }
        });
    }

    public void onViewRecycled(@NonNull FilePickerViewHolder filePickerViewHolder) {
        filePickerViewHolder.onViewRecycled(this.mThumbnailLoader);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mViewModel.getFilesCount();
    }
}
