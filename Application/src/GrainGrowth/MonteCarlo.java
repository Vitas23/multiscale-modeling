package GrainGrowth;

import java.util.ArrayList;
import java.util.Random;
import GrainGrowth.Point;


/// Monte Carlo class will be used in the future
public class MonteCarlo {

    private final int sizeX;
    private final int sizeY;
    private Grain boardGarinsArray[][];
    private int n;
    private Random random;
    private boolean isPeriodic;
    ArrayList<Point> grains;
    private int changed;

    public MonteCarlo(int sizeX, int sizeY, Grain[][] boardGrainsArray) {
        this.boardGarinsArray = new Grain[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                this.boardGarinsArray[i][j] = new Grain();
            }
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.boardGarinsArray = boardGrainsArray;
        random = new Random();
        grains = new ArrayList<Point>();
    }

    public int getChanged() {
        return changed;
    }
    
    public void changePerio() {
        this.isPeriodic = !isPeriodic;
    }

    /// Initializes Monte Carlo with default parameters
    public MonteCarlo() {
        grains = new ArrayList<Point>();
        isPeriodic = false;
        random = new Random();
        this.sizeX = Constants.boardWidth;
        this.sizeY = Constants.boardHeight;
        this.n = 50;
        boardGarinsArray = new Grain[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                boardGarinsArray[i][j] = new Grain();
            }
        }
    }
    
    /// Initializes Monte Carlo with given sizeX, sizeY and count
    public MonteCarlo(int sizeX, int sizeY, int n) {
        grains = new ArrayList<Point>();
        isPeriodic = false;
        random = new Random();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.n = n;
        boardGarinsArray = new Grain[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                boardGarinsArray[i][j] = new Grain();
            }
        }
    }

    public Grain[][] randomBoard() {
        if (n == 0) {

            ArrayList<Point> tmp = new ArrayList<Point>();
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    tmp.add(new Point(i, j, 0, 0));
                }
            }

            for (int i = 0; i < sizeX * sizeY; i++) {
                int rand_id = random.nextInt(tmp.size());
                int rand_x = tmp.get(rand_id).getX();
                int rand_y = tmp.get(rand_id).getY();;
                boardGarinsArray[rand_x][rand_y].setId(i + 1);
                tmp.remove(rand_id);
            }

        } else {
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    boardGarinsArray[i][j].setId(random.nextInt(n) * 7 + 1);
                }
            }
        }

        return boardGarinsArray;
    }

    public Grain[][] calculate() {
        changed = 0;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grains.add(new Point(i, j, 0, boardGarinsArray[i][j].getId()));
            }
        }

        int id = 0;
        int randGrain = 0;
        int[][] temporaryArray = new int[3][3];
        int[][] array = new int[3][3];
        int randomArea = 0;
        int power = 0;

        while (id < sizeX * sizeY) {
            randGrain = random.nextInt(grains.size());
            array = createArea(grains.get(randGrain).getX(), grains.get(randGrain).getY());
            power = power(array);

            if (power > 0) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temporaryArray[i][j] = array[i][j];
                    }
                }

                randomArea = getRandomNeighbor(array);

                temporaryArray[1][1] = randomArea;

                int power_tmp = power(temporaryArray);

                if (power_tmp <= power) {
                    boardGarinsArray[grains.get(randGrain).getX()][grains.get(randGrain).getY()].setId(randomArea);
                    changed++;
                }
            }
            grains.remove(randGrain);
            id++;
        }

        return boardGarinsArray;
    }

    private int[][] createArea(int i, int j) {
        int tmp[][] = new int[3][3];

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int l_x = (sizeX + (i - 1 + k)) % sizeX;
                int l_y = (sizeY + (j - 1 + l)) % sizeY;
                tmp[k][l] = boardGarinsArray[l_x][l_y].getId();
            }
        }

        if (!isPeriodic) {
            if (i == 0) {
                for (int k = 0; k < 3; k++) {
                    tmp[0][k] = 0;
                }
            }
            if (j == 0) {
                for (int k = 0; k < 3; k++) {
                    tmp[k][0] = 0;
                }
            }
            if (i == sizeX - 1) {
                for (int k = 0; k < 3; k++) {
                    tmp[2][k] = 0;
                }
            }
            if (j == sizeY - 1) {
                for (int k = 0; k < 3; k++) {
                    tmp[k][2] = 0;
                }
            }
        }
        return tmp;
    }

    private int power(int[][] tab) {
        int power = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] != tab[1][1]) {
                    power++;
                }
            }
        }
        return power;
    }

    private int getRandomNeighbor(int[][] tab) {
        ArrayList<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(tab[i][j] == 0 || (i == 1 && j == 1))) {
                    tmp.add(new Point(i, j, 0, tab[i][j]));
                }
            }
        }
        return tmp.get(random.nextInt(tmp.size())).getId();
    }

}
