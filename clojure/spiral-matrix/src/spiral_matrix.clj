(ns spiral-matrix)

(defn square [n]
    (apply vector 
       ( map (partial apply vector)        
             (repeat n (repeat n 0)))))

(defn inrange-ex [i min-i max-i]
  (and (>= i 0)(< i max-i)))

(defn inrange [row col  a]
    (and (inrange-ex row 0 (count a))
         (inrange-ex col 0 (count (a 0)))
           (zero? ((a row) col))))

(defn spr [arr row col row-dir col-dir start]
   (if (pos? ((arr row) col))
      arr
      (let [arr (assoc-in arr [row col] start)
         new-row (+ row row-dir)
         new-col (+ col col-dir)]
         (if (inrange new-row new-col arr)
             (recur arr new-row new-col row-dir col-dir (inc start))
             (let [new-row-dir col-dir
                   new-col-dir (- row-dir)
                   row (+ new-row-dir row)
                   col (+ new-col-dir col)]
                   (recur arr row col new-row-dir new-col-dir (inc start))))))) 

(defn spiral [n]
   (cond
    (zero? n) []
    (= 1 n) [[1]] 
    :else  (let [sqr (square n)]
          (spr sqr 0 0 0 1 1))))
