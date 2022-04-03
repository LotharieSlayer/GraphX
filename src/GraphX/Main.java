package GraphX;

import GraphX.utils.Lecture;

import java.util.ArrayList;

import GraphX.utils.Chemin;

public class Main {

    private static ArrayList<int[]> arcs = new ArrayList<>();
    private static Main instance;

    public Main(){
        instance=this;
    }

    public static void main(String[] args) {
        Lecture lecture = new Lecture(args[0], instance);
        Chemin chemin = new Chemin(arcs);

    }

    public static void setLstArcsInt(ArrayList<int[]> lstArcsInt){
        arcs = lstArcsInt;
    }
}