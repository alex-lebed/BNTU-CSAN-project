package by.bntu.fitr.isats.quiz.dto;

import by.bntu.fitr.isats.quiz.entity.user.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class LobbyConfigDto {

    @NotNull
    private Admin admin;
    @NotEmpty
    private String password;
    @Min(2) @Max(5)
    private int playersAmountToStart;
    @Positive
    private int questionsAmount;

}
