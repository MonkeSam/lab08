package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberSwingViewCLI implements DrawNumberView {
    private DrawNumberController controller;
    private static final String NEW_GAME = ": a new game starts!";

    public DrawNumberSwingViewCLI() {

    }

    @Override
    public void setController(DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void start() {
        System.out.println("Choose a number using a GUI interface");
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOU_WON:
                System.out.println(res.getDescription());
                break;

            case YOU_LOST:
                System.out.println(res.getDescription() + NEW_GAME);
                break;

            case YOURS_HIGH:
                System.out.println(res.getDescription());
                break;

            case YOURS_LOW:
                System.out.println(res.getDescription());
                break;
            default:
                break;
        }
    }

}
