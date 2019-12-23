package sk.tsystems.gamestudio.consoleui.nPuzzle;

import java.util.Random;

public class Field {
	private final Tile[][] tile;
	private final int rowCount;
	private final int columnCount;
	private int re;
	private int ce;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tile = new Tile[rowCount][columnCount];
		generate();
	}

	private void generate() {
		int x = 1;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (x < columnCount * rowCount) {
					tile[i][j] = new Tile(x);
					x++;
				} else {
					tile[i][j] = new Tile(0);
				}

			}
		}
		re = rowCount - 1;
		ce = columnCount - 1;

		shuffle();
	}

	public void shuffle() {
		int tileNumber;
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			tileNumber = random.nextInt(rowCount * columnCount - 1) + 1;
			move(tileNumber);
		}
	}

	public boolean isSolved() {
		int x = 1;
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				if (x < columnCount * rowCount) {
					if (tile[i][j].getValue() != x) {
						return false;
					}

				}
				x++;
			}
		}
		return true;
	}

	public void move(int tileNumber) {
		if (re > 0 && tile[re - 1][ce].getValue() == tileNumber) {
			tile[re][ce].setValue(tileNumber);
			tile[re - 1][ce].setValue(0);
			re--;
		} else if (re < getRowCount() - 1 && tile[re + 1][ce].getValue() == tileNumber) {
			tile[re][ce].setValue(tileNumber);
			tile[re + 1][ce].setValue(0);
			re++;
		} else if (ce < getColumnCount() - 1 && tile[re][ce + 1].getValue() == tileNumber) {
			tile[re][ce].setValue(tileNumber);
			tile[re][ce + 1].setValue(0);
			ce++;
		} else if (ce > 0 && tile[re][ce - 1].getValue() == tileNumber) {
			tile[re][ce].setValue(tileNumber);
			tile[re][ce - 1].setValue(0);
			ce--;
		}

	}

	public Tile getTile(int row, int column) {
		return tile[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}
}
