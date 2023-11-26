package tmp;

import com.badlogic.gdx.Game;

public class TmpGame extends Game {
    TmpScreen tmpScreen;

    TwoScreen twoScreen;

    TextScreen1 textScreen1;
    @Override
    public void create() {
        tmpScreen = new TmpScreen();
        twoScreen = new TwoScreen();
        textScreen1 = new TextScreen1();
        setScreen(textScreen1);
    }
}
