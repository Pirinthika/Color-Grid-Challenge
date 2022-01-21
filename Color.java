import java.util.*;
import java.util.stream.Collectors;


class Color{

    private char color;

    private Set<Cell> cells;

    public Color(char color) {
        this.color = color;
        cells = new HashSet<>();
    }

    public boolean addCell(Cell cell) {
        if (cell != null && !cells.contains(cell) && cell.getColor() == this.color)
            return cells.add(cell);
        return false;
    } 
    

    public int size() {
        return cells.size();
    }
    
    public Set<Cell> getAllCells() {
        return cells;
    }

    public Set<String> getAllPoints() {
        return cells.stream().map(n -> n.getX() + "_" + n.getY()).collect(Collectors.toSet());
    }


    public boolean hasCell(Cell cell) {
        if(cell == null)
            return false;
        return cells.stream().anyMatch(n -> n.getId() == cell.getId());
    }


}