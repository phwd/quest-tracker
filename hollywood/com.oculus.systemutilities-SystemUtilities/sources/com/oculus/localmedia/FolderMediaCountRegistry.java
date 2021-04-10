package com.oculus.localmedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FolderMediaCountRegistry {
    private static FolderMediaCountRegistry sFolderMediaCountRegistry;
    private HashMap<MediaType, HashMap<String, FolderMediaCount>> mCounts = new HashMap<>();

    private FolderMediaCountRegistry() {
    }

    public static synchronized FolderMediaCountRegistry getSingleton() {
        FolderMediaCountRegistry folderMediaCountRegistry;
        synchronized (FolderMediaCountRegistry.class) {
            if (sFolderMediaCountRegistry == null) {
                sFolderMediaCountRegistry = new FolderMediaCountRegistry();
            }
            folderMediaCountRegistry = sFolderMediaCountRegistry;
        }
        return folderMediaCountRegistry;
    }

    public HashMap<MediaType, FolderMediaCount> get(String path) {
        HashMap<String, FolderMediaCount> map;
        FolderMediaCount c;
        HashMap<MediaType, FolderMediaCount> counts = new HashMap<>();
        MediaType[] values = MediaType.values();
        for (MediaType t : values) {
            if (!(t == MediaType.UNKNOWN || (map = this.mCounts.get(t)) == null || (c = map.get(path)) == null)) {
                counts.put(t, c);
            }
        }
        return counts;
    }

    public FolderMediaCount get(String path, int mediaTypes) {
        FolderMediaCount count = new FolderMediaCount();
        HashMap<MediaType, FolderMediaCount> counts = get(path);
        if (MediaType.IMAGE.match(mediaTypes) && counts.containsKey(MediaType.IMAGE)) {
            count.add(counts.get(MediaType.IMAGE));
        }
        if (MediaType.VIDEO.match(mediaTypes) && counts.containsKey(MediaType.VIDEO)) {
            count.add(counts.get(MediaType.VIDEO));
        }
        return count;
    }

    public List<String> getFoldersWithMedia(int mediaTypes) {
        HashMap<String, FolderMediaCount> map;
        HashMap<String, FolderMediaCount> map2;
        List<String> paths = new ArrayList<>();
        boolean includeAll = !MediaType.IMAGE.match(mediaTypes) && !MediaType.VIDEO.match(mediaTypes);
        if ((MediaType.IMAGE.match(mediaTypes) || includeAll) && (map2 = this.mCounts.get(MediaType.IMAGE)) != null) {
            for (Map.Entry<String, FolderMediaCount> entry : map2.entrySet()) {
                if (entry.getValue().getCount() > 0) {
                    paths.add(entry.getKey());
                }
            }
        }
        if ((MediaType.VIDEO.match(mediaTypes) || includeAll) && (map = this.mCounts.get(MediaType.VIDEO)) != null) {
            for (Map.Entry<String, FolderMediaCount> entry2 : map.entrySet()) {
                if (entry2.getValue().getCount() > 0 && !paths.contains(entry2.getKey())) {
                    paths.add(entry2.getKey());
                }
            }
        }
        return paths;
    }

    public void index(MediaType type, List<MediaItem> items) {
        HashMap<String, FolderMediaCount> pathToFolderCounts = new HashMap<>();
        for (MediaItem item : items) {
            String path = item.getFolderPath();
            boolean isImmediateParent = true;
            while (path != null) {
                FolderMediaCount metadata = pathToFolderCounts.get(path);
                if (metadata == null) {
                    metadata = new FolderMediaCount();
                    pathToFolderCounts.put(path, metadata);
                }
                if (isImmediateParent) {
                    metadata.increaseCount();
                }
                metadata.increaseAggregateCount();
                path = MediaProviderUtils.getFolderPath(path);
                isImmediateParent = false;
            }
        }
        this.mCounts.put(type, pathToFolderCounts);
    }
}
