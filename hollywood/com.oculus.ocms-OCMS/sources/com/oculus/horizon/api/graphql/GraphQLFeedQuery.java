package com.oculus.horizon.api.graphql;

public class GraphQLFeedQuery {
    private static final String ACTOR_FIELDS = "id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},";
    private static final String ATTACHMENT_ATTRIBUTES = "id,title,actor {  nodes {id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},  },},media {  nodes {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},  },},";
    public static final String FEED_QUERY = "viewer() {  feed      .first(50) {    nodes {id,actors {id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},},title,subtitle,display_time,body_text,deeplink,render_styles,attachments {  nodes {id,title,actor {  nodes {id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},  },},media {  nodes {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},  },},  },},    },  },}";
    private static final String FIRST_N = "50";
    private static final String IMAGE_FIELDS = "name,width,height,uri,scale,";
    private static final String MEDIA_FIELDS = "name,width,height,uri,";
    private static final String STORY_MEDIA_FIELDS = "id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},";
    private static final String STORY_QUERY = "id,actors {id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},},title,subtitle,display_time,body_text,deeplink,render_styles,attachments {  nodes {id,title,actor {  nodes {id,name,deeplink,icon {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},},  },},media {  nodes {id,deeplink,thumbnail_image {name,width,height,uri,scale,},content_media {name,width,height,uri,},  },},  },},";
}
