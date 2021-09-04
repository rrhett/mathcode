def inQuadraticClass(p, surds):
    """Returns if p=8k-1 and (p_i/p)=1 for p_i in surds."""
    if p % 8 != 7:
        return False
    for s in surds:
        if kronecker(s, p) != 1:
            return False
    return True

def isValid(n, surds):
    factors = dict(factor(n))
    for f in factors:
        if factors[f] % 2 == 1 and f not in surds:
            return False
    return True

def isRepresentation(p, n, surds):
    return isValid(n, surds) and isValid(p-n, surds)

def printRepresentations(p, surds):
    for n in range(1, int(p/2)+1):
        if isRepresentation(p, n, surds):
            print("%s = %s + %s = %s + %s" % (p, n, p-n, factor(n), factor(p-n)))

def isRepresentable(p, surds):
    for n in range(1, int(p/2)+1):
        if isRepresentation(p, n, surds):
            return True
    return False

def inSurdClass(p, surds):
    """Returns if p != a^2 + b^2, with a, b in Z[√p for p in surds]"""
    return not isRepresentable(p, surds)

def computeSequence(m = 100000):
    surds = []
    nextSurd = 2
    p = 2
    while p < m:
        while inSurdClass(p, surds):
            print("%s could not be represented with %s" % (p, surds))
            surds.append(nextSurd)
            nextSurd = Primes().next(nextSurd)
        p = Primes().next(p)

# Find min p in the surd classes up to 1000:
computeSequence(1000)
