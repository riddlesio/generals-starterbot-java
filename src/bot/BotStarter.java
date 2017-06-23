package bot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import board.Piece;
import board.PieceType;
import move.Move;
import move.Setup;

/**
 * bot.BotStarter - Created on 22-6-17
 *
 * Magic happens here. You should edit this file, or more specifically
 * the doMove() method to make your bot do more than random moves.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class BotStarter {

    private Random random;

    private BotStarter() {
        this.random = new Random();
    }

    /**
     * Method that is called when the engine asks for the piece setup
     * at the start of the game. You should edit this method to
     * make your bot smarter.
     * Currently returns a completely random setup.
     * @param state The current bot state
     * @return A random setup
     */
    public Setup doSetup(BotState state) {
        ArrayList<PieceType> pieceTypes = new ArrayList<>();

        state.getBoard().getStartingPieceCount().forEach((pieceType, count) -> {
            for (int n = 0; n < count; n++) {
                pieceTypes.add(pieceType);
            }
        });

        // Shuffle the pieces
        Collections.shuffle(pieceTypes, this.random);

        return new Setup(pieceTypes);
    }

    /**
     * Method that is called when the engine asks for a move
     * You should edit this method to make your bot smarter.
     * Currently performs a random but correct piece movement.
     * @param state The current bot state
     * @return A random move
     */
    public Move doMove(BotState state) {
        Move move = null;
        ArrayList<Piece> myPieces = new ArrayList<>(state.getBoard().getMyPieces());

        while (move == null && myPieces.size() > 0) {
            Piece randomPiece = myPieces.remove(this.random.nextInt(myPieces.size()));
            move = doRandomPieceMove(state, randomPiece);
        }

        return move;
    }

    private Move doRandomPieceMove(BotState state, Piece piece) {
        if (piece.getType() == PieceType.BOMB || piece.getType() == PieceType.FLAG) return null;

        ArrayList<Point> validMoves = state.getBoard().getValidPointsForPiece(piece);

        if (validMoves.size() <= 0) return null;

        Point randomTo = validMoves.get(this.random.nextInt(validMoves.size()));

        return new Move(piece.getCoordinate(), randomTo);
    }

    public static void main(String[] args) {
        BotParser parser = new BotParser(new BotStarter());
        parser.run();
    }
}
