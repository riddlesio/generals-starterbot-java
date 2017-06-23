/*
 *  Copyright 2017 riddles.io (developers@riddles.io)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 *
 *      For the full copyright and license information, please view the LICENSE
 *      file that was distributed with this source code.
 */

package board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * board.Board - Created on 22-6-17
 *
 * Parses and stores all information about the game board.
 * Calculations concerning the board are also performed here.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Board {

    private final String LEGAL_FIELD = ".";
    private final String BLOCKED_FIELD = "x";

    private String myId;
    private String opponentId;
    private int width;
    private int height;

    private HashMap<PieceType, Integer> startingPieceCount;
    private Piece[][] fields;
    private String[][] boardLayout;  // Contains what the board looks like without pieces, doesn't change
    private ArrayList<Piece> myPieces;

    public Board() {
        this.startingPieceCount = new HashMap<>();
    }

    public void addStartingPieceCount(String shorthand, int count) {
        PieceType pieceType = PieceType.fromString(shorthand);
        this.startingPieceCount.put(pieceType, count);
    }

    /**
     * Parses the input string given by the engine
     * @param input String representation of the board
     */
    public void parseFromString(String input) {
        this.fields = new Piece[this.width][this.height];
        this.boardLayout = new String[this.width][this.height];
        this.myPieces = new ArrayList<>();
        int x = 0;
        int y = 0;

        for (String fieldString : input.split(",")) {
            fieldString = fieldString.trim();

            Piece piece = null;

            if (fieldString.equals(BLOCKED_FIELD)) {
                this.boardLayout[x][y] = BLOCKED_FIELD;
            } else {
                this.boardLayout[x][y] = LEGAL_FIELD;
                piece = Piece.fromString(fieldString);
            }

            if (piece != null) {
                piece.setCoordinate(new Point(x, y));

                if (piece.getPlayerId().equals(this.myId)) {
                    this.myPieces.add(piece);
                }
            }

            this.fields[x][y] = piece;

            if (++x == this.width) {
                x = 0;
                y++;
            }
        }
    }

    public ArrayList<Point> getValidPointsForPiece(Piece piece) {
        if (piece.getType() == PieceType.SCOUT) {
            return getValidPointsForScout(piece);
        }

        ArrayList<Point> validPointsForPiece = new ArrayList<>();
        Point coordinate = piece.getCoordinate();

        for (Point direction : getValidDirections()) {
            Point newPoint = new Point(coordinate.x + direction.x, coordinate.y + direction.y);

            if (!isPointInBounds(newPoint) || !isPointOnLegalField(newPoint)) continue;

            Piece occupying = this.fields[newPoint.x][newPoint.y];
            if (occupying == null || !occupying.getPlayerId().equals(this.myId)) {
                validPointsForPiece.add(newPoint);
            }
        }

        return validPointsForPiece;
    }

    private ArrayList<Point> getValidPointsForScout(Piece piece) {
        ArrayList<Point> validPointsForPiece = new ArrayList<>();
        Point coordinate = piece.getCoordinate();

        for (Point direction : getValidDirections()) {
            int d = 0;

            while (true) { // scout can move in straight line as long as the line is empty
                d++;

                Point newPoint = new Point(
                        coordinate.x + (direction.x * d), coordinate.y + (direction.y * d));

                if (!isPointInBounds(newPoint) || !isPointOnLegalField(newPoint)) break;

                Piece occupying = this.fields[newPoint.x][newPoint.y];

                if (occupying == null) { // keep moving if empty
                    validPointsForPiece.add(newPoint);
                } else if (!occupying.getPlayerId().equals(this.myId)) { // opponents piece, stop there
                    validPointsForPiece.add(newPoint);
                    break;
                } else { // don't add if blocked or own piece
                    break;
                }
            }
        }

        return validPointsForPiece;
    }

    private ArrayList<Point> getValidDirections() {
        ArrayList<Point> directions = new ArrayList<>();

        directions.add(new Point(1, 0));
        directions.add(new Point(0, 1));
        directions.add(new Point(-1, 0));
        directions.add(new Point(0, -1));

        return directions;
    }

    private boolean isPointInBounds(Point point) {
        return point.x >= 0 && point.y >=0 && point.x < this.width && point.y < this.height;
    }

    private boolean isPointOnLegalField(Point point) {
        return this.boardLayout[point.x][point.y].equals(LEGAL_FIELD);
    }

    public void setMyId(int id) {
        this.myId = id + "";
    }

    public void setOpponentId(int id) {
        this.opponentId = id + "";
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMyId() {
        return this.myId;
    }

    public String getOpponentId() {
        return this.opponentId;
    }

    public HashMap<PieceType, Integer> getStartingPieceCount() {
        return this.startingPieceCount;
    }

    public ArrayList<Piece> getMyPieces() {
        return this.myPieces;
    }
}
