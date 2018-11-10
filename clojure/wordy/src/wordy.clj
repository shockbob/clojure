(ns wordy)

(def op-map {"multiplied" * "divided" / "minus" - "plus" +})

(defn calc-result [words]
     (last (reduce (fn [[op res] word] 
                   (cond 
                      (nil? res) [op word] 
                      (nil? op) [word res] 
                      :else [nil (op res word)])) 
                 [nil nil] 
                 words)))
 
(defn evaluate [math] 
    (let [math (.substring math 0 (dec (count math)))
          words (vec (.split math " "))
          words (remove #{"What" "is" "by" ""} words)
          words (map (fn [word] (op-map word word)) words)
          words (map (fn [word] (if (string? word) (Integer/parseInt word) word)) words)]
       (calc-result words)))

