package org.puj.ingesoft.views;

import javafx.scene.Scene;

import java.io.IOException;

/**
 * Guarantees that the method {@link #getScene()} is present
 *
 * Código modificado de: https://github.com/ksnortum/javafx-multi-scene
 * @author Knute Snortum
 * @version 2018-05-24
 * */
public interface ViewMaker {

    /**
     * Build and return a scene for this view
     *
     * @return the Scene for this view
     */
    Scene getScene() throws IOException;

    void update();
}