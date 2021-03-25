/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package refugeoly;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author categ
 */
public class Game {
    public static boolean k;
     public static void RefugeolyGame() throws IOException, ClassNotFoundException {
     
     RollDiceAction rd=new RollDiceAction();
     StayTurnAction sta=new StayTurnAction();
     Specifically26Action spe=new Specifically26Action();

     Scanner input=new Scanner(System.in);
     String isUpload;
     Refugee savedRefugee = null;
     boolean k=false;
     boolean saved=false;
     boolean forc=true;

     ArrayList <Refugee> p = null;
     String c = null;
     GiverEntity NGOBank = null ;
     ReceiverEntity MafiaBank = null ;
     do{
        System.out.println("Do you want to upload a saved game?(Y/N)");
        isUpload=input.next();
     }while(!isUpload.equals("Y")&&!isUpload.equals("N"));
     
      if(isUpload.equals("Y")){
               p=UploadGamePlayers();
               NGOBank=UploadGameNGOBank();  
               MafiaBank=UploadGameMafiaBank();
               saved=true;
               savedRefugee=UploadGameSavePlayer();
                for (Refugee r: p){
                System.out.println(r.toString());
               }
      }
      else if(isUpload.equals("N")){
            //Create Players
            p=Players();
            //Create NGOBank
            NGOBank=new GiverEntity("NGOBank",10000);
             //Create MafiaBank
            MafiaBank=new ReceiverEntity("MafiaBank",0);
      }
    
        int i;
        boolean[] stay=new boolean[5];
        for(int j=0;j<5;j++){
            stay[j]=true;
        }
        while(true){
            i=0;    
                for (Refugee r: p){
                    
                        
                    boolean forsave;
                    
                        do{
                           
                            forsave=false;
                          //StayTurn
                          if(stay[i]==true){ 
                           // 
                           if((r.getSquare().getNumber()!=36)&&(r.getSquare().getNumber()!=39)&&(CheckIfPlayerIsDead(r))){
                                if(forc){
                                    
                                    do{
                                    System.out.println("**********************************************************");
                                    System.out.println("Write R to roll the dice for your player");
                                    System.out.println("If you want to save the game write SAVE.");
                                    System.out.println("If you want to upload saved  game write UPLOAD.");
                                    System.out.println("**********************************************************");
                                        c =input.next();
                                    }while(!c.equals("R")&&!c.equals("SAVE")&&!c.equals("UPLOAD"));
                                }
                                if(c.equals("R")){
                                    
                                    if(saved==true){
                                        forsave=false;
                                        forc=false;
                                        if(savedRefugee.getName().equals(r.getName())){
                                            saved=false;
                                            forc=true; 
                                            System.out.println(NGOBank.toString());
                                            System.out.println(MafiaBank.toString());
                                            System.out.println(r.toString());
                                           
                                            rd.act(r); 
                                           
                                        System.out.println(r.toString());

                                        if(CheckIfPlayerIsDead(r)){

                                            r.getSquare().doActions(r);
                                        }
                                        CheckIfPlayerIsDead(r);



                                        System.out.println(r.toString());
                                    
                                        ActionsForNGOandMafia(r,MafiaBank,NGOBank);
                                        
                                        //StayTurn
                                      sta.act(r);
                                        stay[i]=sta.play();
                                        //26B
                                       /* if(r.getSquare().getNumber()==26){
                                            spe.act(r);
                                            stay[i]=spe.stay();
                                        }*/

                                        }
                                        
                                    }
                                    else{
                                            System.out.println(NGOBank.toString());
                                            System.out.println(MafiaBank.toString());
                                            System.out.println(r.toString());
                                            
                                            rd.act(r); 
                                            
                                     System.out.println(r.toString());

                                        if(CheckIfPlayerIsDead(r)){

                                            r.getSquare().doActions(r);
                                             
                                        }
                                        CheckIfPlayerIsDead(r);



                                    System.out.println(r.toString());
                                    ActionsForNGOandMafia(r,MafiaBank,NGOBank);
                                  
                                    //StayTurn
                                    sta.act(r);
                                    stay[i]=sta.play();
                                    }
                                }
                                else if(c.equals("SAVE")){
                                    System.out.println("Game will be saved at save.bin");
                                    SavetheGame("save.bin",p,NGOBank,MafiaBank);
                                    SavePlayerWhoSaved(r);
                                    forsave=true;
                                   
                                }
                                else if(c.equals("UPLOAD")){
                                    saved=true;
                                    String yorn;
                                    do{
                                    System.out.println("Do you want to save the current game?(Y/N) ");   
                                    yorn=input.next();
                                    }while(!yorn.equals("Y")&&!yorn.equals("N"));
                                    if(yorn.equals("Y")){
                                        System.out.println("Game will be saved at saveUpload.bin");
                                        SavetheGame("saveUpload.bin",p,NGOBank,MafiaBank);
                                       
                                       
                                    }
                                    
                                    p=UploadGamePlayers();
                                    NGOBank=UploadGameNGOBank();  
                                    MafiaBank=UploadGameMafiaBank();
                                    savedRefugee=UploadGameSavePlayer();
                                    if(yorn.equals("Y")){
                                    CopyUploadSavetoSaveFile();
                                    }
                                }
                          }
                           else{
                               if(!CheckIfPlayerIsDead(r)){
                                CheckIfPlayerIsDead(r);

                                }
                               else{
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                System.out.println(r.getName()+"  You won!!!");
                                break;
                                }
                               break;
                              
                            }
                           
                    }
                    else{
                        System.out.println("You lost your turn");
                        stay[i]=true;
                    }
                        i++;

                        EndingGame(p);
                          
                      
                      }while(forsave==true);
                        
                        
                    }
        }
}  
     //Method for ending the game
    public static void EndingGame(ArrayList <Refugee> p){
        int size=p.size();
        int k=0;
        for(Refugee r:p){
            if((r.getSquare().getNumber()==36)||(r.getSquare().getNumber()==39)||!CheckIfPlayerIsDead(r)){
             k++;
             }
            if(k==size)
                exit(0);
        }
        
    }
    //Methods for Uploading Saved Game
    public static Refugee UploadGameSavePlayer() throws ClassNotFoundException, FileNotFoundException, IOException {
        
         Refugee r = null;
        try {
            FileInputStream fis = new FileInputStream("player.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            Object obj=null;
            obj = ois.readObject();
               
           r= (Refugee)obj;
   
            ois.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        
         return r;
    }
    //Method for Saving the player who saved the game
    public static void SavePlayerWhoSaved(Refugee p) throws FileNotFoundException, IOException{

    try {
            FileOutputStream fos = new FileOutputStream("player.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            
            oos.writeObject(p);
           
            oos.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    
    }   
    //Method of coping file with the new saved game to the file we use for uploading
    public static void CopyUploadSavetoSaveFile() throws FileNotFoundException, IOException{
         FileInputStream fis = new FileInputStream("save.bin");
         FileOutputStream fos = new FileOutputStream("saveUpload.bin");
         int b; 
         while  ((b=fis.read()) != -1) 
            fos.write(b); 
        
        fis.close(); 
        fos.close(); 
  
    }
    //Method for Uploading Data of NGOBank
    public static GiverEntity UploadGameNGOBank() {
         GiverEntity ngo=null;
        try {
            FileInputStream fis = new FileInputStream("save.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean fw=true;
            while(fw){
            Object obj=null;
            obj = ois.readObject();
             if(obj instanceof GiverEntity){
             ngo = (GiverEntity)obj;
                fw=false;
             }
            }
             ois.close(); 
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
 
        
         return ngo;
    } 
     //Method for Uploading Data of MafiaBank
    public static ReceiverEntity UploadGameMafiaBank() {
         ReceiverEntity maf=null;
        try {
            FileInputStream fis = new FileInputStream("save.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean fw=true;
            while(fw){
            Object obj=null;
            obj = ois.readObject();
             if(obj instanceof ReceiverEntity){
             maf = (ReceiverEntity)obj;
                fw=false;
             }
            }
             ois.close(); 
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
 
        
         return maf;
    } 
    //Method for uploading players of the game
    public static ArrayList <Refugee> UploadGamePlayers() throws ClassNotFoundException {
        ArrayList <Refugee> p=new ArrayList <Refugee> ();
        
        try {
            FileInputStream fis = new FileInputStream("save.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            boolean fw=true;
            while(fw){
            Object obj=null;
            obj = ois.readObject();
                if(obj instanceof Refugee){
                Refugee r= (Refugee)obj;
                p.add(r);
                }
                else{
                fw=false; 
                }
                
            }
          
            ois.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        
         return p;
    }
    //Method for saving the game to a binary file 
   public static void SavetheGame(String NameOfFile,ArrayList <Refugee> p,GiverEntity NGOBank,ReceiverEntity MafiaBank) throws FileNotFoundException, IOException{

    try {
            FileOutputStream fos = new FileOutputStream(NameOfFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            for(Refugee r:p){
            oos.writeObject(r);
            }
           oos.writeObject(NGOBank);
         oos.writeObject(MafiaBank);
            oos.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    
    }    
   //Method that checks if a player is dead
    public static boolean CheckIfPlayerIsDead(Refugee r){
        if(r.getMoney()==0){
            System.out.println(r.getName() +"is dead."+r.getName()+ "you lose the game.");
            return false;
        }
        return true;
    }
    public static void ActionsForNGOandMafia(Refugee r,ReceiverEntity MafiaBank,GiverEntity NGOBank){
        int n=r.getSquare().getNumber();
            if(n==1){
              PayMafia(r,MafiaBank);
            }
            else if(n==3){
             PayMafia(r,MafiaBank);
            }
            else if((n==6)||(n==37)){
             PayMafia(r,MafiaBank);
            }
            else if(n==13){
             PayMafia(r,MafiaBank); 
            }
            else if (n==21){
             PayMafia(r,MafiaBank);
            }
            else if(n==20){
                try {
                    NGOBank.giveMoney(1000);
                } catch (NoMoneyException ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }
    public static void PayMafia(Refugee refugee,ReceiverEntity MafiaBank){
        int n=refugee.getSquare().getNumber();
        if(n==1){
          MafiaBank.receiveMoney(100);
        }
        else if(n==3){
          MafiaBank.receiveMoney(300);
        }
        else if((n==6)||(n==37)){
         MafiaBank.receiveMoney(1000); 
        }
        else if(n==13){
         MafiaBank.receiveMoney(200); 
        }
        else if (n==21){
         MafiaBank.receiveMoney(1000);
        }
        
    }
    //Creating players ,board and squares
    public static ArrayList <Refugee> Players(){
        int players;
        ArrayList <Refugee> p=new ArrayList <Refugee> ();
        do{
        System.out.println("Number of Players:");
        Scanner in=new Scanner(System.in);
        players=in.nextInt();
        }while((players<1)||(players>5));
        Board B=new Board();
        Square[] s=CreateSquares(B);
        for(int i=0;i<40;i++){
            B.addSquare(s[i]);
        }
        for(int i=0;i<players;i++){
            System.out.println("Name of Player:");
            Scanner in=new Scanner(System.in);
            String NameRefugee=in.nextLine();
            Refugee r=new Refugee(NameRefugee,B,B.getSquare(0));
            p.add(r);
        }
        
        return p;
    }
    //Creating squares.
    public static Square[] CreateSquares(Board B){
        Square[] s=new Square[40];
        ArrayList <Action> a=new ArrayList <Action>();
        RollDiceAction rd=new RollDiceAction();
        a.add(rd);
        //Squares that only RollDice
        s[2]=new Square("Car. You get a free lift. Roll dice. ",2,B,a);
        s[12]=new Square("You reach an EU coast. Roll dice.  ",12,B,a);
        s[17]=new Square("Train. Roll dice. ",17,B,a);
        s[28]=new Square("UNHCR Aid. Roll dice",28,B,a);
        //////////////////////////////////////////////
        
        ArrayList <Action> b=new ArrayList <Action>();
        GoToAction gta=new GoToAction();
        b.add(gta);
        
        //Squares that GoToA
        s[4]=new Square("Army Control. You go back to war box (0)",4,B,b);
        s[5]=new Square("Border 1. Go back to war box (0).",5,B,b);
        s[7]=new Square("Live Vest. You have an extra life if you land in the sea (Box 10).  ",7,B,b);
        s[15]=new Square("Border Control 2. Back to Border Control 1.",15,B,b);
        s[18]=new Square("Red Cross Shelter. Jump to river crossing box (22). ",18,B,b);
        s[23]=new Square("NGO Lift. Jump to family reunion box (29).",23,B,b);
        s[25]=new Square("Border Control 3. Back to border 2 (box 15). ",25,B,b);
        s[30]=new Square("Right Wing Militia. Back to Border police box (24).",30,B,b);
        s[35]=new Square("Border Control 4. Back to Border 3 (box 25). ",35,B,b);
        s[38]=new Square("Deported. You are sent back to war box (0).",38,B,b);
        s[29]=new Square("Family Reunion. Jump to bus box (31). ",29,B,b);
        /////////////////////////////////////////////////////////////////////
        ArrayList <Action> brd=new ArrayList <Action>();
        brd.add(gta);
        brd.add(rd);
        //Back+RollDice
        s[33]=new Square("Asylum Seeker Application rejected. Back to Train box (17) and Roll dice. ",33,B,brd);
        ///////////////////////////////////////////////
        
        ArrayList <Action> p=new ArrayList <Action>();
        PayMoneyAction pma=new PayMoneyAction();
        p.add(pma);
        
        //Squares that only Pay
        s[1]=new Square("Food for the journey. Pay $100.",1,B,p);
        s[3]=new Square("Communication gear. Mobile phone and sim card. Pay $300 to the Mafia Bank.",3,B,p);
        s[6]=new Square("Mafia. Pay 1000$. .",6,B,p);
        s[13]=new Square("Tent and sleeping bag. Pay 200$ to the Mafia Bank",13,B,p);
        s[21]=new Square("Theft. You lose 1500$. Place this money in the Players Expenses box.",21,B,p);
        s[37]=new Square("Mafia. Pay 1000$ to Mafia Bank.  ",37,B,p);
        ///////////////////////////////////////////////////////////
         ArrayList <Action> NA=new ArrayList <Action>();
         NoActionAction na=new NoActionAction();
         NA.add(na);
         
         s[0]=new Square("Country in conflict.",0,B,NA);
         ArrayList <Action> ST=new ArrayList <Action>();
        StayTurnAction sta=new StayTurnAction();
         ST.add(sta);
        //Squares that stay turn
       
        s[8]=new Square("GPS Location. Stay for a turn",8,B,ST);
        s[11]=new Square("You get sick. Stay one turn.",11,B,ST);
        s[14]=new Square("Border Police. Stay one turn. ",14,B,ST);
        s[19]=new Square("Guard Dogs. Stay one turn. ",19,B,ST);
        s[24]=new Square("Border Police. Stay one turn.",24,B,ST);
        s[27]=new Square("Storm. Stay one turn. ",27,B,ST);
        s[32]=new Square("Government Detention Camp. Stay one turn. ",32,B,ST);
        s[34]=new Square("Border Police. Stay one turn. ",34,B,ST);
        /////////////////////////////////////////////////////////
        
        ArrayList <Action> prd=new ArrayList <Action>();
        prd.add(pma);
        prd.add(rd);
        //Pay+RollDice
        s[9]=new Square("Boat. Pay 3000$ to the Mafia Bank. Roll dice",9,B,prd);
        s[16]=new Square("Refugee Camp. Pay 500$ to the Mafia bank to leave and roll dice. ",16,B,prd);
        s[31]=new Square("Mafia Bus. Pay 800$ to Mafia Bank. Roll dice. ",31,B,prd);
        ////////////////////////////////////////////////////////////////////////
        
        ArrayList <Action> d=new ArrayList <Action>();
        LoseAllAction laa=new LoseAllAction();
        d.add(laa);
        //Dead
        s[10]=new Square("Dead at Sea. You are dead and out of the game. ",10,B,d);
        ////////////////////////////////////////////
        
        ArrayList <Action> r=new ArrayList <Action>();
        ReceiveMoneyAction rma=new ReceiveMoneyAction();
        r.add(rma);
       // r.add(rd);
        //Receive
        s[20]=new Square("NGO Support. You receive 1000$ from NGO Bank.",20,B,r);
       ////////////////////////////////////////////////////
       
       ArrayList <Action> GBAction=new ArrayList <Action>();
       GBAction.add(rd);
       GoBackAction gba=new GoBackAction();
       GBAction.add(gba);
       //GBAction.add(rd);
       ////Goback
       s[22]=new Square("River Crossing. Roll dice and go backwards by the numberon the dice. ",22,B,GBAction);
       ArrayList <Action> A26=new ArrayList <Action>();
       Specifically26Action a26=new Specifically26Action();
       A26.add(a26);
       ///////////////weird
        s[26]=new Square("Asylum Paperwork. Pay 1000$ to Mafia Bank. Option A: Pay $1500 to Mafia Bank and roll dice. Option B: Don’t pay and stay 2 turns.",26,B,A26);
        ///////////////////////////////////////////////////////////////////
        
       ArrayList <Action> WAction=new ArrayList <Action>();
       WinAction wa=new WinAction();
       WAction.add(wa);
        //Win
        s[36]=new Square("Asylum Seeker Application Approved. You win. ",36,B,WAction);
        s[39]=new Square(" New Home. You are finally accepted. You win. . ",39,B,WAction);
       
        return s;
    }

}
