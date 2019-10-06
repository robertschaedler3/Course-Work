def dot(l, k):
    try:
        if l == []:
            return 0.0
        else:
            return l[0] * k[0] + dot(l[1:], k[1:])
    except IndexError:
        return 0.0


def explode(s):

    def explode_helper(s, l):
        if s == '':
            return l
        else:
            l.append(s[0])
            return explode_helper(s[1:], l)

    if s == '':
        return []
    else:
        return explode_helper(s, [])


def ind(e, l):

    def ind_helper(e, l, i):
        if l == '' or l == []:
            return i
        elif e == l[0]:
            return i
        else:
            i += 1
            return ind_helper(e, l[1:], i)

    if l == []:
        return 0
    else:
        return ind_helper(e, l, 0)


def removeAll(e, l):

    def removeAll_helper(e, l, i):
        if i == my_len(l):
            return l
        elif l[i] == e:
            l = l[:i] + l[i+1:]
            return removeAll_helper(e, l, i)
        else:
            i += 1
            return removeAll_helper(e, l, i)

    return removeAll_helper(e, l, 0)


def myFilter(f, l):

    def myFilter_helper(f, l, i):
        try:
            if f(l[i]) == True:
                i += 1
                return myFilter_helper(f, l, i)
            else:
                l = l[:i] + l[i+1:]
                return myFilter_helper(f, l, i)
        except IndexError:
            return l

    if l == []:
        return []
    else:
        return myFilter_helper(f, l, 0)


def deepReverse(l):

    def deepReverse_helper(l, i, new_l):
        try:
            item = l[i]
            if type(item) is list:
                item = deepReverse(item)
            new_l = [item] + new_l
            i += 1
            return deepReverse_helper(l, i, new_l)
        except IndexError:
            return new_l

    return deepReverse_helper(l, 0, [])


def reverse(l):

    def reverse_helper(l, i, new_l):
        try:
            new_l = [l[i]] + new_l
            i += 1
            return reverse_helper(l, i, new_l)
        except IndexError:
            return new_l

    return reverse_helper(l, 0, [])


def my_len(l):
    try:
        l[0]
        return 1 + my_len(l[1:])
    except:
        return 0
