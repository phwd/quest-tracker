package com.oculus.localfilemanager;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Objects;

@TargetApi(21)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class FileModel {

    public enum FileKind {
        FILE,
        FOLDER
    }

    public enum FileType {
        IMAGE,
        VIDEO,
        KNOWN,
        UNKNOWN
    }

    public static class FileMetadata {
        public final long durationInMs;
        public final int height;
        @Nullable
        public final String mimeType;
        public final long size;
        public final FileType type;
        public final int width;

        public FileMetadata(long j, int i, int i2, FileType fileType, @Nullable String str, long j2) {
            this.size = j;
            this.width = i;
            this.height = i2;
            this.type = fileType;
            this.mimeType = str;
            this.durationInMs = j2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != FileMetadata.class) {
                return false;
            }
            FileMetadata fileMetadata = (FileMetadata) obj;
            String str = this.mimeType;
            return Objects.equals(str, str) && fileMetadata.size == this.size && fileMetadata.width == this.width && fileMetadata.height == this.height && fileMetadata.type == this.type && fileMetadata.durationInMs == this.durationInMs;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.size), Integer.valueOf(this.width), Integer.valueOf(this.height), this.type, this.mimeType, Long.valueOf(this.durationInMs));
        }
    }

    public static class FolderMetadata {
        public final int itemCount;

        public FolderMetadata(int i) {
            this.itemCount = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != FolderMetadata.class) {
                return false;
            }
            return ((FolderMetadata) obj).itemCount == this.itemCount;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.itemCount));
        }
    }

    public static class FileData {
        public final int dateAdded;
        @Nullable
        public final FileMetadata fileMetadata;
        @Nullable
        public final FolderMetadata folderMetadata;
        public final int index;
        public final FileKind kind;
        public final String path;

        private FileData(String str, int i, FileKind fileKind, @Nullable FileMetadata fileMetadata2, @Nullable FolderMetadata folderMetadata2, int i2) {
            this.path = str;
            this.dateAdded = i;
            this.kind = fileKind;
            this.fileMetadata = fileMetadata2;
            this.folderMetadata = folderMetadata2;
            this.index = i2;
        }

        public static FileData createFile(String str, int i, FileMetadata fileMetadata2, int i2) {
            return new FileData(str, i, FileKind.FILE, fileMetadata2, null, i2);
        }

        public static FileData createFolder(String str, int i, FolderMetadata folderMetadata2, int i2) {
            return new FileData(str, i, FileKind.FOLDER, null, folderMetadata2, i2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != FileData.class) {
                return false;
            }
            FileData fileData = (FileData) obj;
            return fileData.path.equals(this.path) && fileData.dateAdded == this.dateAdded && fileData.kind == this.kind && Objects.equals(fileData.fileMetadata, this.fileMetadata) && Objects.equals(fileData.folderMetadata, this.folderMetadata) && fileData.index == this.index;
        }

        public int hashCode() {
            return Objects.hash(this.path, Integer.valueOf(this.dateAdded), this.kind, this.fileMetadata, this.folderMetadata, Integer.valueOf(this.index));
        }
    }
}
