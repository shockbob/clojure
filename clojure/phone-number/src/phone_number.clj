(ns phone-number)

(defn number [num-string] 
   (let [num-string (filter (fn [c] (Character/isDigit c)) num-string)
         is-first-one (= \1 (first num-string))
         take-off-1 (and is-first-one (= 11 (count num-string)))
         num-string (if take-off-1 (rest num-string) num-string)
         ok (= 10 (count num-string)) ]
       (if ok
           (apply str num-string)
           "0000000000")))
         
(defn area-code [num-string] 
    (apply str (take 3 (number num-string))))

(defn pretty-print [num-string] 
     (let [st (number num-string)]
        (str "(" (subs st 0 3) ") " (subs st 3 6) "-" (subs st 6))))
