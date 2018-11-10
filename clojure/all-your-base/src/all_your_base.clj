(ns all-your-base)

(defn to-base-10 [in-base [f & r] sum]
   (if (nil? f)
      sum
      (let [sum (+ (* in-base sum) f)]
           (to-base-10 in-base r sum))))

(defn to-base-n [num base coll]
    (if (zero? num)
        coll
        (let [md (mod num base)
              dv (quot num base)
              coll (concat [md] coll)]
           (to-base-n dv base coll))))
 
(defn convert [in-base in-coll out-base]
    (cond (or (< in-base 2)(< out-base 2)(some neg? in-coll)) nil
          (empty? in-coll) []
          (every? zero? in-coll) [0]
          (some (fn [x] (>= x in-base)) in-coll) nil 
          :else (let [b10 (to-base-10 in-base (vec in-coll) 0)]
                    (to-base-n b10 out-base []))))
