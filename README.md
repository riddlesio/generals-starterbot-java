# Generals Java starterbot

This repository contains the starterbot for the Generals game on the Riddles.io platform.

## Running locally

The easiest way is to use Gradle to compile and run the project. You can feed the inputs below
 to the bot and it should give a correct piece setup as output.
 
````
settings player_names player0,player1
settings your_bot player0
settings timebank 2000
settings time_per_move 100
settings max_rounds 200
settings your_botid 0
settings board_width 10
settings board_height 10
settings board_layout .,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,x,x,.,.,x,x,.,.,.,.,x,x,.,.,x,x,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.
settings piece_count_1 1
settings piece_count_2 1
settings piece_count_3 2
settings piece_count_4 3
settings piece_count_5 4
settings piece_count_6 4
settings piece_count_7 4
settings piece_count_8 5
settings piece_count_9 8
settings piece_count_S 1
settings piece_count_B 6
settings piece_count_F 1
action setup 2000
````

## Uploading to Riddles.io

Compress everything inside (not including) the src/ folder to a .zip file. Then you can upload that
file at the Generals competition on Riddles.io

*Git repo: https://github.com/riddlesio/generals-starterbot-java*