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
