package org.puj.ingesoft.views;

import javafx.scene.Scene;

import java.io.IOException;

public abstract class Pantalla  {
    protected Scene scene;

    public abstract void update();

    public Scene getScene()
    {
        return this.scene;
    }

    public abstract void makeScene() throws IOException;
}
