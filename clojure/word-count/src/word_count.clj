(ns word-count)
(defn freq [c] (reduce (fn [m e] (assoc m e (inc (m e 0)))) {} c))

(defn word-count [s] 
    (let [s (.toLowerCase  s)
          s (.replaceAll s "[^a-z0-9 ]" "")
          words (map (partial apply str) (.split s " "))
          words (remove #{""} words)]
    (freq words)))
