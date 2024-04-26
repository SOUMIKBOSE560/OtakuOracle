package io.otakuoracle.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterObject {
    private String name;
    private String picture_url;
    private String myanimelist_url;
    private String voice_actor_name;
    private String voice_actor_picture_url;
    private String voice_actor_myanimelist_url;
}
