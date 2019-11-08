"""
Created on 10/25/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that I have abided by the Stevens Honor system.

CS115 - Hw 7
"""


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


def numToBase(n, b):
    """ Returns the string with the base representation of non-negative integer n. If n is 0, '0' is returned."""

    def helper(n, b):
        return '' if n == 0 else helper(n // b, b) + str(n % b)

    return '0' if n == 0 else helper(n, b)


def baseBToNum(s, b):
    """ Returns the integer corresponding to the base representation in s."""
    return 0 if s == '' else int(s[0]) * (b ** (len(s) - 1)) + baseBToNum(s[1:], b)


def binaryFlipper(s):
    '''Replaces 0s with 1s and 1s with 0s as a part of the process to make a binary number negative.'''
    if s == '':
        return ''
    if s[0] == '0':
        return '1' + binaryFlipper(s[1:])
    else:
        return '0' + binaryFlipper(s[1:])


def TcToNum(s):
    '''Takes as input a string of 8 bits representing an integer in two's-complement, and returns the corresponding integer.'''
    if s[0] == '0':
        return baseBToNum(s, 2)
    return -1 * baseBToNum(addB(binaryFlipper(s), '00000001'), 2)


def NumToTc(n):
    '''Takes as input an integer N, and returns a string representing its two's-complement.'''
    if n >= -128 and n <= 127:
        if n >= 0:
            return '0' * (8-len(numToBase(n, 2))) + numToBase(n, 2)
        else:
            result = binaryFlipper(
                '0' * (8-len(numToBase(-1 * n, 2))) + numToBase(-1 * n, 2))
            return addB(result, '00000001')
    return 'Error'
