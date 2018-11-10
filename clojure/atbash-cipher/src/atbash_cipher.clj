(ns atbash-cipher)

(def zz (int \z))
(def aa (int \a))
(defn isdigit [ch] (Character/isDigit  ch))
(defn isalpha [ch] (Character/isAlphabetic (int ch)))
(defn encode [st]
   (let [st (.toLowerCase st)
         st (filter (fn [x] (or (isdigit x) (isalpha x))) st)
         st (map (fn [x] (if (isdigit x) x  (char (+ aa (- zz (int x)))))) st)
         st (interpose " " (partition-all 5 st)) ] 
      (apply str (map (partial apply str) st))))
