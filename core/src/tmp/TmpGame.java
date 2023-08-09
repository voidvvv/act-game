package tmp;

import com.badlogic.gdx.Game;

public class TmpGame extends Game {
    TmpScreen tmpScreen;
    @Override
    public void create() {
        tmpScreen = new TmpScreen();
        setScreen(tmpScreen);
    }
}
