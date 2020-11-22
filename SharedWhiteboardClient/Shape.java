package NormalClient;

import java.io.Serializable;

public class Shape implements Serializable {

    public int type = 1;
    public int x1=0,y1=0,x2=0,y2=0,color = 0;
    public String text;

    public Shape(int type, int x1, int y1, int x2, int y2, int color, String text){
        this.type = type;
        this.x1 = x1;
        this.x2= x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.text = text;
    }
}
