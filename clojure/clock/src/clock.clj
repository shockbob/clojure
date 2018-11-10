(ns clock)

(defn clock->string [[hour min]]
       (format "%02d:%02d" hour min))

(defn fix-min [n]
   (if (pos? n)
       n
       (fix-min (+ n (* 24 60)))))

(defn clock [hours minutes] 
    (let [min (+ minutes (* 60 hours))
          min (fix-min min) 
          hour (quot min 60)
          hour (mod hour 24)
          min (mod min 60)]
      [hour min]))

(defn add-time [[hour min] time] 
      (clock hour (+ time min)))  
