package fr.ap.apjavafx.lib;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
//Simple interface pour créer un Handle avec un KeyEvent
public interface newHandlerEvent extends EventHandler {
    void handle(KeyEvent event);
}
