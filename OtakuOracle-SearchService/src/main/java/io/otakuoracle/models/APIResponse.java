package io.otakuoracle.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class APIResponse{
    public HttpStatus status;
    public boolean success;
    public List<AnimeDetail> animeDetailsList;
}
