package tmp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SwordsmanFrame {
    public final String subfix;
    public static final String static_coat_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_coat\\sm_coat0000a.img\\";
    public static final String static_pant_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_pants\\sm_pants0000a.img\\";
    public static final String static_body_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_skin\\sm_body0000.img\\";
    public static final String belt_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_belt\\sm_belt0000a_mask1.img\\";
    public static final String cap_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_cap\\sm_cap0000c_mask1.img\\";

    public static final String hair_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_hair\\sm_hair0000a.img\\";

    public static final String neck_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_neck\\sm_neck0000d_mask1.img\\";

    public static final String shoes_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_avatar_shoes\\sm_shoes0000a.img\\";

    public static final String static_hand_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_growtype_basics\\ghosthand.img\\";

    public static final String static_ghost_hand_url = "C:\\myWareHouse\\doc\\asset\\dnf\\sprite_character_swordman_equipment_growtype_basics\\pinkghosthand.img\\";

    Sprite body;

    Sprite coat;

    Sprite pant;

    Array<Sprite> swordMan = new Array<>();

    public final Vector2 position = new Vector2();

    public SwordsmanFrame(int subfix) {
        this.subfix = subfix + ".png";
        position.set(100,100);
    }

    public void init(){
        setBody();
        setCoat();
        setBelt();
        setCap();
        setHair();
        setNeck();
        setHand();
        setShoes();
        setPant();
        System.out.printf(subfix + " init success!");
    }

    private void setHand() {
        String hand_url = SwordsmanFrame.static_hand_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(hand_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);

        hand_url = SwordsmanFrame.static_ghost_hand_url + subfix;
        tmp = new Texture(Gdx.files.absolute(hand_url));
        shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setShoes() {
        String c_shoes_url = SwordsmanFrame.shoes_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(c_shoes_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setNeck() {
        String c_neck_url = SwordsmanFrame.neck_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(c_neck_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setHair() {
        String c_hair_url = SwordsmanFrame.hair_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(c_hair_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setCap() {
        String c_cap_url = SwordsmanFrame.cap_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(c_cap_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setBelt() {
        String c_belt_url = belt_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(c_belt_url));
        Sprite shoes = new Sprite(tmp);
        shoes.setFlip(false,true);
        shoes.setPosition(position.x,position.y);
        swordMan.add(shoes);
    }

    private void setPant() {
        String pant_url = static_pant_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(pant_url));
        pant = new Sprite(tmp);
        pant.setFlip(false,true);
        pant.setPosition(position.x,position.y);
        swordMan.add(pant);
    }

    private void setCoat() {
        String coat_url = static_coat_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(coat_url));
        coat = new Sprite(tmp);
        coat.setFlip(false,true);
        coat.setPosition(position.x,position.y);
        swordMan.add(coat);
    }

    private void setBody() {
        String body_url = static_body_url + subfix;
        Texture tmp = new Texture(Gdx.files.absolute(body_url));
        body = new Sprite(tmp);
        body.setFlip(false,true);
        body.setPosition(position.x,position.y);
        swordMan.add(body);
    }
    ShapeRenderer shapeRenderer;

    public void render(SpriteBatch spriteBatch, OrthographicCamera camera){
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        for(int x=0;x<swordMan.size;x++){
            swordMan.get(x).draw(spriteBatch);
        }
        spriteBatch.end();
    }

    public void renderDebug(ShapeRenderer shapeRenderer, OrthographicCamera camera){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.setProjectionMatrix(camera.combined);

        Sprite sprite = swordMan.get(0);
        shapeRenderer.rect(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());

        shapeRenderer.end();
    }
}
