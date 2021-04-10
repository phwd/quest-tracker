package com.oculus.localfilemanager;

import android.annotation.TargetApi;
import java.util.Objects;

@TargetApi(21)
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
        public final String mimeType;
        public final long size;
        public final FileType type;
        public final int width;

        public FileMetadata(long size2, int width2, int height2, FileType type2, String mimeType2, long durationInMs2) {
            this.size = size2;
            this.width = width2;
            this.height = height2;
            this.type = type2;
            this.mimeType = mimeType2;
            this.durationInMs = durationInMs2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != FileMetadata.class) {
                return false;
            }
            FileMetadata other = (FileMetadata) obj;
            return Objects.equals(this.mimeType, this.mimeType) && other.size == this.size && other.width == this.width && other.height == this.height && other.type == this.type && other.durationInMs == this.durationInMs;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.size), Integer.valueOf(this.width), Integer.valueOf(this.height), this.type, this.mimeType, Long.valueOf(this.durationInMs));
        }
    }

    public static class FolderMetadata {
        public final int itemCount;

        public FolderMetadata(int itemCount2) {
            this.itemCount = itemCount2;
        }

        public boolean equals(Object obj) {
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
        public final FileMetadata fileMetadata;
        public final FolderMetadata folderMetadata;
        public final int index;
        public final FileKind kind;
        public final String path;

        private FileData(String path2, int dateAdded2, FileKind kind2, FileMetadata fileMetadata2, FolderMetadata folderMetadata2, int index2) {
            this.path = path2;
            this.dateAdded = dateAdded2;
            this.kind = kind2;
            this.fileMetadata = fileMetadata2;
            this.folderMetadata = folderMetadata2;
            this.index = index2;
        }

        public static FileData createFile(String path2, int dateAdded2, FileMetadata fileMetadata2, int index2) {
            return new FileData(path2, dateAdded2, FileKind.FILE, fileMetadata2, null, index2);
        }

        public static FileData createFolder(String path2, int dateAdded2, FolderMetadata folderMetadata2, int index2) {
            return new FileData(path2, dateAdded2, FileKind.FOLDER, null, folderMetadata2, index2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != FileData.class) {
                return false;
            }
            FileData other = (FileData) obj;
            return other.path.equals(this.path) && other.dateAdded == this.dateAdded && other.kind == this.kind && Objects.equals(other.fileMetadata, this.fileMetadata) && Objects.equals(other.folderMetadata, this.folderMetadata) && other.index == this.index;
        }

        public int hashCode() {
            return Objects.hash(this.path, Integer.valueOf(this.dateAdded), this.kind, this.fileMetadata, this.folderMetadata, Integer.valueOf(this.index));
        }
    }
}
