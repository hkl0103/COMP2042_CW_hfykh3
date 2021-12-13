package test.view;

import main.java.test.view.MusicPlayer;
import org.junit.jupiter.api.Test;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {
    MusicPlayer sound = new MusicPlayer();

    @Test
    void playSound() {
        assertNotNull(sound);
    }
}