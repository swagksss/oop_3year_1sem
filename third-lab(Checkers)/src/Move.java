/*
 * Move object representing possible moves.
 * (Evaluation of legality happens elsewhere)
 */
class Move {
    int fromRow, fromCol;  // Current piece's position
    int toRow, toCol;      // Tile to which the piece moves

    /*
     * Move (object) stores game piece moves.
     * The legality of moves are determined elsewhere.
     */
    Move(int oldRow, int oldColumn, int newRow, int newColumn) {
        this.fromRow = oldRow;
        this.fromCol = oldColumn;
        this.toRow = newRow;
        this.toCol = newColumn;
    }

    // Check if move is a jump (assume jump is legal).
    // Non-jump moves only move diagonally 1 tile at a time
    boolean isJump() {
        return Math.abs(fromRow - toRow) == 2;
    }
}

