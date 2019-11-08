"""
Created on 10/18/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that I have abided by the Stevens Honor system.

CS115 - Hw 6
"""


def numToBase(n, b):
    """ Returns the string with the base representation of non-negative integer n. If n is 0, '0' is returned."""

    def helper(n, b):
        return '' if n == 0 else helper(n // b, b) + str(n % b)

    return '0' if n == 0 else helper(n, b)


def baseBToNum(s, b):
    """ Returns the integer corresponding to the base representation in s."""
    return 0 if s == '' else int(s[0]) * (b ** (len(s) - 1)) + baseBToNum(s[1:], b)


def baseToBase(b1, b2, s):
    """ Returns a string representing the same number in base 2 given a string in base 1."""
    return numToBase(baseBToNum(s, b1), b2)


def add(s, t):
    """ Returns the sum (in binary) of two binary strings using base conversion."""
    return numToBase(baseBToNum(s, 2) + baseBToNum(t, 2), 2)


def addB(s, t):
    """ Returns the sum (in binary) of two binary strings using binary addition."""

    full_adder = {
        ('0', '0', '0'): ('0', '0'),
        ('0', '0', '1'): ('1', '0'),
        ('0', '1', '0'): ('1', '0'),
        ('0', '1', '1'): ('0', '1'),
        ('1', '0', '0'): ('1', '0'),
        ('1', '0', '1'): ('0', '1'),
        ('1', '1', '0'): ('0', '1'),
        ('1', '1', '1'): ('1', '1')
    }

    def add_helper(x, y, carry_in='0'):
        if x == '' and y == '':
            return '' if carry_in == '0' else carry_in
        bit_sum, carry_out = full_adder[(x[-1:], y[-1:], carry_in)]
        return add_helper(x[:-1], y[:-1], carry_out) + bit_sum

    s_len, t_len = len(s), len(t)
    s = '0' * (t_len - s_len) + s if t_len > s_len else s
    t = '0' * (s_len - t_len) + t if s_len > t_len else t

    return add_helper(s, t)
