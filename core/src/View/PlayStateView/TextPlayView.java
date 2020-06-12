package View.PlayStateView;

import Controller.MyGdxGame;
import Controller.Save;
import Model.Creatures.Player;
import Model.PlayState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TextPlayView {
    private SpriteBatch spriteBatch;
    ShapeRenderer sr;
    private BitmapFont font;
    final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public void init(){
        spriteBatch = new SpriteBatch();
        sr = new ShapeRenderer();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/f1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 44;
        parameter1.characters  = FONT_CHARS;
        font = generator.generateFont(parameter1);
        font.setColor(Color.BLACK);
    }

    public void update(float dt){ }

    public void draw(PlayState ps) {
        String ln = System.lineSeparator();
        int currName = ps.getCurrentPayer().getName().length();

        spriteBatch.begin();

        //parameters for player1
        font.draw(spriteBatch, ps.getPlayer1().getName(),210,710);
        font.draw(spriteBatch, "Удача: " + ps.getPlayer1().getLuck(),10,500);
        font.draw(spriteBatch, "Сила: " + ps.getPlayer1().getPower(),10,445);
        font.draw(spriteBatch, "Скорость: " + ps.getPlayer1().getSpeed(),10,390);
        //parameters for player2
        font.draw(spriteBatch, ps.getPlayer2().getName(),MyGdxGame.WIDTH - 10 * 23 - 200,710);
        font.draw(spriteBatch, "Удача: " + ps.getPlayer2().getLuck(),1090,500);
        font.draw(spriteBatch, "Сила: " + ps.getPlayer2().getPower(),1090,445);
        font.draw(spriteBatch, "Скорость: " + ps.getPlayer2().getSpeed(),1090,390);

        //set parameters
        if (ps.getCurrentPayer().getLuck() == 0 && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "Привет, "+ps.getCurrentPayer().getName() +"!",
                    (640 - (currName*23 + 7*23)/2f),400);
            font.draw(spriteBatch, "Давай установим исходные параметры!", 325, 340);
            font.draw(spriteBatch,  "Нажми ENTER, чтобы бросить кости." + ln +
                    "Начнём с удачи." +ln+"Брось кубик (нажми ENTER).", 325, 285);
        }
        if (ps.getCurrentPayer().getLuck() != 0 && ps.getCurrentPayer().getPower() == 0 && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "Твоя удача " + ps.getCurrentPayer().getLuck() + " !" + ln +
                    "Установим силу." + ln + "Брось кубик (нажми ENTER).", 410, 360);
        }
        if (ps.getCurrentPayer().getPower() != 0 && ps.getCurrentPayer().getSpeed() == 0 && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "Твоя сила " + ps.getCurrentPayer().getPower() + " !" + ln +
                    "Установим скорость." + ln + "Брось кубик (нажми ENTER).", 410, 360);
        }
        if (ps.getCurrentPayer().getSpeed() != 0 && !ps.getCurrentPayer().isParametersSet() && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "Твоя скорость " + ps.getCurrentPayer().getSpeed() + " !", 410, 360);
            font.draw(spriteBatch,  "Все параметры установлены, " + ps.getCurrentPayer().getName() +".", 300, 305);
            ps.changeCurrentPlayer(ps.getCurrentPayer());
            Player check = ps.getCurrentPayer();
            ps.changeCurrentPlayer(ps.getCurrentPayer());
            if (check.isParametersSet()) font.draw(spriteBatch, "Нажми SPACE, чтобы начать игру!", 300, 250);
            else font.draw(spriteBatch, "Нажми SPACE, чтобы сменить игрока.", 300, 250);
        }

        //if parameters increased
        if(ps.getCurrentPayer().getCurrentField() == 9 && ps.getCurrentPayer().readyToShowPoints() &&
                ps.getCurrentPayer().isSpeedIncreased()&& !ps.getCurrentPayer().isExit())
            font.draw(spriteBatch, "Твоя скорость увеличилась!" +ln+
                    "Теперь она составляет " + ps.getCurrentPayer().getSpeed() +".",430, 310);
        if(ps.getCurrentPayer().getCurrentField() == 14 && ps.getCurrentPayer().readyToShowPoints() &&
                ps.getCurrentPayer().isLuckIncreased() && !ps.getCurrentPayer().isExit())
            font.draw(spriteBatch, "Твоя удача увеличилась!"+ln+
                    "Теперь она составляет " + ps.getCurrentPayer().getLuck() +".",430, 310);
        if(ps.getCurrentPayer().getCurrentField() == 23 && ps.getCurrentPayer().readyToShowPoints() &&
                ps.getCurrentPayer().isPowerIncreased() && !ps.getCurrentPayer().isExit())
            font.draw(spriteBatch, "Твоя сила увеличилась!" +ln+
                    "Теперь она составляет " + ps.getCurrentPayer().getPower() +".",430, 310);

        //ENTER to roll dice
        if (ps.isReady() && !(ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) &&
                !ps.getCurrentPayer().isExit() && !ps.getCurrentPayer().isBlocked() &&
                !ps.getCurrentPayer().readyToShowPoints() && !ps.getCurrentPayer().isShow() &&
                !ps.getCurrentPayer().isWannaFight() && !ps.getCurrentPayer().notWannaFight() &&
                !ps.getCurrentPayer().isFight() && !ps.getCurrentPayer().isPowerIncreased())
            font.draw(spriteBatch, ps.getCurrentPayer().getName() + ", брось кубик (нажми ENTER).",
                    (640 - (currName*23 + 20*23)/2f), 300);

        //rolled points
        if (!(ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) && !ps.getCurrentPayer().isExit()
                && ((ps.getCurrentPayer().readyToShowPoints() && !ps.getCurrentPayer().notWannaFight() &&
                 !ps.getCurrentPayer().isBlocked()) || (ps.getCurrentPayer().isWin() && !ps.getCurrentPayer().isShow()))) {
            font.draw(spriteBatch, ps.getCurrentPayer().getName() +", тебе выпало "+ ps.getDice().getRolled() +" !",
                    (640 - (currName*23 + 12*23)/2f), 400);
        }

        //item out of fight
        if(!(ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) &&
                ps.getCurrentPayer().isItemFound() && !ps.getCurrentPayer().notWannaFight()
                && ps.getCurrentPayer().readyToShowPoints() && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "Ты нашел артефакт!", 450, 310);
        }

        //monster
        if(!(ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) &&
                ps.getMonster().isMonsterHere(ps.getCurrentPayer().getPosition()) && !ps.getCurrentPayer().notWannaFight()
                && ps.getCurrentPayer().readyToShowPoints() && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch, "оу... Здесь монстр..." + "его сила " + ps.getMonster().getPowerOfMonster() + ln +
                    "Ты хочешь сразиться?" + ln +
                    "Нажми Y, если хочешь сразитья, N, если нет.", 300, 315);
        }
        //if want to fight
        if(ps.getCurrentPayer().isFight() && !ps.getCurrentPayer().isShow() && !ps.getCurrentPayer().isExit()){
            font.draw(spriteBatch, "Брось кости, чтобы увеличить силу!", 350, 300);
        }
        //results of fight
        if(ps.getCurrentPayer().isWin() && !ps.getCurrentPayer().isShow() &&!ps.getCurrentPayer().isExit()) {
            int currentPlayerPower = ps.getCurrentPayer().getIncreasedPower() + ps.getCurrentPayer().getPower();
            int currentMonsterPower= ps.getMonster().getIncreasedPowerOfMonster() + ps.getMonster().getPowerOfMonster();
            font.draw(spriteBatch, "Монстр тоже бросил кости...выпало "
                    + ps.getMonster().getIncreasedPowerOfMonster() + "." +ln+
                    "теперь твоя текущая сила " + currentPlayerPower +","+ ln+
                    "текущая сила монстра " + currentMonsterPower +".", 310, 350);
            font.draw(spriteBatch, "Нажми SPACE, чтобы увидеть результат.", 330, 165);
        }
        if(ps.getCurrentPayer().isShow() && ps.getCurrentPayer().isWin() && !ps.getCurrentPayer().isExit()){
            int currentPlayerPower = ps.getCurrentPayer().getIncreasedPower() + ps.getCurrentPayer().getPower();
            int currentMonsterPower= ps.getMonster().getIncreasedPowerOfMonster() + ps.getMonster().getPowerOfMonster();
            if(currentPlayerPower > currentMonsterPower){
                font.draw(spriteBatch, ps.getCurrentPayer().getName() + ", ты победил(а) монстра!",
                        (640 - (currName*23 + 17*23)/2f) , 400);
                font.draw(spriteBatch,
                        "Теперь твоя сила " + ps.getCurrentPayer().getPower()+"." + ln+
                        "ого! Ты нашел артефакт!", 450, 340);
            }
            if(currentPlayerPower == currentMonsterPower ){
                font.draw(spriteBatch, ps.getCurrentPayer().getName() +", удача на твоей стороне..",
                        (640 - (currName*23 + 20*23)/2f) , 400);
                font.draw(spriteBatch, "Ты победил(а) монстра!", 450, 340);
                if(ps.getCurrentPayer().getLuck() >= 3)
                    font.draw(spriteBatch,"Теперь твоя удача  "+ ps.getCurrentPayer().getLuck() +".",450,300);
            }
            if(currentPlayerPower < currentMonsterPower){
                font.draw(spriteBatch, ps.getCurrentPayer().getName() +", ты проиграл(а) монстру..",
                        (640 - (currName*23 + 20*23)/2f) , 400);
                if(!ps.getCurrentPayer().isLose())
                    font.draw(spriteBatch, "Теперь твоя сила " + ps.getCurrentPayer().getPower() + ".",450, 340);
                if(ps.getCurrentPayer().isLose())
                    font.draw(spriteBatch, "Ты пропускаешь следующий ход.", 410, 340);
            }
        }
        //if not wanna fight
        if( ps.getCurrentPayer().notWannaFight() && !ps.getCurrentPayer().isExit()) {
            font.draw(spriteBatch,  ps.getCurrentPayer().getName() + "!", (640 - (currName * 23 ) / 2f) ,400);
            font.draw(spriteBatch, "Ты пропускаешь следующий ход.", 350, 340);
        }
        //lose move
        if(ps.getCurrentPayer().isBlocked() && !ps.getCurrentPayer().isLose() && !ps.getCurrentPayer().isExit()){
            font.draw(spriteBatch, ps.getCurrentPayer().getName() +"!",(440 - (currName*23)/2f) + 200, 400);
            font.draw(spriteBatch, "пропуск хода :(" , 350, 340);
        }

        //SPACE to continue
        if(!(ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) && !ps.getCurrentPayer().isExit()
                && (ps.getCurrentPayer().readyToShowPoints() && !ps.getMonster().isMonsterHere(ps.getCurrentPayer().getPosition())
                || ps.getCurrentPayer().notWannaFight() || (ps.getCurrentPayer().isShow() && ps.getCurrentPayer().isWin())
                || (ps.getCurrentPayer().isBlocked() && !ps.getCurrentPayer().isLose())))
            font.draw(spriteBatch, "Нажми SPACE чтобы продолжить игру.", 350, 165);

        //end of the game
        if((ps.getPlayer1().getCountItems() == 3 || ps.getPlayer2().getCountItems() == 3) &&
                !ps.getCurrentPayer().isReadyToShowResult()) {
            Player winner;
            if(ps.getPlayer1().getCountItems() == 3) winner = ps.getPlayer1();
            else winner = ps.getPlayer2();
            font.draw(spriteBatch, winner.getName()+ " победил(а)!"+ ln +"Конец игры!", (640 - (currName*23+8*23)/2f),330);
            int total = winner.getPower() + winner.getSpeed() + winner.getLuck();
            if(Save.data.isScoreHigher(winner.getPower())) {
                font.draw(spriteBatch, "Новый рекорд: " + total + "!", (640 - (currName*23+8*23)/2f), 400);
            }
            font.draw(spriteBatch, "Нажми ESCAPE, чтобы выйти в главное меню", 290, 165);
        }
        //exit
        if(ps.getCurrentPayer().isExit()){
            font.draw(spriteBatch,"Покинуть игру?",500,400);
            font.draw(spriteBatch, "Нажми SPACE, если хочешь покинуть игру." + ln+
                    "(данные текущей игры не сохранятся)" +ln+
                    "Нажми ENTER, если хочешь продолжить.",325,320);
        }
        spriteBatch.end();
    }
    public void dispose(){}
}
