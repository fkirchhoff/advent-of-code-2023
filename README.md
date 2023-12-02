# Day 1

I first took the approach of just removing all non digits from the string which worked well for part 1.
For part 2 I 1st replaced all instances of the digit words with its digit equivalent, and then apply the part 1 solution.
That didn't work input lines like "eightwo" which resulted in "8wo" instead of the desired "82". I then switched to 
scanning the line character by character looking for digits and/or words.

