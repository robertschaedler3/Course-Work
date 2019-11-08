'''
Created on 10/8/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that i have abided by the Stevens Honor System.

CS115 - Lab 6
'''
def isOdd(n):
    ''' Returns whether or not the integer argument is odd.'''
    return n % 2 != 0

def numToBinary(n):
    ''' Precondition: integer argument is non-negative.
        Returns the string with the binary representation of non-negative integer n.
        If n is 0, the empty string is returned.'''
    if (n == 0):
        return ''
    else:
        return numToBinary(n // 2) + str(n % 2)
        
def binaryToNum(s):
    ''' Precondition: s is a string of 0s and 1s.
        Returns the integer corresponding to the binary representation in s.
        Note: the empty string represents 0.'''
    if s == '':
        return 0
    else: 
        return int(s[0]) * (2 ** (len(s) - 1)) + binaryToNum(s[1:])

def increment(s):
    ''' Precondition: s is a string of 8 bits.
        Returns the binary representation of binaryToNum(s) + 1.'''
    if s == '':
        return ''
    
    bit_sum = int(s[-1]) + 1
    if bit_sum == 1:
        return s[:-1] + '1'
    elif bit_sum == 2:
        return increment(s[:-1]) + '0'
    else:
        return s

def count(s, n):
    ''' Precondition: s is an 8-bit string and n >= 0.
        Prints s and its n successors.'''
    print(s)
    if n == 0:
        return
    else:
        count(increment(s), n - 1)

def numToTernary(n):
    ''' Precondition: integer argument is non-negative.
        Returns the string with the ternary representation of non-negative integer
        n. If n is 0, the empty string is returned.'''
    if (n == 0):
        return ''
    else:
        return numToTernary(n // 3) + str(n % 3)

def ternaryToNum(s):
    ''' Precondition: s is a string of 0s, 1s, and 2s.
        Returns the integer corresponding to the ternary representation in s.
        Note: the empty string represents 0.'''
    if s == '':
        return 0
    else: 
        return int(s[0]) * (3 ** (len(s) - 1)) + ternaryToNum(s[1:])

'''
    Why 59 == 2012 in ternary:

    59 // 3 => 19        59 % 3 => 2
    19 // 3 => 6         19 % 3 => 1     ^
    6  // 3 => 2         6  % 3 => 0     |
    2  // 3 => 0         2  % 3 => 2

    By then reading the mods from bottom to top you get 2012
'''


