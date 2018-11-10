(ns isbn-verifier)

(defn valid [c]
   (or (Character/isDigit c)(= c \x)))

(defn calc-digs [chs]
   (map (fn [c] (if (= \x c) 10 (Integer/parseInt (str c)))) chs))

(defn calcmults [digs]
    (map * (iterate dec 10) digs)) 

(defn get-valid-chars [isbn]
   (filter valid (vec isbn)))

(defn isbn? [isbn] 
   (let [isbn (.toLowerCase isbn)
         chars (get-valid-chars isbn)
         digs (calc-digs chars)
         x-digit (.indexOf isbn "x")
         sum (apply + (calcmults digs)) ] 
      (cond 
          (and (not= x-digit -1) (not= x-digit (dec (count isbn)))) false
          (not= (count chars) 10) false
          :else (zero? (mod sum 11)))))
