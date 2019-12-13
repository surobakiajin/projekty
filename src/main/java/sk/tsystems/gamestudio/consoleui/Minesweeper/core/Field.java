package sk.tsystems.gamestudio.consoleui.Minesweeper.core;
import java.util.Random;

import sk.tsystems.gamestudio.consoleui.Minesweeper.core.Tile.State;
/**
 * Field represents playing field and game logic.
 */
public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;

    public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Tile  getTiles(int row ,int column) {
		return tiles[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

 

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	/**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];

        //generate the field content
        generate();
    }

    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        Tile tile = tiles[row][column];
        if (tile.getState() == Tile.State.CLOSED) {
            tile.setState(Tile.State.OPEN);
if(tile instanceof Clue) {
	if (((Clue)tile ).getValue()==0) {
		openAdjacentTiles(row,column);
	}
}
            if (tile instanceof Mine) {
                state = GameState.FAILED;
                return;
            }
            

            if (isSolved()) {
                state = GameState.SOLVED;
                return;
            }
        }
    }

    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {
    	 Tile tile = tiles[row][column];
         if (tile.getState() == Tile.State.MARKED) {
             tile.setState(Tile.State.CLOSED);
             return;
         }
             if (tile.getState() == Tile.State.CLOSED ) {
                 tile.setState(Tile.State.MARKED);
             return;
         }
    }

    /**
     * Generates playing field.
     */
    private void generate() {
    	Random rand = new Random();
        int freeMines = mineCount ;
    	while (freeMines != 0) {
    	   int randColumn = rand.nextInt(columnCount);
    	   int randRow = rand.nextInt(rowCount);
    	  if (tiles[randRow][randColumn] == null) {
    		  tiles[randRow][randColumn] = new Mine ();
    		  freeMines--;
    		  
    	  } 
    	}
    for (int i =0;i < columnCount ; i++) {
    for (int j=0;j < rowCount ;j++) {
    	if (tiles[j][i] == null) {
    		tiles[j][i] = new Clue(countAdjacentMines(j,i));
    		
    	}
    	}
    }
    }

    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    public boolean isSolved() {
    	while ((rowCount*columnCount) - getNumberOf(Tile.State.OPEN) == getMineCount()) {
    		return true;
    	} return false;
    }


    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
    
    private void openAdjacentTiles(int row, int column) {
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Clue && ((Clue) tiles[actRow][actColumn]).getState() == Tile.State.CLOSED) {
                        	
                        	tiles[actRow][actColumn].setState(Tile.State.OPEN);
                        	if(((Clue) tiles[actRow][actColumn]).getValue() == 0 ) {
                        	 openAdjacentTiles(actRow,actColumn);
                        }}
                    }
                }
            }
        }

 
    }
    
    private int getNumberOf(Tile.State state) { 
		int count = 0;
    	for(int x = 0; x < rowCount; x++) {
    		for(int y = 0; y < columnCount; y++) {
    			Tile tile = tiles[x][y];
    			if (tile.getState() == Tile.State.OPEN) {
					count++;
    			}
    		}
    	}
		return count;
	} 

}
