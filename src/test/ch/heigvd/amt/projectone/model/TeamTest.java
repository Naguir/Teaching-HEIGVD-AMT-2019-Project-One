package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team team = null;
    @Test
    void CreateTeam() {
        team = new Team.TeamBuilder().name("AMT")
                .dateCreation(Date.valueOf("2019-05-22"))
                .location("Yverdon")
                .build();

        assertNotNull(team);
        assertEquals(Date.valueOf("2019-05-22"),team.getDateCreation());
    }

}