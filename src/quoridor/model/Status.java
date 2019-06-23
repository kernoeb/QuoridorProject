package quoridor.model;

/**
 * Enumeration representing a square status.
 * @author Noéwen Boisnard, Sébastien Gavignet
 */
public enum Status {
    /** Fence possible.
    */
    FENCEPOSSIBLE,

    /** Fence of the first player.
    */
    FENCEPAWN1,

    /** Fence of the second player.
    */
    FENCEPAWN2,

    /** Pawn possible.
    */
    PAWNPOSSIBLE,

    /** Pawn of the first player.
    */
    PAWN1,

    /** Pawn of the second player.
    */
    PAWN2
}
