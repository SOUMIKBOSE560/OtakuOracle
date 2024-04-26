package io.otakuoracle.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CharacterAndVoiceActor {
    private String characterName;
    private String characterImageUrl;
    private String voiceActor;
    private String voiceActorImageUrl;
}
