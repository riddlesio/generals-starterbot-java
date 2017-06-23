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

import java.util.HashMap;
import java.util.Map;

/**
 * board.PieceType - Created on 22-6-17
 *
 * [description]
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public enum PieceType {
    MARSHAL("1"),
    GENERAL("2"),
    COLONEL("3"),
    MAJOR("4"),
    CAPTAIN("5"),
    LIEUTENANT("6"),
    SERGEANT("7"),
    MINER("8"),
    SCOUT("9"),
    SPY("S"),
    BOMB("B"),
    FLAG("F"),
    HIDDEN("X");

    private final String shortHand;
    private static final Map<String, PieceType> TYPE_MAP = new HashMap<>();

    static {
        for (PieceType pieceType : values()) {
            TYPE_MAP.put(pieceType.toString(), pieceType);
        }
    }

    PieceType(String shortHand) {
        this.shortHand = shortHand;
    }

    public static PieceType fromString(String shortHand) {
        return TYPE_MAP.get(shortHand);
    }

    public String toString() {
        return this.shortHand;
    }
}
