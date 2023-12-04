# Overview

The project structure is organized as follows: solutions, resources, and tests for each day can be found in
the `src/main/java/day<X>`, `src/main/resources/day<x>`, and `src/test/java/day<X>` directories, respectively.

## Day 1

Initially, my strategy involved removing all non-digits from the string, which proved effective for solving part 1 of
the problem. However, when addressing part 2, I encountered challenges with input lines containing combinations like "
eightwo," resulting in "8wo" rather than the intended "82." To overcome this, I modified my approach to scan the line
character by character, identifying both digits and words.

## Day 2

For Day 2, I introduced a `Game` class and a `Game.Draw` record. Parsing is achieved by removing all spaces, splitting
on ":" or ",", and using a regex to parse the count + color (e.g., "8red"). Part 1 involves filtering draws that exceed
the provided counts, while Part 2 focuses on finding the maximum count of each color in a game.

## Day 3

I reused some grid-related code from AOC 2022 Day 8 to print the grid, utilizing the `Engine`, `Part`, and `Symbol`
classes. I got stuck because of an "off by one" error in the parsing logic. While writing tests for the parsing logic,
I explored an alternative method using regular expressions (RE), which turned out to be simpler and cleaner. The final
version of the code opted for this RE-based approach.

## Day 4

I learned my lesson and used RegEx to parse the input :-). For part 1I used intersections of two `Set` to compute the winning numbers.
For part 2 I used a simple array to keep track of the number of each card. 
