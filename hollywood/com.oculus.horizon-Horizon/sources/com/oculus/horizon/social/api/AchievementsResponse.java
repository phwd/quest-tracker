package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.List;

@SingleEntryMapResponse
public class AchievementsResponse {
    public Image cover_landscape_image;
    public String display_name;
    public Grouping grouping;

    public static class AchievementDefinition {
        public String achievement_type;
        public String description;
        public Image locked_image;
        public ProgressForUser progress_for_user;
        public long target;
        public String title;
        public String unlocked_description_override;
        public Image unlocked_image;
    }

    public static class AchievementDefinitions {
        public List<AchievementDefinition> nodes;
    }

    public static class Grouping {
        public AchievementDefinitions achievement_definitions;
        public int secret_achievement_count_earned_by_user_but_not_viewer;
        public int unearned_secret_achievement_count;
    }

    public static class Image {
        public String uri;
    }

    public static class ProgressForUser {
        public String bitfield_progress;
        public long count_progress;
        public boolean is_unlocked;
    }
}
