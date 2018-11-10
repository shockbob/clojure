def reverse(text):
    rev = ''
    for c in list(text):
        rev = c + rev
    return rev
