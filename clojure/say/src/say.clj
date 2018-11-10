(ns say)


(def simple-map {0 "zero" 1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six"
              7 "seven" 8 "eight" 9 "nine" 10 "ten" 11 "eleven" 12 "twelve"
              13 "thirteen" 14 "fourteen" 15 "fifteen" 16 "sixteen"
              17 "seventeen" 18 "eighteen" 19 "nineteen"})

(def tens-map {0 "" 1 "ten" 2 "twenty" 3 "thirty" 4 "forty" 5 "fifty"
               6 "sixty"  7 "seventy" 8 "eighty" 9 "ninety"})

(defn lastwo [num]
  (let [simp (simple-map num)]
  (cond 
      (zero? num) ""
      simp simp
      :else (let [tens (quot num 10)
                  ones (mod num 10)
                  onestr (if (zero? ones) "" (simple-map ones))
                  tenstr (tens-map tens)
                  res (remove empty? [tenstr onestr])]
                  (apply str (interpose "-" res))))))

(defn divme 
 ([x] (divme x []))
 ([x c] 
   (if (>= x 1000) 
       (divme (quot x 1000) (concat [(mod x 1000)] c)) 
       (concat [x] c))))


(defn numx [onum]
       (let [huns (quot onum 100)
             hunstr (if (zero? huns) "" (str (simple-map huns) " hundred"))
             twodig (lastwo (mod onum 100))]
            (apply str (interpose " " (remove empty? [hunstr twodig])))))

(def numnames {0 "" 1 "thousand" 2 "million" 3 "billion"})

(defn number [n] 
    (cond 
       (= 0 n) "zero"
       (or (> n 999999999999) (< n 0)) (throw (IllegalArgumentException. ""))
       :else 
          (let [divs (divme n)]
            (.trim (apply str 
             (mapcat 
                 (fn [d i] (let [nx (numx d)]
                         (if (empty? nx) 
                             " " 
                            [nx " " (numnames i) " "]))) 
                     divs 
                     (reverse (range (count divs)))))))))
