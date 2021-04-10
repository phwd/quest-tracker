package com.oculus.localmedia;

import java.util.HashMap;
import java.util.List;

public class FolderMediaCountRegistry {
    public static FolderMediaCountRegistry sFolderMediaCountRegistry;
    public HashMap<MediaType, HashMap<String, FolderMediaCount>> mCounts = new HashMap<>();

    public static synchronized FolderMediaCountRegistry getSingleton() {
        FolderMediaCountRegistry folderMediaCountRegistry;
        synchronized (FolderMediaCountRegistry.class) {
            folderMediaCountRegistry = sFolderMediaCountRegistry;
            if (folderMediaCountRegistry == null) {
                folderMediaCountRegistry = new FolderMediaCountRegistry();
                sFolderMediaCountRegistry = folderMediaCountRegistry;
            }
        }
        return folderMediaCountRegistry;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (com.oculus.localmedia.MediaType.VIDEO.match(r6) != false) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> getFoldersWithMedia(int r6) {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.FolderMediaCountRegistry.getFoldersWithMedia(int):java.util.List");
    }

    public void index(MediaType mediaType, List<MediaItem> list) {
        HashMap<String, FolderMediaCount> hashMap = new HashMap<>();
        for (MediaItem mediaItem : list) {
            String str = mediaItem.mFolderPath;
            boolean z = true;
            while (str != null) {
                FolderMediaCount folderMediaCount = hashMap.get(str);
                if (folderMediaCount == null) {
                    folderMediaCount = new FolderMediaCount();
                    hashMap.put(str, folderMediaCount);
                }
                if (z) {
                    folderMediaCount.increaseCount();
                }
                folderMediaCount.increaseAggregateCount();
                str = MediaProviderUtils.getFolderPath(str);
                z = false;
            }
        }
        this.mCounts.put(mediaType, hashMap);
    }

    public FolderMediaCount get(String str, int i) {
        FolderMediaCount folderMediaCount = new FolderMediaCount();
        HashMap<MediaType, FolderMediaCount> hashMap = get(str);
        MediaType mediaType = MediaType.IMAGE;
        if (mediaType.match(i) && hashMap.containsKey(mediaType)) {
            folderMediaCount.add(hashMap.get(mediaType));
        }
        MediaType mediaType2 = MediaType.VIDEO;
        if (mediaType2.match(i) && hashMap.containsKey(mediaType2)) {
            folderMediaCount.add(hashMap.get(mediaType2));
        }
        return folderMediaCount;
    }

    public HashMap<MediaType, FolderMediaCount> get(String str) {
        HashMap<String, FolderMediaCount> hashMap;
        FolderMediaCount folderMediaCount;
        HashMap<MediaType, FolderMediaCount> hashMap2 = new HashMap<>();
        MediaType[] values = MediaType.values();
        for (MediaType mediaType : values) {
            if (!(mediaType == MediaType.UNKNOWN || (hashMap = this.mCounts.get(mediaType)) == null || (folderMediaCount = hashMap.get(str)) == null)) {
                hashMap2.put(mediaType, folderMediaCount);
            }
        }
        return hashMap2;
    }
}
