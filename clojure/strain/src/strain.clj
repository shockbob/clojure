(ns strain)

(defn retain [f coll] 
   (reduce (fn [arr x] (if (f x) (conj arr x) arr)) [] coll))

(defn discard [f coll] 
   (retain #(not (f %)) coll))
