(ns beer-song
  (:require [clojure.string :refer [join]]))


(defn bottle [num] 
   (cond (zero? num) "No more bottles"
         (= 1 num) "1 bottle"
         :else (str num " bottles")))

(defn it [num] (cond 
                  (= 1 num) "it" 
                  :else "one"))

(defn inner-bottle [num]
    (cond (zero? num) "no more bottles"
           :else (bottle num)))

(defn first-line [num]
   (str (bottle num) " of beer on the wall, " (inner-bottle num) " of beer.\n"))
             
(defn verse [num]
  (cond (zero? num)
        (str   (first-line 0)
           "Go to the store and buy some more, 99 bottles of beer on the wall.\n")
       :else (str (first-line num) "Take " (it num) " down and pass it around, " (inner-bottle (dec num)) " of beer on the wall.\n")))

(defn sing
  ([start] (sing start 0))
  ([start end]
     (join "\n" (map verse (range start (dec end) -1)))))

