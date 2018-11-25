(ns minesweeper
  (:require [clojure.string :refer [join]]))

(def line-sep (System/getProperty "line.separator"))

(defn get-at [mine row col row-count col-count]
    (if  (and (< -1 col col-count) (< -1 row row-count)) 
         (get-in mine [row col])
          ""))

(def dirs [[-1 1][1 -1][1 1][-1 -1][0 1][0 -1][1 0][-1 0]])

(defn get-neighbors [mine row col row-count col-count]
    (map (fn [[dr dc]] (get-at mine (+ row dr) (+ col dc) row-count col-count)) 
           dirs ))

(defn make-cell [mine row col row-count col-count]
    (if (= \* (get (mine row) col))
       \*
       (let [ct (count (filter #{\*}  (get-neighbors mine row col row-count col-count)))]
            (if (zero? ct) 
                " "
                ct))))

(defn make-board [mine]
     (let [row-count (count mine)
           col-count (count (get mine 0)) ]
           (for [r (range row-count) ]
              (for [c (range col-count) ]  
                   (make-cell mine r c row-count col-count)))))

(defn draw [mine] 
       (->> (vec (.split mine line-sep))
            (make-board)
            (map join)
            (join line-sep)))
