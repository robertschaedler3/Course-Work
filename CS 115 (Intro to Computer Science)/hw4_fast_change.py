import turtle as t

# for limiting the recursive calls needed for excessively large numbers
from math import gcd
from functools import reduce


def snowflake(trunk_length, levels):

    def snowflake_helper(length, levels, count):
        if levels == count:
            t.forward(length)
        else:
            snowflake_helper(length, levels, count + 1)
            t.left(60)
            snowflake_helper(length, levels, count + 1)
            t.right(120)
            snowflake_helper(length, levels, count + 1)
            t.left(60)
            snowflake_helper(length, levels, count + 1)

    if levels == 0:
        t.forward(trunk_length)
        t.right(120)
        t.forward(trunk_length)
        t.right(120)
        t.forward(trunk_length)
        t.right(120)
    else:
        snowflake_helper(trunk_length/levels, levels, 0)
        t.right(120)
        snowflake_helper(trunk_length/levels, levels, 0)
        t.right(120)
        snowflake_helper(trunk_length/levels, levels, 0)
        t.right(120)


def fast_change(amount, coins):
    '''Takes an amount and a list of coin denominations as input.
    Returns the number of coins required to total the given amount.
    Use memoization to improve performance.'''

    def LCM(num_list):
        return reduce(lambda a, b: a * b // gcd(a, b), num_list)

    def fast_change_helper(amount, coins, memo):
        if amount == 0:
            return 0
        elif coins == ():
            return float('inf')
        elif coins[0] > amount:
            return fast_change_helper(amount, coins[1:], memo)
        else:
            if (amount - coins[0] in memo):
                use_it = memo[amount - coins[0]]
            else:
                use_it = 1 + fast_change_helper(amount - coins[0], coins, memo)

            if (amount in memo):
                lose_it = memo[amount]
            else:
                lose_it = fast_change_helper(amount, coins[1:], memo)

            result = min(use_it, lose_it)
            if result == use_it:
                memo[amount - coins[0]] = result
            else:
                memo[amount] = result

            return result

    lcm = LCM(coins)
    if lcm > amount:
        return fast_change_helper(amount, tuple(coins), {})

    if lcm in coins:
        initial_count = amount // lcm
        amount = amount % lcm
        return initial_count + fast_change_helper(amount, tuple(coins), {})
    else:
        return fast_change_helper(amount, tuple(coins), {})




