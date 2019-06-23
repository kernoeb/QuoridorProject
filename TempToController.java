// =============================================================================
// Game.java
// =============================================================================

public void start() throws SaveGameException {
  // TODO - implement Game.start
  while((!this.checkHasFinished(this.player1)) && (!this.checkHasFinished(this.player2))) {
    System.out.println(this);
    this.nextPlayer();
  }

  this.endOfGame();
}


// =============================================================================
// HumanPlayer.java
// =============================================================================

	private transient Scanner scan;

  // A voir pour cette méthode comment faire
  public void play() throws SaveGameException {
      if(this.terminal) {
          if(this.nbFences > 0) {
              String mode = this.askMode();

              if (mode.equalsIgnoreCase("pawn")) {
                  this.board.displayForPawn();
                  this.playPawn();
              }

              else if (mode.equalsIgnoreCase("fence")) {
                  this.board.displayForFence();
                  this.playFence();
                  System.out.println("Il vous reste " + super.nbFences + " barrières !");
              }
              else if (mode.equalsIgnoreCase("save")) {
              	throw new SaveGameException("");
              }
          }
          else {
              System.out.println("Vous n'avez plus de murs disponibles !");

              this.board.displayForPawn();
              this.playPawn();
          }
      }
  }



  public void playFence() {
		// TODO - implement HumanPlayer.playFence
		this.listOfOldPositions.clear();

		System.out.println("Sur quelle case voulez-vous jouer ?");

		int x = this.askX(this.board.getSIZE() - 1);
		int y = this.askY(this.board.getSIZE() - 1);
		String dir = this.askDir();

		Square currentSquare = this.getCurrentSquare();

		while (!this.checkFencePossible(this.board.getGrid()[this.board.fenceCoord(x)][this.board.fenceCoord(y)], dir)) {
			System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
								+ "Veuillez en choisir une autre !");

			x = this.askX(this.board.getSIZE() - 1);
			y = this.askY(this.board.getSIZE() - 1);
			dir = this.askDir();

			this.setCurrentSquare(currentSquare);
		}

		this.setCurrentSquare(currentSquare);

		this.board.setFence(this.board.fenceCoord(x), this.board.fenceCoord(y), dir, this);
		this.setNbFences(this.nbFences - 1);
	}

  public void playPawn() {
    System.out.print("Vous pouvez jouer un pion sur les cases : ");

    this.board.printListOfPossibilitiesPawn(this);
    // this.game.getBoardGUI().addTmpPossibilities(this.board.listOfPossibilitiesPawn(this));

    System.out.println("Sur quelle case voulez-vous jouer ?");
    int x = this.askX(this.board.getSIZE());
    int y = this.askY(this.board.getSIZE());

    while (((this.board.pawnCoord(x) == this.currentSquare.getX()) && (this.board.pawnCoord(y) == this.currentSquare.getY()))
        || (this.board.listOfPossibilitiesPawn(this).contains(this.board.getGrid()[this.board.pawnCoord(x)][this.board.pawnCoord(y)]) == false)) {
      System.out.println("Vous ne pouvez pas jouer sur cette case. \n"
                + "Veuillez en choisir une autre !");

      x = this.askX(this.board.getSIZE());
      y = this.askY(this.board.getSIZE());
    }
    super.movePawn(this.board.pawnCoord(x), this.board.pawnCoord(y));
  }


// =============================================================================
// Player.java
// =============================================================================

// A modifier selon les changements effectués dans HumanPlayer et AutoPlayer
