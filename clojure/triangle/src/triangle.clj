(ns triangle)

(defn lens-ok [a b c]
         (and (> (+ a b) c) (> (+ b c) a) (> (+ a c) b)))

(defn valid? [a b c]
    (let [zero (or (zero? a) (zero? b) (zero? c))
          ok1 (lens-ok a b c)] 
        (and (not zero) ok1)))

(defn type [a b c] 
    (if (not (valid? a b c))
        :illogical
        (let [lens (set [a b c])
              size-set (count lens)]
        (cond
            (= size-set 1) :equilateral
            (= size-set 2)  :isosceles 
            :else :scalene)))) 
