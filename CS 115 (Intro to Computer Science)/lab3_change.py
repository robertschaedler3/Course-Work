"""
    Robert Schaedler III
    I pledge my honor that I have abided by the Stevens Honor System.
    Stevens ID: rschaedl
    Lab: LE
"""


def change(amount, coins):
    """ Returns the minimum amount of coins needed to supply the given an amount and a list of coins. """
    if amount == 0:
        return 0
    elif coins == []:
        return float('inf')
    elif coins[0] > amount:
        return change(amount, coins[1:])
    else:
        use_it = 1 + change(amount-coins[0], coins)
        lose_it = change(amount, coins[1:])
        return min(use_it, lose_it)
