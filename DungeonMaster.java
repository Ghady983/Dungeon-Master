
import java.util.Scanner;
public class DungeonMaster
{
    public static void main(String[] args)
    {
        int startagain=0;
    
        do{
        Scanner scan = new Scanner(System.in);
        System.out.println(" ___    __ __  ____    ____    ___   ___   ____       ___ ___   ____  _____ ______    ___  ____  \r\n" + //
                "|   \\  |  |  ||    \\  /    |  /  _] /   \\ |    \\     |   |   | /    |/ ___/|      |  /  _]|    \\ \r\n" + //
                "|    \\ |  |  ||  _  ||   __| /  [_ |     ||  _  |    | _   _ ||  o  (   \\_ |      | /  [_ |  D  )\r\n" + //
                "|  D  ||  |  ||  |  ||  |  ||    _]|  O  ||  |  |    |  \\_/  ||     |\\__  ||_|  |_||    _]|    / \r\n" + //
                "|     ||  :  ||  |  ||  |_ ||   [_ |     ||  |  |    |   |   ||  _  |/  \\ |  |  |  |   [_ |    \\ \r\n" + //
                "|     ||     ||  |  ||     ||     ||     ||  |  |    |   |   ||  |  |\\    |  |  |  |     ||  .  \\\r\n" + //
                "|_____| \\__,_||__|__||___,_||_____| \\___/ |__|__|    |___|___||__|__| \\___|  |__|  |_____||__|\\_|\r\n" + //
                "                                                                                                ");
        
        String playerInputName;
        do{
        System.out.println("Input Adventurer Name:");
        try{ playerInputName = scan.nextLine();}
        catch(Exception e){playerInputName ="Player";}
        }while(playerInputName.equalsIgnoreCase(""));
        int inputDifficulty;
        String initMessage="";
        do{
        System.out.println("Choose Difficulty:    0 : Peaceful    1 : Easy    2 : Medium    3 : Hard");
        try{inputDifficulty = scan.nextInt();}
        catch(Exception e){inputDifficulty = 3;initMessage="Difficulty Set to Hard :P !";}
        }while(inputDifficulty<0||inputDifficulty>3);
        World world1 = new World(35, 10,playerInputName,inputDifficulty,initMessage);
        world1.GamePlay();

                System.out.println(" ___    __ __  ____    ____    ___   ___   ____       ___ ___   ____  _____ ______    ___  ____  \r\n" + //
                "|   \\  |  |  ||    \\  /    |  /  _] /   \\ |    \\     |   |   | /    |/ ___/|      |  /  _]|    \\ \r\n" + //
                "|    \\ |  |  ||  _  ||   __| /  [_ |     ||  _  |    | _   _ ||  o  (   \\_ |      | /  [_ |  D  )\r\n" + //
                "|  D  ||  |  ||  |  ||  |  ||    _]|  O  ||  |  |    |  \\_/  ||     |\\__  ||_|  |_||    _]|    / \r\n" + //
                "|     ||  :  ||  |  ||  |_ ||   [_ |     ||  |  |    |   |   ||  _  |/  \\ |  |  |  |   [_ |    \\ \r\n" + //
                "|     ||     ||  |  ||     ||     ||     ||  |  |    |   |   ||  |  |\\    |  |  |  |     ||  .  \\\r\n" + //
                "|_____| \\__,_||__|__||___,_||_____| \\___/ |__|__|    |___|___||__|__| \\___|  |__|  |_____||__|\\_|\r\n" + //
                "                                                                                                ");
        System.out.println("Wanna Start Again?  1 Yes   !1 No");
        world1.ConsoleUp(10);
        try{startagain = scan.nextInt();}
        catch(Exception e){startagain=0;}


        }while(startagain==1);
    }
}

//Classes

class World
{
    int width,height,Difficulty,lastmsgindex,lastmsgmodif=0,amountMana=5,frame=0,spawnDB=2,modif=1;
    String Difficulty_Text="Difficulty",msg="";
    boolean gameStillOnGoing=true;
    Player player1;
    DoublePower dbpowerup=new DoublePower(-2,-2);
        World(int w,int h,String PlayerInputNameWorld,int diff,String initialmsg){width=w;height=h;player1 = new Player(PlayerInputNameWorld);Difficulty=diff;msg=initialmsg;}
ScoreObj[] scores={new ScoreObj(1, 1,0)};
ScoreObj[][] scoresDif =
{ {new ScoreObj(1, 1,0),new ScoreObj(2, 2,1),new ScoreObj(3, 3,2),new ScoreObj(4, 4,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0)}
,
{new ScoreObj(1, 1,0),new ScoreObj(2, 2,1),new ScoreObj(3, 3,2),new ScoreObj(4, 4,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0),new ScoreObj(3, 3,2)}
,
{new ScoreObj(1, 1,0),new ScoreObj(2, 2,1),new ScoreObj(3, 3,2),new ScoreObj(4, 4,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,2),new ScoreObj(5, 5,0),new ScoreObj(3, 3,2)}
,
{new ScoreObj(1, 1,0),new ScoreObj(2, 2,1),new ScoreObj(3, 3,1),new ScoreObj(4, 4,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0),new ScoreObj(5, 5,0)}

};
    
  
    void RenderWorld()
    {
        switch(frame)
        {
        case 0 : frame=1;break;
        case 1 : frame=0;break;
        }
        dbpowerup.gameObject.shapeObj=dbpowerup.shapes[frame];

         player1.Display_Transform_Position();
        for(int i=0;i<height;i++)
        {
            for(int j =0;j<width;j++)
            { 
                Position tmpPosition = new Position(j,i);
                boolean isScore=false;
                if(Transform.SamePosition(player1.gameObject.transform.position, tmpPosition)){ System.out.print(player1.gameObject.shapeObj);j+=player1.gameObject.shapeObj.length()-1;isScore=false;continue;}
                if(Transform.SamePosition(dbpowerup.gameObject.transform.position, tmpPosition)){ System.out.print(dbpowerup.gameObject.shapeObj);j+=dbpowerup.gameObject.shapeObj.length()-1;isScore=false;continue;}
                  //scores
                for(int k=0;k<scores.length;k++)
                {
                if(Transform.SamePosition(scores[k].gameObject.transform.position, tmpPosition)){ System.out.print(scores[k].gameObject.shapeObj);j+=scores[k].gameObject.shapeObj.length()-1;isScore=true;continue;}
                }
                if(isScore){continue;}
                
                if(!Transform.SamePosition(player1.gameObject.transform.position, tmpPosition)){System.out.print(" ");isScore=false;}                
            }
            if(i==0){System.out.print("                      |Notifications|");}
            if(i==1){System.out.print("                      |"+msg);if(lastmsgmodif>1){System.out.print("x"+lastmsgmodif);}System.out.print("|");}

            if(player1.player_score>=amountMana)
            {
            if(i==3){System.out.print("                      |Bonus|");}
            if(i==4){System.out.print("                      |Input q To Spend "+amountMana+" Mana for 1 Health|");}
            }
             if(i==7){if(modif==2){System.out.print("                      "+dbpowerup.gameObject.shapeObj);}}
            if(i==8){System.out.print("                      |Menu|");}
            if(i==9){System.out.print("                      |Input m To End Game|");}
            System.out.println("");
        }
        System.out.println("-------------------------------------------");
        player1.Display_All_Player_Stats();
        System.out.println("-------------------------------------------");
        
    }
    void GamePlay()
    {
    
        switch(Difficulty)
    {
    case 0: scores = scoresDif[3];Difficulty_Text="Peaceful";amountMana=5;break;
    case 1:scores= scoresDif[0];Difficulty_Text="Easy";player1.player_health=2;amountMana=11;break;
    case 2:scores= scoresDif[1];Difficulty_Text="Medium";player1.player_health=2;amountMana=8;break;
    case 3:scores= scoresDif[2];Difficulty_Text="Hard";player1.player_health=3;amountMana=5;break;
    }
       RandomizeScorePos();
        CheckSamePosScore();
        this.RenderWorld();
        char input_move;
        Scanner scan = new Scanner(System.in);
            while (gameStillOnGoing) 
        {
           
            if(spawnDB==0){dbpowerup.gameObject.RandomizePosition(this);spawnDB=1;}
            else if(spawnDB>1) {spawnDB = (int)((Math.random())*25);if(spawnDB ==1){spawnDB=2;}}
            for(int k=0;k<scores.length;k++)
                {
                if(Transform.SamePosition(scores[k].gameObject.transform.position, player1.gameObject.transform.position))
                {
                    switch(scores[k].power)
                    {
                        case 0: msg ="You gained Mana!";if(lastmsgindex==0){lastmsgmodif++;}else{lastmsgmodif=1;}if(modif>1){lastmsgmodif+=modif-1;}lastmsgindex=0;player1.player_score+=modif;player1.addPlayerXP(1);RandomizeScorePos();this.RenderWorld();modif=1;break;
                        case 1: msg ="Wizard Gave You Health!";if(lastmsgindex==1){lastmsgmodif++;}else{lastmsgmodif=1;}if(modif>1){lastmsgmodif+=modif-1;}lastmsgindex=1;player1.player_health+=modif;player1.addPlayerXP(2);RandomizeScorePos();this.RenderWorld();modif=1;break;
                        case 2: msg ="The Dark One Dealt Damage!";if(lastmsgindex==2){lastmsgmodif++;}else{lastmsgmodif=1;}lastmsgindex=2;player1.subPlayerHealth();RandomizeScorePos();if(player1.player_health<1){player1.player_XP += player1.player_score;player1.player_score=0;}this.RenderWorld();break;
                    }
                    
                }
                }
                if(Transform.SamePosition(dbpowerup.gameObject.transform.position, player1.gameObject.transform.position)||Transform.SamePosition(new Position((int)(dbpowerup.gameObject.transform.position.x+dbpowerup.gameObject.shapeObj.length()), (int)(dbpowerup.gameObject.transform.position.y)), player1.gameObject.transform.position)){modif=2;dbpowerup.gameObject.transform.position.x=-2;dbpowerup.gameObject.transform.position.y=-2;spawnDB=2;}
                 CheckSamePosScore();
            if(player1.player_health<1){break;}
            System.out.println("Difficulty: "+Difficulty_Text);
            System.out.println("Choose Player Movement:    w Up    s Down    a Left    d Right");
            this.ConsoleUp(12);       
            try{input_move = scan.nextLine().charAt(0);}
            catch(final Exception e){input_move = ' ';}
            
            switch (input_move) 
            {
                case 'w':player1.gameObject.transform.MoveUp();CheckPlayerConditions();this.RenderWorld();break;
                case 's':player1.gameObject.transform.MoveDown();CheckPlayerConditions();this.RenderWorld();break;
                case 'a':player1.gameObject.transform.MoveLeft();CheckPlayerConditions();this.RenderWorld();break;
                case 'd':player1.gameObject.transform.MoveRight();CheckPlayerConditions();this.RenderWorld();break;
                case 'q':if(player1.player_score>=amountMana){player1.addPlayerHealth();player1.spendScore(amountMana);player1.addPlayerXP(2);msg="You Spent "+amountMana+" Mana For 1 Health!";if(lastmsgindex==3){lastmsgmodif++;}else{lastmsgmodif=1;}lastmsgindex=3;}this.RenderWorld();break;
                case 'm':gameStillOnGoing=false;this.RenderWorld();break;
                default:input_move =0;this.RenderWorld();break;
            }
        }
        System.out.println("  ____   ____  ___ ___    ___   ___   __ __    ___  ____   __ \r\n" + //
                " /    | /    ||   |   |  /  _] /   \\ |  |  |  /  _]|    \\ |  |\r\n" + //
                "|   __||  o  || _   _ | /  [_ |     ||  |  | /  [_ |  D  )|  |\r\n" + //
                "|  |  ||     ||  \\_/  ||    _]|  O  ||  |  ||    _]|    / |__|\r\n" + //
                "|  |_ ||  _  ||   |   ||   [_ |     ||  :  ||   [_ |    \\  __ \r\n" + //
                "|     ||  |  ||   |   ||     ||     | \\   / |     ||  .  \\|  |\r\n" + //
                "|___,_||__|__||___|___||_____| \\___/   \\_/  |_____||__|\\_||__|");

                this.ConsoleUp(5);
        System.out.println("Press Enter To Continue...");
        this.ConsoleUp(17);
         scan.nextLine();
            }

    void CheckSamePosScore()
    {
        for(int i =0;i<scores.length;i++)
        {
            for(int k =i+1;k<scores.length;k++)
            {
                while(Transform.SamePosition(scores[i].gameObject.transform.position,scores[k].gameObject.transform.position))
                {scores[i].gameObject.RandomizePosition(this);}
            }
        }
    }
    void RandomizeScorePos()
    {
         for(int k=0;k<scores.length;k++)
        {
        scores[k].gameObject.RandomizePosition(this);
        }
    }

    void CheckPlayerConditions()
    {
            //Player conditions
            if(player1.gameObject.transform.position.x<0){player1.gameObject.transform.position.x=width-1;}
            if(player1.gameObject.transform.position.x>=width){player1.gameObject.transform.position.x=0;}
            if(player1.gameObject.transform.position.y<0){player1.gameObject.transform.position.y=height-1;}
            if(player1.gameObject.transform.position.y>=height){player1.gameObject.transform.position.y=0;}
            //
    }

 void ConsoleUp(int times)
{
for(int i=0;i<times;i++){System.out.println("");};
}
}

class ScoreObj
{
    //0 score, 1 health , 2 bomb
     Object gameObject ;
     int power;
     ScoreObj(int x,int y,int pow){gameObject = new Object("o", new Position(x,y));power=pow;}
}

class Object
{
    Transform transform = new Transform();
    String shapeObj;
    Object(String shape){shapeObj =shape;}
    Object(String shape,Position pos){shapeObj =shape;transform.position =pos;}

    void RandomizePosition(World world)
    {
        int x,y;
        x = (int)((Math.random())*(world.width-1));
        y = (int)((Math.random())*(world.height-1));
       transform.position = new Position(x,y);

    }
}

class DoublePower
{
    String[] shapes={"x2","--"};
    Object gameObject;
    DoublePower(int x ,int y){gameObject = new Object(shapes[0]);gameObject.transform.position.x=x;gameObject.transform.position.y=y;}
}

class Player
{
    String player_name;
    int player_health=1,player_score,player_XP=0;
    Object gameObject;
    Player(String name){player_name=name;gameObject = new Object(name.substring(0,1));}
    void Display_Name(){System.out.println(player_name);}
    void Display_HealthAndScore(){System.out.println("Health: "+player_health+"    "+"Score : "+ player_score);}
    void addPlayerHealth(){player_health++;}
    void subPlayerHealth(){player_health--;}
    void addPlayerScore(){player_score++;}
    void addPlayerXP(int amount){player_XP+=amount;}
    void Display_Transform_Position(){System.out.println("Player: Position ==> X : "+gameObject.transform.position.x+"    "+"Y : "+gameObject.transform.position.y);}
    void Display_All_Player_Stats(){System.out.println(player_name+"    Health: "+player_health+"    "+"Mana : "+ player_score +"    "+"Xp : "+ player_XP);}
    void spendScore(int amount){player_score-=amount;}
}

class Transform
{
Position position = new Position();

void MoveUp(){this.position.y--;} void MoveDown(){this.position.y++;}
void MoveLeft(){this.position.x--;} void MoveRight(){this.position.x++;}

static Boolean SamePosition(Position p1,Position p2){return (p1.x == p2.x && p1.y ==p2.y);}
}

class Position
{
float x,y;
Position(){}
Position(int x,int y){this.x=x;this.y=y;}
}
