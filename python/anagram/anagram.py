def fix_word(word):
   l = list(word.lower())
   l.sort()
   return l 

def find_anagrams(word, candidates):
   out = []
   for candidate in candidates:
      if (candidate.lower() != word.lower() and fix_word(candidate) == fix_word(word)):
         out.append(candidate)
   return out

 
