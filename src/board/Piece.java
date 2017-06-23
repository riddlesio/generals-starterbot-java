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

/**
 * board.Piece - Created on 22-6-17
 *
 * [description]
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Piece {

    private PieceType type;
    private String playerId;
    private Point coordinate;

    public Piece(PieceType type, String playerId) {
        this.type = type;
        this.playerId = playerId;
    }

    public static Piece fromString(String string) {
        if (string.length() != 3) {
            return null;
        }

        String[] split = string.split("P");

        PieceType type = PieceType.fromString(split[0]);
        String playerId = split[1];

        return new Piece(type, playerId);
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public PieceType getType() {
        return this.type;
    }

    public String getPlayerId() {
        return this.playerId;
    }

    public Point getCoordinate() {
        return this.coordinate;
    }

    public boolean isHidden() {
        return this.type == PieceType.HIDDEN;
    }
}
