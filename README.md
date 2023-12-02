# Day 1

Initially, my strategy involved stripping all non-digits from the string, and this method proved effective for solving
part 1 of the problem. However, when tackling part 2, I initially attempted to replace all occurrences of digit words
with their numeric equivalents before applying the part 1 solution. This approach encountered challenges with input
lines containing combinations like "eightwo," resulting in "8wo" rather than the intended "82." Then I modified
my approach to scan the line character by character, identifying both digits and words.

