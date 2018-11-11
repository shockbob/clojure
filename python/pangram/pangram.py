def is_pangram(sentence):
    output = ""
    sentence = sentence.lower()
    for ch in sentence:
       if (ch.isalpha()):
          output = output + ch
    return len(set(list(output))) == 26
    
