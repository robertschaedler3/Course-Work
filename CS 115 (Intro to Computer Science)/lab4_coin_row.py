"""
Created on 9/25/2019
@author:   Robert Schaedler III
Pledge:    I pledge my honor that I have abided by the Stevens Honor System.

CS115 - lab 4
"""


def coin_row(l):
    """ Takes in a list of coin values and returns maximum money that can be picked up from these coins without picking up any adjacent coins."""
    if l == []:
        return 0
    else:
        use_it = l[0] + coin_row(l[2:])
        lose_it = coin_row(l[1:])
        return max(use_it, lose_it)


def coin_row_with_values(l):
    """ Takes in a list of coin values and returns a list ofmaximum money that can be picked up from the list and what coins to actually select without picking up any adjacent coins."""
    if l == []:
        return [0, []]
    else:
        use_it_amount, use_it_coins = coin_row_with_values(l[2:])
        lose_it_amount, lose_it_coins = coin_row_with_values(l[1:])
        max_amount = max(l[0] + use_it_amount, lose_it_amount)
        if max_amount == l[0] + use_it_amount:
            return [max_amount, [l[0]] + use_it_coins]
        else:
            return [lose_it_amount, lose_it_coins]


if __name__ == '__main__':
    print(coin_row([]))
    print(coin_row_with_values([]))
    print(coin_row([5, 1, 2, 10, 6, 2]))
    print(coin_row_with_values([5, 1, 2, 10, 6, 2]))
    print(coin_row([10, 5, 5, 5, 10, 50, 1, 10, 1, 1, 25]))
    print(coin_row_with_values([10, 5, 5, 5, 10, 50, 1, 10, 1, 1, 25]))
