package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private DeathNoteImplementation note;
    private final String WHO_DIES = "Sauron_Aka_BCZ";
    private final String HOPED_DEATH = "Nicholas Giulio Magi";

    @BeforeEach
    public void initialize() {
        note = new DeathNoteImplementation();
    }

    @Test
    void wrongRule() {
        try {
            for (final var i : List.of(-1, 0, this.note.RULES.size())) {
                assertFalse(this.note.getRule(i).isBlank());
                assertFalse(this.note.getRule(i).isEmpty());
            }

        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isBlank());
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void allRules() {
        String s;
        for (int i = 1; i < DeathNoteImplementation.RULES.size(); i++) {
            s = this.note.getRule(i);
            assertFalse(s.isBlank());
            assertFalse(s.isEmpty());
        }
    }

    @Test
    void checkEmptyRules() {
        for (String s : DeathNoteImplementation.RULES) {
            assertFalse(s.isBlank());
            assertFalse(s.isEmpty());
        }
    }

    @Test
    void checkDeath() {
        assertFalse(this.note.isNameWritten(WHO_DIES));
        this.note.writeName(WHO_DIES);
        assertTrue(this.note.isNameWritten(WHO_DIES));
        assertFalse(this.note.isNameWritten(""));

    }

}