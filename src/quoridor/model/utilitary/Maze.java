package quoridor.model.utilitary;

import quoridor.model.Board;
import quoridor.model.Square;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Node
 * Temporarily save the squares
 */
class Node {
    final int x;
    final int y;
    final int dist;

    Node(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

/**
 * Maze
 * BFS implementation
 */
public class Maze {
    private final int[] row = {-1, 0, 0, 1};
    private final int[] col = {0, -1, 1, 0};
    private final Board board;
    private final int sens;

    /**
     * Constructor
     *
     * @param board Used board
     * @param sens  direction
     */
    public Maze(Board board, int sens) {
        this.board = board;
        this.sens = sens;
    }

    /**
     * Convert the grid in matrix
     *
     * @return the matrix "maze"
     */
    public int[][] convertToMaze() {
        Square temp;
        int[][] maze = new int[this.board.getTotalSize()][this.board.getTotalSize()];

        for (int x = 0; x < this.board.getTotalSize(); x++) {
            for (int y = 0; y < this.board.getTotalSize(); y++) {
                temp = this.board.getGrid()[x][y];

                if (this.sens == 0) {
                    if (x % 2 != 0 && y % 2 != 0) {
                        if (fenceAround(x, y, 0)) maze[x][y] = 0;
                        else if (fenceAround(x, y, 1)) maze[x][y] = 0;
                    } else if ((temp.isPawn2() && x == 0 && fenceX(x, y, 0)) || temp.isFencePawn1() || temp.isFencePawn2())
                        maze[x][y] = 0;
                    else maze[x][y] = 1;

                } else {
                    if ((x % 2 != 0) && (y % 2 != 0)) {
                        if (fenceAround(x, y, 0)) maze[x][y] = 0;
                        else if (fenceAround(x, y, 1)) maze[x][y] = 0;
                    } else if ((temp.isPawn1() && x == this.board.getTotalSize() - 1 && fenceX(x, y, this.board.getTotalSize() - 1)) || temp.isFencePawn1() || temp.isFencePawn2())
                        maze[x][y] = 0;
                    else maze[x][y] = 1;
                }
            }
        }
        return maze;
    }

    /**
     * Check if square is valid (size and not visited)
     *
     * @param row         x
     * @param col         y
     * @return true if valid
     */
    private boolean isValid(int[][] mat, boolean[][] visited, int row, int col) {
        return (row >= 0) && (row < this.board.getTotalSize()) && (col >= 0) && (col < this.board.getTotalSize()) && mat[row][col] == 1 && !visited[row][col];
    }

    /**
     * BFS Algorithm to find a path
     *
     * @param i       "beginning" X
     * @param j       "beginning" Y
     * @param x       the arrival
     * @return true if found a path, false if not
     */
    public boolean BFS(int[][] mat, int i, int j, int x) {
        boolean[][] visited = new boolean[this.board.getTotalSize()][this.board.getTotalSize()];
        Queue<Node> queue = new ArrayDeque<>();

        visited[i][j] = true;
        queue.add(new Node(i, j, 0));

        int min_dist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            i = node.x;
            j = node.y;
            int dist = node.dist;

            if (i == x) {
                min_dist = dist;
                break;
            }

            for (int k = 0; k < 4; k++) {
                if (isValid(mat, visited, i + row[k], j + col[k])) {
                    visited[i + row[k]][j + col[k]] = true;
                    queue.add(new Node(i + row[k], j + col[k], dist + 1));
                }
            }
        }

        return min_dist != Integer.MAX_VALUE;
    }

    /**
     * Check if there are fences to the left and right of a square
     *
     * @param x square X
     * @param y square Y
     * @param i coeff
     * @return true if there are fences
     */
    private boolean fenceAround(int x, int y, int i) {
        try {
            if (!this.board.getGrid()[x - i][y - i].isFencePossible())
                if (!this.board.getGrid()[x - (2 * i)][y - (2 * i)].isFencePossible())
                    if (!this.board.getGrid()[x - (3 * i)][y - (3 * i)].isFencePossible())
                        if (!this.board.getGrid()[x + i][y + i].isFencePossible())
                            if (!this.board.getGrid()[x + (2 * i)][y + (2 * i)].isFencePossible())
                                if (!this.board.getGrid()[x + (3 * i)][y + (3 * i)].isFencePossible())
                                    return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    /**
     * Check if there is a FENCE status at the left and right
     *
     * @param x square X
     * @param y square right
     * @param v direction
     * @return true if there is a FENCE status
     */
    private boolean fenceX(int x, int y, int v) {
        try {
            if (x == v) {
                if ((this.board.getGrid()[x][y - 1].isFencePawn1() || this.board.getGrid()[x][y - 1].isFencePawn2())
                        && (this.board.getGrid()[x][y + 1].isFencePawn1() || this.board.getGrid()[x][y + 1].isFencePawn2()))
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }
}