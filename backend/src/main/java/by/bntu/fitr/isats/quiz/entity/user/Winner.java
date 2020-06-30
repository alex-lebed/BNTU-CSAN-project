package by.bntu.fitr.isats.quiz.entity.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "quiz_winner")
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    private Integer localId; // id in the lobby

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "game_date")
    private Timestamp gameDate;

    private int score;

    public Winner(Integer localId, String playerName, Timestamp gameDate, int score) {
        this.localId = localId;
        this.playerName = playerName;
        this.gameDate = gameDate;
        this.score = score;
    }

}
