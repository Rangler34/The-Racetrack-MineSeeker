package ca.cmpt276.as3.Model;

/**
 * GameLogic class including the singleton method, used for
 * storing the data of the game such as
 * the board size and the amount of logos (mines)
 */
public class GameLogic {

    private static GameLogic instance;
    private int rows;
    private int columns;
    private int numLogos;
    private int logosFound;

    private GameLogic(){

    }
    public static GameLogic getInstance() {
        if(instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    public int getLogosFound() {
        return logosFound;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumLogos() {
        return numLogos;
    }

    public void findRowsAndCols(String sizeGame){
        if(sizeGame.equals("4 rows x 6 columns")){
            this.rows = 4;
            this.columns = 6;
        }
        if(sizeGame.equals("5 rows x 10 columns")){
            this.rows = 5;
            this.columns = 10;
        }
        if(sizeGame.equals("6 rows x 15 columns")){
            this.rows = 6;
            this.columns = 15;
        }
    }

    public void findNumLogos(int amountOfLogos){
        switch(amountOfLogos){
            case 6:
                this.numLogos = 6;
                break;
            case 10:
                this.numLogos = 10;
                break;
            case 15:
                this.numLogos = 15;
                break;
            case 20:
                this.numLogos = 20;
                break;
        }
    }
}
