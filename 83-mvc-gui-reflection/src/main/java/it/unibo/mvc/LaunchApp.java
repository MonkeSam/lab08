package it.unibo.mvc;

import java.util.List;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberSwingView;
import it.unibo.mvc.view.DrawNumberSwingViewCLI;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() {
    }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException    if the fetches class does not exist
     * @throws NoSuchMethodException     if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException    if the constructor throws exceptions
     * @throws IllegalAccessException    in case of reflection issues
     * @throws IllegalArgumentException  in case of reflection issues
     */
    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        final int N_VIEW = 3;
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        for (String string : List.of("", "CLI")) {
            for (int i = 0; i < N_VIEW; i++) {

                try {
                    var view = Class.forName("it.unibo.mvc.view.DrawNumberSwingView" + string);
                    app.addView((DrawNumberView) view.getConstructor().newInstance());
                } catch (Exception e) {
                    throw new IllegalArgumentException();
                }
            }
        }

    }
}
