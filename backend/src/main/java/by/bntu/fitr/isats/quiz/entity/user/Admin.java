package by.bntu.fitr.isats.quiz.entity.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class Admin {

    private String login;
    private String password;
    private String name;

}
