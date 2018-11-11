def word_count(phrase):
    phrase2 = "" 
    badcharacters = set(",:_!!&@$%^&.")
    for ch in phrase.lower():
       if (ch in badcharacters):
           phrase2 = phrase2 + " " 
       else:
           phrase2 = phrase2 + ch
    words = phrase2.split() 
    wordmap = {}
    for word in words:
       word = word.strip("'")
       wordmap[word] = wordmap.get(word,0)+1 
    return wordmap 
