# Overview

Solution/resource/test for each day can be found in src/main/java/day<X>, src/main/resources/day<x> and src/test/java/day<X>.

# Day 1

Initially, my strategy involved stripping all non-digits from the string, and this method proved effective for solving
part 1 of the problem. However, when tackling part 2, I initially attempted to replace all occurrences of digit words
with their numeric equivalents before applying the part 1 solution. This approach encountered challenges with input
lines containing combinations like "eightwo," resulting in "8wo" rather than the intended "82." Then I modified
my approach to scan the line character by character, identifying both digits and words.

# Day 2

Added a Game class and Game.Draw record. Parsing is done by removing all spaces and spliting on ":" or "," 
and using a RegEx to parse the count+color(e.g. "8red"). 
Part 1 is just filtering the draws for any that exceeds the provided counts. Part 2 is just finding the max
count of each color in a Game.

# Day 3

I reused some "grid" related code from AOC 2022 Day 8 to print the grid. I used Engine, Part and Symbol classes. 
The parsing logic was a bit convoluted, but it worked! While writing test for the logic I relied on an alternate way 
to parse using regular expressions (RE). That approach is much simpler so my final version used REs.

