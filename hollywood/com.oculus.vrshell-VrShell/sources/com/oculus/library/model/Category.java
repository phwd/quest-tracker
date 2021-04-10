package com.oculus.library.model;

public enum Category {
    UNKNOWN(-1),
    APPS(0),
    CONCEPTS(1),
    EARLY_ACCESS(2),
    ENTERTAINMENT(3),
    ENVIRONMENTS(4),
    GALLERY(5),
    GAMES(6),
    INTERNAL(7),
    SYSTEM(8);
    
    public final int value;

    private Category(int i) {
        this.value = i;
    }

    public static Category fromString(String str) {
        Category[] values = values();
        for (Category category : values) {
            if (category.name().equals(str)) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
