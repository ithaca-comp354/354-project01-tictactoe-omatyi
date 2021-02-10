package edu.ithaca.dragon.games.tictactoe.player;
import javax.crypto.Cipher;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;


public class RuleBasedAgentOwen implements TicTacToePlayer {
    /**
 * For some simple rules to consider (from section 1.2 here):
    Rule 1: If I have a winning move, take it.
    Rule 2: If the opponent has a winning move, block it.
    Rule 3: If I can create a fork (two winning ways) after this move, do it.
    Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
    Rule 5: Place in the position such as I may win in the most number of possible ways.

 */ 
    private int[][] preferredMoves = {
        {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
        {0, 1}, {1, 0}, {1, 2}, {2, 1}};
    @Override

    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        char oppSymbol = 'X';
        if(yourSymbol == 'X'){
            oppSymbol = 'O';
        }
        return ruleOne(curBoard, yourSymbol, oppSymbol);
    }
    /**
        Pair<Integer,Integer> currentPos = new Pair<>(x,y);
                if (curBoard.isSquareOpen(currentPos)){
                    TicTacToeBoard tempBoard = tryMove(curBoard, currentPos, yourSymbol);
                    if(tempBoard.checkForWin(yourSymbol)){
                        return currentPos;
                    }
                    else{
                        return ruleTwo(curBoard, currentPos, yourSymbol);
                    }
                }
     */
    
    public Pair<Integer,Integer> ruleOne(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        return hWinTop(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> hWinTop(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,0))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,0))){
                return new Pair<>(2,0);
            }
            else if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,0))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,0))){
                return new Pair<>(1,0);
            }
            else if(curBoard.checkSquare(new Pair<>(1,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,0))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,0))){
                return new Pair<>(0,0);
            } 
         }
         return hWinMid(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> hWinMid(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(0,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,1))){
                return new Pair<>(2,1);
            }
            else if (curBoard.checkSquare(new Pair<>(0,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
                return new Pair<>(1,1);
            }
            else if(curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,1))){
                return new Pair<>(0,1);
            }
        }
        return hWinBot(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> hWinBot(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(0,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,2))){
                return new Pair<>(2,2);
            }
            else if (curBoard.checkSquare(new Pair<>(0,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,2))){
                return new Pair<>(1,2);
            }
            else if(curBoard.checkSquare(new Pair<>(1,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,2))){
                return new Pair<>(0,2);
            }
            
        }
        return vWinLeft(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> vWinLeft(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,2))){
                return new Pair<>(0,2);
            }
            else if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,1))){
                return new Pair<>(0,1);
            }
            else if(curBoard.checkSquare(new Pair<>(0,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,0))){
                return new Pair<>(0,0);
            } 
         }
         return vWinMid(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> vWinMid(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(1,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,2))){
                return new Pair<>(1,2);
            }
            else if (curBoard.checkSquare(new Pair<>(1,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
                return new Pair<>(1,1);
            }
            else if(curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,0))){
                return new Pair<>(1,0);
            }
        }
        return vWinRight(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> vWinRight(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,2))){
                return new Pair<>(2,2);
            }
            else if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,1))){
                return new Pair<>(2,1);
            }
            else if(curBoard.checkSquare(new Pair<>(2,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,0))){
                return new Pair<>(2,0);
            }
        }
        return dWinLeft(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> dWinLeft(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,2))){
                return new Pair<>(2,2);
            }
            else if (curBoard.checkSquare(new Pair<>(0,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
                return new Pair<>(1,1);
            }
            else if(curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(0,0))){
                return new Pair<>(0,0);
            }
            
        }
        return dWinRight(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> dWinRight(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(1,1))==yourSymbol&& curBoard.isSquareOpen(new Pair<>(0,2))){
                return new Pair<>(0,2);
            }
            else if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(1,1))){
                return new Pair<>(1,1);
            }
            else if(curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol && curBoard.isSquareOpen(new Pair<>(2,0))){
                return new Pair<>(2,0);
            }
        }
        if(yourSymbol == oppSymbol){
            return ruleThree(curBoard, yourSymbol,oppSymbol);
        }
        else{
            return ruleTwo(curBoard, yourSymbol,oppSymbol);
        }
        
    }
    
/**
 * public TicTacToeBoard tryMove(TicTacToeBoard curBoard, Pair<Integer,Integer> position, char symbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
        tempBoard.setSquare(position, symbol);
        tempBoard.calcGameStatus();
        return tempBoard;
    }
 */
    
    public Pair<Integer, Integer> ruleFive(TicTacToeBoard curBoard, char yourSymbol){
        if(curBoard.isSquareOpen(new Pair<>(1,1))){
            return new Pair<Integer,Integer>(1,1);
        }
        else if(curBoard.isSquareOpen(new Pair<>(0,0))){
            return new Pair<Integer,Integer>(0,0);
        }
        else if(curBoard.isSquareOpen(new Pair<>(2,0))){
            return new Pair<Integer,Integer>(2,0);
        }
        else if(curBoard.isSquareOpen(new Pair<>(0,2))){
            return new Pair<Integer,Integer>(0,2);
        }
        else if(curBoard.isSquareOpen(new Pair<>(2,2))){
            return new Pair<Integer,Integer>(2,2);
        }
        else{
            throw new IllegalArgumentException("Board with no moves given to player:\n" + curBoard.displayString());
        }
    }
    public Pair<Integer, Integer> ruleFour(TicTacToeBoard curBoard,  char yourSymbol,char oppSymbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
        Pair<Integer,Integer> tempPair;
        
        
        return triFork(curBoard, oppSymbol, oppSymbol);
        /**
         * Pair<Integer, Integer> currentPos = position;
        char opSymbol = yourSymbol == 'X' ? 'O': 'X';
        Pair<Integer, Integer> canidatePos = chooseSquare(tempBoard, opSymbol);
        tempBoard.setSquare(canidatePos, opSymbol);
        tempBoard.calcGameStatus();
        if(tempBoard.checkForWin(opSymbol)){
            return canidatePos;
        }
        else{
            return ruleFive(curBoard, yourSymbol);
        }
         */
        
    }
    public Pair<Integer,Integer> ruleThree(TicTacToeBoard curBoard,  char yourSymbol,char oppSymbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
        Pair<Integer,Integer> tempPair;
        

        return triFork(curBoard, yourSymbol, oppSymbol);
        /**
         * TicTacToeBoard tempBoard = curBoard.copyBoard();
        Pair<Integer, Integer> currentPos = position;
        Pair<Integer, Integer> canidatePos = chooseSquare(tempBoard, yourSymbol);
        tempBoard.setSquare(canidatePos, yourSymbol);
        tempBoard.calcGameStatus();
        if(tempBoard.checkForWin(yourSymbol)){
            return currentPos;
        }
        else{
            return ruleFour(curBoard,currentPos, yourSymbol);
        }
         */
        
    }
    public Pair<Integer,Integer> triFork(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            //left
            if (curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol){
                return new Pair<>(0,0);
            }
            //up
            else if (curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,0))==yourSymbol){
                return new Pair<>(0,0);
            }
            //right
            else if (curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,2))==yourSymbol){
                return new Pair<>(2,0);
            }
            //down
            else if (curBoard.checkSquare(new Pair<>(1,1)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol){
                return new Pair<>(2,2);
            }
        }
        return arrFork(curBoard, yourSymbol,oppSymbol);
    }
    
    public Pair<Integer,Integer> arrFork(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            //Top Left
            if (curBoard.checkSquare(new Pair<>(1,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,1))==yourSymbol){
                return new Pair<>(0,0);
            }
            //Top Right
            else if (curBoard.checkSquare(new Pair<>(1,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,1))==yourSymbol){
                return new Pair<>(2,0);
            }
            //Bottom Right
            else if (curBoard.checkSquare(new Pair<>(1,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(2,1))==yourSymbol){
                return new Pair<>(2,2);
            }
            //Bottom Left
            else if (curBoard.checkSquare(new Pair<>(1,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,1))==yourSymbol){
                return new Pair<>(0,2);
            }
            
        }
        return cirForkL(curBoard, yourSymbol,oppSymbol);
    }
    public Pair<Integer,Integer> cirForkL(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            //Top Left
            if (curBoard.checkSquare(new Pair<>(2,0)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,2))==yourSymbol){
                if(curBoard.isSquareOpen(new Pair<>(2,0))){
                    return new Pair<>(0,0);
                }
                else{
                    return new Pair<>(2,2);
                }
            }
        }
        return cirForkR(curBoard, yourSymbol, oppSymbol);
    }
    public Pair<Integer,Integer> cirForkR(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        for(int i = 0; i<3;i++){
            
            // Right
            if (curBoard.checkSquare(new Pair<>(2,2)) == yourSymbol && curBoard.checkSquare(new Pair<>(0,0))==yourSymbol){
                if(curBoard.isSquareOpen(new Pair<>(2,0))){
                    return new Pair<>(2,0);
                }
                else{
                    return new Pair<>(0,2);
                }
                
            }
            
        }
        if(yourSymbol == oppSymbol){
            if(yourSymbol == 'X'){
                return ruleFive(curBoard, 'O');
            }
            else{
                return ruleFive(curBoard, 'X');
            }
        }
        else{
            return ruleFour(curBoard, yourSymbol,oppSymbol);
        }
    }
    
        
        
    
    public Pair<Integer,Integer> ruleTwo(TicTacToeBoard curBoard, char yourSymbol,char oppSymbol){
        
        return hWinTop(curBoard, oppSymbol,oppSymbol);
    }   
}

    

