(ns rotational-cipher)

(defn c-range [start end]
   (map char (range (int start) (inc (int end)))))

(def lowers (vec (c-range \a \z)))
(def uppers (vec (c-range \A \Z)))
(def pos-map-lowers (zipmap lowers (range)))
(def pos-map-uppers (zipmap uppers (range)))

(defn rotate-ch [num c]
   (let [pos-upper (pos-map-uppers c)
         pos-lower (pos-map-lowers c)]
    (cond pos-upper (uppers (mod (+ pos-upper num) 26))
          pos-lower (lowers (mod (+ pos-lower num) 26))
          :else c)))

(defn rotate [st num] 
   (apply str (map (partial rotate-ch num) st)))
