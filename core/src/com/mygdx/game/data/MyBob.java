package com.mygdx.game.data;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.FightPropData;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.data.charact.AbstractAnimation;
import com.mygdx.game.data.enchantress.Skill1;
import com.mygdx.game.input.InputStateData;
import com.mygdx.game.input.TouchVector;

public class MyBob extends AbstractAnimation {
    public static final Logger log = new Logger("MyBob", Logger.INFO);
    static final float defaultVelHorizon = 50;
    static final float defaultVelDirect = 50;
    public float[] shadowBox = new float[4];

    public static final int DIRECT_RIGHT = 1 << 0;
    public static final int DIRECT_LEFT = 1 << 1;
    public static final int DIRECT_UP = 1 << 2;
    public static final int DIRECT_DOWN = 1 << 3;

    public static final int STATUS_IDOL = 0;
    public static final int STATUS_RUN = 1;

    public static final int STATUS_ATTACK1 = 2;
    public static final int MOD_STATUS_KEY = 0;
    public static final int MOD_STATUS_MOUSE = 1;


    public int mod = MOD_STATUS_KEY; // key

    public long direct = 0;


    public int status = STATUS_IDOL;
    private final PositionData positionData = new PositionData();


    private final FightPropData fightPropData = new FightPropData();

//    public

    public Vector2 vel = new Vector2();

    public float stateTime = 0;

    public float currentMaxSkillTime = 0.5f;

    public final Array<AbstractSkill> skills = new Array<>();

    private InputStateData inputStateData;


    public MyBob(float height, float boxX, float boxY, MapData mapData) {

//        this.bobRender = MyGdxGame.getGame().getMainAsset().getBobRender();
        inputStateData = MyGdxGame.getGame().getInputStateData();
        positionData.rectangle.x = boxX;
        positionData.rectangle.y = boxY;
        positionData.height = height;
        this.skills.add(new Skill1(Skill1.MAX_DURATION, this));


    }

    int button_index = -1;

    public void update(float delta) {
        stateTime += delta;
        checkButton();
        updateDirection(delta);
        if (status == STATUS_RUN) {
            vel.scl(delta);
            move();
            vel.scl(1.0f / delta);
        }
        checkSkill0(delta);

        pos().update(delta);
        fixBobPosition();
    }

    private void checkSkill0(float delta) {
        if (inputStateData.attack_key_0.key_down && !inputStateData.attack_key_0.consume){
            inputStateData.attack_key_0.consume = true;
            useSkill(0);
        }
        // inputStateData
        if (status == STATUS_ATTACK1) {
            skills.get(0).update(delta);
            if (stateTime >= currentMaxSkillTime) {
                System.out.println("makeBobIdolForce");
                makeIdolForce();

            }
        }
    }

    private void checkButton() {
        TouchVector[] arr = MyGdxGame.getInstance().getActInputProcessor().moveTo;
        if (button_index == -1) {

            for (int x = 0; x < arr.length; x++) {
                if (!arr[x].consume && (arr[x].touch || arr[x].drag)) {
                    button_index = x;
                    target.set(arr[x].x, arr[x].y);
                    if (arr[x].touch){
                        arr[x].consume = true;
                    }
                    break;
                }
            }
        } else {
            if (!arr[button_index].touch) {
                button_index = -1;
                return;
            } else {
                target.set(arr[button_index].x, arr[button_index].y);
            }
        }
        if (button_index >= 0) {
            moveTo();
        }
    }

    private void fixBobPosition() {
        MapData mapData = MyGdxGame.getGame().getMainAsset().getMapData();
        float width = mapData.width;
        float height = mapData.height;
        if (this.pos().pos.x < this.pos().rectangle.x / 2) {
            this.pos().pos.x = this.pos().rectangle.x / 2;
        } else if (this.pos().pos.x > width - this.pos().rectangle.x / 2) {
            this.pos().pos.x = width - this.pos().rectangle.x / 2;
        }

        if (this.pos().pos.y < 0) {
            this.pos().pos.y = 0;
        } else if (this.pos().pos.y > height - this.pos().rectangle.y) {
            this.pos().pos.y = height - this.pos().rectangle.y;
        }
    }

    @Override
    public void render() {
        MyGdxGame.getGame().getMainAsset().getBobRender().render(this);
        if (status == STATUS_ATTACK1) {
            skills.get(0).render();
        }
    }

    private void updateDirection(float delta) {
        if (mod == MOD_STATUS_MOUSE && status == STATUS_RUN) {


            if (MathUtils.isZero(positionData.pos.dst(target))) {
                status = STATUS_IDOL;
            }
        }
    }

    Vector2 tmpTarget = new Vector2();

    private void move() {
        if (status == STATUS_RUN) {
            if (mod == MOD_STATUS_MOUSE && positionData.pos.dst(target) <= positionData.pos.dst(tmpTarget.set(positionData.pos.x + vel.x, positionData.pos.y + vel.y))) {
                positionData.pos.set(target);
            } else {
                positionData.pos.add(vel.x, vel.y);
            }
            reset();
        }
    }

    public void reset() {
        shadowBox[0] = pos().pos.x - pos().rectangle.x / 2;
        shadowBox[1] = pos().pos.y - pos().rectangle.y / 2;
        shadowBox[2] = pos().rectangle.x;
        shadowBox[3] = pos().rectangle.y;
    }

    private void setdirect(Vector2 position, Vector2 target) {
        this.positionData.directVect.x = target.x - position.x;
        this.positionData.directVect.y = target.y - position.y;
        this.positionData.directVect.nor();
    }

    public void resetDirect() {
        if (direct == 0) {
            this.positionData.directVect.x = 0;
            this.positionData.directVect.y = 0;
        }
    }

    public void bobUp(boolean flag) {
        mod = MOD_STATUS_KEY;
        if (flag) {
//            resetDirect();
            vel.y = defaultVelDirect;
            setdirect(DIRECT_UP, 0);
            makeBobRun();
        } else {
            setdirect(0, DIRECT_UP);
            if ((direct & DIRECT_DOWN) > 0) {
                bobDown(true);
            } else {
                vel.y = 0;
                makeBobIdol();
            }
        }
    }

    private void setdirect(int now, int pre) {
        addDirect(now);
        excludeDirect(pre);
        if (now == 0) {
            return;
        }
        if (now == DIRECT_DOWN) {
            this.positionData.directVect.y = -1;
        } else if (now == DIRECT_UP) {
            this.positionData.directVect.y = 1;
        }
        if (now == DIRECT_LEFT) {
            this.positionData.directVect.x = -1;
        } else if (now == DIRECT_RIGHT) {
            this.positionData.directVect.x = 1;
        }
    }

    public void addDirect(int now) {
        direct |= now;
    }

    public void excludeDirect(int d) {
        direct &= (~d);
    }

    public static void main(String[] args) {
        System.out.println(3 ^ DIRECT_RIGHT);
    }

    public void bobDown(boolean flag) {
        mod = MOD_STATUS_KEY;
        if (flag) {
//            resetDirect();
            vel.y = -defaultVelDirect;
            setdirect(DIRECT_DOWN, 0);
            makeBobRun();
        } else {
            setdirect(0, DIRECT_DOWN);

            if ((direct & DIRECT_UP) > 0) {
                bobUp(true);
            } else {
                vel.y = 0;
                makeBobIdol();
            }
        }
    }

    public void makeBobIdol() {

        if (MathUtils.isZero(vel.x) && MathUtils.isZero(vel.y)) {
            status = STATUS_IDOL;
            stateTime = 0;
        }

    }

    public void makeIdolForce() {
        status = STATUS_IDOL;
        stateTime = 0;
        vel.setZero();

    }

    public void makeBobRun() {
        if (status != STATUS_RUN) {
            status = STATUS_RUN;
            stateTime = 0;
        }
    }

    public void bobRight(boolean flag) {
        mod = MOD_STATUS_KEY;
        if (flag) {
            resetDirect();
            vel.x = defaultVelHorizon;
            setdirect(DIRECT_RIGHT, 0);
//            System.out.println("bobRight!");
            makeBobRun();
            System.out.println("key down right : " + direct);
        } else {
            setdirect(0, DIRECT_RIGHT);

            if ((direct & DIRECT_LEFT) > 0) {

                bobLeft(true);
            } else {
                vel.x = 0f;
                makeBobIdol();
            }
            System.out.println("key release right : " + direct);

        }
    }

    Vector2 target = new Vector2();

    public void moveTo() {
        if (status == STATUS_IDOL || status == STATUS_RUN) {
            mod = MOD_STATUS_MOUSE;
//            target.set(x,y);
            status = STATUS_RUN;

            setdirect(positionData.pos, localCenterToWorld(target));
            vel.x = defaultVelHorizon * this.positionData.directVect.x;
            vel.y = defaultVelDirect * this.positionData.directVect.y;
        }

    }

    public void bobLeft(boolean flag) {
        mod = MOD_STATUS_KEY;
        if (flag) {
            resetDirect();
            vel.x = -defaultVelHorizon;
            setdirect(DIRECT_LEFT, 0);
            System.out.println("key down left : " + direct);
            makeBobRun();
        } else {
            setdirect(0, DIRECT_LEFT);
            if ((direct & DIRECT_RIGHT) > 0) {
                bobRight(true);
            } else {
                vel.x = 0f;
                makeBobIdol();
            }
            System.out.println("key release left : " + direct);
        }
    }

    public Vector2 localCenterToWorld(Vector2 vector2) {

        return vector2;
    }

    public void useSkill(int i) {
        skills.get(i).apply(this);
    }

    @Override
    public void makeBobAttacking1() {
        this.status = STATUS_ATTACK1;
        this.currentMaxSkillTime = Skill1.MAX_DURATION;
    }


    @Override
    public PositionData pos() {
        return positionData;
    }

    @Override
    public FightPropData fightProp() {
        return this.fightPropData;
    }

    @Override
    public int camp() {
        return 0;
    }

    @Override
    public void beAttacked(AbstractAnimation anim, SkillEffect skillEffect) {
        log.info(name() + "受到攻击进行处理");
    }


    @Override
    public String name() {
        return "bob";
    }
}
