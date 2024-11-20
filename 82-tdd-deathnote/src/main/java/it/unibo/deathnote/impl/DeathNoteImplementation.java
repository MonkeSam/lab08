package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private Map<String, Death> book;
    private String lastName;

    public DeathNoteImplementation() {
        book = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber > 0 && ruleNumber < RULES.size()) {
            return RULES.get(ruleNumber);
        }
        throw new IllegalArgumentException("rule number must be greater than 0");
    }

    @Override
    public void writeName(final String name) {
        if (!name.isEmpty() && !name.isBlank()) {
            book.put(name, new Death());
            this.lastName = name;
        } else {
            throw new IllegalArgumentException("Name is null or empty");
        }

    }

    @Override
    public boolean writeDeathCause(String cause) {

        return this.book.get(this.lastName).setCauseOfDeath(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if (this.book.get(this.lastName).getCauseOfDeath().isEmpty()) {
            return this.book.get(this.lastName).setDetailsOfDeath(details);
        }
        throw new IllegalAccessError("Before write death details, must write the cause of death");
    }

    @Override
    public String getDeathCause(String name) {
        return this.getDeathCause(name);
    }

    @Override
    public String getDeathDetails(String name) {
        return this.getDeathDetails(name);
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.book.containsKey(name);
    }

    private class Death {
        private String cause;
        private String details;
        private long CAUSE_TIMOUT = 40;
        private long DETAILS_TIMOUT = 6000 + CAUSE_TIMOUT;
        private long timer;

        public Death(String cause, String details) {
            this.cause = cause;
            this.details = details;
            timer = System.currentTimeMillis();
        }

        public Death(String cause) {
            this(cause, "");
        }

        public Death() {
            this("", "");
        }

        /**
         * Set the cause of death if the argument not null
         * 
         * @param cause the cause of death
         * @return true if cause is not null
         */
        public boolean setCauseOfDeath(String cause) {

            if (cause != null) {
                if (System.currentTimeMillis() - this.timer > CAUSE_TIMOUT) {
                    this.cause = "heart attack";
                } else {
                    this.cause = cause;
                }
                return true;
            }
            return false;
        }

        /**
         * Set the details of death if the argument not null
         * 
         * @param details the cause of death
         * @return true if details is not null
         */
        public boolean setDetailsOfDeath(String details) {
            if (details != null) {
                if (System.currentTimeMillis() - this.timer <= this.DETAILS_TIMOUT) {
                    this.details = details;
                }
                return true;
            }

            return false;
        }

        public String getCauseOfDeath() {
            return this.cause;
        }

        public String getDetailsOfDeath() {
            return this.details;
        }

    }
}
