package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Builder
@EqualsAndHashCode
@Getter
public class Coach {
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private Boolean isAdmin;
}
