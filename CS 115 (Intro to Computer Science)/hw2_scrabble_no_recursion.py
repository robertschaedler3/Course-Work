import sys
from cs115 import map, reduce, filter
# Be sure to submit hw2.py.  Remove the '_template' from the file name.

# Allows up to 10000 recursive calls.
# The maximum permitted limit varies from system to system.
sys.setrecursionlimit(10000)

# Leave the following lists in place.
scrabbleScores = [['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2], ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], [
    'm', 3], ['n', 1], ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1], ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10]]

Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar',
              'babble', 'can', 'foo', 'spam', 'spammy', 'zzyzva']

# Implement your functions here.


def remove_list_element(e, l):
    """Removes element e from list l and returns the new list."""
    i = l.index(e)
    l = l[:i] + l[i + 1:]
    return l


def letterScore(letter, scorelist):
    """Takes a letter and a score list and returns the score for that letter. None is returned if the letter does not exist in the score list."""
    for l, score in scorelist:
        if l == letter:
            return score
    else:
        return None


def wordScore(word, scorelist):
    """Takes a word and a score list and returns the score of the word."""
    total = 0
    for l in word:
        total += letterScore(l, scorelist)
    return total


def scoreList(Rack):
    """Takes a rack and returns all the possible scores that can be made."""
    scores = []
    for word in Dictionary:
        rack = Rack
        for letter in word:
            if letter in rack:
                rack = remove_list_element(letter, rack)
            else:
                break
        else:
            scores.append([word, wordScore(word, scrabbleScores)])
    return scores


def bestWord(Rack):
    """Takes a rack and returns the highest scoring word and its score."""
    best = ['', 0]
    for score in scoreList(Rack):
        if score[1] > best[1]:
            best = score
    return best
