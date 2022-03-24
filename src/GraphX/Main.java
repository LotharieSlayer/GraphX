package GraphX;

import GraphX.utils.Lecture;
import GraphX.utils.Chemin;

public class Main {
    public static void main(String[] args) {
        Lecture lecture = new Lecture(args[0]);
        Chemin chemin = new Chemin(lecture.getLstArcsInt());

    }
}
