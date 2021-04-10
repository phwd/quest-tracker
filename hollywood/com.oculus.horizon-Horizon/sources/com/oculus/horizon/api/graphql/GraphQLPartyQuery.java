package com.oculus.horizon.api.graphql;

public class GraphQLPartyQuery {
    public static final String JOIN_PARTY = "Mutation PartyJoin : PartyJoinResponsePayload {  party_join(<input>) {    party {id,party_room {  id,},party_users {  nodes {    id,    name,    alias,    display_name,    profile_photo.size(64x64) {      uri,    },    avatar_image.size(64x64) {      uri,    },  }}    },  }}";
    public static final String LEAVE_PARTY = "Mutation PartyLeave : PartyLeaveResponsePayload {  party_leave(<input>) {    party {id,party_room {  id,},party_users {  nodes {    id,    name,    alias,    display_name,    profile_photo.size(64x64) {      uri,    },    avatar_image.size(64x64) {      uri,    },  }}    },  }}";
    public static final String PARTY_DATA = "id,party_room {  id,},party_users {  nodes {    id,    name,    alias,    display_name,    profile_photo.size(64x64) {      uri,    },    avatar_image.size(64x64) {      uri,    },  }}";
    public static final String PARTY_STATUS_QUERY = "viewer() {  user {    current_party {id,party_room {  id,},party_users {  nodes {    id,    name,    alias,    display_name,    profile_photo.size(64x64) {      uri,    },    avatar_image.size(64x64) {      uri,    },  }}    }  }}";
}
