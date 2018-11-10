(ns flatten-array)

(defn flatme [coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (sequential? (first s))
        (concat (flatme (first s)) (flatme (rest s)))
        (cons (first s) (flatme (rest s)))))))
(defn flatten [coll]
  (filter identity (flatme coll)))
