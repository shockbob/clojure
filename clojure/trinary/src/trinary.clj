(ns trinary)
(defn to-decimal
   ([n] (to-decimal n 0))
   ([[f & r] c]
       (if (or (nil? f) (nil? (#{\1 \0 \2} f)))
           c
           (to-decimal r (+ ( * c 3) (Integer/parseInt (str f)))))))

