(ns change)

(defn dochange [pennies [f & r] output]
   (if (nil? f)
       output
       (let [dv (quot pennies f)
             rm (mod pennies f)]
           (dochange rm r (concat (repeat dv f) output )))))

(defn get-coins [pennies coins] 
    (->> coins 
         (sort-by -)
         (iterate rest)
         (take-while first)
         (map (fn [c] (dochange pennies c [])))
         (sort-by count)
         (first)))

(defn issue [pennies coins] 
    (cond 
        (zero? pennies) []
        (not (some (fn [c] (< c pennies)) coins)) (throw (IllegalArgumentException."cannot change"))
        (neg? pennies) (throw (IllegalArgumentException."cannot change"))
        :else (get-coins pennies coins)))
