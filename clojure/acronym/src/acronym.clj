(ns acronym)

(defn convert-ch [c]
   (if (or (Character/isAlphabetic (int c))(= \space c))
       c
       \space))

(defn capitalize [st]
   (str (.toUpperCase (.substring st 0 1)) (.substring st 1)))

(defn is-cap? [c]
   (<= (int \A) (int c) (int \Z)))

(defn fix-word [word]
   (if (every? is-cap? word)
       (str (first word))
       word))

(defn acronym [st] 
    (let [st (apply str (map convert-ch st))
          words (remove empty? (.split  st " "))
          words (map (comp fix-word capitalize) words)
          caps (filter is-cap? (apply str words))]
        (apply str caps))) 
