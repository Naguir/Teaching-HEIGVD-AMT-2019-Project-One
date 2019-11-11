package ch.heigvd.amt.projectone.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoachTest {

    @Test
    public void createCoach(){

        Coach coach = Coach.builder()
                .lastName("Alic")
                .firstName("Robel")
                .username("robNal")
                .password("test")
                .isAdmin(true)
                .build();
        assertNotNull(coach);
        assertEquals("Alic",coach.getLastName());
        assertEquals("Robel", coach.getFirstName());
    }

}