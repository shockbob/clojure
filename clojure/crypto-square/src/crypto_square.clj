(ns crypto-square)

(defn normalize-plaintext [st] 
   (apply str (filter (fn [c] (or (Character/isDigit c) (Character/isAlphabetic (int c)))) (.toLowerCase st))))

(defn square-size [st] 
    (int (Math/ceil (Math/sqrt (count st)))))

(defn pad [size st]
   (str st (apply str (repeat (- size (count st)) " "))))

(defn plaintext-segments [plain] 
   (let [normalized (normalize-plaintext plain)
         sq-size (square-size normalized)
         segments (partition-all sq-size normalized)
         segments (map (partial apply str) segments)]
       (vec segments)))

(defn ciphertext [plain] 
    (let [segments (plaintext-segments plain)]
         (apply str (flatten (for [col (range 0 (count (get segments 0)))] 
                   (for [row (range 0 (count segments))]
                      (get (get segments row) col)))))))

(defn partition-by-lens 
   ([st lens] (partition-by-lens st lens []))
   ([st [f & r] sts] 
     (if (nil? f) 
         sts 
         (partition-by-lens (subs st f) r (conj sts (subs st 0 f))))))

(defn get-width-height [size] 
    (let [width (first (filter (fn [x] (or (>= (* x x) size) (>= (* x (inc x)) size))) (range)))]
          (if (>= (* width width) size) 
             [width width] 
             [width (inc width)]))) 

(defn normalize-ciphertext [plain] 
   (let [ciphered (ciphertext plain)
         size (count ciphered)
         [width height] (get-width-height size) 
         diff  (- (* width height) size)
         end-lens (repeat diff (dec width))
         beg-lens (repeat (- height (count end-lens)) width) 
         segments (partition-by-lens ciphered (concat beg-lens end-lens)) 
         result (apply str (interpose " " segments)) ]
      result))

