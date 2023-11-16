package tmp;

import com.badlogic.gdx.Game;

public class TmpGame extends Game {
    TmpScreen tmpScreen;

    TwoScreen twoScreen;
    @Override
    public void create() {
        tmpScreen = new TmpScreen();
        twoScreen = new TwoScreen();
        setScreen(twoScreen);
    }
}
