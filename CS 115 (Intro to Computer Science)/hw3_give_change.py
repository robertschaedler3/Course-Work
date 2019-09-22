"""
Created on 9/20/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that I have abided by the Stevens Honor System.

CS115 - Hw 3
"""
# Be sure to submit hw3.py.  Remove the '_template' from the file name.

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 1
' Implement the function giveChange() here:
' See the PDF in Canvas for more details.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
# your code goes here


def giveChange(amount, coins):
    """ Returns the minimum amount of coins (including specific coins) needed to supply the given an amount and a list of coins. """
    if amount == 0:
        return [0, []]
    elif coins == []:
        return [float('inf'), []]
    elif coins[0] > amount:
        return giveChange(amount, coins[1:])
    else:
        use_it_amount, use_it_coins = giveChange(amount - coins[0], coins)
        lose_it_amount, lose_it_coins = giveChange(amount, coins[1:])
        val = min(1 + use_it_amount, lose_it_amount)
        if val == 1 + use_it_amount:
            return [1 + use_it_amount, use_it_coins + [coins[0]]]
        return [lose_it_amount, lose_it_coins]
