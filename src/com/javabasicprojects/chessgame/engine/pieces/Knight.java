package com.javabasicprojects.chessgame.engine.pieces;

import com.javabasicprojects.chessgame.engine.board.Board;
import com.javabasicprojects.chessgame.engine.board.BoardUtils;
import com.javabasicprojects.chessgame.engine.board.Move;
import com.javabasicprojects.chessgame.engine.board.Move.*;

import java.util.*;

public final class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };
    private final static Map<Integer, int[]> PRECOMPUTED_CANDIDATES = computeCandidates();

    public Knight(final Alliance alliance,
                  final int piecePosition) {
        super(alliance, piecePosition, PieceType.KNIGHT, true);
    }

    public Knight(final Alliance alliance,
                  final int piecePosition,
                  final boolean isFirstMove) {
        super( alliance, piecePosition,PieceType.KNIGHT, isFirstMove);
    }

    private static Map<Integer, int[]> computeCandidates() {
        final Map<Integer, int[]> candidates = new HashMap<>();
        for (int position = 0; position < BoardUtils.NUM_TILES; position++) {
            final int[] legalOffsets = new int[CANDIDATE_MOVE_COORDINATES.length];
            int numLegalMoves = 0;
            for (final int offset : CANDIDATE_MOVE_COORDINATES) {
                if(isFirstColumnExclusion(position, offset) ||
                        isSecondColumnExclusion(position, offset) ||
                        isSeventhColumnExclusion(position, offset) ||
                        isEighthColumnExclusion(position, offset)) {
                    continue;
                }
                final int destination = position + offset;
                if (BoardUtils.isValidTileCoordinate(destination)) {
                    legalOffsets[numLegalMoves++] = destination;
                }
            }
            candidates.put(position, Arrays.copyOf(legalOffsets, numLegalMoves));
        }
        return Collections.unmodifiableMap(candidates);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateDestinationCoordinate : PRECOMPUTED_CANDIDATES.get(this.piecePosition)) {
            final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
            if (pieceAtDestination == null) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            } else {
                final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                    legalMoves.add(new MajorAttackMove(board, this, candidateDestinationCoordinate,
                            pieceAtDestination));
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public int locationBonus() {
        return this.pieceAlliance.knightBonus(this.piecePosition);
    }

    @Override
    public Knight movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedKnight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition,
                                                  final int candidateOffset) {
        return BoardUtils.INSTANCE.FIRST_COLUMN.get(currentPosition) && ((candidateOffset == -17) ||
                (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.INSTANCE.SECOND_COLUMN.get(currentPosition) && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition,
                                                    final int candidateOffset) {
        return BoardUtils.INSTANCE.SEVENTH_COLUMN.get(currentPosition) && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.INSTANCE.EIGHTH_COLUMN.get(currentPosition) && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }

}
