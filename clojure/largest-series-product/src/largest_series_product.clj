(ns largest-series-product)

(defn calc-result [size nums]
   (let [nums (map (fn [c] (Integer/parseInt (str c))) nums)
         parts (partition size 1 nums)
         mults (map (partial apply *)  parts)
         answer (apply max mults)] 
      answer))

(defn largest-product [size nums] 
   (cond (zero? size) 1 
         (empty? nums) (throw (Throwable. ""))
         :else (calc-result size nums)))
