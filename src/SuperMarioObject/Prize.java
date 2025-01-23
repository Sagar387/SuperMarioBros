package SuperMarioObject;

//import manager.GameEngine;
//import manager.MapManager;
//import model.hero.Mario;

import java.awt.*;

import SuperMarioBros.SuperMarioBros;

public interface Prize {

    int getPoint();

    void reveal();

    Rectangle getBounds();

    void onTouch(Player player, SuperMarioBros engine);

}
