package io.otakuoracle.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class AnimeObject {
    private String title;
    private Object description;
    private String picture_url;
    private String episodeNo;
    private String status;
    private String airedDate;
    private Double score;
    private Double ranked;
    private List<CharacterAndVoiceActor> characterAndVoiceActorList;
}
