// ============================================================================================================
// Board.java
// ============================================================================================================

/**
 * Print the list of possibilities
 * @param player player
 */
public void printListOfPossibilitiesPawn(Player player) {
  // System.out.println(player.getCurrentSquare().getX()/2 + " " + player.getCurrentSquare().getY()/2);

  ArrayList<Square> listOfPossibilities = listOfPossibilitiesPawn(player);

  // System.out.print("Coups possibles : ");
  for (Square s : listOfPossibilities) {
    System.out.print(s.getX()/2 + ", " + s.getY()/2	 + " | ");
  }
  System.out.println();
}

public String toString() {
  String ret = "";

  Square temp = null;

  System.out.print("  ");
  for (int i = 0; i < this.getSIZE(); i++) System.out.print(ANSI_YELLOW + i + "   ");
  System.out.println("");

  for (int x = 0; x < this.getTotalSize(); x++) {
    if (this.isEvenNumber(x)) {
      ret += ANSI_YELLOW + x/2 + " ";
    } else ret += "  ";
    for (int y = 0; y < this.getTotalSize(); y++) {
      temp = this.grid[x][y];

      if (temp.isPawn()) {
        if (temp.isPawnPossible()) {
          ret += this.ANSI_WHITE;
        }

        else if (temp.isPawn1()) {
          ret += this.ANSI_RED;
        }

        else if (temp.isPawn2()) {
          ret += this.ANSI_GREEN;
        }

        ret += "X ";
      }


      else if (temp.isFence()) {
        if (temp.isFencePossible()) {
          ret += this.ANSI_GREY;
        }

        else if (temp.isFencePawn1()) {
          ret += this.ANSI_RED;
        }

        else if (temp.isFencePawn2()) {
          ret += this.ANSI_GREEN;
        }

        if (this.isEvenNumber(x)) {
          ret += "| ";
        }

        else if (this.isEvenNumber(y)) {
          ret += "─ ";
        }

        else {
          ret += "+ ";
        }
      }
    }

    ret += this.ANSI_RESET + "\n";
  }
  return ret;
}


// =============================================================================
// Game.java
// =============================================================================

  public void endOfGame() {
    Player finishPlayer = null;

    if(this.checkHasFinished(this.player1)) {
      finishPlayer = this.player1;
    }
    else if(this.checkHasFinished(this.player2)) {
      finishPlayer = this.player2;
    }

    System.out.println("Félicitations ! Le joueur " + this.player1.getName() + " a gagné la partie !");
  }

  public String toString() {
    // TODO - implement Game.toString
    String ret = "";

    // this.boardGUI.displayBoardGUI(this.board);

    ret += this.board;
    ret += this.actualPlayer.getName() + " :";

    return ret;
  }


// =============================================================================
// HumanPlayer.java
// =============================================================================
