# Blackjack

## Introduction

The objective of this assignment is to build multi-player Blackjack by
implementing several card-related interfaces, including some abstract
classes. It is also a chance to get your hands dirty working with a
complete Java system: implementation, build, and execution. If you are
not familiar with Blackjack, it is worth taking a look at the
[Wikipedia page](https://en.wikipedia.org/wiki/Blackjack) to
understand its game play. Fortunately, only a basic understanding is
required at this point.

## Implementation

Your job is to implement classes within the `impl` package (directory)
based on interface specifications in the `api` package. By default,
these files are all empty. Please see code in the `api` package for
documentation on the purpose of each interface/abstract class. Code in
these directories should not be altered in any way.

## Java package basics

This project uses the Java packaging system; thus, the first line in
each implementation file should be
```java
  package impl;
```
which informs the build system that this class resides in the `impl`
package. See complete Java files in the other directories for more
examples.

To use other Java libraries, the `import` statement should be
employed:
```java
  import java.util.list;
```
This also goes, however, for the abstract classes and interfaces in
the `api` directory. For example, to use the `Card` class, your
implementation must first import it:
```java
  import api.Card;
```
Notice that the first thing `BlackJackGame` imports is a package
defined in your `impl` directory.

## Class layout

Package | Class | Implements | Extends
---|---|---|---
api | Table | |
    | Hand | Comparable |
    | Card | Comparable |
    | Dealer | |
    | Player | Comparable |
impl | BlackJackTable | | Table
     | BlackJackHand | | Hand
     | BlackJackPlayer | Player |
     | BlackJackDealer | Dealer | BlackJackPlayer

Again, classes in `api` and `test` are provided; classes in `impl` are
what should be implemented for this assignment. Their
definitions -- what they extend and implement -- should follow this
table exactly.

## Build

Using the Java command line tools directly -- as we have done
thus far -- is nice, but for larger projects build systems are
preferable. One well established system is
[Ant](http://ant.apache.org), a project managed by the Apache
Foundation. Ant interprets commands in an XML file to essentially run
the Java command line tools on your behalf. This project comes with a
simple Ant file you can use.

For those using the command line, enter the directory containing the
`build.xml` file and run
```bash
  $> ant compile
```
to compile the code (rather than `javac` directly).

For those using Eclipse, you create a new project in the IDE based on
an Ant file. Select
```
File->New->Project...
```
In the wizard, select
```
Java->Java Project from Existing Ant Buildfile
```
then click Next. Populate the "Ant buildfile" box with the path to the
build file from this homework (use "Browse" to find it on your
file system). If the "Link to the buildfile in the file system" option
is unchecked, a copy of the Java files will be created. From there,
you can "play" the files like normal.

## Run

This project expects the user to specify the number of players. It
does so by parsing the command line arguments (see `BlackJackGame` for
details). Fortunately, Ant takes care of this for you; in order to run
the game, type
```bash
  $> ant test
```
and a game with five players is run. If you would like to test with a
different number of players, you can do so by running another Ant
"target":
```bash
  $> ant play -Dplayers=10
```
would play a game with 10 players. Substitute any positive integer for
10 to play with that number of players. If you are curious about the
details, see the `build.xml` to deduce where and how this is
specified. As an aside, `play` depends on `compile`, so by running
`play` the code is automatically compiled first (if compilation fails,
execution will not commence).

For those using Eclipse, command line arguments are set in the "Run
Configurations" dialogue:
```
Run->Run Configurations...
```
In the left pane, double-click `Java Application`. From there, a new
dialogue appears; by default it is likely called
"New_configuration". In the "Arguments" tab, there is a large box
called "Program Arguments" -- put a numeric value in the box and click
"Apply".

## Setup

You should use GitHub judiciously! To start, fork this repository into
your own account. Because it is private, its subsequent fork should
also be private. You should grant access to the project to the
instructors (`jsw7` and `kqm1`; see `Settings->Collaborators` on your
project page), but leave the repository private otherwise.

## Grading

You will be graded along the following dimensions:

1. The program should run without error, and the run should be
   non-trivial.
2. Correctness of the implementation:
   * The game itself should be based on some accepted version of
     BlackJack
   * API implementations should respect their commented intentions.
3. Good general habits:
   * A single top-level class per Java file.
   * No [wild card](http://stackoverflow.com/q/147454) imports.
   * Programming to interfaces where appropriate. Any non-primitive
     variables should be the most abstract type as possible.
4. Using parent classes correctly. In general, using the parent
   classes requires you to implement anything that is abstract, and
   override methods and attributes where necessary.
5. Implementing interfaces correctly. In places where the interface
   method has a well-defined contract, that contract should be
   completely attempted.
6. Usage of GitHub. There should be evidence of continued GitHub usage
   as you develop. An extreme case that is bad: a single commit just
   before the deadline. While some people are prone to lots of
   commits, there is no excuse for just a few (or less).

## Submit

See the assignment listing in NYU Classes for the due date. We will
grade the code that is on your master branch at the exact deadline.
