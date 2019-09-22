"""
    Robert Schaedler III
    I pledge my honor that I have abided by the Stevens Honor System.
    Stevens ID: rschaedl
    Lab: LE
"""

from cs115 import map, reduce
import math


def inverse(n):
    """ Takes a number n as input and returns its reciprocal."""
    return 1/n


def e(n):
    """ Returns an approximation of the mathematical value 'e' using a Taylor expansion."""
    e_approx = 1
    for i in range(1, n + 1):
        e_approx += 1 / math.factorial(i)
    return e_approx


def error(n):
    """ Returns the absolute value of the difference between the "actual" value of e (using math.e) and the approximation using the e(n) function assuming that n terms (beyond the leading 1) are used."""
    approx = e(n)
    return abs(approx - math.e)
