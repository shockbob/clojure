(ns accumulate)

(defn accumulate 
    ([func coll]  (accumulate func coll []))
    ([func [f & r] result]
        (if (nil? f)
            result
            (let [result (conj result (func f))]
                (recur func r result)))))
