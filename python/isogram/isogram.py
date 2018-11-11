def is_isogram(string):
    
    l = list(string.lower().replace(" ","").replace("-",""))
    s = set(l)
    return len(l) == len(s)
