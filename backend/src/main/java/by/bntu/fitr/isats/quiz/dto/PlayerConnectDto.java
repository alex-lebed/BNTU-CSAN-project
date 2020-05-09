package by.bntu.fitr.isats.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class PlayerConnectDto {

    private String lobbyPassword;
    @NotEmpty
    private String name;

}
