package edu.rutgers.chess.game.match;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Match {
    public static ArrayList<Match> allMatches;
    private Stack<String> allMoves;
    private Queue<String> queueMoves;
    private  String name;

    public Match(String name){
        this.name = name;
        allMoves = new Stack<>();
        queueMoves = new LinkedList<>();
        allMatches.add(this);
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Stack getAllMoves(){
        return allMoves;
    }

    public Queue getQueueMoves(){
        return queueMoves;
    }

    public void addMove(String move){
        allMoves.push(move);
        queueMoves.add(move);
    }

    public void undoMove(){
        String move = allMoves.pop();
        queueMoves.remove(move);
    }

}
