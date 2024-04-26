package io.otakuoracle.models;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnimeDetail {
    private String title;
    private String description;
    private String picture_url;
    private String myanimelist_url;
    private Integer myanimelist_id;
}
