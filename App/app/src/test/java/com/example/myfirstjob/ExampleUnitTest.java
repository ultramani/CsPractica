package com.example.myfirstjob;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    
    @Test
    public void comprobationPattern_isCorrect() {
        Singup singup = new Singup();
        String emailGood = "paco@gmail.com";
        String emailBad = "pacoPata7i62equdq2";
        boolean condGood = singup.comprobationPattern(emailGood);
        boolean condBad = singup.comprobationPattern(emailBad);
        assertTrue(condGood && !condBad);
    }
    
    @Test
    public void encryptPassword_isCorrect() {
        String passwordString = "1234A";
        String expectedPass = "N49rLzJed91dElP4DH9GgQ==";
        String pass = TypePassword.encryptPassword(passwordString);

        assertEquals(expectedPass, pass);
    }

    @Test
    public void changePassword_isCorrect() {
        String oldPassword = "1234A";
        User user = new User("Paco", "paco@gmail.com", oldPassword, null, "51649875B", "GIS", "URJC");
        String newPassword = "CalidadSoftware";

        assertTrue(user.changePassword(TypePassword.encryptPassword(oldPassword), newPassword, newPassword));
    }
}
