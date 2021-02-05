package edu.ithaca.dragon.games.tictactoe.player;
import javax.crypto.Cipher;

import org.javatuples.Pair;

import edu.ithaca.dragon.games.tictactoe.board.TicTacToeBoard;


public class ExpertPlayer implements TicTacToePlayer {
    /**
 * For some simple rules to consider (from section 1.2 here):
    Rule 1: If I have a winning move, take it.
    Rule 2: If the opponent has a winning move, block it.
    Rule 3: If I can create a fork (two winning ways) after this move, do it.
    Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
    Rule 5: Place in the position such as I may win in the most number of possible ways.

 */
    @Override
    
    public Pair<Integer, Integer> chooseSquare(TicTacToeBoard curBoard, char yourSymbol) {
        char opSymbol = yourSymbol == 'X' ? 'O': 'X';
        for (int y=0; y<3; y++){
            for(int x=0; x<3;x++){
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
            }
        }
        return ruleFive(curBoard, yourSymbol);
    }
    
    
    
    public TicTacToeBoard tryMove(TicTacToeBoard curBoard, Pair<Integer,Integer> position, char symbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
        tempBoard.setSquare(position, symbol);
        tempBoard.calcGameStatus();
        return tempBoard;
    }
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
            System.out.print("Lol wut how did you get this?");
            return new Pair<Integer,Integer>(0,0);
        }
    }
    public Pair<Integer, Integer> ruleFour(TicTacToeBoard curBoard,Pair<Integer, Integer> position,  char yourSymbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
        Pair<Integer, Integer> currentPos = position;
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
    }
    public Pair<Integer,Integer> ruleThree(TicTacToeBoard curBoard,Pair<Integer, Integer> position,  char yourSymbol){
        TicTacToeBoard tempBoard = curBoard.copyBoard();
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
    }
    public Pair<Integer,Integer> ruleTwo(TicTacToeBoard curBoard,Pair<Integer, Integer> position,  char yourSymbol){
        char opSymbol = yourSymbol == 'X' ? 'O': 'X';
        Pair<Integer, Integer> currentPos = position;
        TicTacToeBoard tempBoard = tryMove(curBoard, currentPos, opSymbol)
        if(tempBoard.checkForWin(opSymbol)){
            return currentPos;
        }
        else{
            return ruleThree(curBoard,currentPos, yourSymbol);
            
    }
}}

    

