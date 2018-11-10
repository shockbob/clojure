(ns minesweeper
  (:require [clojure.string :refer [join]]))

(def line-sep (System/getProperty "line.separator"))

(defn get-at [mine row col max-row max-col]
    (cond  (and (<= 0 col max-col) (<= 0 row max-row)) (get (mine row) col)
           :else ""))

(def dirs [[-1 1][1 -1][1 1][-1 -1][0 1][0 -1][1 0][-1 0]])

(defn get-neighbors [mine row col max-row max-col]
    (map (fn [[dr dc]] (get-at mine (+ row dr) (+ col dc) max-row max-col)) 
           dirs ))

(defn count-bombs [mine row col max-row max-col]
    (if (= \* (get (mine row) col))
       \*
       (count (apply str (filter 
                            #{\*}  
                            (get-neighbors mine row col max-row max-col)))))) 

(defn make-vecs [mine]
   (map (partial apply str)
     (let [max-row (dec (count mine))
           max-col (dec (count (get mine 0))) ]
           (for [r (range 0 (inc max-row)) ]
              (for [c (range 0 (inc max-col)) ]  
                   (count-bombs mine r c max-row max-col))))))

(defn draw [mine] 
  (let [mine (vec (.split mine line-sep))
        vecs (vec (make-vecs mine))
        st  (join line-sep vecs) 
        st  (.replaceAll st "0" " ") ]
     st)) 
