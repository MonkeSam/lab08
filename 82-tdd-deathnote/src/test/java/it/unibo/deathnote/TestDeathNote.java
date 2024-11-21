package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import static java.lang.Thread.sleep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {
    private DeathNoteImplementation note;
    private final String WHO_DIES = "Sauron_Aka_BCZ";
    private final String HOPED_DEATH = "Nicholas Giulio Magi";
    private final String CAUSE_OF_DEATH = "Ate too many Pistacchiosa's Pizzas";
    private final String DEATH_DETAIL = "Pistacchio is not the only topper on Pistacchiosa's Pizzas";
    private final static int OVERTIME_CAUSE = 100;
    private final static int OVERTIME_DETAILS = 6000 + OVERTIME_CAUSE;
    private String DEFAULT_CAUSE = "heart attack";

    @BeforeEach
    public void initialize() {
        note = new DeathNoteImplementation();
    }

    @Test
    void wrongRule() {
        try {
            for (final var i : List.of(-1, 0, DeathNote.RULES.size())) {
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

    /**
     * Test writeDeathCause asserting the time constraint is respected (Could be
     * written better).
     * 
     * @throws InterruptedException
     */
    @Test
    void testwriteCause() throws InterruptedException {
        this.note.writeName(HOPED_DEATH);
        assertFalse(this.note.getDeathCause(HOPED_DEATH).isBlank() && !this.note.getDeathCause(HOPED_DEATH).isEmpty());
        assertTrue(this.note.getDeathCause(HOPED_DEATH).isEmpty());
        this.note.writeDeathCause(CAUSE_OF_DEATH);
        assertEquals(this.note.getDeathCause(HOPED_DEATH), CAUSE_OF_DEATH);

        this.note.writeName(WHO_DIES);
        assertFalse(this.note.getDeathCause(WHO_DIES).isBlank() && !this.note.getDeathCause(WHO_DIES).isEmpty());
        assertTrue(this.note.getDeathCause(WHO_DIES).isEmpty());
        // wait more than 40 milliseconds
        sleep(OVERTIME_CAUSE);
        this.note.writeDeathCause(CAUSE_OF_DEATH);
        assertEquals(this.note.getDeathCause(WHO_DIES), DEFAULT_CAUSE);

    }

    @Test
    void testwriteDetails() throws InterruptedException {
        this.note.writeName(HOPED_DEATH);
        try {
            this.note.writeDetails(DEATH_DETAIL);
        } catch (IllegalAccessError e) {
            assertFalse(e.getMessage().isBlank());
        }

        this.note.writeDeathCause(CAUSE_OF_DEATH);
        assertFalse(
                this.note.getDeathDetails(HOPED_DEATH).isBlank() && !this.note.getDeathDetails(HOPED_DEATH).isEmpty());
        assertTrue(this.note.getDeathDetails(HOPED_DEATH).isEmpty());
        this.note.writeDetails(DEATH_DETAIL);
        assertEquals(this.note.getDeathDetails(HOPED_DEATH), DEATH_DETAIL);

        this.note.writeName(WHO_DIES);
        this.note.writeDeathCause(CAUSE_OF_DEATH);
        assertFalse(
                this.note.getDeathDetails(WHO_DIES).isBlank() && !this.note.getDeathDetails(WHO_DIES).isEmpty());
        assertTrue(this.note.getDeathDetails(WHO_DIES).isEmpty());
        // wait more than 6s 40ms
        sleep(OVERTIME_DETAILS);
        this.note.writeDetails(DEATH_DETAIL);
        assertTrue(this.note.getDeathDetails(WHO_DIES).isBlank());
    }

}