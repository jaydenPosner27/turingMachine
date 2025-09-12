Turing Machine Simulator

A Java project that simulates the execution of a restricted Turing machine.

Description

The Turing Machine Simulator reads a machine definition from a text file, takes an input string as the initial tape, and runs transitions up to a maximum number of steps. The program halts and outputs the final tape contents followed by one of four outcomes:

accept – the machine enters an accept state

reject – the machine enters a reject state or no valid transition exists

quit – the maximum number of transitions is reached

crash – the head moves past the left end of the tape

This project was built to practice Java programming, file parsing, and simulating automata.

Technologies Used

Java

Scanner for file and input parsing

Object-oriented design with State and Transition classes

Project Structure
posner_p1.java   # Main entry point, parses arguments, runs the simulator
State.java       # (inner class) represents a state, including accept/reject flags and transitions
Transition.java  # (inner class) represents a transition: read symbol, new state, write symbol, direction


All classes are included in a single file (posner_p1.java) for simplicity, but separated into logical components.

How to Run
# Compile the program
javac posner_p1.java

# Run the program
java posner_p1 <machine_file> <input_string> <max_transitions>

Example

Given a machine definition file sample.txt:

state 0 start
state 1
state 2 accept
transition 0 0 1 1 R
transition 1 1 2 1 S


Run:

java posner_p1 sample.txt 01 10


Output:

11 accept