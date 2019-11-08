'''
Created on 10/16/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that I have abided by the Stevens Honor system.

CS115 - Hw 5
'''
# Number of bits for data in the run-length encoding format.
from functools import reduce
COMPRESSED_BLOCK_SIZE = 5

# Number of bits for data in the original format.
MAX_RUN_LENGTH = 2 ** COMPRESSED_BLOCK_SIZE - 1


def numToBinary(n):
    ''' Returns the string with the binary representation of non-negative integer n.
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


def compress(s):
    """ Takes a binary string S of length 64 as input and returns another binary string as output."""

    def helper(s, bit):
        """ The main recursive function for compressing a string s."""
        if s == '':
            return ''
        n = count_bits(s, bit)
        return format_block(n) + helper(s[n:], not bit)

    def count_bits(s, bit):
        """ Returns the number of a given bit at the beginning of a string."""
        if s == '':
            return 0
        if int(s[0]) == bit:
            return 1 + count_bits(s[1:], bit)
        return 0

    def format_block(n):
        """ Formats an integer into its binary representation accoring to the global COMPRESSED_BLOCK_SIZE."""
        bin_num = numToBinary(n)
        if n <= MAX_RUN_LENGTH:
            return (COMPRESSED_BLOCK_SIZE - len(bin_num)) * '0' + bin_num
        return '1' * COMPRESSED_BLOCK_SIZE + '0' * COMPRESSED_BLOCK_SIZE + format_block(n-MAX_RUN_LENGTH)

    return helper(s, 0)


def uncompress(s):
    """ Inverts the compressing in the compress() function."""

    def helper(s, bit):
        """ Inverts the compression algorithm."""
        if s == '':
            return ''
        return str(int(bit)) * binaryToNum(s[:COMPRESSED_BLOCK_SIZE]) + helper(s[COMPRESSED_BLOCK_SIZE:], not bit)

    return helper(s, 0)


def compression(s):
    """ Return the ratio of the compressed size to the original size for image S."""
    return len(compress(s)) / len(s)


"""
    What is the largest number of bits that your compress algorithm could possibly use to encode a 64-bit string/image?

        The maximum number of bits that my compression algorithm could use to encode a 64-bit string is 325 bits or:

        (length_of_input + 1) * COMPRESSED_BLOCK_SIZE

        This would be the case where the input string begins with a '1' and the bits alternate ( compress('10' * 32) ).
"""

"""
    Describe the tests that you conducted and the compression ratios that you found:

        The tests I conducted to test my compression algorithm generally utilized large amounts of consecutive bits 
        (ie: '0'*32 + '1'*32). However, I noticed that the compression ratio gets worse when the amount of consecutive bits decreases. 
        This can be attributed to the large number of 'unused' bits in the compression output that lead to a larger compresses size.
"""

""" 
    Why can a compression algorithm of this type not always return a shorter string:

        Since the block size must remain constant in this example, there are some cases where the block will represent the decimal number 1.
        In this case all of the bits except the right-most bit are essentially wasted and take up memory but hold no significance
        beyond maintaining the block size.
"""
