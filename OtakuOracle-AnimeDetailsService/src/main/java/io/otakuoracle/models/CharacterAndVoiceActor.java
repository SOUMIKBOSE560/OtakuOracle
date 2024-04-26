package io.otakuoracle.models;

import lombok.*;
import org.springframework.stereotype.Component;

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
