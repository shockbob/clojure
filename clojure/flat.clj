
(defn flat?
  "Returns true if seq contains no sequences"
  [seq]
  (not-any? (fn [x] (seq? x)) seq))

(defn flatme 
  "Returns an unnested sequence from the non-sequence elements of seq
for example, it turns (1 (2) 3) into (1 2 3)"
  [seq]
  (if (seq? seq)
    (if (flat? seq)
      seq
      (apply concat (map flatme seq)))
    (vec seq)))

(println (flatme [[1 2 3][4 5 [6 7]]]))
