package GrainGrowth;

public class Grain {

    /// Id of the grain
    private int id = 0;
    
    private double ro = 0;
    
    /// Variable indicating if it is boundary or not
    private boolean isBoundary = false;
    
    /// Variable indicating if it is boundary or not
    private boolean isBlocked = false;
    
    private boolean r = false;
    
    /// Red color, part of RGB color palette
    private int R;
    
    /// Green Color, part of RGB color palette
    private int G;
    
    /// Blue color, part of RGB color palette
    private int B;
    
    /// X position of the grain
    private int x;
    
    /// Y position of the grain
    private int y;

    /// - MARK: Setters and Getters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
   
    public int getId() {
        return id;
    }

    public double getRo() {
        return ro;
    }

    public void setRo(double ro) {
        this.ro = ro;
    }

    public boolean isBoundary() {
        return isBoundary;
    }

    public void setB(boolean isBoundary) {
        this.isBoundary = isBoundary;
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }
    
    public void addRo(double ro){
        this.ro +=ro;
    }
    
    public void setRGB(int R, int G, int B) {
      this.R = R;
      this.G = G;
      this.B = B;
    }
    
    public int getRColorParameter() {
        return R;
    }
    
    public int getGColorParameter() {
        return G;
    }
    
    public int getBColorParameter() {
        return B;
    }
}
