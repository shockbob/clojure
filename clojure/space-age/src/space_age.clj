(ns space-age)
(def earth-secs 31557600)
(def planet-map {:earth 31557600 :mercury (* earth-secs 0.2408467) :venus (* earth-secs 0.61519726)
   :mars (* earth-secs 1.8808158) :jupiter (* earth-secs 11.862615) :saturn (* earth-secs 29.447498)
   :uranus (* earth-secs 84.016846) :neptune (* earth-secs 164.79132)})

(defn get-age [planet secs]
   (/ (double secs) (planet-map planet)))

(defn on-mercury [num] 
   (get-age :mercury num))
  
(defn on-venus [num] 
   (get-age :venus num))

(defn on-earth [num] 
   (get-age :earth num))

(defn on-mars [num] 
   (get-age :mars num))

(defn on-jupiter [num] 
   (get-age :jupiter num))

(defn on-saturn [num] 
   (get-age :saturn num))
  
(defn on-neptune [num] 
   (get-age :neptune num))

(defn on-uranus [num] 
   (get-age :uranus num))
