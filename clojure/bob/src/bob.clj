(ns bob)

(defn response-for [s] 
   (let [all-spaces (= [\space] (distinct (vec s)))
         has-uppers (some (fn [c] (<= (int \A) (int c) (int \Z))) s) 
         all-uppers (and has-uppers (= (.toUpperCase s) s))
         question (= (last s) \?)
         exclaim (= (last s) \!) ]
   (cond
      all-spaces "Fine. Be that way!" 
      (and all-uppers question) "Calm down, I know what I'm doing!" 
      question "Sure."
      (and all-uppers exclaim) "Whoa, chill out!"
      all-uppers "Whoa, chill out!" 
      (empty? s) "Fine. Be that way!"
      :else "Whatever.")))
