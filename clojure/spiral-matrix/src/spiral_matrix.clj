(ns spiral-matrix)

(defn square [n]
    (vec (map vec (repeat n (repeat n 0)))))

(defn inrange [[row col] a n]
    (and (< -1 row n) (< -1 col n) (zero? (get-in a [row col]))))

(defn turn90 [[dr dc]]
   [dc (- dr)])

(defn move [[row col] [dr dc]] 
   [(+ row dr) (+ col dc)])

(defn spr [n] 
    (loop [arr (square n) n n loc [0 0] dir [0 1] start 1]
        (if (pos? (get-in arr loc))
           arr
           (let [arr (assoc-in arr loc start)
                 new-loc (move loc dir)]
                 (if (inrange new-loc arr n)
                     (recur arr n new-loc dir (inc start))
                     (let [dir (turn90 dir) 
                           loc (move loc dir)] 
                         (recur arr n loc dir (inc start))))))))

(defn spiral [n]
   (cond
      (zero? n) []
      (= 1 n) [[1]] 
      :else  (spr n)))
