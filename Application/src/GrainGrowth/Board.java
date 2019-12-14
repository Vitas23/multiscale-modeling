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

    private final double reA = 86710969050178.5;
    private final double reB = 9.41268203527779;
    private final double roMax = 28105600.95;
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

    public Grain[][] randomBoard(int randomSetup, int countX, int countY, int randomSize, int ringSize, int countRing) {
        switch (randomSetup) {
            case 0:

                for (int i = 0; i < sizeX; i++) {
                    for (int j = 0; j < sizeY; j++) {
                        if (random.nextInt(1000) > 998) {
                            n++;
                            grainsArray[i][j].setId(n);
                        }
                    }
                }

                break;
            case 1:

                for (int i = countX; i < sizeX; i += countX) {
                    for (int j = countY; j < sizeY; j += countY) {
                        n++;
                        grainsArray[i][j].setId(n);
                    }
                }

                break;
            case 2:

                for (int i = 0; i < randomSize; i++) {
                    n++;
                    grainsArray[random.nextInt(sizeX)][random.nextInt(sizeY)].setId(n);
                }

                break;
            case 3:

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

                break;
            default:
                break;
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
                if (grainsArray[i][j].getId() == 0) {
                    shouldEndSimulation = false;
                    if (neighborhoodType == 7) {
                        temporaryBoardArray[i][j] = randomArea(i, j, r);
                    } 
                    else if (neighborhoodType == 8)
                    {
                        temporaryBoardArray[i][j] = extendedMoorArea(i,j);
                    }
                    else {
                        tmp = createArea(i, j, neighborhoodType, false);
                        temporaryBoardArray[i][j] = checkNeighborhood(tmp);
                    }
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
        shouldEndSimulation = true;
        double suma = 0;
        ro = reA / reB + (1 - (reA / reB)) * Math.exp(-1 * reB * dT);
        System.out.print(String.format("%.12f   ",ro));
        roSr = ro / (sizeX * sizeY);

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                
                if ( (grainsArray[i][j].isBoundary() || contentGrains ) && !grainsArray[i][j].isR()) {
                    if (random.nextDouble() > (1 - recrystalizationPercentage/100)) { // zwiększa ro tylko % ziaren
                        double add = roSr * (1.2 + random.nextDouble() * 0.6);
                        grainsArray[i][j].addRo(add);
                        shouldEndSimulation = false;
                    }
                    if (grainsArray[i][j].getRo() > roMax) {
                        n++;
                        grainsArray[i][j].setId(n);
                        grainsArray[i][j].setB(false);
                        grainsArray[i][j].setR(true);
                        grainsArray[i][j].setRo(0);
                        countGrainsRecristal++;
                        shouldEndSimulation = false;
                    }
                } else {
                    double add = roSr * (random.nextDouble() * 0.3);
                    grainsArray[i][j].addRo(add);
                }
            }
        }
        

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                grainsTemporaryArray[i][j].setId(grainsArray[i][j].getId());
            }
        }

        int tmp[][] = new int[3][3];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (!grainsArray[i][j].isR()){
                    if (areaSetup == 7) {
                        tmp = createArea(i, j, 6, true);
                        temporaryBoardArray[i][j] = checkNeighborhood(tmp);
                    } else {
                        tmp = createArea(i, j, areaSetup, true);
                        temporaryBoardArray[i][j] = checkNeighborhood(tmp);
                    }
                    grainsTemporaryArray[i][j].setId(temporaryBoardArray[i][j]);
                }
            }
        }

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (grainsTemporaryArray[i][j].getId() > 0) {

                    shouldEndSimulation = false;
                    grainsArray[i][j].setId(grainsTemporaryArray[i][j].getId());
                    grainsArray[i][j].setB(false);
                    grainsArray[i][j].setR(true);
                    grainsArray[i][j].setRo(0);
                }
            }
        }
        
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                suma += grainsArray[i][j].getRo();
            }
        }
        System.out.println(String.format("%.12f",suma));
        
        return grainsArray;

    }
    
    private int randomArea(int x, int y, int r) {
        int tmp[][];
        tmp = new int[2 * r + 1][2 * r + 1];

        for (int i = 0; i < 2 * r + 1; i++) {
            for (int j = 0; j < 2 * r + 1; j++) {
                float r_tmp = (float) (Math.sqrt(Math.pow(r - i, 2) + Math.pow(r - j, 2)));
                if (r < r_tmp) {
                    tmp[i][j] = 0;
                } else {
                    int xl = (x - r + i) % sizeX;
                    xl = xl < 0 ? sizeX - 1 : xl;

                    int yl = (y - r + j) % sizeY;
                    yl = yl < 0 ? sizeY - 1 : yl;

                    if (isPeriodic) {
                        tmp[i][j] = grainsArray[xl][yl].getId();
                    } else {
                        if ((x - r + i) >= 0 && (x - r + i) < sizeX && (y - r + j) >= 0 && (y - r + j) < sizeY) {
                            tmp[i][j] = grainsArray[xl][yl].getId();
                        } else {
                            tmp[i][j] = 0;
                        }
                    }

                }
            }
        }

        ArrayList<Point> areas = new ArrayList<Point>();
        ArrayList<Point> maxAreas = new ArrayList<Point>();
        int max = 0;
        boolean find = false;

        for (int i = 0; i < 2 * r + 1; i++) {
            for (int j = 0; j < 2 * r + 1; j++) {
                if (tmp[i][j] != 0) {

                    find = false;
                    for (int l = 0; l < areas.size(); l++) {
                        if (tmp[i][j] == areas.get(l).getId()) {
                            areas.get(l).increaseValue();
                            find = true;
                            max = max < areas.get(l).getValue() ? areas.get(l).getValue() : max;
                            break;
                        }
                    }

                    if (!find) {
                        areas.add(new Point(i, j, 1, tmp[i][j]));
                        max = max < 1 ? 1 : max;
                    }
                }
            }
        }

        if (max == 0) {
            return 0;
        } else {
            for (int l = 0; l < areas.size(); l++) {
                if (max == areas.get(l).getValue()) {
                    maxAreas.add(areas.get(l));
                }
            }
            return maxAreas.get(new Random().nextInt(maxAreas.size())).getId();
        }

    }
    
    private int[][] createArea(int i, int j, int neighborhoodType, boolean isRecrystalization) {
        int tmp[][] = new int[3][3];

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                int l_x = (sizeX + (i - 1 + k)) % sizeX;
                int l_y = (sizeY + (j - 1 + l)) % sizeY;
                if (isRecrystalization) {
                    if (grainsArray[l_x][l_y].isR()) {
                        tmp[k][l] = grainsArray[l_x][l_y].getId();
                    } else {
                        tmp[k][l] = 0;
                    }
                } else {
                    tmp[k][l] = grainsArray[l_x][l_y].getId();
                }
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

        int currentNeighborhood = neighborhoodType;
        boolean isRandom = true;
        while (isRandom) {
            switch (currentNeighborhood) {
                case 0: //moor
                {
                    isRandom = false;
                    break;
                }
                case 1: //neumann
                {
                    tmp[0][0] = 0;
                    tmp[2][0] = 0;
                    tmp[0][2] = 0;
                    tmp[2][2] = 0;
                    isRandom = false;
                    break;
                }
                case 2: //hex L
                {
                    tmp[0][2] = 0;
                    tmp[2][0] = 0;
                    isRandom = false;
                    break;
                }
                case 3: //hex P
                {
                    tmp[0][0] = 0;
                    tmp[2][2] = 0;
                    isRandom = false;
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
                    isRandom = false;
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
                    isRandom = false;
                    break;
                }
                case 6: //rand
                {
                    currentNeighborhood = random.nextInt(5);
                    break;
                }
                case 7: //for moor extended
                {
                    tmp[0][1] = 0;
                    tmp[1][0] = 0;
                    tmp[1][2] = 0;
                    tmp[2][1] = 0;
                    isRandom = false;
                    break;
                }
                default:
                    isRandom = false;
                    break;
            }
        }
        return tmp;
    }
    
    // Checks neighborhood and return ID of the seed that dominate in this area 
    // Checks neighborhood and return ID of the seed that dominate in this area 
    private int checkNeighborhood(int[][] tab) {  
        List<Integer> list = new ArrayList<Integer>(); 
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] != 0 && tab[i][j] != -1)
                list.add(tab[i][j]);
            }
        }
        if( list.size() != 0) {
            return mostCommon(list);
        } else {
            return 0;
        }
        
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
        
    private int extendedMoorArea(int x, int y) {
        int tmp[][] = new int[3][3];
        HashSet<Integer> uniqueIds = new HashSet<>();
        
        /// Applying rules
        LinkedHashMap<Integer, Integer> configurations = new LinkedHashMap<Integer, Integer>(){{put(0,5); put(1,3); put(7,3);}};
        Set<Map.Entry<Integer,Integer>> configurationsSet = configurations.entrySet(); 

        for (Map.Entry<Integer, Integer> it : configurationsSet){
            tmp = createArea(x ,y ,it.getKey(), false);
            uniqueIds = getUniqueIdsFromNeighborhood(tmp);
            for(Integer id : uniqueIds)
                 if (countOccurrence(id, tmp) > it.getValue()) return id;
        }
        
        tmp = createArea(x ,y ,0, false);
        
        /// Applying propability
        if(random.nextInt(100)> 80) return checkNeighborhood(tmp);
        else return 0;
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
}