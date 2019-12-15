package GrainGrowth;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;


/// Main element of the application that presents the grains and simulation container
public class Canvas extends JComponent {
    
    private Grain[][] grainsArray;
    private final boolean b = false; 
    private int sizeX = Constants.boardWidth;
    private int sizeY = Constants.boardHeight;
    private int maximumX = Constants.boardWidth;
    private int maximumY = Constants.boardHeight;
    
    /// Value that indicates if edge should be hidden or not
    private boolean shouldShowEdge;

    public void changeShowEdge() {
        shouldShowEdge = !shouldShowEdge;
    }
       
    /// Initializes Canvas
    public Canvas(){
        grainsArray = new Grain[sizeX][sizeY];
        for(int i=0;i<sizeX;i++){
            for(int j=0;j<sizeY;j++){
                grainsArray[i][j] = new Grain();
            } 
        }
        shouldShowEdge = false;
    }
    
    /// Sets RGB color to the grains
    public void setRGB(Grain[][] grains) {
        for(int j=0;j<sizeY;j++){
            for(int i=0;i<sizeX;i++){
                    int R=0;
                    int G=0;
                    int B=0;
                    int grain = grains[i][j].getId();
                    
                    if(grain == 0) {
                        grains[i][j].setRGB(0, 0, 0);
                    } else if(grain == -1){
                        grains[i][j].setRGB(255,255,255);
                    } else if(grain == -2){
                        grains[i][j].setRGB(200,200,200);
                    } else if(grain == -3){
                        grains[i][j].setRGB(255,105,180);
                    } else {
                        switch(grain%3) {
                            case 0:
                                R=(grain*10+100)%254;
                                G=(grain*5+100)%254;
                                B=(grain*2+100)%254;
                                break;
                            case 1:
                                R=(grain*2+100)%254;
                                G=(grain*10+100)%254;
                                B=(grain*5+100)%254;
                                break;
                            case 2:
                                R=(grain*5+100)%254;
                                G=(grain*2+100)%254;
                                B=(grain*10+100)%254;
                                break;
                            default:
                                break;
                        }
                        grains[i][j].setRGB(R, G, B);
                    }
                    grains[i][j].setX(i);
                    grains[i][j].setY(j);
            }
        }
    }
        
    public void paint(Graphics g){
        for(int j=0;j<sizeY;j++) {
            for(int i=0;i<sizeX;i++) {
                if(grainsArray[i][j].getId() == 0 || (grainsArray[i][j].isBoundary() && shouldShowEdge) ){
                    g.setColor(Color.BLACK);
                } else{
                    g.setColor(new Color(grainsArray[i][j].getRColorParameter(),grainsArray[i][j].getGColorParameter(),grainsArray[i][j].getBColorParameter()));
                }
                g.fillRect(Math.round((maximumX/sizeX)*i),Math.round((maximumY/sizeY)*j),Math.round(maximumX/sizeX),Math.round(maximumY/sizeY));
            } 
        }
    }
        
    public void resizeBoard(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        this.maximumX = x;
        this.maximumY = y;
    }
    
    public void setGrains(Grain[][] grains){
        setRGB(grains);
        this.grainsArray = grains;
    }
}
