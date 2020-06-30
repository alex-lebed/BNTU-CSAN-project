package by.bntu.fitr.isats.quiz.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "quiz_admin")
public class Admin {

    @Id
    private String login;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_name")
    private String name;

}
