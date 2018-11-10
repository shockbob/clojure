(ns pascals-triangle)

(def triangle (iterate 
                 (fn [row] (concat [1] (map (partial apply +') (partition 2 1 row))[1])) 
                 [1] ))

(defn row [n] 
   (last (take n triangle)))
