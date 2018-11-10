(ns leap)

(defn leap-year? [year] 
    (if  (zero? (mod year 4)) 
         (if (zero? (mod year 100))
              (if (zero? (mod year 400))
                 true
                 false)
               true)
           false)) 
