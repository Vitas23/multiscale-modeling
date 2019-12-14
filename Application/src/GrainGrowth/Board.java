package GrainGrowth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Board {

    /// MARK: Variables
    private final int sizeX;
    private final int sizeY;
    private final  Grain[][] grainsArray;
    private final Grain[][] grainsTemporaryArray;
    private final int[][] temporaryBoardArray;
 
    private int countGrainsCristal = 0;
    private int countGrainsRecristal = 0;

    private int n;
    private final Random random = new Random();
    private boolean isPeriodic;
    private boolean shouldEndSimulation;

    private double ro = 0;
    private double roSr = 0;
    private double recrystalizationPercentage;
    private boolean contentGrains;
    private boolean isEdgeGenerated;

    public void changeContentGrains() {
        contentGrains = !contentGrains;
    }
   
    public void setRecrystalPercent(double recrystalPercent) {
        this.recrystalizationPercentage = recrystalPercent;
    }

    public int getCountGrainsRecristal() {
        return countGrainsRecristal;
    }

    public int getCountGrainsCristal() {
        return countGrainsCristal;
    }

    public boolean shouldEndSimulation() {
        return shouldEndSimulation;
    }
    
    boolean isEdgeGenerated() {
        return isEdgeGenerated;
    }

    public Board(int size_x, int size_y) {
        contentGrains = false;
        isEdgeGenerated = false;
        recrystalizationPercentage = 10;
        shouldEndSimulation = false;
        isPeriodic = false;
        this.sizeX = size_x;
        this.sizeY = size_y;
        n = 0;
        grainsArray = new Grain[size_x][size_y];
        grainsTemporaryArray = new Grain[size_x][size_y];
        temporaryBoardArray = new int[size_x][size_y];
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                grainsArray[i][j] = new Grain();
                grainsTemporaryArray[i][j] = new Grain();
            }
        }
    }

    public void changePeriodic() {
        isPeriodic = !isPeriodic;
    }

    public Grain[][] randomBoard(int ringSize, int countRing) {
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < countRing; i++) {
            int spr = 0;
            int randX = random.nextInt(sizeX);
            int randY = random.nextInt(sizeY);
            boolean findOk = false;
            while (spr < 100) {
                spr++;
                findOk = true;

                for (Point p : points) {
                    findOk = true;

                    if (!p.point2point(randX, randY, ringSize)) {
                        findOk = false;
                        randX = random.nextInt(sizeX);
                        randY = random.nextInt(sizeY);
                        break;
                    }
                }
                if (findOk) {
                    n++;
                    points.add(new Point(randX, randY, 0, n));
                    break;
                }
            }
        }
        for (Point p : points) {
            grainsArray[p.getX()][p.getY()].setId(p.getId());
        }

        countGrainsCristal = n;
        return grainsArray;
    }

    public Grain[][] clear() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grainsArray[i][j].setId(0);
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 1; j < sizeY; j++) {
                grainsArray[i][j].setB(false);
            }
        }
        n = 0;
        isEdgeGenerated = false;
        return grainsArray;
    }

    public Grain[][] edge() {
        for (int i = 1; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grainsArray[i][j].getId() != grainsArray[i - 1][j].getId() && !grainsArray[i][j].isBoundary()) {
                    grainsArray[i][j].setB(true);
                }
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int j = 1; j < sizeY; j++) {
                if (grainsArray[i][j].getId() != grainsArray[i][j - 1].getId() && !grainsArray[i][j].isBoundary()) {
                    grainsArray[i][j].setB(true);
                }
            }
        }
        isEdgeGenerated = true;
        return grainsArray;
    }

    public Grain[][] addGrain(int i, int j) {
        n++;
        grainsArray[i][j].setId(n);
        countGrainsCristal = n;
        return grainsArray;
    }

    public int recrystal() {
        int sum = 0;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grainsArray[i][j].isR()) {
                    sum++;
                }
            }
        }
        return sum;
    }
    
    public ArrayList<Grain> getGrainsOnBorder(){
        ArrayList<Grain> grainList = new ArrayList();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if(grainsArray[i][j].isBoundary())
                    grainList.add(grainsArray[i][j]);
            }
        }
        return grainList;
    }

    public int ammountOfNotEmptyCells() {
        int sum = 0;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grainsArray[i][j].getId() != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public void clearRecrystal() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grainsArray[i][j].setR(false);
                grainsArray[i][j].setRo(0);
            }
        }
        countGrainsRecristal = 0;
    }
    
    /// One thread calculation
    public Grain[][] calculate(int neighborhoodType, int r) {
        shouldEndSimulation = true;
        int tmp[][] = new int[3][3];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                temporaryBoardArray[i][j] = grainsArray[i][j].getId();
            }
        }

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grainsArray[i][j].getId() == 0) { // if empty cell
                    shouldEndSimulation = false;
                    tmp = createArea(i, j, neighborhoodType);
                    temporaryBoardArray[i][j] = checkNeighborhood(tmp);
                }
            }
        }

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grainsArray[i][j].setId(temporaryBoardArray[i][j]);
            }
        }
        return grainsArray;
    }

    /// Recristalization calculation
    public Grain[][] recristalizationCalculate(int areaSetup, double dT) {
        return grainsArray;
    }
        
    private int[][] createArea(int i, int j, int neighborhoodType) {
        int tmp[][] = new int[3][3];

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int l_x = (sizeX + (i - 1 + k)) % sizeX;
                int l_y = (sizeY + (j - 1 + l)) % sizeY;
                tmp[k][l] = grainsArray[l_x][l_y].getId();
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

            switch (neighborhoodType) {
            case 0: //moor
            {
                break;
            }
            case 1: //neumann
            {
                tmp[0][0] = 0;
                tmp[2][0] = 0;
                tmp[0][2] = 0;
                tmp[2][2] = 0;
                break;
            }
            case 2: //hex L
            {
                tmp[0][2] = 0;
                tmp[2][0] = 0;
                break;
            }
            case 3: //hex P
            {
                tmp[0][0] = 0;
                tmp[2][2] = 0;
                break;
            }
            case 4: //hex R
            {
                if (random.nextBoolean()) {
                    tmp[0][2] = 0;
                    tmp[2][0] = 0;
                } else {
                    tmp[0][0] = 0;
                    tmp[2][2] = 0;
                }
                break;
            }
            case 5: //pen L
            {
                int randPent = random.nextInt(4);
                if (randPent == 0) {
                    for (int k = 0; k < 3; k++) {
                        tmp[0][k] = 0;
                    }
                } else if (randPent == 1) {
                    for (int k = 0; k < 3; k++) {
                        tmp[k][0] = 0;
                    }
                } else if (randPent == 2) {
                    for (int k = 0; k < 3; k++) {
                        tmp[2][k] = 0;
                    }
                } else {
                    for (int k = 0; k < 3; k++) {
                        tmp[k][2] = 0;
                    }
                }
                break;
            }
            case 7:
            {
                tmp[0][1] = 0;
                tmp[1][0] = 0;
                tmp[1][2] = 0;
                tmp[2][1] = 0;
                break;
            }
            default:
                break;
        }
        return tmp;
    }
    
    // Checks neighborhood and return ID of the seed that dominate in this area 
    private int checkNeighborhood(int[][] tab) {  
        List<Integer> list = new ArrayList<Integer>(); 
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] != 0 && tab[i][j] != -1)
                list.add(tab[i][j]);
            }
        }
        
        return list.isEmpty() ? 0 : mostCommon(list);
    }
    
    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

            Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }
    
    private HashSet<Integer> getUniqueIdsFromNeighborhood(int tmp[][]) {
        HashSet<Integer> uniqueIds = new HashSet<>();
        for(int i =0;i<3;i++)
            for(int j =0;j<3;j++)
                if(tmp[i][j] != 0) uniqueIds.add(tmp[i][j]);
        return uniqueIds;      
    }
    
    private int countOccurrence(int id, int tmp[][]) {
        int count = 0;
        for(int i =0;i<3;i++)
            for(int j =0;j<3;j++)
                if (id == tmp[i][j]) count++;
        
        return count;
    }
        
//    private int extendedMoorArea(int x, int y) {
//        int tmp[][] = new int[3][3];
//        HashSet<Integer> uniqueIds = new HashSet<>();
//        
//        /// Applying rules
//        LinkedHashMap<Integer, Integer> configurations = new LinkedHashMap<Integer, Integer>(){{put(0,5); put(1,3); put(7,3);}};
//        Set<Map.Entry<Integer,Integer>> configurationsSet = configurations.entrySet(); 
//
//        for (Map.Entry<Integer, Integer> it : configurationsSet){
//            tmp = createArea(x ,y ,it.getKey());
//            uniqueIds = getUniqueIdsFromNeighborhood(tmp);
//            for(Integer id : uniqueIds)
//                 if (countOccurrence(id, tmp) > it.getValue()) return id;
//        }
//        
//        tmp = createArea(x ,y ,0);
//        
//        /// Applying propability
//        if(random.nextInt(100)> 80) return checkNeighborhood(tmp);
//        else return 0;
//    }
}