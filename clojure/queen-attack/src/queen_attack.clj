(ns queen-attack)

(def one-row  "_ _ _ _ _ _ _ _\n")

(def empty-board (mapv vec (vec (repeat 8 one-row)))) 

(defn tostrs [vecs] 
    (apply str (mapv (partial apply str) vecs)))

(defn put-piece [board [row col] piece]
   (assoc-in board [row (* 2 col)] piece))

(defn board-string [mp]
   (let [board (if (mp :w) (put-piece empty-board (mp :w) \W) empty-board)
         board (if (mp :b) (put-piece board (mp :b) \B) board)]
     (tostrs board)))

(defn abs [n] (Math/abs n))

(defn can-attack [piece-map]
     (if (and (piece-map :w) (piece-map :b))
        (let [[white-row white-col] (piece-map :w)
              [black-row black-col] (piece-map :b)]
        (or (= black-row white-row) (= black-col white-col)
            (= (abs (- white-row black-row)) (abs (- white-col black-col)))))))
