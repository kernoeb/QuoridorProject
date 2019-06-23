// =============================================================================
// Game.java
// =============================================================================

	private BoardGUI boardGUI;

    this.boardGUI = new BoardGUI(this, this.board);

  public BoardGUI getBoardGUI() {
    return this.boardGUI;
  }

  private void runAutoPlayer() {
    if (this.actualPlayer instanceof AutoPlayer) {
      Square square = this.actualPlayer.randomSquare();
      this.actualPlayer.play(square, this.boardGUI);
      // this.actualPlayer = this.boardGUI.getGame().getActualPlayer();
      // this.boardGUI.setFencesEnabled(square);
      this.boardGUI.displayBoardGUI();
      this.boardGUI.addTmpPossibilities(this.boardGUI.getBoard().listOfPossibilitiesPawn(this.actualPlayer), this.actualPlayer);
    }
  }

// =============================================================================
// Board.java
// =============================================================================

public boolean possibleFence(Square square, Player player) {
    boolean ret = false;
    int x = square.getX();
    int y = square.getY();
    Square squareFence = null;
    // Barrière horizontale
    if (x % 2 != 0 && y % 2 == 0) {
      try {
        if (y != player.getGame().getBoardGUI().getSquares().length-1) squareFence = this.grid[x][y+1];
        else squareFence = this.grid[x][y-1];

        if (player.checkFencePossible(squareFence, "h")) {
          this.removeFence(squareFence.getX(), squareFence.getY(), "h");
          ret = true;
        }

      } catch (Exception ex) {}
    }
    // Barrière verticale
    else if (x % 2 == 0 && y % 2 != 0) {
      try {
        if (y != player.getGame().getBoardGUI().getSquares().length-1) squareFence = this.grid[x+1][y];
        else squareFence = this.grid[x-1][y];

        if (player.checkFencePossible(squareFence, "v")) {
          this.removeFence(squareFence.getX(), squareFence.getY(), "v");
          ret = true;
        }
      } catch (Exception ex) {}
    }
  return ret;
}

// =============================================================================
// HumanPlayer.java
// =============================================================================

public boolean play(Square square, BoardGUI boardGUI) {
boolean ret = false;

    if(!this.terminal) {
  if (square.isPawn()) {
    ret = this.playPawn(square);
    // System.out.println("playPawn : " + ret);
    if (ret) {
      System.out.println("HumanPlayer - play() isPawn -> nextPlayerGUI");
      this.game.setActualPlayer();
    }
  }

  else if (square.isFence()) {
    ret = this.playFence(square, boardGUI);
    // System.out.println("playFence : " + ret);
    if (ret) {
      System.out.println("HumanPlayer - play() isFence -> nextPlayerGUI");
      this.game.setActualPlayer();
    }
  }
  }
return ret;
}

public boolean playFence(Square square, BoardGUI boardGUI) {
  boolean ret = false;

  if (this.getNbRestingFences() > 0) {
    int x = square.getX();
    int y = square.getY();
    Square squareFence = null;
    // Barrière horizontale
    if (x % 2 != 0 && y % 2 == 0) {
      try {
        if (y != boardGUI.getSquares().length-1) squareFence = this.board.getGrid()[x][y+1];
        else squareFence = this.board.getGrid()[x][y-1];

        if (this.checkFencePossible(squareFence, "h")) {
          this.board.setFence(squareFence.getX(), squareFence.getY(), "h", this);
              this.setNbFences(this.nbFences - 1);
          ret = true;
        }

      } catch (Exception ex) {}
    }
    // Barrière verticale
    else if (x % 2 == 0 && y % 2 != 0) {
      try {
        if (y != boardGUI.getSquares().length-1) squareFence = this.board.getGrid()[x+1][y];
        else squareFence = this.board.getGrid()[x-1][y];

        if (this.checkFencePossible(squareFence, "v")) {
          this.board.setFence(squareFence.getX(), squareFence.getY(), "v", this);
            this.setNbFences(this.nbFences - 1);
          ret = true;
        }
      } catch (Exception ex) {}
    }
    // if (ret) this.board.removeFence(squareFence);
  }
  return ret;

// =============================================================================
// AutoPlayer.java
// =============================================================================
public boolean play(Square square, BoardGUI boardGUI) {
  boolean ret = false;

  if(!this.terminal) {
    if (square.isPawn()) {
      ret = this.playPawn(square);

      if (ret) {
        System.out.println("AutoPlayer - play() isPawn -> nextPlayerGUI");
        this.game.setActualPlayer();
      }
    }

    else if (square.isFence()) {
      ret = this.playFence(square, boardGUI);

      if (ret) {
        System.out.println("AutoPlayer - play() isFence -> nextPlayerGUI");
        this.game.setActualPlayer();
      }
    }
  }

  return ret;
}

public boolean playFence(Square square, BoardGUI boardGUI) {
  boolean ret = false;

  if (this.getNbRestingFences() > 0) {
    int x = square.getX();
    int y = square.getY();

    try {

      if (this.checkFencePossible(square, "h")) {
        this.board.setFence(square.getX(), square.getY(), "h", this);
        this.setNbFences(this.nbFences - 1);
        ret = true;
      }

      else if (this.checkFencePossible(square, "v")) {
        this.board.setFence(square.getX(), square.getY(), "v", this);
        this.setNbFences(this.nbFences - 1);
        ret = true;
      }

    } catch (Exception ex) {}
  }

  return ret;
}


// =============================================================================
// Player.java
// =============================================================================
	public abstract boolean play(Square square, BoardGUI boardGUI);
  	public abstract boolean playFence(Square square, BoardGUI boardGUI);
// A modifier selon les changements effectués dans HumanPlayer et AutoPlayer
