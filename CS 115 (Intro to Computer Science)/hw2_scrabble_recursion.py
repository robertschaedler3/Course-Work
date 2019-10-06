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

def letterScore(letter, scorelist):
    """ Takes a letter and a score list and returns the score for that letter. None is returned if the letter does not exist in the score list."""
    # print(scorelist[0])
    l, s = scorelist[0]
    if l == letter:
        return s
    return letterScore(letter, scorelist[1:])


def wordScore(word, scorelist):
    """ Takes a word and a score list and returns the score of the word."""

    return reduce(lambda a, b: a + b, map(lambda l: letterScore(l, scorelist), word))


def remove_list_element(e, l):
    """Removes element e from list l and returns the new list."""
    i = l.index(e)
    l = l[:i] + l[i + 1:]
    return l


def word_possible(word, rack):
    """ Returns wheather a word can be made with the given rack"""
    if word != '' and rack == []:
        return False
    elif word == '':
        return True
    else:
        if word[0] in rack:
            rack = remove_list_element(word[0], rack)
            return word_possible(word[1:], rack)
        else:
            return False


def scoreList(rack):
    """Takes a rack and returns all the possible scores that can be made."""
    return map(lambda word: [word, wordScore(word, scrabbleScores)], filter(lambda word: word_possible(word, rack), Dictionary))


def bestWord(rack):
    """ Takes a rack and returns the highest scoring word and its score."""
    def greater_score(score1, score2):
        if score1[1] > score2[1]:
            return score1
        return score2
    return reduce(greater_score, scoreList(rack), ['', 0])
