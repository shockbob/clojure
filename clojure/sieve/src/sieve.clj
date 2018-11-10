(ns sieve)

(defn init-sieve [n]
    (let [result (vec (repeat (inc n) true))
          result (assoc-in result [0] false)
          result (assoc-in result [1] false)]
        result))

(defn next-prime [input-sieve n]
    (first (filter input-sieve (range n (count input-sieve)))))

(defn get-primes [primes]
    (filter primes (range 0 (count primes))))

(defn remove-primes [input-sieve start]
    (reduce (fn [res e] (assoc-in res [e] false)) 
             input-sieve
            (range (* 2 start) (count input-sieve) start)))

(defn siever [input-sieve start]
    (if (>= start (quot (count input-sieve) 2))
        input-sieve 
        (let [input-sieve (remove-primes input-sieve start) 
              start (next-prime input-sieve (inc start))]
              (recur input-sieve start))))

(defn sieve [n] 
      (let [input-sieve (init-sieve n)]
          (get-primes (siever input-sieve 2)))) 

