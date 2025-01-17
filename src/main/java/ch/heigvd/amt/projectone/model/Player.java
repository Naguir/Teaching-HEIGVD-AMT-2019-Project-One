package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Player {

    int id;
    private String lastName;
    private String firstName;
    String position;
    int number;
    Team team;
}
