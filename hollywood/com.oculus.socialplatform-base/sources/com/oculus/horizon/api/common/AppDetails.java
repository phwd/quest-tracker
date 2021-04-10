package com.oculus.horizon.api.common;

import java.util.List;
import javax.annotation.Nullable;

public class AppDetails {
    @Nullable
    public List<Category> categories;
    public String developerName;
    @Nullable
    public String forumUrl;
    public List<Genre> genres;
    public long installationSize;
    public String packageName;
    public List<String> permissions;
    public String publisherName;
    public int versionCode;
    public String versionString;
    @Nullable
    public String website;

    public static class Genre {
        public String id;
        public String name;
    }

    public static class Category {
        public String id;
        public String name;

        public Category(String str, String str2) {
            this.id = str;
            this.name = str2;
        }
    }
}
