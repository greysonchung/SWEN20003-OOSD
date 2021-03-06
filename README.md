# SWEN20003 Object Oriented Software Development

- Language: Java
- External Library: The Basic Academic Graphical Engine Library (Bagel)

## Assignment 1 
- Design and create a simulation of a fictional universe. For Assignment 1, we will create the basis of a larger simulation for Assignment 2

## Assignment 2
- Create a graphical simulation of a world and its inhabitants, continuing from Assignment 1

## Simulation Overview
Shadow Life is a graphical simulation of a world inhabited by creatures called gatherers. Their purpose in life is to gather fruit from the trees, and deposit them at stockpiles. Once they have gathered all the fruit from their trees, they rest in front of fences.

Making their life difficult is the thief who aims to steal fruit from the stockpiles and place it in their hoards. The thief and gatherers follow rigid rules, and once they all reach their final goals (the fence), the simulation halts. They are quite industrious workers—with enough time, they could calculate anything that any computer can!

The behaviour of the simulation is entirely determined by the world file loaded when the Shadow Life program starts: each gatherer, thief, and other element begins at a specified location and follows a set of rules to determine their behaviour. Once all gatherers and thieves have reached a fence, the simulation halts, and the amount of fruit at each stockpile and hoard is tallied up. The simulation proceeds in ticks, with the tick rate (time between ticks) determined by a command- line parameter. If more than a maximum number of ticks (also determined by a command-line parameter) pass before halting, the simulation times out. Otherwise, the number of elapsed ticks, together with the amounts of fruit at each location, is printed to form the result of the world file.
