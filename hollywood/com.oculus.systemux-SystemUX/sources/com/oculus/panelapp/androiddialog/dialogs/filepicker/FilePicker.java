package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.localfilemanager.FileModel;
import com.oculus.localfilemanager.MediaStoreFileLoader;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCDropdownVisibilityCallback;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.FilePickerDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;
import com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback;
import com.oculus.tablet.utils.backtotop.BackToTopButtonManager;
import com.oculus.tablet.utils.backtotop.BackToTopButtonProvider;
import com.oculus.tablet.utils.backtotop.PendingScrollUpdate;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FilePicker {
    private static final String TAG = LoggingUtil.tag(FilePicker.class);

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface GetSelectedFile {
        FileModel.FileData run();
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface LoadMoreFiles {
        void run();
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnCancel {
        void run();
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnFileSelected {
        void run(FileModel.FileData fileData);
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnFilterSelected {
        void run(MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter, MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter);
    }

    /* access modifiers changed from: package-private */
    public static class LinearLayoutManagerWithDeactivableScrolling extends LinearLayoutManager {
        private boolean mScrollingEnabled = true;

        public LinearLayoutManagerWithDeactivableScrolling(Context context) {
            super(context);
        }

        public void setScrollingEnabled(boolean z) {
            this.mScrollingEnabled = z;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager, androidx.recyclerview.widget.LinearLayoutManager
        public boolean canScrollVertically() {
            return this.mScrollingEnabled && super.canScrollVertically();
        }
    }

    private FilePicker() {
    }

    private static Map<MediaStoreFileLoader.FileCategoryFilter, String> getCategoriesMap(final Resources resources) {
        return new HashMap<MediaStoreFileLoader.FileCategoryFilter, String>() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass1 */

            {
                put(MediaStoreFileLoader.FileCategoryFilter.ALL, resources.getString(R.string.file_picker_category_filter_all));
                put(MediaStoreFileLoader.FileCategoryFilter.MEDIA, resources.getString(R.string.file_picker_category_filter_media));
                put(MediaStoreFileLoader.FileCategoryFilter.DOWNLOADS, resources.getString(R.string.file_picker_category_filter_downloads));
            }
        };
    }

    private static Map<MediaStoreFileLoader.FileOrderingFilter, String> getOrderingsMap(final Resources resources) {
        return new HashMap<MediaStoreFileLoader.FileOrderingFilter, String>() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass2 */

            {
                put(MediaStoreFileLoader.FileOrderingFilter.LARGEST, resources.getString(R.string.file_picker_ordering_filter_largest));
                put(MediaStoreFileLoader.FileOrderingFilter.SMALLEST, resources.getString(R.string.file_picker_ordering_filter_smallest));
                put(MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT, resources.getString(R.string.file_picker_ordering_filter_most_recent));
                put(MediaStoreFileLoader.FileOrderingFilter.OLDEST, resources.getString(R.string.file_picker_ordering_filter_oldest));
            }
        };
    }

    @VisibleForTesting
    static void initializeSelectors(OCEventHandler oCEventHandler, OCSelect<MediaStoreFileLoader.FileCategoryFilter> oCSelect, OCSelect<MediaStoreFileLoader.FileOrderingFilter> oCSelect2, OnFilterSelected onFilterSelected, Resources resources, final LinearLayoutManagerWithDeactivableScrolling linearLayoutManagerWithDeactivableScrolling) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MediaStoreFileLoader.FileCategoryFilter.ALL);
        arrayList.add(MediaStoreFileLoader.FileCategoryFilter.MEDIA);
        arrayList.add(MediaStoreFileLoader.FileCategoryFilter.DOWNLOADS);
        Map<MediaStoreFileLoader.FileCategoryFilter, String> categoriesMap = getCategoriesMap(resources);
        oCSelect.setItems(arrayList);
        oCSelect.setTitleMap(categoriesMap);
        oCSelect.setOnItemClick(new Function(onFilterSelected, oCSelect2) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$6AUZZFt7EdhjOHXZlZO7bvTjI */
            private final /* synthetic */ FilePicker.OnFilterSelected f$1;
            private final /* synthetic */ OCSelect f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FilePicker.lambda$initializeSelectors$7(OCSelect.this, this.f$1, this.f$2, (MediaStoreFileLoader.FileCategoryFilter) obj);
            }
        });
        AnonymousClass3 r0 = new OCDropdownVisibilityCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass3 */

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onShow() {
                linearLayoutManagerWithDeactivableScrolling.setScrollingEnabled(false);
            }

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onHide() {
                linearLayoutManagerWithDeactivableScrolling.setScrollingEnabled(true);
            }
        };
        oCSelect.setVisibilityCallback(r0);
        oCSelect.setSelectedItem(MediaStoreFileLoader.FileCategoryFilter.MEDIA);
        oCSelect.setEventHandler(oCEventHandler);
        ArrayList arrayList2 = new ArrayList(EnumSet.allOf(MediaStoreFileLoader.FileOrderingFilter.class));
        Map<MediaStoreFileLoader.FileOrderingFilter, String> orderingsMap = getOrderingsMap(resources);
        oCSelect2.setItems(arrayList2);
        oCSelect2.setTitleMap(orderingsMap);
        oCSelect2.setSelectedItem(MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT);
        oCSelect2.setEventHandler(oCEventHandler);
        oCSelect2.setVisibilityCallback(r0);
        oCSelect2.setOnItemClick(new Function(onFilterSelected, oCSelect) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$xfvSBFzLoO4M9UWfQAnMOQgNPNs */
            private final /* synthetic */ FilePicker.OnFilterSelected f$1;
            private final /* synthetic */ OCSelect f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FilePicker.lambda$initializeSelectors$8(OCSelect.this, this.f$1, this.f$2, (MediaStoreFileLoader.FileOrderingFilter) obj);
            }
        });
    }

    static /* synthetic */ Object lambda$initializeSelectors$7(OCSelect oCSelect, OnFilterSelected onFilterSelected, OCSelect oCSelect2, MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter) {
        oCSelect.setSelectedItem(fileCategoryFilter);
        onFilterSelected.run(fileCategoryFilter, (MediaStoreFileLoader.FileOrderingFilter) oCSelect2.getSelectedItem());
        return null;
    }

    static /* synthetic */ Object lambda$initializeSelectors$8(OCSelect oCSelect, OnFilterSelected onFilterSelected, OCSelect oCSelect2, MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter) {
        oCSelect.setSelectedItem(fileOrderingFilter);
        onFilterSelected.run((MediaStoreFileLoader.FileCategoryFilter) oCSelect2.getSelectedItem(), fileOrderingFilter);
        return null;
    }

    @VisibleForTesting
    static void initializeButtons(OCEventHandler oCEventHandler, OCButton oCButton, OCButton oCButton2, OnFileSelected onFileSelected, OnCancel onCancel, GetSelectedFile getSelectedFile) {
        oCButton.setOnClickListener(new View.OnClickListener(getSelectedFile) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$TU6BQbPi8M4Y4vB_lM304K5za4 */
            private final /* synthetic */ FilePicker.GetSelectedFile f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                FilePicker.lambda$initializeButtons$9(FilePicker.OnFileSelected.this, this.f$1, view);
            }
        });
        oCButton.setEventHandler(oCEventHandler);
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$gy0Ip6IqSdqxKc3pCRvaQRFHY */

            public final void onClick(View view) {
                FilePicker.lambda$initializeButtons$10(FilePicker.OnCancel.this, view);
            }
        });
        oCButton2.setEventHandler(oCEventHandler);
    }

    @VisibleForTesting
    static void initializeRecyclerView(RecyclerView recyclerView, final LinearLayoutManager linearLayoutManager, FilePickerAdapter filePickerAdapter, final LoadMoreFiles loadMoreFiles, final BackToTopButtonManager backToTopButtonManager) {
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(filePickerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass4 */

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int findFirstVisibleItemPosition;
                super.onScrolled(recyclerView, i, i2);
                if (linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount() - 1) {
                    loadMoreFiles.run();
                }
                if (i2 != 0 && (findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()) >= 0) {
                    backToTopButtonManager.doActionBasedOnPosition(findFirstVisibleItemPosition);
                }
            }
        });
    }

    static void initalizePreviewImage(final ThumbnailLoader thumbnailLoader, final ImageView imageView, final FilePickerViewModel filePickerViewModel) {
        filePickerViewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass5 */

            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (i == BR.selectedFilePath) {
                    thumbnailLoader.unload(imageView);
                    FileModel.FileData selectedFile = filePickerViewModel.getSelectedFile();
                    if (selectedFile != null) {
                        int i2 = AnonymousClass8.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType[(selectedFile.fileMetadata != null ? selectedFile.fileMetadata.type : FileModel.FileType.UNKNOWN).ordinal()];
                        if (i2 == 1) {
                            thumbnailLoader.load(R.drawable.file_picker_preview_unknown_file, imageView);
                        } else if (i2 != 2) {
                            thumbnailLoader.load(selectedFile.path, imageView);
                        } else {
                            thumbnailLoader.load(R.drawable.file_picker_preview_known_file, imageView);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker$8  reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localfilemanager$FileModel$FileType = new int[FileModel.FileType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.localfilemanager.FileModel$FileType[] r0 = com.oculus.localfilemanager.FileModel.FileType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass8.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass8.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.localfilemanager.FileModel$FileType r1 = com.oculus.localfilemanager.FileModel.FileType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass8.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.localfilemanager.FileModel$FileType r1 = com.oculus.localfilemanager.FileModel.FileType.KNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass8.<clinit>():void");
        }
    }

    static void initializeBackToTopButton(BackToTopButtonManager backToTopButtonManager, FilePickerDialogBinding filePickerDialogBinding, OCEventHandler oCEventHandler, final LinearLayoutManagerWithDeactivableScrolling linearLayoutManagerWithDeactivableScrolling, PendingScrollUpdate pendingScrollUpdate, ScrollCallback scrollCallback, FilePickerViewModel filePickerViewModel) {
        filePickerDialogBinding.backToTop.backToTopButton.setEventHandler(oCEventHandler);
        backToTopButtonManager.initialize(new BackToTopButtonProvider(filePickerDialogBinding.backToTop.backToTopButtonHeightLayout, filePickerDialogBinding.backToTop.backToTopButton), scrollCallback, new BackToTopButtonActionCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass6 */

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onClick() {
                linearLayoutManagerWithDeactivableScrolling.setScrollingEnabled(true);
            }

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onHoverEnter() {
                linearLayoutManagerWithDeactivableScrolling.setScrollingEnabled(false);
            }

            @Override // com.oculus.tablet.utils.backtotop.BackToTopButtonActionCallback
            public void onHoverExit() {
                linearLayoutManagerWithDeactivableScrolling.setScrollingEnabled(true);
            }
        });
    }

    static ScrollCallback initializeScrollCallback(final FilePickerDialogBinding filePickerDialogBinding, final PendingScrollUpdate pendingScrollUpdate, final BackToTopButtonManager backToTopButtonManager) {
        return new ScrollCallback() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.AnonymousClass7 */

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void onUpdate() {
                FilePickerDialogBinding filePickerDialogBinding;
                int scrollToPosition;
                if (pendingScrollUpdate != null && (filePickerDialogBinding = filePickerDialogBinding) != null && filePickerDialogBinding.filesList != null && pendingScrollUpdate.hasPendingScrollAction()) {
                    Log.d(FilePicker.TAG, String.format("Scroll to position: %d", Integer.valueOf(pendingScrollUpdate.getScrollToPosition())));
                    filePickerDialogBinding.filesList.scrollToPosition(pendingScrollUpdate.getScrollToPosition());
                    if (backToTopButtonManager != null && (scrollToPosition = pendingScrollUpdate.getScrollToPosition()) >= 0) {
                        backToTopButtonManager.doActionBasedOnPosition(scrollToPosition);
                    }
                    pendingScrollUpdate.resetPendingScrollAction();
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void addPendingScrollToPosition(int i) {
                PendingScrollUpdate pendingScrollUpdate = pendingScrollUpdate;
                if (pendingScrollUpdate != null) {
                    pendingScrollUpdate.addPendingScrollToPosition(i);
                    Log.d(FilePicker.TAG, String.format("Setting pending scroll position: %d", Integer.valueOf(pendingScrollUpdate.getScrollToPosition())));
                }
            }

            @Override // com.oculus.tablet.utils.backtotop.ScrollCallback
            public void smoothScrollToPosition(int i) {
                FilePickerDialogBinding filePickerDialogBinding = filePickerDialogBinding;
                if (filePickerDialogBinding != null && filePickerDialogBinding.filesList != null) {
                    filePickerDialogBinding.filesList.smoothScrollToPosition(i);
                }
            }
        };
    }

    static void init(Context context, ThumbnailLoader thumbnailLoader, OCEventHandler oCEventHandler, FilePickerDialogBinding filePickerDialogBinding, OnFileSelected onFileSelected, OnCancel onCancel) {
        Log.d(TAG, "Initializing FilePicker");
        FilePickerViewModel filePickerViewModel = new FilePickerViewModel();
        filePickerDialogBinding.setViewModel(filePickerViewModel);
        BackToTopButtonManager backToTopButtonManager = new BackToTopButtonManager(context);
        PendingScrollUpdate pendingScrollUpdate = new PendingScrollUpdate();
        ScrollCallback initializeScrollCallback = initializeScrollCallback(filePickerDialogBinding, pendingScrollUpdate, backToTopButtonManager);
        FilePickerAdapter filePickerAdapter = new FilePickerAdapter(context, filePickerViewModel, oCEventHandler, thumbnailLoader);
        initalizePreviewImage(thumbnailLoader, filePickerDialogBinding.previewLayout.previewImage, filePickerViewModel);
        MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter = MediaStoreFileLoader.FileCategoryFilter.MEDIA;
        MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter = MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT;
        filePickerAdapter.getClass();
        filePickerViewModel.loadFiles(true, context, fileCategoryFilter, fileOrderingFilter, new FilePickerViewModel.OnFilesLoaded() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$n1yYov8gywTogMOB5m2ML535I */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel.OnFilesLoaded
            public final void run(boolean z, int i, int i2) {
                FilePickerAdapter.this.refresh(z, i, i2);
            }
        });
        LinearLayoutManagerWithDeactivableScrolling linearLayoutManagerWithDeactivableScrolling = new LinearLayoutManagerWithDeactivableScrolling(context);
        OCButton oCButton = filePickerDialogBinding.confirmButton;
        OCButton oCButton2 = filePickerDialogBinding.cancelButton;
        filePickerViewModel.getClass();
        initializeButtons(oCEventHandler, oCButton, oCButton2, onFileSelected, onCancel, new GetSelectedFile() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$59B0uCmb5YGLlhHRUGqlTcO6LVI */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.GetSelectedFile
            public final FileModel.FileData run() {
                return FilePickerViewModel.this.getSelectedFile();
            }
        });
        initializeBackToTopButton(backToTopButtonManager, filePickerDialogBinding, oCEventHandler, linearLayoutManagerWithDeactivableScrolling, pendingScrollUpdate, initializeScrollCallback, filePickerViewModel);
        OCSelect oCSelect = filePickerDialogBinding.categorySelector;
        OCSelect oCSelect2 = filePickerDialogBinding.orderingSelector;
        initializeSelectors(oCEventHandler, oCSelect, oCSelect2, new OnFilterSelected(context, filePickerAdapter) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$xto8iwHyJrH2xVoZGNsJx_M4b58 */
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ FilePickerAdapter f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.OnFilterSelected
            public final void run(MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter, MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter) {
                FilePicker.lambda$init$11(FilePickerViewModel.this, this.f$1, this.f$2, fileCategoryFilter, fileOrderingFilter);
            }
        }, context.getResources(), linearLayoutManagerWithDeactivableScrolling);
        initializeRecyclerView(filePickerDialogBinding.filesList, linearLayoutManagerWithDeactivableScrolling, filePickerAdapter, new LoadMoreFiles(context, oCSelect, oCSelect2, filePickerAdapter) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePicker$mdJHQmNbaSeeET9WlMDWD8ILLhU */
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ OCSelect f$2;
            private final /* synthetic */ OCSelect f$3;
            private final /* synthetic */ FilePickerAdapter f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePicker.LoadMoreFiles
            public final void run() {
                FilePicker.lambda$init$12(FilePickerViewModel.this, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        }, backToTopButtonManager);
    }

    static /* synthetic */ void lambda$init$11(FilePickerViewModel filePickerViewModel, Context context, FilePickerAdapter filePickerAdapter, MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter, MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter) {
        filePickerAdapter.getClass();
        filePickerViewModel.loadFiles(true, context, fileCategoryFilter, fileOrderingFilter, new FilePickerViewModel.OnFilesLoaded() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$n1yYov8gywTogMOB5m2ML535I */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel.OnFilesLoaded
            public final void run(boolean z, int i, int i2) {
                FilePickerAdapter.this.refresh(z, i, i2);
            }
        });
    }

    static /* synthetic */ void lambda$init$12(FilePickerViewModel filePickerViewModel, Context context, OCSelect oCSelect, OCSelect oCSelect2, FilePickerAdapter filePickerAdapter) {
        filePickerAdapter.getClass();
        filePickerViewModel.loadFiles(false, context, (MediaStoreFileLoader.FileCategoryFilter) oCSelect.getSelectedItem(), (MediaStoreFileLoader.FileOrderingFilter) oCSelect2.getSelectedItem(), new FilePickerViewModel.OnFilesLoaded() {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$n1yYov8gywTogMOB5m2ML535I */

            @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel.OnFilesLoaded
            public final void run(boolean z, int i, int i2) {
                FilePickerAdapter.this.refresh(z, i, i2);
            }
        });
    }
}
