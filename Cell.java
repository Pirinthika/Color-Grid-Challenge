class Cell {
	
	private int id;
    private int x;
    private int y;
    private char color;
    

    public Cell(int id, int x, int y, char color) {
    	this.id = id;
    	this.color = color;
        this.y = y;
        this.x =x;
    }

    public int getId() {
        return id;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    }


    @Override
    public int hashCode() {
        return 27 * (x + y + (int) color);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (color != cell.color) return false;
        return cell.x == this.x && cell.y == cell.y;
    }

    

    @Override
    public String toString() {
        return " [" + x + ", " + y + ", " + color + "] ";
    }
}