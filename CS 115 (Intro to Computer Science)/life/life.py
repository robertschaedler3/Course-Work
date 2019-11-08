'''
@author: Robert Schaedler III
I pledge my honor that I have abided by the Stevens Honor System- lbrew
'''
import sys
import random


def createOneRow(width):
    """Returns one row of zeros width"""
    row = []
    for col in range(width):
        row += [0]
    return row


def createBoard(width, height):
    """Returns a 2d array with "height" rows and "width" cols"""
    A = []
    for _ in range(height):
        A = A + [createOneRow(width)]
    return A


def printBoard(A):
    """Prints the 2d list-of-lists A without spaces (using sys.stdout.write)"""
    for row in A:
        for col in row:
            sys.stdout.write(str(col))
        sys.stdout.write('\n')


def diagonalize(width, height):
    """Creates an empty board with a diagonal strip of "on" cells."""
    A = createBoard(width, height)
    for row in range(height):
        for col in range(width):
            if row == col:
                A[row][col] = 1
            else:
                A[row][col] = 0
    return A


def innerCells(w, h):
    """ Returns a 2d array of all live cells - with the value of 1 - 
    except for a one-cell-wide border of empty cells (with the
    value of 0) around the edge of the 2d array"""
    A = createBoard(w, h)
    for row in range(h):
        for col in range(w):
            if row == 0 or col == 0 or row == h-1 or col == w-1:
                A[row][col] = 0
            else:
                A[row][col] = 1
    return A


def randomCells(w, h):
    """Takes an empty board as input and modifies that board
    so that its inner cells (non-edge) are either 0 or 1"""
    A = createBoard(w, h)
    for row in range(h):
        for col in range(w):
            rnd = random.randint(0, 1)
            if row == 0 or col == 0 or row == h-1 or col == w-1:
                A[row][col] = 0
            elif rnd == 1:
                A[row][col] = 1
            else:
                A[row][col] = 0
    return A


def copy(B):
    """Creates a deep copy of B"""
    new_board = createBoard(len(B), len(B[0]))
    for row in range(len(B)):
        for col in range(len(B[0])):
            new_board[row][col] = B[row][col]
    return new_board


def innerReverse(E):
    """Reverses everything inside but the border"""
    newE = copy(E)
    for row in range(len(E) - 1):
        for col in range(len(E[0]) - 1):
            if row != 0 and col != 0:
                if E[row][col] == 0:
                    newE[row][col] = 1
                else:
                    newE[row][col] = 0
    return newE


def neighbor(row, col, A):
    """Determines the number of present neighbors."""
    numberOfNeighbors = 0
    for r in range(row-1, row+2):
        for c in range(col-1, col+2):
            if A[r][c] == 1:
                numberOfNeighbors = numberOfNeighbors + 1
    if A[row][col] == 1:
        numberOfNeighbors = numberOfNeighbors - 1
    return numberOfNeighbors


def next_life_generation(A):
    """ Copies A. Inside of this copy, generates Conway's Game of Life on all cells excluding the 1-width border."""
    next_gen = copy(A)
    for row in range(len(A)):
        for col in range(len(A[0])):

            if row == 0 or col == 0 or row == len(A)-1 or col == len(A[0])-1:
                next_gen[row][col] = 0
            elif (neighbor(row, col, A) > 3 or neighbor(row, col, A)) < 2 and A[row][col] == 1:
                next_gen[row][col] = 0
            elif neighbor(row, col, A) == 3 and A[row][col] == 0:
                next_gen[row][col] = 1
            else:
                next_gen[row][col] = A[row][col]
    return next_gen
