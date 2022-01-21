import java.util.*;
import java.util.stream.Collectors;


public class Search {

    private HashMap<String, Cell> graph;

    private int column;
    private int row;
    //public String col;

    public void initialize(int columns, int rows, ArrayList<String> colors) {
        this.column = columns;
        this.row = rows;
        this.graph = new HashMap<>();
        Random random = new Random();
        
        int count = 0;
        for (int x = 0; x< row; x++) {
            for (int y = 0; y< column; y++) {
            	this.graph.put(x+"_"+y, new Cell(count++, x, y, colors.get(random.nextInt(colors.size())).charAt(0)));
            }
        }
    }

    public Cell getCell(int x, int y) {
        return this.graph.get(x+"_"+y);
    }

    public void displayGrid() {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < column; x++) {
                if(x == this.column - 1 )
                    System.out.println(getCell(y, x).getColor());
                else
                    System.out.print(getCell(y, x).getColor() + " ");
            }
        }
    }

    public void displayGridWithBlocks(Color block) {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < column; x++) {
                Cell n = getCell(y, x);
                String color = block.hasCell(n) ? "*" : String.valueOf(n.getColor());
           
                if(x == this.column - 1 ) {
                    System.out.println(color);
                } else {
                    System.out.print(color + " ");
                }
            }
        }
    }

    private List<Cell> findNearestCell(Cell cell, Color block) {
        
        int x = cell.getX();
        int y = cell.getY();

        return Arrays.asList(
                this.graph.get(createKey(x,y+1)),
                this.graph.get(createKey(x+1,y)),
                this.graph.get(createKey(x,y-1)),
                this.graph.get(createKey(x-1,y)))
            .stream()
            .filter(c -> c != null && c.getColor() == cell.getColor() && !block.hasCell(c))
            .collect(Collectors.toList());
    }
    
    private String createKey (int x, int y) {
        return x+"_"+y;
    }

    public Color getLargeConnectingBlock(int x, int y) {
        Cell firstCell = this.graph.get(createKey(x,y));
        Color colorBlock = new Color(firstCell.getColor());
        colorBlock.addCell(firstCell);

        LinkedList<Cell> groupCells = new LinkedList<>();
        groupCells.addAll(findNearestCell(firstCell, colorBlock));

        while(!groupCells.isEmpty()) {
            Cell nextCell = groupCells.remove();
            colorBlock.addCell(nextCell);
            groupCells.addAll(findNearestCell(nextCell, colorBlock));
        }

        return colorBlock;
    }

    public Color getLargeBlock() {
        Set<String> keys = new HashSet<>(this.graph.keySet());
        List<Color> blocks = new ArrayList<>();
        while(!keys.isEmpty()) {
            String key = keys.iterator().next();
            Cell cell = this.graph.get(key);
            Color newBlock = getLargeConnectingBlock(cell.getX(), cell.getY());
            blocks.add(newBlock);
            keys.removeAll(newBlock.getAllPoints());
        }
        blocks.sort((Color cb1, Color cb2) -> cb2.size() - cb1.size());
        return blocks.size() > 0 ? blocks.get(0) : null;
    }
}