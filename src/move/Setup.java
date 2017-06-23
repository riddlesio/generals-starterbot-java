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

package move;

import java.util.ArrayList;
import java.util.stream.Collectors;

import board.PieceType;

/**
 * move.SetupAction - Created on 22-6-17
 *
 * Stores the setup action and can transform it to a
 * correct string to return to the engine. The piece types are
 * stored in an ordered list and the first piece type will be placed
 * in the top-left corner of available placement cells
 * on your side of the board (as seen from your side of the board),
 * the last piece will be placed in the bottom-right corner.
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class Setup {

    private ArrayList<PieceType> pieceTypes;

    public Setup(ArrayList<PieceType> pieceTypes) {
        this.pieceTypes = pieceTypes;
    }

    public String toString() {
        String piecesString = this.pieceTypes.stream()
                .map(PieceType::toString)
                .collect(Collectors.joining(","));

        return String.format("setup %s", piecesString);
    }
}
