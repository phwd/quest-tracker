package com.oculus.modules;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import com.google.common.util.concurrent.FutureCallback;
import com.oculus.localfilemanager.FileModel;
import com.oculus.localfilemanager.MediaStoreFileLoader;
import com.oculus.localmedia.MediaItem;
import com.oculus.modules.codegen.MediaStoreFileLoaderModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.ThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MediaStoreFileLoaderModuleImpl extends MediaStoreFileLoaderModule {
    private static final int PAGE_SIZE = 25;
    private static final String TAG = MediaStoreFileLoaderModuleImpl.class.getSimpleName();
    private final Context mContext;
    private final MediaMetadataRetriever mMediaMetadataRetriever;
    private MediaStoreFileLoader.MediaStoreFileLoaderRequest mNextRequest = MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(PAGE_SIZE, MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT, MediaStoreFileLoader.FileCategoryFilter.ALL);

    public MediaStoreFileLoaderModuleImpl(Context context) {
        this.mContext = context;
        this.mMediaMetadataRetriever = new MediaMetadataRetriever();
    }

    public static long getId(String filePath, long fileSize) {
        return (((long) Math.abs(filePath.hashCode())) << 32) | (4294967295L & fileSize);
    }

    private static MediaStoreFileLoaderModule.FileManagerFileMetadata fromFileMetadata(Context context, FileModel.FileMetadata fileMetadata, String filePath) {
        MediaStoreFileLoaderModule.FileManagerFileMetadata fileManagerFileMetadata = new MediaStoreFileLoaderModule.FileManagerFileMetadata();
        fileManagerFileMetadata.height = (double) fileMetadata.height;
        fileManagerFileMetadata.width = (double) fileMetadata.width;
        fileManagerFileMetadata.size = (double) fileMetadata.size;
        fileManagerFileMetadata.mimeType = fileMetadata.mimeType;
        fileManagerFileMetadata.type = fileMetadata.type.name();
        fileManagerFileMetadata.durationInMs = Double.valueOf(Long.valueOf(fileMetadata.durationInMs).doubleValue());
        if (fileMetadata.type == FileModel.FileType.VIDEO) {
            fileManagerFileMetadata.thumbnail = MediaItem.generateThumbnail(context, filePath, getId(filePath, fileMetadata.size), true);
        }
        return fileManagerFileMetadata;
    }

    private static MediaStoreFileLoaderModule.FileManagerFolderMetadata fromFolderMetadata(FileModel.FolderMetadata folderMetadata) {
        MediaStoreFileLoaderModule.FileManagerFolderMetadata fileManagerFolderMetadata = new MediaStoreFileLoaderModule.FileManagerFolderMetadata();
        fileManagerFolderMetadata.itemCount = (double) folderMetadata.itemCount;
        return fileManagerFolderMetadata;
    }

    /* access modifiers changed from: private */
    public static MediaStoreFileLoaderModule.FileManagerFile fromFileData(Context context, FileModel.FileData fileData) {
        MediaStoreFileLoaderModule.FileManagerFileMetadata fromFileMetadata;
        MediaStoreFileLoaderModule.FileManagerFolderMetadata fileManagerFolderMetadata = null;
        MediaStoreFileLoaderModule.FileManagerFile fileManagerFile = new MediaStoreFileLoaderModule.FileManagerFile();
        fileManagerFile.path = fileData.path;
        fileManagerFile.dateAdded = (double) fileData.dateAdded;
        if (fileData.fileMetadata == null) {
            fromFileMetadata = null;
        } else {
            fromFileMetadata = fromFileMetadata(context, fileData.fileMetadata, fileData.path);
        }
        fileManagerFile.fileMetadata = fromFileMetadata;
        if (fileData.folderMetadata != null) {
            fileManagerFolderMetadata = fromFolderMetadata(fileData.folderMetadata);
        }
        fileManagerFile.folderMetadata = fileManagerFolderMetadata;
        fileManagerFile.kind = fileData.kind.name();
        return fileManagerFile;
    }

    /* access modifiers changed from: private */
    public static class Result {
        public final List<MediaStoreFileLoaderModule.FileManagerFile> files;
        public final MediaStoreFileLoader.MediaStoreFileLoaderResult result;

        public Result(List<MediaStoreFileLoaderModule.FileManagerFile> files2, MediaStoreFileLoader.MediaStoreFileLoaderResult result2) {
            this.files = files2;
            this.result = result2;
        }
    }

    private static MediaStoreFileLoader.FileOrderingFilter getOrdering(String ordering) {
        if (ordering == null) {
            return MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT;
        }
        try {
            return MediaStoreFileLoader.FileOrderingFilter.valueOf(ordering);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "invalid ordering", e);
            return MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT;
        }
    }

    private void getFiles(final MediaStoreFileLoader.MediaStoreFileLoaderRequest request, final Consumer<Result> onSuccess, final Consumer<Throwable> onFailure) {
        ThreadExecutor.getInstance().execute(new Callable<Result>() {
            /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public Result call() {
                MediaStoreFileLoader.MediaStoreFileLoaderResult result = new MediaStoreFileLoader().getFiles(MediaStoreFileLoaderModuleImpl.this.mContext, request, MediaStoreFileLoaderModuleImpl.this.mMediaMetadataRetriever);
                ArrayList<MediaStoreFileLoaderModule.FileManagerFile> files = new ArrayList<>();
                for (int i = 0; i < result.files.size(); i++) {
                    files.add(MediaStoreFileLoaderModuleImpl.fromFileData(MediaStoreFileLoaderModuleImpl.this.mContext, result.files.get(i)));
                }
                return new Result(files, result);
            }
        }, new FutureCallback<Result>() {
            /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass2 */

            public void onSuccess(Result result) {
                onSuccess.accept(result);
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public void onFailure(Throwable throwable) {
                onFailure.accept(throwable);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MediaStoreFileLoaderModule
    public void getFilesInFolderImpl(String folderPath, String ordering, final Resolver<List<MediaStoreFileLoaderModule.FileManagerFile>> resolver) {
        getFiles(MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(folderPath, getOrdering(ordering)), new Consumer<Result>() {
            /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass3 */

            public void accept(Result result) {
                resolver.resolve(result.files);
            }
        }, new Consumer<Throwable>() {
            /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass4 */

            public void accept(Throwable throwable) {
                resolver.reject(throwable);
            }
        });
    }

    private void getFilesFromMediaStore(MediaStoreFileLoader.MediaStoreFileLoaderRequest request, boolean reset, final Resolver<List<MediaStoreFileLoaderModule.FileManagerFile>> resolver) {
        if (this.mNextRequest != null || reset) {
            if (!reset) {
                request = this.mNextRequest;
            }
            getFiles(request, new Consumer<Result>() {
                /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass5 */

                public void accept(Result result) {
                    MediaStoreFileLoaderModuleImpl.this.mNextRequest = result.result.nextMediaStoreFileLoaderRequest;
                    resolver.resolve(result.files);
                }
            }, new Consumer<Throwable>() {
                /* class com.oculus.modules.MediaStoreFileLoaderModuleImpl.AnonymousClass6 */

                public void accept(Throwable throwable) {
                    resolver.reject(throwable);
                }
            });
            return;
        }
        resolver.resolve(new ArrayList());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MediaStoreFileLoaderModule
    public void getAllFilesFromMediaStoreImpl(boolean reset, String ordering, Resolver<List<MediaStoreFileLoaderModule.FileManagerFile>> resolver) {
        getFilesFromMediaStore(MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(PAGE_SIZE, getOrdering(ordering), MediaStoreFileLoader.FileCategoryFilter.ALL), reset, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MediaStoreFileLoaderModule
    public void getMediaFilesFromMediaStoreImpl(boolean reset, String ordering, Resolver<List<MediaStoreFileLoaderModule.FileManagerFile>> resolver) {
        getFilesFromMediaStore(MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(PAGE_SIZE, getOrdering(ordering), MediaStoreFileLoader.FileCategoryFilter.MEDIA), reset, resolver);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MediaStoreFileLoaderModule
    public void getDownloadFilesFromMediaStoreImpl(boolean reset, String ordering, Resolver<List<MediaStoreFileLoaderModule.FileManagerFile>> resolver) {
        getFilesFromMediaStore(MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(PAGE_SIZE, getOrdering(ordering), MediaStoreFileLoader.FileCategoryFilter.DOWNLOADS), reset, resolver);
    }
}
