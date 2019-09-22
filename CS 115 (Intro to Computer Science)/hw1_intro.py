''' Created on September 4, 2019
    I pledge my honor that I have abided by the Stevens Honor System.
    @author: Robert Schaedler III
    username: rschaedl
'''

import sys
from cs115 import map, reduce


def factorial(n):
    def mult(a, b):
        return a * b
    return reduce(mult, range(1, n + 1))


def mean(num_list):
    def add(a, b):
        return a + b
    return reduce(add, num_list) / len(num_list)


def prime(n):
    def divides(n):
        def div(k):
            return k % n == 0
        return div

    def f(func):
        return func(n)
    if n > 0:
        return True not in map(f, map(divides, list(range(1, n))))[1:]
    else:
        raise ValueError('Number given was less than 0')
