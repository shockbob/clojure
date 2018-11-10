(ns poker)
(def rank-map {"2" 2 "3" 3 "4" 4 "5" 5 "6" 6 "7" 7
      "8" 8 "9" 9 "10" 10 "J" 11 "Q" 12 "K" 13 "A" 14})

(defn rank [st] (rank-map (.substring st 0 (dec (count st)))))
(defn suit [st] (.substring st (dec (count st))))

(defn make-rank-thing [cards]
  (sort-by val (frequencies (map rank cards))))

(defn high-card [cards] 
     (apply max (map rank cards)))

(defn high-card-1 [cards]
   (let [rt (make-rank-thing cards)
         rt (remove (fn [[rank freq]] (= 1 freq)) rt)]
         (apply + (map first rt))))

(def straight-array [14 2 3 4 5 6 7 8 9 10 11 12 13 14])

(def straights (map set (partition 5 1 straight-array)))

(defn straight [cards]
    (let [ranks (set (map rank cards))]
          (some (partial = ranks) straights)))

(defn flushh [cards]
   (= 1 (count (distinct (map suit cards)))))

(defn straight-flush [cards]
   (and (flushh cards) (straight cards)))

(defn getmatcher [pattern]
    (fn [cards] (let
       [rank-thing (make-rank-thing cards)
        sorted-ranks (map last rank-thing)]
        (= sorted-ranks pattern))))

(defn hand-rank [[type score matcher addon]]
   {:type type :score score :matcher matcher :add-on addon})   

(def hand-type
 [[:straight-flushh 100000 straight-flush high-card]
  [:four-of-a-kind 90000 (getmatcher [1 4]) high-card-1]
  [:full-house 80000 (getmatcher [2 3]) high-card-1]
  [:flush 70000 flushh high-card]
  [:straight 60000 straight high-card]
  [:three-of-a-kind 50000 (getmatcher [1 1 3]) high-card-1]
  [:two-pair 40000 (getmatcher [1 2 2]) high-card-1]
  [:pair 30000 (getmatcher [1 1 1 2]) high-card-1]
  [:high-card 20000 (getmatcher [1 1 1 1 1]) high-card]])

(defn find-rank [hand] 
   (let [cards (vec (.split hand " "))
         filt (filter (fn [ht] ((ht :matcher) cards)) hand-types)
         htx (first filt)]
       {:score (htx :score) :addon (htx :add-on) :cards cards :hand hand}))



(def hand-types (map hand-rank hand-type))

    
(defn best-hands [hands]
   (let [hand-scores (map (fn [hand] [hand (find-rank hand)]) hands)
         mx (apply max (map :score hand-scores))
         besties (filter (fn [hand-score]] (= mx (hand-score :score))) hand-scores)
         ))

