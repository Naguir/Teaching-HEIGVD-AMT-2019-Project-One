package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.DateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = null;
    @Test
    void CreatePlayer() {
            player = new Player.PlayerBuilder().firstName("Robel")
                .lastName("Teklehaimanot").number(22).position("AVG").id(9)
                .team(new Team("AMT","Yverdon",Date.valueOf("2019-05-22"))).build();

        assertNotNull(player);
        assertEquals("Robel",player.getFirstName());
    }

}